import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.TreeSet;

public class ProcessingResultForkJoinTask {

    public static void getFileWithLinks(String startUrl, Path path, TreeSet<String> links) {

        StringBuilder urls = new StringBuilder(startUrl + "\n");
        links.forEach(s -> urls.append("\t".repeat(getNumberHierarchy(s))).append(s).append("\n"));

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
        }).sum() - 3;
    }
}
