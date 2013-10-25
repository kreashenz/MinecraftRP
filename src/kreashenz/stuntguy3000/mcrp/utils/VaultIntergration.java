package kreashenz.stuntguy3000.mcrp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;

public class VaultIntergration implements Economy {

	private Plugin plugin;
	private MinecraftRP mcrp;

	private final String name = "MCRP Eco";

	public VaultIntergration(Plugin plugin){
		this.plugin = plugin;

		if(mcrp == null){
			Plugin minecraftRP = plugin.getServer().getPluginManager().getPlugin("MinecraftRP");
			if(minecraftRP != null && minecraftRP.isEnabled()){
				mcrp = (MinecraftRP) mcrp;
				Functions.log(Level.INFO, String.format("[%s][Economy] %s hooked.", plugin.getDescription().getName(), name));
			}
		}
	}


	@Override
	public boolean createPlayerAccount(String arg0) {
		return hasAccount(arg0);
	}

	@Override
	public boolean createPlayerAccount(String arg0, String arg1) {
		return createPlayerAccount(arg0);
	}

	@Override
	public String currencyNamePlural() {
		return "";
	}

	@Override
	public String currencyNameSingular() {
		return "";
	}

	@Override
	public EconomyResponse depositPlayer(String arg0, double arg1) {
		if(arg1 < 0){
			return new EconomyResponse(0, 0, ResponseType.FAILURE, "Cannot give players negative money");
		}

		double d;
		ResponseType type;
		String uhohs = null;

		Player p = Bukkit.getPlayerExact(arg0);
		if(p != null){
			MPlayer pm = MPlayer.getMPlayer(p);
			pm.addMoney(arg1);
			d = pm.getMoney();
			type = ResponseType.SUCCESS;
		} else {
			d = 0;
			type = ResponseType.FAILURE;
			arg1 = 0;
			uhohs = "Player wasn't found!";
		}
		return new EconomyResponse(arg1, d, type, uhohs);
	}

	@Override
	public EconomyResponse depositPlayer(String arg0, String arg1, double arg2) {
		return depositPlayer(arg0, arg2);
	}

	@Override
	public String format(double arg0) {
		return null;
	}

	@Override
	public int fractionalDigits() {
		return -1;
	}

	@Override
	public double getBalance(String arg0) {
		Player p = Bukkit.getPlayerExact(arg0);
		if(p != null){
			MPlayer pm = MPlayer.getMPlayer(p);
			return pm.getMoney();
		} else {
			return 0;
		}
	}

	@Override
	public double getBalance(String arg0, String arg1) {
		return getBalance(arg0);
	}

	@Override
	public List<String> getBanks() {
		return new ArrayList<String>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean has(String arg0, double arg1) {
		Player p = Bukkit.getPlayerExact(arg0);
		if(p != null){
			MPlayer pm = MPlayer.getMPlayer(p);
			return pm.canAfford(arg1);
		}
		return false;
	}

	@Override
	public boolean has(String arg0, String arg1, double arg2) {
		return has(arg0, arg2);
	}

	@Override
	public boolean hasAccount(String arg0) {
		return false;
	}

	@Override
	public boolean hasAccount(String arg0, String arg1) {
		return false;
	}

	@Override
	public boolean hasBankSupport() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		if(mcrp == null)return false;
		else return mcrp.isEnabled();
	}

	@Override
	public EconomyResponse withdrawPlayer(String arg0, double arg1) {
		Player p = Bukkit.getPlayerExact(arg0);
		if(p != null){
			MPlayer pm = MPlayer.getMPlayer(p);
			pm.takeMoney(arg1);
		}
		return null;
	}

	@Override
	public EconomyResponse withdrawPlayer(String arg0, String arg1, double arg2) {
		return withdrawPlayer(arg0, arg2);
	}

	@Override
	public EconomyResponse bankBalance(String arg0) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, name + " doesn't support banks");
	}

	@Override
	public EconomyResponse bankDeposit(String arg0, double arg1) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, name + " doesn't support banks");
	}

	@Override
	public EconomyResponse bankHas(String arg0, double arg1) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, name + " doesn't support banks");
	}

	@Override
	public EconomyResponse bankWithdraw(String arg0, double arg1) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, name + " doesn't support banks");
	}

	@Override
	public EconomyResponse createBank(String arg0, String arg1) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, name + " doesn't support banks");
	}

	@Override
	public EconomyResponse isBankMember(String arg0, String arg1) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, name + " doesn't support banks");
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, String arg1) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, name + " doesn't support banks");
	}

	@Override
	public EconomyResponse deleteBank(String arg0) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, name + " doesn't support banks");
	}

	public class MinecraftRPEconomyServerListener implements Listener {
		VaultIntergration pl;

		public MinecraftRPEconomyServerListener(VaultIntergration pl){
			this.pl = pl;
		}

		@EventHandler
		public void onPluginEnable(PluginEnableEvent e){
			if(pl.mcrp == null){
				Plugin mcrp = e.getPlugin();

				if(mcrp.getDescription().getName().equalsIgnoreCase("MinecraftRP")){
					pl.mcrp = (MinecraftRP)mcrp;
					Functions.log(Level.INFO, String.format("[%s][Economy] %s hooked", plugin.getDescription().getName(), pl.name));
				}

			}
		}

		@EventHandler
		public void onPluginDisable(PluginDisableEvent e){
			if(pl.mcrp != null){
				if(e.getPlugin().getDescription().getName().equalsIgnoreCase("MinecraftRP")){
					pl.mcrp = null;
					Functions.log(Level.INFO, String.format("[%s][Economy] %s unhooked", plugin.getDescription().getName(), pl.name));
				}
			}
		}

	}

}
