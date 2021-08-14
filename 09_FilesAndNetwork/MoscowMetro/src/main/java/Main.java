import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parseMetro.JsonObject;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static final Logger logger = LogManager.getLogger("LOGGER");

    public static void main(String[] args) {
        String url = "https://www.moscowmap.ru/metro.html#lines";
        Path pathFolder = Paths.get("src/main/resources/mapMoscow.json");
        try (PrintWriter writer = new PrintWriter(pathFolder.toFile())) {
            writer.println(JsonObject.getJSONMoscowMetro(url));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        Metro.printMoscowMetroForJson(pathFolder.toString());
    }
}