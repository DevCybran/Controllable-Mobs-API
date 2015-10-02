package de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors;

import net.minecraft.server.v1_8_R3.PathEntity;
import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionMoveAbstract;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

public class PathfinderGoalActionMoveAbstract<A extends ControllableMobActionMoveAbstract> extends PathfinderGoalActionDelayed<A> {
	private PathEntity path;

	public PathfinderGoalActionMoveAbstract(CraftControllableMob<?> mob, ActionType type) {
		super(mob, type);
		this.setMutexBits(3);
	}
	
	@Override
	protected boolean canStartAction() {
		if(action.world!=this.mob.nmsEntity.world) return false;
		this.mob.adjustMaximumNavigationDistance(NativeInterfaces.ENTITY.METHOD_GETDISTANCETOLOCATION.invoke(this.mob.nmsEntity, action.x, action.y, action.z) + 50.0D);
		final PathEntity path = NativeInterfaces.NAVIGATION.METHOD_CREATEPATHTOLOCATION.invoke(this.mob.nmsEntity.getNavigation(), action.x, action.y, action.z);
		if(path==null) {
			return false;
		}
		this.path = path;
		return true;
	}

	@Override
	protected void onStartAction() {
		NativeInterfaces.NAVIGATION.METHOD_MOVEALONGPATH.invoke(this.mob.nmsEntity.getNavigation(), this.path, this.action.movementSpeedMultiplicator);
	}

	@Override
	protected boolean canContinueAction() {
		return !NativeInterfaces.NAVIGATION.METHOD_ISNOTMOVING.invoke(this.mob.nmsEntity.getNavigation());
	}

	@Override
	protected void onEndAction() {
		NativeInterfaces.NAVIGATION.METHOD_STOP.invoke(this.mob.nmsEntity.getNavigation());
	}

	@Override
	protected int getDelayTicks() {
		return 1;
	}

}
