package de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors;

import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionState;
import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionBase;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionManager;

abstract class PathfinderGoalActionBase<A extends ControllableMobActionBase> extends PathfinderGoalWrapper {
	protected final CraftControllableMob<?> mob;
	private final ControllableMobActionManager actMgr;
	private final ActionType actionType;
	protected A action;
	private int startTicks;
	
	public PathfinderGoalActionBase(final CraftControllableMob<?> mob, final ActionType actionType) {
		this.mob = mob;
		this.actMgr = mob.getActionManager();
		this.actionType = actionType;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected final boolean canStart() {
		if(this.action==null) {
			this.action = (A) this.actMgr.getRunningAction(this.actionType);
			if(this.action==null) return false;
			this.startTicks = 1;
		}
		
		if(!action.isValid()) {
			this.action.finish();
			this.action = null;
			return false;
		}
		
		if(--this.startTicks>0) return false;
		
		if(!this.isActionRequired()) {
			this.action.setIdle();
			this.startTicks = 3;
			return false;
		}
		
		if(this.isActionBlocked() || !this.canStartAction()) {
			this.action.setBlocked();
			this.startTicks = 8;
			return false;
		}
		
		this.action.setRunning();
		return true;
	}
	
	@Override
	protected void onStart() {
		this.onStartAction();
	}

	@Override
	protected boolean canContinue() {
		if(this.isActionBlocked()) {
			this.action.setBlocked();
			return false;
		}
		return this.action.isValid() && this.canContinueAction();
	}
	
	@Override
	protected void onTick() {
		this.onTickAction();
	};
	
	@Override
	protected final void onEnd() {
		this.onEndAction();
		if(this.action.getState()!=ActionState.BLOCKED) {
			if(this.action.getType().isMultiCycleTask && this.action.isValid()) {
				this.action.setPreparing();
			} else {
				this.action.finish();
			}
		}
		this.action = null;
	}
	
	protected boolean isActionBlocked() {
		return false;
	}
	protected boolean isActionRequired() {
		return true;
	}
	protected boolean canStartAction() {
		return true;
	}
	protected abstract boolean canContinueAction();
	protected void onStartAction() {};
	protected void onTickAction() {};
	protected void onEndAction() {}
	
}
