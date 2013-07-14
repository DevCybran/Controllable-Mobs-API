package de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors;

import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionLook;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

public class PathfinderGoalActionLook extends PathfinderGoalActionBase<ControllableMobActionLook> {

	public PathfinderGoalActionLook(CraftControllableMob<?> mob) {
		super(mob, ActionType.LOOK);
		this.setMutexBits(1);
	}
	
	@Override
	protected boolean isActionBlocked() {
		return action.getWorld()!=this.mob.notchEntity.world;
	}

	@Override
	protected boolean canContinueAction() {
		return true;
	}

	@Override
	protected void onTickAction() {
		NativeInterfaces.CONTROLLERLOOK.METHOD_LOOKATCOORDINATES.invoke(this.mob.notchEntity.getControllerLook(), this.action.getX(), this.action.getY(), this.action.getZ(), 10.0F, NativeInterfaces.ENTITYINSENTIENT.METHOD_GETVERTICALHEADSPEED.invoke(this.mob.notchEntity));
	}

	

}
