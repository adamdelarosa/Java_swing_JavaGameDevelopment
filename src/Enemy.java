import java.awt.*;
import java.util.Random;

public class Enemy {

    private double x, y;
    private Textures tex;

    Random r = new Random();

    public Enemy(double x, double y, Textures tex) {
        this.x = x;
        this.y = y;
        this.tex = tex;
    }

    public void tick() {
        y += 1;

        if (y > (Game.HEIGHT * Game.SCALE)) {
            y = 0;
            x = r.nextInt(Game.WIDTH * Game.SCALE);
        }
    }

    public void render(Graphics g) {
        g.drawImage(tex.enemey, (int) x, (int) y, null);

    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
