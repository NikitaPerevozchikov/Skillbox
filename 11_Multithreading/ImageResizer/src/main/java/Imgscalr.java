import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Imgscalr implements Runnable {
    private File[] files;
    private String newFolder;
    private int newWidth;
    private long start;

    public Imgscalr(File[] files, String newFolder, int newWidth, long start) {
        this.files = files;
        this.newFolder = newFolder;
        this.newWidth = newWidth;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                int newHeight = (int) Math.round(image.getHeight() / (image.getWidth() / (double) newWidth));
                BufferedImage newImage = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, newWidth, newHeight);
                File newFile = new File(newFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.format("Duration new method: %s ms %n", (System.currentTimeMillis() - start));

    }


}
