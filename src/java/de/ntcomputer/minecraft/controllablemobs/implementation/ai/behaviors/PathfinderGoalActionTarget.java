package de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors;

import net.minecraft.server.v1_6_R3.EntityCreature;
import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionTarget;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

public class PathfinderGoalActionTarget extends PathfinderGoalActionDelayed<ControllableMobActionTarget> {

	public PathfinderGoalActionTarget(CraftControllableMob<?> mob) {
		super(mob, ActionType.TARGET);
		this.setMutexBits(1);
	}
	
	@Override
	protected boolean isActionBlocked() {
		return action.target.world!=this.mob.notchEntity.world;
	}

	@Override
	protected void onStartAction() {
		if(this.action.target!=null) this.mob.adjustMaximumNavigationDistance((float) Math.sqrt(NativeInterfaces.ENTITY.METHOD_GETDISTANCETOENTITYSQUARED.invoke(this.mob.notchEntity, this.action.target) * 2));
		// set target for AI goals
		this.mob.notchEntity.setGoalTarget(this.action.target);
		// set target for old AI & bukkit interface (so "getTarget" on Creature will return the correct entity)
		if(this.mob.notchEntity instanceof EntityCreature) ((EntityCreature) this.mob.notchEntity).target = this.action.target;
	}

	@Override
	protected boolean canContinueAction() {
		return this.action.target!=null;
	}

	@Override
	protected void onEndAction() {
		this.mob.notchEntity.setGoalTarget(null);
	}

	@Override
	protected int getDelayTicks() {
		return 2;
	}

}
