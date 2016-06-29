import java.awt.image.BufferedImage;

/**
 * Created by ROSA on 29/06/2016.
 */
public class Textures {

    private SpriteSheet ss;

    public BufferedImage[] player = new BufferedImage[3];
    public BufferedImage[] missle = new BufferedImage[3];
    public BufferedImage[] enemey = new BufferedImage[3];

    public Textures(Game game) {
        ss = new SpriteSheet(game.getSpriteSheet());

        getTextures();
    }

    private void getTextures() {
        player[0] = ss.grabImage(1, 1, 32, 32);
        player[1] = ss.grabImage(2, 1, 32, 32);
        player[2] = ss.grabImage(3, 1, 32, 32);

        missle[0] = ss.grabImage(1, 2, 32, 32);
        missle[1] = ss.grabImage(2, 2, 32, 32);
        missle[2] = ss.grabImage(3, 2, 32, 32);

        enemey[0] = ss.grabImage(1, 3, 32, 32);
        enemey[1] = ss.grabImage(2, 3, 32, 32);
        enemey[2] = ss.grabImage(3, 3, 32, 32);

    }
}
