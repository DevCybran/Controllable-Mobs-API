package de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors;

import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionJump;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NmsInterfaces;

public class PathfinderGoalActionJump extends PathfinderGoalActionBase<ControllableMobActionJump> {

	public PathfinderGoalActionJump(final CraftControllableMob<?> mob) {
		super(mob, ActionType.JUMP);
	}

	@Override
	protected void onStartAction() {
		NmsInterfaces.CONTROLLERJUMP.METHOD_JUMP.invoke(this.mob.notchEntity.getControllerJump());
	}

	@Override
	protected boolean canContinueAction() {
		return !this.mob.notchEntity.onGround;
	}

	@Override
	protected void onEndAction() {
		this.action.times--;
	}

}
