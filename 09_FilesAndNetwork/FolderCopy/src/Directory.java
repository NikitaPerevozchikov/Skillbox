import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Directory {
    public static void nameFolderWrong() {
        System.out.println("Путь указан не верно. Повторите ввод");
    }

    public static void copyFolder(File folderIn, File folderOut) {
        File[] fileList = folderIn.listFiles();
        try {
            if (fileList != null) {
                for (File files : fileList) {
                    Path wayNewFile = Paths.get(folderOut.getPath(), files.getName());
                    Files.copy(files.toPath(), wayNewFile, StandardCopyOption.REPLACE_EXISTING);
                    if (Files.isDirectory(files.toPath())) {
                        File newFolder = new File(wayNewFile.toString());
                        copyFolder(files, newFolder);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
