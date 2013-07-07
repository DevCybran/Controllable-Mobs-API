package de.ntcomputer.minecraft.controllablemobs.implementation.ai;

import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors.PathfinderGoalActionFollow;
import de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors.PathfinderGoalActionJump;
import de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors.PathfinderGoalActionLook;
import de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors.PathfinderGoalActionMove;
import de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors.PathfinderGoalActionWait;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

class AIGoalController extends AIController {

	public AIGoalController(CraftControllableMob<?> mob) {
		super(mob, NativeInterfaces.ENTITYLIVING.FIELD_GOALSELECTOR);
	}

	@Override
	protected void createActionGoals() {
		this.addActionGoal(-2, new PathfinderGoalActionMove(mob));
		this.addActionGoal(-1, new PathfinderGoalActionFollow(mob));
		this.addActionGoal(0, new PathfinderGoalActionWait(mob));
		this.addActionGoal(Integer.MAX_VALUE, new PathfinderGoalActionJump(mob));
		this.addActionGoal(Integer.MAX_VALUE, new PathfinderGoalActionLook(mob));
	}

}
