import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.TreeSet;

public class ParseSite {


    private TreeSet<String> listLinks = new TreeSet<>();

    public TreeSet<String> getListLinks() {
        return listLinks;
    }

    public void parseLinks(String url) {
        try {
            Document doc = Jsoup.connect(url).maxBodySize(0).get();
            Elements elements = doc.select("ul > li > a");
            elements.stream().map(e -> e.attr("abs:href"))
                    .forEach(e -> {
                        boolean isUniqueURL = listLinks.add(e);
                        if (isUniqueURL) {
                            parseLinks(e);
                        }
                    });
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
