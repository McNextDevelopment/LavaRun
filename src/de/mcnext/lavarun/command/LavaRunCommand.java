package de.mcnext.lavarun.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mcnext.lavarun.LavaRun;
import de.mcnext.lavarun.util.Data;
import de.mcnext.lavarun.util.ItemBuilder;

public class LavaRunCommand implements CommandExecutor {
	
	private LavaRun instance;
	private Data data;
	
	public LavaRunCommand(LavaRun instance) {
		this.instance = instance;
		this.data = instance.getData();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			instance.getLogger().warning(data.Error_No_Player());
			return true;
		}
		
		Player p = (Player) sender;
		
		if(!p.hasPermission("lavarun.*")) {
			p.sendMessage(data.Error_No_Perms());
			return true;
		}
		
		if(args.length != 1) {
			p.sendMessage(data.Prefix() + "§cFalsche Eingabe, bitte benutze </LavaRun Help> für weitere Informationen.");
			return true;
		}
		
		switch (args[0].toLowerCase()) {
		case "help":
			
			p.sendMessage(data.Prefix() + "§7----------> §c§lLavaRun Hilfe §7<----------");
			p.sendMessage(data.Prefix() + "§7» §6/LavaRun Help §7- §bZeige diese Hilfe.");
			p.sendMessage(data.Prefix() + "§7» §6/LavaRun Build §7- §bSetze dich in den Build Modus (Erlaubt es dir abzubauen).");
			p.sendMessage(data.Prefix() + "§7» §6/LavaRun Create §7- §bErstelle die Arena (Zuerst Regionen setzen mit Location-Util).");
			p.sendMessage(data.Prefix() + "§7» §6/LavaRun Util §7- §bErhalte das Location-Util.");
			p.sendMessage(data.Prefix() + "§7» §6/LavaRun SetSpawn §7- §bSetze den Spawn-Punkt für den Spieler.");
			p.sendMessage(data.Prefix() + "§7----------> §c§lLavaRun Hilfe §7<----------");
			break;
			
		case "create":
			
			if(!data.getTempPos1().containsKey(p.getName()) || !data.getTempPos2().containsKey(p.getName())) {
				p.sendMessage(data.Prefix() + "§cBitte setze zuerst die Eckpunkte der Arena mit dem Location-Util.");
				return true;
			}
			
			instance.getLocManager().setArena(data.getTempPos1().get(p.getName()), data.getTempPos2().get(p.getName()));
			p.sendMessage(data.Prefix() + "§aDu hast die Arena erfolgreich erstellt.");
			break;
			
		case "build":
			
			if(data.getInBuildMode().contains(p.getName())) {
				data.getInBuildMode().remove(p.getName());
				p.sendMessage(data.Prefix() + "§aDu hast den Build-Modus §bdeaktiviert§a.");
			} else {
				data.getInBuildMode().add(p.getName());
				p.sendMessage(data.Prefix() + "§aDu hast den Build-Modus §baktiviert§a.");
			}
			
			break;
			
		case "setspawn":
			instance.getLocManager().setLocation(p.getLocation(), "Spawn");
			p.sendMessage(data.Prefix() + "§aDu hast den Spawn erfolgreich gesetzt.");
			break;
			
		case "util":
			p.getInventory().addItem(new ItemBuilder(Material.IRON_NUGGET).setDisplayName(data.Name_Location_Util()).create());
			p.sendMessage(data.Prefix() + "§aDu hast das Location-Util erhalten.");
			break;

		default:
			p.sendMessage(data.Prefix() + "§cFalsche Eingabe, bitte benutze </LavaRun Help> für weitere Informationen.");
			break;
		}
		
		return true;
	}

}
