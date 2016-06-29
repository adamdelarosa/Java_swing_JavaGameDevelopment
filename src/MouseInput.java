import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by ROSA on 29/06/2016.
 */
public class MouseInput implements MouseListener{
    @Override
    public void mouseClicked(MouseEvent e) {

    }


    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        /*public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 150, 100, 50);
        public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 120, 250, 100, 50);
        public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 350, 100, 50);*/

        //Play button
        if(mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220){
            if(my >= 150 && my <= 200){
                // Press Play button
                Game.state = Game.STATE.GAME;
            }
        }

        //Help button
        if(mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220){
            if(my >= 250 && my <= 300){
                // Press Help button
                System.exit(1);
            }
        }

        //Quit button
        if(mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220){
            if(my >= 350 && my <= 400){
                // Press Quit button
                System.exit(1);
            }
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
