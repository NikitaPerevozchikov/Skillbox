import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Directory {
    private long weightFolder;
    private Path path;

    public Directory(String wayFolder) {
        path = Paths.get(wayFolder);
        countWeightFolderBeforeAPI(path);
    }

    public long getWeightFolder() {
        return weightFolder;
    }

    public static String formatReadableWeightFolder(long weight) {
        if (weight <= 8) {
            return weight + " бит";
        } else if (weight <= Math.pow(2, 10)) {
            return weight / 8 + " байт";
        } else if (weight <= Math.pow(2, 20)) {
            return ((double) ((int) ((weight / Math.pow(2, 10)) * 100))) / 100 + " Кб";
        } else if (weight <= Math.pow(2, 30)) {
            return ((double) ((int) ((weight / Math.pow(2, 20)) * 100))) / 100 + " Мб";
        } else {
            return ((double) ((int) ((weight / Math.pow(2, 30)) * 100))) / 100 + " Гб";
        }
    }

    public static boolean isFolderExist(Directory directory) {
        boolean fileFolder = true;
        if (!Files.isDirectory(directory.path)) {
            fileFolder = false;
        }
        return fileFolder;
    }

    private void countWeightFolderBeforeAPI(Path path) {
        try {
            Files.list(path).forEach(s -> {
                if (Files.isRegularFile(s)) {
                    try {
                        weightFolder += Files.size(s);
                    } catch (IOException e) {
                        e.getMessage();
                    }
                } else countWeightFolderBeforeAPI(s);
            });
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
