import java.awt.*;

public class Enemy {

    private double x, y;
    private Textures tex;

    public Enemy(double x, double y, Textures tex) {
        this.x = x;
        this.y = y;
        this.tex = tex;
    }

    public void tick() {
        y += 5;
    }

    public void render(Graphics g) {
        g.drawImage(tex.enemey, (int) x, (int) y, null);

    }
}
