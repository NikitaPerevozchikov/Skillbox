import java.io.File;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File inFolder;
        File outFolder;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите исходную папку (пример: C:/Users/Никита/Desktop/FileCopy): ");
            String wayInFolder = scanner.nextLine();
            inFolder = new File(wayInFolder);
            if (!Files.isDirectory(inFolder.toPath())) {
                Directory.nameFolderWrong();
                continue;
            }
            System.out.println("Введите конечную папку (пример: C:/Users/Никита/Desktop/FileCopy(copy)): ");
            for (; ; ) {
                String wayOutFolder = scanner.nextLine();
                outFolder = new File(wayOutFolder);
                if (!outFolder.isAbsolute() || Files.exists(outFolder.toPath())) {
                    Directory.nameFolderWrong();
                    continue;
                }
                outFolder.mkdir();
                break;
            }
            Directory.copyFolder(inFolder, outFolder);
            System.out.println("Копия папки создана");
        }
    }
}
