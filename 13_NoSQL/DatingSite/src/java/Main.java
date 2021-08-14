public class Main {
    
    public static void main(String[] args) {
        String address = "127.0.0.1";
        Integer port = 6379;
        DatingSite datingSite = new DatingSite(address, port);
        datingSite.siteEmulation();
        datingSite.siteWork();
    }
}
