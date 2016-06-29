import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 20 * 10;
    public static final int SCALE = 2;
    public final String TITLE = "2D Game";
    public boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
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

    public void init() {

        requestFocus();

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            spriteSheet = loader.loadImage("rsc/ship.png");
            background = loader.loadImage("rsc/background.jpg");
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }

        this.addKeyListener(new Keyboard(this));


        tex = new Textures(this);
        p = new Player(200, 200, tex);
        c = new Controller(tex);
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

            if (UP) p.setVelY(-5);
            if (DOWN) p.setVelY(5);
            if (LEFT) p.setVelX(-5);
            if (RIGHT) p.setVelX(5);

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
        p.tick();
        c.tick();
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
        g.drawImage(background, 0, 0, null);
        p.render(g);
        c.render(g);

        ////
        g.dispose();
        bs.show();

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (e.getKeyCode()) {
        }

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
            c.addEntity(new Bullets(p.getX(), p.getY(), tex));
            isShooting = true;
        } else if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
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
        frame.setResizable(false);
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