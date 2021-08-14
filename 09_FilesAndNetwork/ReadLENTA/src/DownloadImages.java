import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

public class DownloadImages {
    public static void downloadImage(Map<String, String> mapLink, Path pathFolder) {
        File images = new File(pathFolder.toString());
        images.mkdir();
        for (Map.Entry<String, String> entry : mapLink.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            Path pathDownloadImage = pathFolder.resolve(Paths.get(value));

//способ №1
            try {
                URL linkImage = new URL(key);
                InputStream is = linkImage.openStream();
                Files.copy(is, pathDownloadImage, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

//способ №2
//            try (OutputStream os = new FileOutputStream(String.valueOf(pathDownloadImage))) {
//                URL linkImage = new URL(key);
//                InputStream is = linkImage.openStream();
//                for (; ; ) {
//                    int data = is.read();
//                    os.write(data);
//                    if (data == -1) {
//                        break;
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        }
    }
}
