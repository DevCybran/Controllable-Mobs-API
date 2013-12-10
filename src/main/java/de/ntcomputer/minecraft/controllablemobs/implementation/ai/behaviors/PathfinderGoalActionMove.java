package de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors;

import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionMove;

public class PathfinderGoalActionMove extends PathfinderGoalActionMoveAbstract<ControllableMobActionMove> {

	public PathfinderGoalActionMove(CraftControllableMob<?> mob) {
		super(mob, ActionType.MOVE);
	}
	
}
