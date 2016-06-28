import java.awt.*;
import java.util.LinkedList;

/**
 * Created by ROSA on 28/06/2016.
 */
public class Controller {

    private LinkedList<Bullets> b = new LinkedList<Bullets>();

    Bullets TempBullets;

    public void tick(){
        for(int i = 0; i < b.size(); i++){
            TempBullets = b.get(i);

            TempBullets.tick();
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < b.size(); i++){
            TempBullets = b.get(i);

            TempBullets.render(g);
        }
    }

}
