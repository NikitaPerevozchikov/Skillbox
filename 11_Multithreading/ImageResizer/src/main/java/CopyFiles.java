import java.io.File;

public class CopyFiles {
    public static void copyFilesNewSize(File[] files, String newFolderOne, String newFolderTwo, int newWidth) {
        int processorCount = Runtime.getRuntime().availableProcessors();
        int startArray = 0;
        int endArray = 0;
        int lengthArray;

        for (int i = 0; i < processorCount; i++) {
            long start = System.currentTimeMillis();
            lengthArray = (files.length - endArray) / (processorCount - i);
            endArray += lengthArray;
            File[] filesPart = new File[lengthArray];
            System.arraycopy(files, startArray, filesPart, 0, lengthArray);
            startArray += lengthArray;
            new Thread(new ImageResizer(filesPart, newFolderOne, newWidth, start)).start();
            new Thread(new Imgscalr(filesPart, newFolderTwo, 300, start)).start();

        }
    }

}
