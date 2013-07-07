package de.ntcomputer.minecraft.controllablemobs.api.ai;

/**
 * Enum describing the current state of an AI part.<br>
 * Can be obtained by calling {@link AIPart#getState()}.
 * 
 * @author Cybran
 * @version v4
 *
 */
public enum AIState {
	/**
	 * The AI part is not attached to the {@link de.ntcomputer.minecraft.controllablemobs.api.ControllableMob}.<br>
	 * You can re-attach it by calling {@link AIPart#reattach()}.
	 */
	UNATTACHED,
	
	/**
	 * The AI part is attached to the {@link de.ntcomputer.minecraft.controllablemobs.api.ControllableMob}, but is currently inactive because certain conditions are not fulfilled.
	 */
	INACTIVE,
	
	/**
	 * The AI part is attached to the {@link de.ntcomputer.minecraft.controllablemobs.api.ControllableMob} and is currently executing certain AI tasks.
	 */
	ACTIVE;
}
