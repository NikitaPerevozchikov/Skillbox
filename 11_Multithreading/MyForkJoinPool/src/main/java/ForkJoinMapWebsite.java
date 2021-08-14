import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.RecursiveTask;

public class ForkJoinMapWebsite  extends RecursiveTask<TreeSet<String>> {
    private String url;
    private Set<String> visitedLinks;
    private String rootUrl;

    public ForkJoinMapWebsite(String url, Set<String> visitedLinks, String rootUrl) {
        this.url = url;
        this.visitedLinks = visitedLinks;
        this.rootUrl = rootUrl;
    }

    @Override
    protected TreeSet<String> compute() {
        List<String> localListLinks = ParseWebPage.loadPageAndParseUrls(url, rootUrl, visitedLinks);
        TreeSet<String> result = new TreeSet<>(localListLinks);
        List<ForkJoinMapWebsite> tasks = new ArrayList<>();
        for (String subLink : localListLinks) {
            ForkJoinMapWebsite task = new ForkJoinMapWebsite(subLink,visitedLinks,rootUrl);
            task.fork();
            tasks.add(task);
        }

        for (ForkJoinMapWebsite task : tasks) {
            result.addAll(task.join());
        }
        return result;
    }
}
