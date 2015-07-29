package controller;

import model.GameData;
import model.game.characters.Player;
import model.game.object.MapObject;
import model.game.tiles.Tile;
import view.View;
import controller.input.Keyboard;

public class GameController implements Runnable
{

    boolean running;

    GameData gameData;
    View view;
    Thread thread;
    Keyboard keyboard;
    Player player;

    public GameController(GameData gameData, View view)
    {
        this.gameData = gameData;
        this.view = view;

        player = gameData.player;

        keyboard = new Keyboard();
        view.addKeyListener(keyboard);
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

            view.render();
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
        // Handle user input
        keyboard.update();
        movePlayer();
        view.getGameScreen().centerScreen(player.getX() + Tile.TILESIZE / 2, player.getY() + Tile.TILESIZE / 2);

        // Animate the MapObjects
        for (MapObject mo : gameData.getMap().getObjects())
        {
            mo.animate();
        }
    }

    /**
     * Handles the player movement
     */
    private void movePlayer()
    {
        int xMove = 0;
        int yMove = 0;

        if (keyboard.up) yMove--;
        if (keyboard.right) xMove++;
        if (keyboard.down) yMove++;
        if (keyboard.left) xMove--;

        player.move(xMove, yMove);
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
}
