package parseMetro;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parseMetro.model.Connection;
import parseMetro.model.Line;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ParseMetro {

    protected static List<Line> getLines(Document doc, Elements lines) {
        List<Line> lineList = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String name = lines.get(i).text();
            String number = lines.get(i).attr("data-line");
            Elements station = doc.select("[data-line = " + number + "] span.name");
            List<String> stations = station.stream()
                    .map(Element::text)
                    .collect(Collectors.toList());
            lineList.add(new Line(name, number, stations));
        }

        return lineList;
    }

    protected static List<Connection> getConnections(Document doc, Elements lines) {
        List<Connection> listConnections = new ArrayList<>();
//создаём коллекцию с из номеров линий
        List<String> numberLines = lines.stream().map(e -> e.attr("data-line")).collect(Collectors.toList());
        for (String numberLine : numberLines) {
//создаём коллекцию с из станций для каждой линии
            Elements stations = doc.select("[data-line = " + numberLine + "] span.name");
            List<String> stationsOneLine = stations.stream()
                    .map(Element::text)
                    .collect(Collectors.toList());
//создаём коллекцию с объектами из станций, которые содержат переходы
            for (String station : stationsOneLine) {
                Elements withConnections = doc.select("[data-line = " + numberLine + "] > p > a:has(span.name:contains(" + station + ")) > span.t-icon-metroln");
                if (withConnections.size() > 0) {
                    for (Element withConnection : withConnections) {
                        Connection connection = new Connection(
                                numberLine,
                                station,
                                withConnection.className().substring(18),
                                printNameNextStation(withConnection.attr("title"))
                        );
                        listConnections.add(connection);
                    }
                }
            }
        }
        return listConnections;
    }

    private static String printNameNextStation(String text) {
        return text.substring(text.indexOf("«") + 1, text.indexOf("»"));
    }

}
