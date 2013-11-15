package de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors;

import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionAttackMove;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

public class PathfinderGoalActionAttackMove extends PathfinderGoalActionAbstractMove<ControllableMobActionAttackMove> {

	public PathfinderGoalActionAttackMove(CraftControllableMob<?> mob) {
		super(mob, ActionType.ATTACKMOVE);
	}
	
	@Override
	protected boolean isActionBlocked() {
		if(this.mob.nmsEntity.getGoalTarget()==null) return false;
		double distanceSquared = NativeInterfaces.ENTITY.METHOD_GETDISTANCETOLOCATIONSQUARED.invoke(this.mob.nmsEntity, action.x, action.y, action.z);
		if(distanceSquared > this.action.lastDistanceSquared) return false;
		this.action.lastDistanceSquared = distanceSquared;
		return true;
	}

	@Override
	protected boolean isActionRequired() {
		double distanceSquared = NativeInterfaces.ENTITY.METHOD_GETDISTANCETOLOCATIONSQUARED.invoke(this.mob.nmsEntity, action.x, action.y, action.z);
		if(this.mob.nmsEntity.getGoalTarget()!=null) {
			if(distanceSquared < this.action.lastDistanceSquared) {
				this.action.lastDistanceSquared = distanceSquared;
				return false;
			}
			if(Math.sqrt(distanceSquared) < Math.sqrt(this.action.lastDistanceSquared) + this.action.maxDistraction) {
				return false;
			}
		}
		return true;
	}

}
