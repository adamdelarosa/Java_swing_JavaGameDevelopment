import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;

    public SpriteSheet(BufferedImage ss){
        this.image = ss;
    }

    public BufferedImage grabImage(int col,int row, int width, int height){
        BufferedImage img = image.getSubimage((col * 32) - 32,(row * 32) - 32,width,height);
        img.getScaledInstance(12,12,12);
        return img;
    }
}
