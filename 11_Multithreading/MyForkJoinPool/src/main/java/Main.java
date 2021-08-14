import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String url = "https://skillbox.ru/";
        Set<String> visitedLinks = ConcurrentHashMap.newKeySet();
        TreeSet<String> resultOne = new ForkJoinPool().invoke(new ForkJoinMapWebsite(url, visitedLinks, url));
        ProcessingResultForkJoinTask.getFileWithLinks(url, Paths.get("Links.txt"), resultOne);

        System.out.println("Время выполнения: " + (System.currentTimeMillis() - start) + " мс");
    }
}
