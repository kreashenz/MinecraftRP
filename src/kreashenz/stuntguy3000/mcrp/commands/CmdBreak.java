package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdBreak extends ICommand {

	public CmdBreak(MinecraftRP plugin) {
		super(plugin);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.break")){
				Block b = p.getTargetBlock(null, 20);
				if(b != null){
					b.setType(Material.AIR);
				} else Functions.tell(p, "§cNo block in sight.");
			} else Functions.noPerm(p);
		}
	}

}
