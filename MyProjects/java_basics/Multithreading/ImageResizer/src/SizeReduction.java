import org.imgscalr.Scalr;
import java.awt.image.BufferedImage;
public class SizeReduction {
    BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight){
        return Scalr.resize(originalImage,
                Scalr.Method.QUALITY,
                Scalr.Mode.AUTOMATIC,
                targetWidth,
                targetHeight,
                Scalr.OP_BRIGHTER);
    }
}
