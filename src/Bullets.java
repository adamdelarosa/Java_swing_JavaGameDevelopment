import java.awt.*;

public class Bullets extends GameObject implements Entity{

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

    public double getY(){
        return y;
    }
    public double getX(){
        return x;
    }

}
