package de.mcnext.lavarun;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.mcnext.lavarun.command.LavaRunCommand;
import de.mcnext.lavarun.countdown.Countdown;
import de.mcnext.lavarun.listener.BuildListener;
import de.mcnext.lavarun.listener.ConnectionListener;
import de.mcnext.lavarun.listener.InteractListener;
import de.mcnext.lavarun.listener.MoveListener;
import de.mcnext.lavarun.util.Data;
import de.mcnext.lavarun.util.GameManager;
import de.mcnext.lavarun.util.LocationManager;

public class LavaRun extends JavaPlugin {
	
	// TODO Implement Starting-Countdown + Lava + Winning / Losing Detection + Join / Leave Detection
	
	private Data data;
	private LocationManager locManager;
	private Countdown countdown;
	private Player target;
	private GameManager gameManager;
	
	@Override
	public void onEnable() {
		getLogger().info("Trying to start LavaRun");
		init(Bukkit.getPluginManager());
		getLogger().info("LavaRun started successfully.");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Trying to stop LavaRun");
		getLogger().info("LavaRun stopped successfully.");
	}
	
	private void init(PluginManager pm) {
		this.data = new Data();
		this.locManager = new LocationManager(this);
		
		this.countdown = new Countdown(this);
		this.gameManager = new GameManager(this);
		
		locManager.loadArena();
		
		getCommand("lavarun").setExecutor(new LavaRunCommand(this));
		
		pm.registerEvents(new BuildListener(this), this);
		pm.registerEvents(new InteractListener(this), this);
		pm.registerEvents(new ConnectionListener(this), this);
		pm.registerEvents(new MoveListener(this), this);
	}
	
	public Data getData() {
		return data;
	}
	
	public LocationManager getLocManager() {
		return locManager;
	}
	
	public Countdown getCountdown() {
		return countdown;
	}
	
	public Player getTarget() {
		return target;
	}
	
	public void setTarget(Player target) {
		this.target = target;
	}
	
	public GameManager getGameManager() {
		return gameManager;
	}
}
