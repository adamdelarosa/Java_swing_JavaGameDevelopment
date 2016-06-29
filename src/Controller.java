import java.awt.*;
import java.util.LinkedList;

public class Controller {

    private LinkedList<Bullets> b = new LinkedList<Bullets>();
    private LinkedList<Enemy> e = new LinkedList<>();

    Bullets TempBullets;
    Enemy TempEnemey;

    Game game;
    Textures tex;

    public Controller(Game game,Textures tex){
        this.game = game;
        this.tex = tex;

        for(int x = 0; x< (Game.WIDTH * Game.SCALE);x+= 64){
            addEnemey(new Enemy(x,0,tex));
        }
    }

    public void tick(){
        for(int i = 0; i < b.size(); i++){
            TempBullets = b.get(i);

            if(TempBullets.getY() <0){
                removeBulet(TempBullets);
            }

            TempBullets.tick();
        }

        for(int i = 0; i < e.size(); i++){
            TempEnemey = e.get(i);
            TempEnemey.tick();
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < b.size(); i++){
            TempBullets = b.get(i);
            TempBullets.render(g);
        }
        for(int i = 0; i < e.size(); i++){
            TempEnemey = e.get(i);
            TempEnemey.render(g);
        }
    }

    public void addBullet(Bullets block) {
        b.add(block);
    }

    public void removeBulet(Bullets block){
        b.remove(block);
    }

    public void addEnemey(Enemy block) {
        e.add(block);
    }

    public void removeEnemey(Enemy block){
        e.remove(block);
    }
}
