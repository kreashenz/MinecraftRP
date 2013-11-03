package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CmdSpeed extends ICommand {

	public CmdSpeed(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.speed")){
				if(args.length == 0){
					Functions.tell(p, "§cInvalid arguments. §f/speed <speed>");
				} else {
					float speed = Float.valueOf(args[0]).floatValue();
					if (speed > 10.0F)
						speed = 10.0F;
					else if (speed < 0.0F) {
						speed = 0.0F;
					}
					float def = 0.1F;
					float max = 1.0F;
					float f = (speed - 1.0F) / 9.0F * (max - def);
					p.setFlySpeed(f + def);
					Functions.tell(p, "§6Set fly speed to §c" + (f + def));
				}
			} else Functions.noPerm(p);
		}
	}

}
