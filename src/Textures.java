import java.awt.image.BufferedImage;

/**
 * Created by ROSA on 29/06/2016.
 */
public class Textures {

    private SpriteSheet ss;

    public BufferedImage player,missle,enemey;

    public Textures(Game game) {
        ss = new SpriteSheet(game.getSpriteSheet());

        getTextures();
    }

    private void getTextures() {
        player = ss.grabImage(1, 1, 32, 32);
        missle = ss.grabImage(1, 2, 32, 32);
        enemey = ss.grabImage(1, 3, 32, 32);
    }
}
