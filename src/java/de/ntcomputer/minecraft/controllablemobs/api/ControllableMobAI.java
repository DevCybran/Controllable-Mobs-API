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
 * @version v4
 */
public interface ControllableMobAI<E extends LivingEntity> {
	
	/**
	 * Adds a given AI behavior - the entity will act corresponding to this behavior.
	 * AI behaviors are persistent, if you add them one time, they will stay active until you remove them.
	 * AI behaviors start certain actions automatically, defined by the behavior you add.
	 * You can retrieve an AI behavior instance by creating a subclass of {@link AIBehavior}.
	 * You must not add a behavior more than one time.
	 * 
	 * @param behavior the new AI behavior
	 * @throws IllegalArgumentException when behavior is null or has already been added
	 * 
	 * @deprecated changed to {@link ControllableMobAI#addBehavior(AIBehavior)}. Method will be removed in v5.
	 */
	@SuppressWarnings("rawtypes")
	@Deprecated
	public void addAIBehavior(AIBehavior behavior) throws IllegalArgumentException;
	
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
	 * @see ControllableMobAI#addAIBehavior(AIBehavior)
	 * @param behaviors an array of new AI behaviors
	 * @throws IllegalArgumentException when behaviors is null or contains null
	 * 
	 * @deprecated changed to {@link ControllableMobAI#addBehaviors(AIBehavior...)}. Method will be removed in v5.
	 */
	@SuppressWarnings("rawtypes")
	public void addAIBehaviors(AIBehavior[] behaviors) throws IllegalArgumentException;
	
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
	 * Removes the given AI behavior.<br>
	 * The behavior must have been added manually before.
	 * 
	 * @param behavior the AI behavior to remove. Be sure, that the instance was previously added by {@link ControllableMobAI#addAIBehavior(AIBehavior)}
	 * @throws IllegalArgumentException when behavior is null
	 * 
	 * @deprecated removing parts of the AI by passing the behavior is deprecated. You should switch to {@link de.ntcomputer.minecraft.controllablemobs.api.ai.AIPart#remove()}. Method will be removed in v5 or v6.
	 */
	@SuppressWarnings("rawtypes")
	@Deprecated
	public void removeAIBehavior(AIBehavior behavior) throws IllegalArgumentException;
	
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
	 * 
	 * @deprecated renamed to {@link ControllableMobAI#clear()}. Method will be removed in v5.
	 */
	@Deprecated
	public void clearAIBehaviors();
	
	/**
	 * Removes all AI behaviors.<br>
	 * Removes custom, manually added behaviors as well as default behaviors.<br>
	 * The result: the entity will not move nor attack automatically.
	 */
	public void clear();
	
	/**
	 * Restores all default AI behaviors.
	 * Removes custom, manually added behaviors and restores the default behaviors for this entity.
	 * The result: the entity will act normal.
	 * 
	 * @deprecated renamed to {@link ControllableMobAI#reset()}. Method will be removed in v5.
	 */
	@Deprecated
	public void restoreAIBehaviors();
	
	/**
	 * Resets to default AI behaviors.<br>
	 * Removes custom, manually added behaviors and restores the default behaviors for this entity.<br>
	 * The result: the entity will act normal.
	 */
	public void reset();
	
	/**
	 * Retrieves all custom AI behaviors.
	 * 
	 * @return an array of all custom AI behaviors (default AI behaviors are NOT included)
	 * 
	 * @deprecated retrieving the AI by returning the behaviors is deprecated. You should switch to {@link ControllableMobAI#getAIParts()}. Method will be removed in v5 or v6.
	 */
	@SuppressWarnings("rawtypes")
	@Deprecated
	public AIBehavior[] getAIBehaviors();
	
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
