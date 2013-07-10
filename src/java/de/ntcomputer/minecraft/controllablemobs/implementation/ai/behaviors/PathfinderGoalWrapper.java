package de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors;

import net.minecraft.server.v1_6_R2.PathfinderGoal;

public abstract class PathfinderGoalWrapper extends PathfinderGoal {

	// TODO: important: check for changes with every new minecraft release
	
	@Override
	public final boolean a() {
		return this.canStart();
	}

	@Override
	public final boolean b() {
		return this.canContinue();
	}

	@Override
	public final void c() {
		this.onStart();
	}

	@Override
	public final void d() {
		this.onEnd();
	}

	@Override
	public final void e() {
		this.onTick();
	}
	
	protected void setMutexBits(final int bits) {
		this.a(bits);
	}
	
	
	protected abstract boolean canStart();
	protected abstract void onStart();
	protected abstract boolean canContinue();
	protected abstract void onTick();
	protected abstract void onEnd();
	

}
