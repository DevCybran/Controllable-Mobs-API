package de.ntcomputer.minecraft.controllablemobs.api.actions;

/**
 * An interface to access and control an underlying action.
 * An instance is returned by any action method of {@link de.ntcomputer.minecraft.controllablemobs.api.ControllableMobActions}
 * 
 * @author Cybran
 * @version v4
 *
 */
public interface ControllableMobAction {
	
	/**
	 * Cancels the action immediately.
	 * The action is being removed from running actions and queued actions.
	 * It will stop before the desired goal is reached.
	 */
	public void cancel();
	
	/**
	 * Retrieves the current state of the action.
	 * 
	 * @return an {@link ActionState} for this action
	 */
	public ActionState getState();
	
	/**
	 * Retrieves the type of the action.
	 * 
	 * @return this actions {@link ActionType}
	 */
	public ActionType getType();
}
