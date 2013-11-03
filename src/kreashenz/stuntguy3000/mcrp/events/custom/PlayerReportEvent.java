package kreashenz.stuntguy3000.mcrp.events.custom;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerReportEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private boolean cancelled = false;

	private Player reporter;

	private String report;

	public PlayerReportEvent(Player p, String report){
		this.reporter = p;
		this.report = report;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList(){
		return handlers;
	}

	public Player getPlayer(){
		return reporter;
	}

	public String getReport(){
		return report;
	}

}
