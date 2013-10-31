package de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors;

import net.minecraft.server.v1_6_R3.PathEntity;
import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionMove;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

public class PathfinderGoalActionMove extends PathfinderGoalActionDelayed<ControllableMobActionMove> {
	private PathEntity path;

	public PathfinderGoalActionMove(CraftControllableMob<?> mob) {
		super(mob, ActionType.MOVE);
		this.setMutexBits(3);
	}
	
	@Override
	protected boolean canStartAction() {
		if(action.world!=this.mob.notchEntity.world) return false;
		this.mob.adjustMaximumNavigationDistance((float) NativeInterfaces.ENTITY.METHOD_GETDISTANCETOLOCATION.invoke(this.mob.notchEntity, action.x, action.y, action.z));
		final PathEntity path = NativeInterfaces.NAVIGATION.METHOD_CREATEPATHTOLOCATION.invoke(this.mob.notchEntity.getNavigation(), action.x, action.y, action.z);
		//final PathEntity path = NativeInterfaces.WORLD.METHOD_GENERATEPATHTOCOORDS.invoke(this.mob.notchEntity.world, this.mob.notchEntity, action.x, action.y, action.z, 100F, true, false, false, true);
		if(path==null) return false;
		this.path = path;
		return true;
	}

	@Override
	protected void onStartAction() {
		NativeInterfaces.NAVIGATION.METHOD_MOVEALONGPATH.invoke(this.mob.notchEntity.getNavigation(), this.path, 1.0);
	}

	@Override
	protected boolean canContinueAction() {
		return !NativeInterfaces.NAVIGATION.METHOD_ISNOTMOVING.invoke(this.mob.notchEntity.getNavigation());
	}

	@Override
	protected void onEndAction() {
		NativeInterfaces.NAVIGATION.METHOD_STOP.invoke(this.mob.notchEntity.getNavigation());
	}

	@Override
	protected int getDelayTicks() {
		return 1;
	}

}
