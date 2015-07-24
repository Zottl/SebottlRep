package control;

import model.GameData;
import view.View;

public class GameControl implements Runnable
{

	boolean running;

	GameData gd;
	View view;
	Thread thread;

	public GameControl(GameData gd, View view)
	{
		this.gd = gd;
		this.view = view;
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
		// do stuff...
	}

	public void start()
	{
		running = true;
		run();

		// create and start thread called "Display"
		thread = new Thread(this, "Display");
		thread.start();
	}

	public void stop()
	{
		running = false;

		// close thread, if it fails print stack trace
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
