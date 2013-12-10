package de.ntcomputer.minecraft.controllablemobs.implementation.actions;

import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;

public class ControllableMobActionJump extends ControllableMobActionBase {
	public int times;

	public ControllableMobActionJump(final ControllableMobActionManager manager, final int times) {
		super(manager, ActionType.JUMP);
		this.times = times;
	}

	@Override
	public boolean isValid() {
		return super.isValid() && this.times>0;
	}

}
