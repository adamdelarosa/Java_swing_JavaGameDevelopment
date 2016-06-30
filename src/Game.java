import Classes.EntityA;
import Classes.EntityB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 20 * 10;
    public static final int SCALE = 4;
    public final String TITLE = "2D Game";
    public boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH , HEIGHT , BufferedImage.TYPE_3BYTE_BGR);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;


    private boolean UP = false;
    private boolean DOWN = false;
    private boolean LEFT = false;
    private boolean RIGHT = false;
    private boolean isShooting = false;

    private int enemy_count = 5;
    private int enemy_killed = 0;

    private Player p;
    private Controller c;
    private Textures tex;
    private Menu menu;

    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;

    public static int HEALTH = 100 * 2;

    public static enum STATE {
        MENU,
        GAME
    }

    ;

    public static STATE state = STATE.MENU;

    public Game() {
    }

    public void init() {

        requestFocus();

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            spriteSheet = loader.loadImage("rsc/ship.png");

            background = loader.loadImage("rsc/background.jpg");
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }


        tex = new Textures(this);
        c = new Controller(tex, this);
        p = new Player(200, 200, tex, this, c);
        menu = new Menu();

        ea = c.getEntityA();
        eb = c.getEntityB();

        this.addKeyListener(new Keyboard(this));
        this.addMouseListener(new MouseInput());

        c.createEnemey(enemy_count);


    }

    private synchronized void start() {
        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running)
            return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void run() {

        init();

        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();


        while (running) {

            //Keyboard:

            if (UP) p.setVelY(-10);
            if (DOWN) p.setVelY(10);
            if (LEFT) p.setVelX(-10);
            if (RIGHT) p.setVelX(10);

            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("Ticks: " + updates + " FPS: " + frames);

                updates = 0;
                frames = 0;
            }

        }
        stop();
    }

    private void tick() {

        if (state == STATE.GAME) {
            p.tick();
            c.tick();
        }


        if (enemy_killed >= enemy_count) {
            enemy_count += 2;
            enemy_killed = 0;
            c.createEnemey(enemy_count);
        }
    }



    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();


        ////

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(background, 0,0,100,100,this);




        if (state == STATE.GAME) {
            p.render(g);
            c.render(g);

            //Health Bar
            g.setColor(Color.red);
            g.fillRect(5, 5, 200, 10);

            g.setColor(Color.green);
            g.fillRect(5, 5, HEALTH, 10);

            g.setColor(Color.white);
            g.drawRect(5, 5, 200, 10);

        } else if (state == STATE.MENU) {
            menu.render(g);
        }

        ////
        g.dispose();
        bs.show();

    }



    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (state == STATE.GAME) {

            if (key == KeyEvent.VK_RIGHT) {
                RIGHT = true;
                p.setVelX(5);

            } else if (key == KeyEvent.VK_LEFT) {
                LEFT = true;
                p.setVelX(-5);

            } else if (key == KeyEvent.VK_DOWN) {
                DOWN = true;
                p.setVelY(5);

            } else if (key == KeyEvent.VK_UP) {
                UP = true;
                p.setVelY(-5);

            } else if (key == KeyEvent.VK_SPACE && !isShooting) {
                c.addEntity(new Bullets(p.getX(), p.getY(), tex, this));
                isShooting = true;
            } else if (key == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            RIGHT = false;
            p.setVelX(0);

        } else if (key == KeyEvent.VK_LEFT) {
            LEFT = false;
            p.setVelX(0);

        } else if (key == KeyEvent.VK_DOWN) {
            DOWN = false;
            p.setVelY(0);

        } else if (key == KeyEvent.VK_UP) {
            UP = false;
            p.setVelY(0);

        } else if (key == KeyEvent.VK_SPACE) {
            isShooting = false;
        }
    }


    public static void main(String args[]) {
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }


    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public int getEnemy_count() {
        return enemy_count;
    }

    public void setEnemy_count(int enemy_count) {
        this.enemy_count = enemy_count;
    }

    public int getEnemy_killed() {
        return enemy_killed;
    }

    public void setEnemy_killed(int enemy_killed) {
        this.enemy_killed = enemy_killed;
    }


}