import java.io.File;
import java.nio.file.Files;

public class Directory {
    private long weightFolder;
    private File file;

    public Directory(String wayFolder) {
        file = new File(wayFolder);
        countWeightFolder(file);
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

    private void countWeightFolder(File file) {
        File[] fileList = file.listFiles();
        if (fileList != null) {
            for (File files : fileList) {
                if (!Files.isDirectory(files.toPath())) {
                    weightFolder += files.length();
                }
                if (files.isDirectory()) {
                    countWeightFolder(files);
                }
            }
        }
    }

    public static boolean isFolderExist(Directory directory) {
        boolean fileFolder = true;
        if (!Files.isDirectory(directory.file.toPath())) {
            fileFolder = false;
        }
        return fileFolder;
    }
}
