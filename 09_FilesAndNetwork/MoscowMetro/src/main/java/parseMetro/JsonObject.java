package parseMetro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import parseMetro.model.Connection;
import parseMetro.model.Line;

import java.util.List;

public class JsonObject {
    public static JSONObject getJSONMoscowMetro(String url) {
        JSONObject mapMoscow = new JSONObject();
        try {
            Document doc = Jsoup.connect(url).maxBodySize(0).get();
            Elements lines = doc.select("span.js-metro-line");

            List<Line> lineList = ParseMetro.getLines(doc, lines);
            List<Connection> connectionList = ParseMetro.getConnections(doc, lines);

            mapMoscow.put("stations", getJSONStations(lineList));
            mapMoscow.put("lines", getJSONLines(lineList));
            mapMoscow.put("connections", getJSONConnections(connectionList));

        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return mapMoscow;
    }

    private static JSONObject getJSONStations(List<Line> lineList) {
        JSONObject stations = new JSONObject();
        lineList.forEach(e -> stations.put(e.getNumber(), e.getStations()));
        return stations;
    }

    private static JSONArray getJSONLines(List<Line> linesList) {
        JSONArray stations = new JSONArray();
        linesList.stream()
                .map(e -> {
                    try {
                        return new JSONObject()
                                .put("name", e.getName())
                                .put("number", e.getNumber());
                    } catch (JSONException ex) {
                        ex.fillInStackTrace();
                    }
                    return null;
                })
                .forEach(stations::put);
        return stations;
    }

    private static JSONArray getJSONConnections(List<Connection> stationsList) {
        JSONArray connections = new JSONArray();
        for (int i = 0; i < stationsList.size(); i++) {
            JSONArray array = new JSONArray();
            try {
                for (int j = stationsList.size() - 1; j >= 0; j--) {
                    if (stationsList.get(i).getNameLine().equals(stationsList.get(j).getNextNameLine()) &&
                            stationsList.get(i).getNameStation().equals(stationsList.get(j).getNextNameStation())) {
                        JSONObject two = new JSONObject();
                        two.put("line", stationsList.get(j).getNameLine());
                        two.put("stations", stationsList.get(j).getNameStation());
                        array.put(two);
                        stationsList.remove(j);
                    }
                }
                if (array.length() > 0) {
                    JSONObject one = new JSONObject();
                    one.put("line", stationsList.get(i).getNameLine());
                    one.put("stations", stationsList.get(i).getNameStation());
                    array.put(one);
                    stationsList.remove(i);
                    i--;
                    connections.put(array);
                }
            } catch (JSONException e) {
                e.fillInStackTrace();
            }
        }
        return connections;
    }


}
