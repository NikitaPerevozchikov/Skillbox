import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Metro {
    private Map<String, List<String>> stations;
    private List<Lines> lines;
    private ArrayList<Connections[]> connections;

    public Map<String, List<String>> getStations() {
        return stations;
    }

    public List<Lines> getLines() {
        return lines;
    }

    public ArrayList<Connections[]> getConnections() {
        return connections;
    }

    Metro() {
    }

    private static class Lines {
        private String name;
        private String number;

        Lines() {
        }

        public String getName() {
            return name;
        }

        public String getNumber() {
            return number;
        }
    }

    private static class Connections {
        private String line;
        private String stations;

        Connections() {
        }

        public String getLine() {
            return line;
        }

        public String getStations() {
            return stations;
        }
    }


    public static void printMoscowMetroForJson(String fileWay) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Metro metro = objectMapper.readValue(new File(fileWay), Metro.class);
            printFormat(metro);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    private static void printFormat(Metro metro) {
        for (Lines m : metro.lines) {
            System.out.format("%-14s%-35s%s\n", "Линия: " + m.number, m.name, metro.stations.get(m.number).size());
        }
        System.out.printf("\nКоличество переходов: %s", metro.connections.size());
    }
}



