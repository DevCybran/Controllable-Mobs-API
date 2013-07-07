package de.ntcomputer.minecraft.controllablemobs.implementation.ai;

import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors.PathfinderGoalActionTarget;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

class AITargetController extends AIController {

	public AITargetController(CraftControllableMob<?> mob) {
		super(mob, NativeInterfaces.ENTITYLIVING.FIELD_TARGETSELECTOR);
	}

	@Override
	protected void createActionGoals() {
		this.addActionGoal(-1, new PathfinderGoalActionTarget(mob));
	}

}
