public class Main {
    private static String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String rootPath = "hdfs://7090b8aa8ed7:8020/";


    public static void main(String[] args) throws Exception {
        FileAccess fileAccess = new FileAccess(rootPath);

        fileAccess.create("test/text.txt");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10_000_000; i++) {
            sb.append(getRandomWord() + " ");
        }
        fileAccess.append("test/text.txt", sb.toString());

        fileAccess.appendFile("test", "src/main/resources/story.txt");
    }

    private static String getRandomWord() {
        StringBuilder builder = new StringBuilder();
        int length = 2 + (int) Math.round(10 * Math.random());
        int symbolsCount = symbols.length();
        for (int i = 0; i < length; i++) {
            builder.append(symbols.charAt((int) (symbolsCount * Math.random())));
        }
        return builder.toString();
    }
}
