public class Main {
    public static void main(String[] args) {
        String nameBase = "skill-mongo";
        String host = "127.0.0.1";
        int port = 27017;
        Terminal terminal = new Terminal(host, port, nameBase);
        terminal.startTerminal();
    }
}
