package de.mcnext.lavarun.util;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import de.mcnext.lavarun.LavaRun;

public class GameManager {
	
	private BukkitTask lava, timer;
	private LavaRun instance;
	private int time;
	private int lowY;
	private boolean running;
	private boolean lost;
	
	public GameManager(LavaRun instance) {
		this.instance = instance;
		setRunning(false);
	}
	
	public void startGame() {
		instance.getTarget().sendTitle("Das Spiel startet jetzt!", "BEEIL DICH!", 20, 20*3, 20);
		
		setRunning(true);
		run();
		checkLava();
	}
	
	public void run() {
		timer = new BukkitRunnable() {
			
			@Override
			public void run() {
				
				instance.getTarget().setLevel(time);
				setTime(getTime() + 1);
				
			}
		}.runTaskTimer(instance, 20, 20);
	}
	
	public void checkLava() {
		setLowY(instance.getLocManager().getArena().getLowerY());
		lava = new BukkitRunnable() {
			
			@Override
			public void run() {
				
				for(Block b : instance.getLocManager().getArena().getBlocks()) {
					if(b.getY() == lowY) {
						b.setType(Material.LAVA);
					}
					
					if(lowY > instance.getLocManager().getArena().getUpperY()) {
						stopCheckLava();
					}
				}
				instance.getLogger().info("CheckLava");
				setLowY(getLowY() + 1);
				
			}
		}.runTaskTimer(instance, 20*5, 20*5);
	}
	
	public void stopCheckLava() {
		lava.cancel();
	}
	
	public void stopTimer() {
		timer.cancel();
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public void setLowY(int lowY) {
		this.lowY = lowY;
	}
	
	public int getLowY() {
		return lowY;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public void setLost(boolean lost) {
		this.lost = lost;
	}
	
	public boolean isLost() {
		return lost;
	}

}
