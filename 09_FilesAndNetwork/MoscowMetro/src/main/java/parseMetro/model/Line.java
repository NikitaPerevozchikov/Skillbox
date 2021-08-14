package parseMetro.model;

import java.util.List;

public class Line {
    private String name;
    private String number;
    private List<String> stations;

    public Line(String name, String number, List<String> stations) {
        this.name = name;
        this.number = number;
        this.stations = stations;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public List<String> getStations() {
        return stations;
    }
}
