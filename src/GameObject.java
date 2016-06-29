import java.awt.*;

/**
 * Created by ROSA on 29/06/2016.
 */
public class GameObject {

    public double x, y;

    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle getBounds(int width,int height) {
        return new Rectangle((int) x, (int) y, width, height);
    }
}
