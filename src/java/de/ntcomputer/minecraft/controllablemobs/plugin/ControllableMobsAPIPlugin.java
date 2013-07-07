package de.ntcomputer.minecraft.controllablemobs.plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ControllableMobsAPIPlugin extends JavaPlugin implements Listener, Runnable {
	private List<String> usedBy;

	@Override
	public void onEnable() {
		this.usedBy = new ArrayList<String>();
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getScheduler().scheduleSyncDelayedTask(this, this);
	}

	@Override
	public void run() {
		if(this.usedBy.isEmpty()) {
			this.getLogger().warning(" is not used by any plugins.");
		} else {
			if(this.usedBy.size()==1) {
				this.getLogger().info(" is used by the plugin ["+this.usedBy.get(0)+"]");
			} else {
				String plugins = "["+usedBy.get(0)+"]";
				for(int i=1; i<usedBy.size(); i++) {
					plugins+= ", ["+this.usedBy.get(i)+"]";
				}
				this.getLogger().info(" is used by the plugins "+plugins);
			}
		}
	}
	
	@EventHandler
	public void onPluginEnabled(PluginEnableEvent event) {
		if(event.getPlugin()==this) return;
		final List<String> depend = event.getPlugin().getDescription().getDepend();
		if(depend!=null && depend.contains(this.getName())) {
			this.usedBy.add(event.getPlugin().getDescription().getFullName());
		}
	}

	@Override
	public void onDisable() {
		this.usedBy.clear();
		this.usedBy = null;
	}

}
