package de.ntcomputer.minecraft.controllablemobs.api;

import org.bukkit.entity.LivingEntity;

import de.ntcomputer.minecraft.controllablemobs.api.ai.AIPart;
import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIBehavior;

/**
 * This interface lets you control and modify the entities AI.
 * You can retrieve an instance by using {@link ControllableMob#getAI()}
 * 
 * @author Cybran
 * @version v5
 */
public interface ControllableMobAI<E extends LivingEntity> {
	
	/**
	 * Adds a given AI behavior - the entity will act corresponding to this behavior.<br>
	 * AI behaviors start certain actions automatically, defined by the behavior you add.<br>
	 * You can retrieve an AI behavior instance by creating a subclass of {@link AIBehavior}.<br>
	 * You can add the passed behavior to more than one entity.
	 * 
	 * @param behavior the new AI behavior
	 * @return an AI part representing the behavior. You can use it to inspect or temporarily remove the behavior later.
	 * @throws IllegalArgumentException when behavior is null
	 */
	public <B extends AIBehavior<? super E>> AIPart<E,B> addBehavior(B behavior) throws IllegalArgumentException;
	
	/**
	 * Adds the given AI behaviors.
	 * 
	 * @see ControllableMobAI#addBehavior(AIBehavior)
	 * @param behaviors multiple new AI behaviors
	 * @return an array of AI parts. The parts are ordered similar to the behavior order in the behaviors array.
	 * @throws IllegalArgumentException when behaviors is null or contains null
	 */
	public AIPart<E,?>[] addBehaviors(AIBehavior<? super E>... behaviors) throws IllegalArgumentException;
	
	/**
	 * Unattaches all AI parts of the specified types.<br>
	 * You are free to re-attach any of the removed AI parts later.
	 * 
	 * @param typesToRemove multiple AI types. Any AI part of a type contained in this array will be unattached.
	 */
	public void remove(AIType... typesToRemove);
	
	/**
	 * Unattaches all AI parts that are NOT of the specified types.<br>
	 * You are free to re-attach any of the removed AI parts later.
	 * 
	 * @param typesToKeep multiple AI types. Any AI part of a type that is NOT contained in this array will be unattached.
	 */
	public void removeExcept(AIType... typesToKeep);

	/**
	 * Removes all AI behaviors.<br>
	 * Removes custom, manually added behaviors as well as default behaviors.<br>
	 * The result: the entity will not move nor attack automatically.
	 */
	public void clear();
	
	/**
	 * Resets to default AI behaviors.<br>
	 * Removes custom, manually added behaviors and restores the default behaviors for this entity.<br>
	 * The result: the entity will act normal.
	 */
	public void reset();
	
	/**
	 * Retrieves all AI parts, including custom behaviors and default behaviors.
	 * 
	 * @return an array of all custom and default AI parts. Use it to inspect or remove specific parts.
	 */
	public AIPart<E,?>[] getParts();
	
	/**
	 * Retrieves all AI parts, including custom behaviors and default behaviors of a specific type.
	 * 
	 * @param types the filter types
	 * @return an array of all AI parts that have one of the types you specified.
	 */
	public AIPart<E,?>[] getPartsOf(AIType... types);
	
	/**
	 * Checks whether a behavior of the given type exists.
	 * @param type the type to check for
	 * @return true, if this mob has an AI behavior of the specified type, and false otherwise
	 */
	public boolean hasBehavior(AIType type);
	
}
