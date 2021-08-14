import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Loader {
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();

    public static void main(String[] args) throws Exception {

        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long startTime = System.currentTimeMillis();
        String fileName = "res/data-1572M.xml";
        parseFile(fileName);
        long finishMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - startMemory;
        long finishTime = System.currentTimeMillis();
        System.out.println("Memory: " + (finishMemory - startMemory) / 1_048_576 + " Mb");
        System.out.println("Time: " + (finishTime - startTime) / 1_000 + " s");
    }

    private static void parseFile(String fileName) throws Exception {
        DBConnection.getConnection();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);
        DBConnection.executeMultiInsert(DBConnection.getInsertQuery().toString());
        DBConnection.getService().shutdown();
        DBConnection.getService().awaitTermination(2, TimeUnit.HOURS);
        DBConnection.printVoterCounts();
    }

    private static void findEqualVoters(Document doc) throws Exception {
        NodeList voters = doc.getElementsByTagName("voter");
        int votersCount = voters.getLength();
        for (int i = 0; i < votersCount; i++) {
            Node node = voters.item(i);
            NamedNodeMap attributes = node.getAttributes();
            String name = attributes.getNamedItem("name").getNodeValue();
            String birthDay = attributes.getNamedItem("birthDay").getNodeValue();
        }

    }

    private static void fixWorkTimes(Document doc) throws Exception {
        NodeList visits = doc.getElementsByTagName("visit");
        int visitCount = visits.getLength();
        for (int i = 0; i < visitCount; i++) {
            Node node = visits.item(i);
            NamedNodeMap attributes = node.getAttributes();

            Integer station = Integer.parseInt(attributes.getNamedItem("station").getNodeValue());
            Date time = visitDateFormat.parse(attributes.getNamedItem("time").getNodeValue());
            WorkTime workTime = voteStationWorkTimes.get(station);
            if (workTime == null) {
                workTime = new WorkTime();
                voteStationWorkTimes.put(station, workTime);
            }
            workTime.addVisitTime(time.getTime());
        }
    }
}