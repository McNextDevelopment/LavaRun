package de.mcnext.lavarun.countdown;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import de.mcnext.lavarun.LavaRun;

public class Countdown {
	
	private boolean running; // true or false
	private int time;
	private LavaRun instance;
	private BukkitTask timer;
	
	public Countdown(LavaRun instance) {
		this.instance = instance;
		this.running = false;
		this.time = 15;
	}
	
	public void start() {
		run();
		setRunning(true);
	}
	
	public void stop() {
		timer.cancel();
		setRunning(false);
		setTime(15);
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	private void run() {
		timer = new BukkitRunnable() {
			
			@Override
			public void run() {
				
				if(!isRunning()) {
					return;
				}
				
				switch (time) {
				case 15: case 10: case 5: case 3: case 2: case 1:
					instance.getTarget().sendTitle("Das Spiel startet in", String.valueOf(getTime()), 10, 30, 10);
					break;
				case 0:
					instance.getGameManager().startGame();
					timer.cancel();
					break;
				default:
					break;
				}
				instance.getTarget().setLevel(getTime());
				setTime(getTime() - 1);
			}
		}.runTaskTimer(instance, 20, 20);
	}
	
}
