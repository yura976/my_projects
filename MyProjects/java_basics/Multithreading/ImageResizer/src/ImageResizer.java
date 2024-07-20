import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer extends Thread {
    private File[] files;
    private int newWidth;
    private int newHeight;
    private String dstFolder;
    private long start;

    public ImageResizer(File[] files, int newWidth, int newHeight, String dstFolder, long start) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.newHeight = newHeight;
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
                SizeReduction sizeReduction = new SizeReduction();
                BufferedImage newImage = sizeReduction.resizeImage(image, newWidth, newHeight);
                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
                newImage.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}