import Classes.EntityA;

import java.awt.*;

public class Bullets extends GameObject implements EntityA {

    private Textures tex;

    public Bullets(double x, double y,Textures tex) {
        super(x,y);
        this.tex = tex;
    }

    public void tick() {
        y -= 10;
    }

    public void render(Graphics g) {
        g.drawImage(tex.missle, (int) x, (int) y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public double getY(){
        return y;
    }
    public double getX(){
        return x;
    }

}
