import java.awt.*;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 20;
    public static final int HEIGHT = WIDTH / 19 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "2D Game";

    public static void main(String args[]) {
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

    }

    @Override
    public void run() {

    }
}
