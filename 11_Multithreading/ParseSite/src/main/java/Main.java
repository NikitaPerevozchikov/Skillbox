import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String url = "https://secure-headland-59304.herokuapp.com/";
        Path path = Paths.get("Links.txt");
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ParseSite parseSite = new ParseSite();
        parseSite.parseLinks(url);
        StringBuilder urls = new StringBuilder();
        parseSite.getListLinks().forEach(s->urls.append("\t".repeat(getNumberHierarchy(s)) + s + "\n"));

        try {
            Files.writeString(path, urls);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getNumberHierarchy(String url) {
        String[] urls = url.split("");
        return Arrays.stream(urls).mapToInt((e) -> {
            int i = 0;
            if (e.equals("/")) {
                i++;
            }
            return i;
        }).sum() - 4;
    }


}

