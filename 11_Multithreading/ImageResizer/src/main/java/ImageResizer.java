import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer implements Runnable {

    private File[] files;
    private String newFolder;
    private int newWidth;
    private long start;

    public ImageResizer(File[] files, String newFolder, int newWidth, long start) {
        this.files = files;
        this.newFolder = newFolder;
        this.newWidth = newWidth;
        this.start = start;
    }

    public ImageResizer() {

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
                BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                int widthStep = image.getWidth() / newWidth;
                int heightStep = image.getHeight() / newHeight;
                for (int x = 0; x < newWidth; x++) {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = image.getRGB(x * widthStep, y * heightStep);
                        newImage.setRGB(x, y, rgb);
                    }
                }
                File newFile = new File(newFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.format("Duration old method: %s ms %n", (System.currentTimeMillis() - start));
    }
}
