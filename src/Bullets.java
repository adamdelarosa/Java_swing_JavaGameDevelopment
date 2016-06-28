import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullets {

    private double x;
    private double y;

    BufferedImage image;

    public Bullets(double x, double y, Game game) {
        this.x = x;
        this.y = y;

        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());

        image = ss.grabImage(1, 2, 32, 32);
    }

    public void tick() {
        y -= 10;
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }
}
