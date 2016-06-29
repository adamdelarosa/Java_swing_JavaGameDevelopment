import Classes.EntityB;

import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject implements EntityB {

    private Textures tex;
    Random r = new Random();

    private int speed = r.nextInt(3) + 1;

    public Enemy(double x, double y, Textures tex) {
        super(x,y);
        this.tex = tex;
    }

    public void tick() {
        y += speed;

        if (y > (Game.HEIGHT * Game.SCALE)) {
            speed = r.nextInt(3)+1;
            x = r.nextInt(640);
            y = -10;
        }
    }

    public void render(Graphics g) {
        g.drawImage(tex.enemey, (int) x, (int) y, null);

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
