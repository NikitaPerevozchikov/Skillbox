import java.io.File;

public class Main {
    public static void main(String[] args) {
        String srcFolder = "C:/Users/Никита/Desktop/src";
        String dstFolderOne = "C:/Users/Никита/Desktop/drc";
        String dstFolderTwo = "C:/Users/Никита/Desktop/trc";

        File srcDir = new File(srcFolder);

        File[] files = srcDir.listFiles();

        CopyFiles.copyFilesNewSize(files, dstFolderOne, dstFolderTwo, 300);
    }

}
