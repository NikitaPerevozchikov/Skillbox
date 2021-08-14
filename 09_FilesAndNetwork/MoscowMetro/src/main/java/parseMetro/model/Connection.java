package parseMetro.model;

public class Connection {
    private String nameLine;
    private String nameStation;
    private String nextNameLine;
    private String nextNameStation;

    public String getNameLine() {
        return nameLine;
    }

    public String getNameStation() {
        return nameStation;
    }

    public String getNextNameLine() {
        return nextNameLine;
    }

    public String getNextNameStation() {
        return nextNameStation;
    }


    public Connection(String nameLine, String nameStation, String nextNameLine, String nextNameStation) {
        this.nameLine = nameLine;
        this.nameStation = nameStation;
        this.nextNameLine = nextNameLine;
        this.nextNameStation = nextNameStation;
    }
}
