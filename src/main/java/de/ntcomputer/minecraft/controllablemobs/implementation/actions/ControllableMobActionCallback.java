package de.ntcomputer.minecraft.controllablemobs.implementation.actions;

import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;

public class ControllableMobActionCallback extends ControllableMobActionBase {
	private final Runnable runnable;

	public ControllableMobActionCallback(final ControllableMobActionManager manager, final Runnable runnable) {
		super(manager, ActionType.CALLBACK);
		this.runnable = runnable;
	}

	@Override
	void start() {
		super.start();
		if(this.runnable!=null) this.runnable.run();
		this.finish();
	}

}
