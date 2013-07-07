package de.ntcomputer.minecraft.controllablemobs.implementation.actions;

import java.util.EnumMap;
import java.util.LinkedList;

import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;

public final class ControllableMobActionManager {
	private LinkedList<ControllableMobActionBase> queue;
	private EnumMap<ActionType, ControllableMobActionBase> running;
	private EnumMap<ActionType, ControllableMobActionBase> background;
	
	public ControllableMobActionManager() {
		this.queue = new LinkedList<ControllableMobActionBase>();
		this.running = new EnumMap<ActionType, ControllableMobActionBase>(ActionType.class);
		this.background = new EnumMap<ActionType, ControllableMobActionBase>(ActionType.class);
	}
	
	
	// external accessors
	public boolean isActionRunning(final ActionType type) {
		return type.isBackgroundTask ? this.background.containsKey(type) : this.running.containsKey(type);
	}
	
	
	// internal accessors
	public ControllableMobActionBase getRunningAction(final ActionType actionType) {
		return actionType.isBackgroundTask ? this.background.get(actionType) : this.running.get(actionType);
	}
	
	private void checkQueuing() {
		if(this.running.isEmpty() && !this.queue.isEmpty()) {
			final ControllableMobActionBase action = this.queue.pop();
			this.addActionAsRunning(action);
			if(action.getType().isBackgroundTask) this.checkQueuing();
		}
	}
	
	
	// queue modifying
	public void addActionToQueue(ControllableMobActionBase action) {
		this.queue.offer(action);
		this.checkQueuing();
	}
	
	void removeQueuedAction(ControllableMobActionBase action) {
		this.queue.remove(action);
	}
	
	public void clearQueue() {
		for(ControllableMobActionBase action: this.queue) {
			action.setCancelled();
		}
		this.queue.clear();
	}
	
	
	// running modifying
	public void addActionAsRunning(ControllableMobActionBase action) {
		final EnumMap<ActionType, ControllableMobActionBase> actionList = action.getType().isBackgroundTask ? this.background : this.running;
		if(actionList.containsKey(action.getType())) {
			actionList.get(action.getType()).setCancelled();
		}
		actionList.put(action.getType(), action);
		action.start();
	}
	
	void removeRunningAction(ControllableMobActionBase action) {
		final EnumMap<ActionType, ControllableMobActionBase> actionList = action.getType().isBackgroundTask ? this.background : this.running;
		if(actionList.get(action.getType())==action) actionList.remove(action.getType());
		if(!action.getType().isBackgroundTask) this.checkQueuing();
	}
	
	public void clearRunning() {
		for(ControllableMobActionBase action: this.background.values()) {
			action.setCancelled();
		}
		this.background.clear();
		for(ControllableMobActionBase action: this.running.values()) {
			action.setCancelled();
		}
		this.running.clear();
		this.checkQueuing();
	}
	
	
	// cleanup
	public void dispose() {
		this.clearQueue();
		this.clearRunning();
		
		this.queue = null;
		this.background = null;
		this.running = null;
	}

}
