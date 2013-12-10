package de.ntcomputer.minecraft.controllablemobs.plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.PluginDescriptionFile;
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
				StringBuilder sb = new StringBuilder();
				sb.append(" is used by the plugins [").append(usedBy.get(0)).append("]");
				int size = this.usedBy.size();
				for(int i=1; i<size; i++) {
					sb.append(", [").append(this.usedBy.get(i)).append("]");
				}
				this.getLogger().info(sb.toString());
			}
		}
	}
	
	private boolean containsIgnoreCase(List<String> list, String object) {
		if(list==null) return false;
		object = object.trim();
		for(String str: list) {
			if(str.trim().equalsIgnoreCase(object)) return true;
		}
		return false;
	}
	
	@EventHandler
	public void onPluginEnabled(PluginEnableEvent event) {
		if(event.getPlugin()==this) return;
		PluginDescriptionFile desc = event.getPlugin().getDescription();
		String name = this.getName();
		if( this.containsIgnoreCase(desc.getDepend(), name) || this.containsIgnoreCase(desc.getSoftDepend(), name)) {
			this.usedBy.add(desc.getFullName());
		}
	}

	@Override
	public void onDisable() {
		this.usedBy = null;
	}

}
