package de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors;

import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionJump;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

public class PathfinderGoalActionJump extends PathfinderGoalActionBase<ControllableMobActionJump> {

	public PathfinderGoalActionJump(final CraftControllableMob<?> mob) {
		super(mob, ActionType.JUMP);
	}

	@Override
	protected void onStartAction() {
		NativeInterfaces.CONTROLLERJUMP.METHOD_JUMP.invoke(this.mob.nmsEntity.getControllerJump());
	}

	@Override
	protected boolean canContinueAction() {
		return !this.mob.nmsEntity.onGround;
	}

	@Override
	protected void onEndAction() {
		this.action.times--;
	}

}
