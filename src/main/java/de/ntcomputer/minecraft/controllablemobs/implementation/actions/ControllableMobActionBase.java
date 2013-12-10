package de.ntcomputer.minecraft.controllablemobs.implementation.actions;

import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionState;
import de.ntcomputer.minecraft.controllablemobs.api.actions.ControllableMobAction;
import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;

public abstract class ControllableMobActionBase implements ControllableMobAction {
	private final ControllableMobActionManager manager;
	private final ActionType type;
	private ActionState state;
	
	public ControllableMobActionBase(final ControllableMobActionManager manager, final ActionType type) {
		this.manager = manager;
		this.type = type;
		this.state = ActionState.IN_QUEUE;
	}
	
	// API methods
	
	@Override
	public ActionType getType() {
		return this.type;
	}

	@Override
	public void cancel() {
		if(this.state!=ActionState.FINISHED && this.state!=ActionState.CANCELLED) {
			if(this.state==ActionState.IN_QUEUE) this.manager.removeQueuedAction(this);
			else this.manager.removeRunningAction(this);
			this.state = ActionState.CANCELLED;
		}
	}

	@Override
	public ActionState getState() {
		return this.state;
	}
	
	
	// AI check methods
	
	public boolean isValid() {
		return this.state.isMutable;
	}
	
	
	// AI set methods
	
	public void setPreparing() {
		if(this.state.isMutable) this.state = ActionState.PREPARING;
	}
	
	public void setBlocked() {
		if(this.state.isMutable) this.state = ActionState.BLOCKED;
	}
	
	public void setRunning() {
		if(this.state.isMutable) this.state = ActionState.RUNNING;
	}
	
	public void setIdle() {
		if(this.state.isMutable) this.state = ActionState.IDLE;
	}
	
	public final void finish() {
		if(this.state.isMutable) {
			this.manager.removeRunningAction(this);
			this.state = ActionState.FINISHED;
		}
	}
	

	// action manager set methods
	
	void start() {
		this.state = ActionState.PREPARING;
	}
	
	void setCancelled() {
		this.state = ActionState.CANCELLED;
	}
	
	
	
}
