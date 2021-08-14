import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://lenta.ru/").get();
            Elements elementsImages = doc.select("img.g-picture");

            Map<String, String> mapLink = elementsImages.stream()
                    .collect(Collectors.toMap(e -> e.absUrl("abs:src"), e -> e.attr("src").substring(e.attr("src").lastIndexOf("/") + 1)));

            Path pathFolder = Paths.get("Images");

            DownloadImages.downloadImage(mapLink, pathFolder);

            Files.walk(pathFolder).forEach(e -> System.out.println(e.getFileName()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
