import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ParseWebPage {

    public static List<String> loadPageAndParseUrls(String url, String rootUrl, Set<String> visitedLinks) {
        List<String> listLinks = new ArrayList<>();
        System.out.println(Thread.currentThread().getName() + " working with: " + url);
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Document doc = Jsoup.connect(url).maxBodySize(0).get();
            Elements elements = doc.select("a");
            elements.stream().map(e -> e.attr("abs:href"))
                    .filter(e -> e.contains(rootUrl))
                    .filter(e -> !e.equals(rootUrl))
                    .filter(e -> e.charAt(e.length() - 1) == '/')
                    .forEach(e -> {
                        if (visitedLinks.add(e)) {
                            listLinks.add(e);
                        }
                    });

        } catch (IOException e) {
            e.getMessage();
        }
        return listLinks;
    }

}
