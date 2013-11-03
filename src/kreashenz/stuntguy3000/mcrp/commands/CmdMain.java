package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdMain implements CommandExecutor {

	private MinecraftRP plugin;

	public CmdMain(){
		this.plugin = MinecraftRP.getInstance();
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("adminchat")){
			new CmdAdminChat(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("balance")){
			new CmdBalance(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("ban")){
			new CmdBan(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("break")){
			new CmdBreak(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("broadcast")){
			new CmdBroadcast(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("burn")){
			new CmdBurn(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("clear")){
			new CmdClear(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("clearchat")){
			new CmdClearChat(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("duty")){
			new CmdDuty(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("enchant")){
			new CmdEnchant(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("enderchest")){
			new CmdEnderchest(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("enderchestclear")){
			new CmdEnderchestClear(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("ext")){
			new CmdExt(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("give")){
			new CmdGive(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("help")){
			new CmdHelp(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("item")){
			new CmdItem(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("kick")){
			new CmdKick(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("msg")){
			new CmdMsg(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("nick")){
			new CmdNick(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("reply")){
			new CmdReply(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("report")){
			new CmdReport(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("reports")){
			new CmdReports(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("setspawn")){
			new CmdSetspawn(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("socialspy")){
			new CmdSocialspy(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("spawn")){
			new CmdSpawn(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("speed")){
			new CmdSpeed(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("tp")){
			new CmdTp(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("tpa")){
			new CmdTpa(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("tpaccept")){
			new CmdTpaccept(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("tphere")){
			new CmdTphere(plugin).execute(s, cmd, args);
		}
		if(cmd.getName().equalsIgnoreCase("tptoggle")){
			new CmdTptoggle(plugin).execute(s, cmd, args);
		}
		return true;
	}

}
