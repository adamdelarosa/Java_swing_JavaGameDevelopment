import java.awt.*;
import java.util.LinkedList;

public class Controller {

    private LinkedList<Bullets> b = new LinkedList<Bullets>();

    Bullets TempBullets;

    Game game;

    public Controller(Game game){
        this.game = game;
    }

    public void tick(){
        for(int i = 0; i < b.size(); i++){
            TempBullets = b.get(i);

            if(TempBullets.getY() <0){
                removeBulet(TempBullets);
            }

            TempBullets.tick();
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < b.size(); i++){
            TempBullets = b.get(i);

            TempBullets.render(g);
        }
    }

    public void addBullet(Bullets block) {
        b.add(block);
    }

    public void removeBulet(Bullets block){
        b.remove(block);
    }
}
