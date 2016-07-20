package controller;

import model.GameData;
import model.game.characters.Player;
import model.game.object.MapObject;
import model.game.tiles.Tile;
import view.UserInterface;
import view.View;
import view.ui.UIElement;
import controller.input.FocusManager;
import controller.input.Keyboard;
import controller.input.Mouse;

public class GameController implements Runnable
{
    /**
     * Game updates per second
     */
    public static final int UPS = 60;

    boolean running;

    private GameData gameData;
    private View view;
    private UserInterface ui;
    private Thread thread;
    private Keyboard keyboard;
    private Mouse mouse;
    private FocusManager focusManager;
    private MovementHandler movHandler;

    // TODO: maybe make this static in some way?
    public static int xScreenOffset;
    public static int yScreenOffset;

    public GameController(View view)
    {
        this.gameData = GameData.getInstance();
        this.view = view;
        this.ui = view.getGameScreen().getUserInterface();

        keyboard = new Keyboard();
        view.addKeyListener(keyboard);

        mouse = new Mouse();
        view.addMouseListener(mouse);
        view.addMouseMotionListener(mouse);

        focusManager = new FocusManager(keyboard, mouse);
        view.addWindowlistener(focusManager);

        Player player = gameData.createPlayer();
        gameData.getMap().addMapObject(player);

        CollisionHandler colHandler = new CollisionHandler();
        colHandler.loadMapCollision(gameData.getMap());

        movHandler = new MovementHandler(colHandler);
    }

    public void run()
    {
        // time when the method is first started
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        // create quotient
        final double ns = 1000000000.0 / 60.0;
        // difference between start time and current time
        double delta = 0;
        // counter for frames per second
        int frames = 0;
        // counter for updates per second
        int updates = 0;

        while (running)
        {
            long now = System.nanoTime();
            // add up the times of every loop run and get the value in seconds /
            // 60 (basically increase by 1/60th of a second every loop)
            delta += (now - lastTime) / ns;
            lastTime = now;
            // gets called 60 times per second because of delta calculation
            while (delta >= 1)
            {
                update();
                updates++;
                delta--;
            }

            view.render(xScreenOffset, yScreenOffset);
            frames++;

            // gets called every second
            if (System.currentTimeMillis() - timer > 1000)
            {
                // "reset" timer variable
                timer += 1000;
                System.out.println(updates + " ups, " + frames + " fps");
                view.setTitle("| " + updates + " ups, " + frames + " fps" + " |");
                // reset frames and updates variables to start counting from 0
                // at the start of every second
                frames = 0;
                updates = 0;
            }
        }
    }

    public void update()
    {
        // Inform the AIs of the update
        for (MapObject mo : gameData.getMap().getObjects())
        {
            mo.getAI().advance();
        }
        
        // update the UI
        for (UIElement uiEl : ui.getUIElements())
        {
            uiEl.update();
        }

        // Move the map objects
        movHandler.moveObjects();

        // Refresh the screen position
        Player player = gameData.getPlayer();
        this.centerScreen((int) player.getX() + Tile.TILESIZE / 2, (int) player.getY() + Tile.TILESIZE / 2);
    }

    public void start()
    {
        running = true;

        // create and start the Controller-Thread
        thread = new Thread(this, "Controller-Thread");
        thread.start();
    }

    public void stop()
    {
        running = false;

        // close thread
        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Center the screen at a certain coordinate
     * 
     * @param x
     *            X coordinate to center the screen on
     * @param y
     *            Y coordinate to center the screen on
     */
    public void centerScreen(int x, int y)
    {
        // Scaled parameters
        int width = View.WIDTH;
        int height = View.HEIGHT;
        int mapWidth = gameData.getMap().getWidth();
        int mapHeight = gameData.getMap().getHeight();

        xScreenOffset = x - width / 2;
        yScreenOffset = y - height / 2;

        // Limit Offset, so that the view never leaves the map
        if (xScreenOffset < 0)
        {
            xScreenOffset = 0;
        }
        if (xScreenOffset > mapWidth - width)
        {
            xScreenOffset = mapWidth - width;
        }
        if (yScreenOffset < 0)
        {
            yScreenOffset = 0;
        }
        if (yScreenOffset > mapHeight - height)
        {
            yScreenOffset = mapHeight - height;
        }
    }
}
