package de.ntcomputer.minecraft.controllablemobs.api.actions;

/**
 * Enum describing the current state of an action.
 * Can be retrieved by calling {@link ControllableMobAction#getState()}
 * 
 * @author Cybran
 * @version 73
 *
 */
public enum ActionState {
	/**
	 * The action is in queue, waiting to be executed.
	 */
	IN_QUEUE (false),
	
	/**
	 * The action is not in queue anymore, but execution hasn't started yet.
	 * The decision whether the action will be RUNNING, IDLE or BLOCKED is pending.
	 */
	PREPARING (true),
	
	/**
	 * The action is being executed, but is not doing anything.
	 * Only background actions can have this state.
	 * (for example: following a target is not necessary due to the proximity)
	 */
	IDLE (true),
	
	/**
	 * The action is being executed, but is blocked.
	 * See the corresponding action method to see what can block an action.
	 * If an action is blocked, it will not have any effect, but also won't be removed or marked as finished. Instead, it will block other actions which are in queue.
	 */
	BLOCKED (true),
	
	/**
	 * The action is being executed and running normally.
	 */
	RUNNING (true),
	
	/**
	 * The action has been finished.
	 * It is not longer bound to any ControllableMob, the action instance will be useless. You should remove the reference to the instance.
	 */
	FINISHED (false),
	
	/**
	 * The action has been cancelled before it could finish.
	 * It is not longer bound to any ControllableMob, the action instance will be useless. You should remove the reference to the instance.
	 * An action can be cancelled by your plugin (calling action.cancel()), or by the internal action manager when a new action of the same type overrides the old one.
	 */
	CANCELLED (false);
	
	public final boolean isMutable;
	
	private ActionState(final boolean mutable) {
		this.isMutable = mutable;
	}
}
