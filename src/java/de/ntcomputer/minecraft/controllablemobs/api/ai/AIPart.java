package de.ntcomputer.minecraft.controllablemobs.api.ai;

import de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIBehavior;

/**
 * An interface representing one part of the entities AI.<br>
 * It can be used to examine and control this part.<br>
 * You can obtain an instance by calling {@link de.ntcomputer.minecraft.controllablemobs.api.ControllableMobAI#addBehavior(AIBehavior)}.
 * 
 * @author Cybran
 * @version v4
 *
 */
public interface AIPart {
	
	/**
	 * Unattaches the AI part from the {@link de.ntcomputer.minecraft.controllablemobs.api.ControllableMob}.<br>
	 * This will remove the {@link de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIBehavior} represented by this part from the entity.
	 * If active, the AI parts execution will be stopped immediately.<br>
	 * Later you can re-attach this part at any time by calling {@link AIPart#reattach()} 
	 */
	public void unattach();
	
	/**
	 * Re-Attaches the AI part to its {@link de.ntcomputer.minecraft.controllablemobs.api.ControllableMob}.<br>
	 * The {@link de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIBehavior} represented by this part will affect the entity again.
	 * 
	 * @throws IllegalStateException if you try to re-attach the part after the {@link de.ntcomputer.minecraft.controllablemobs.api.ControllableMob} has been unassigned
	 */
	public void reattach() throws IllegalStateException;
	
	/**
	 * @return whether you added this AI part (true) or it is a default part of the entity (false). 
	 */
	public boolean isCustomized();
	
	/**
	 * @return the {@link de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIBehavior} represented by this AI part
	 */
	public AIBehavior getBehavior();
	
	/**
	 * @return the current AI parts state.
	 */
	public AIState getState();
	
	/**
	 * @return the type of this AI part.
	 */
	public AIType getType();
	

}
