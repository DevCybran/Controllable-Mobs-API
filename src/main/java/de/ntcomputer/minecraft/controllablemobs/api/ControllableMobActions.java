package de.ntcomputer.minecraft.controllablemobs.api;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.api.actions.ControllableMobAction;

/**
 * An interface which creates actions for an entity.
 * You can order the entity to move or look at certain locations, jump, etc.
 * You can retrieve an instance by using {@link ControllableMob#getActions()}
 * 
 * @author Cybran
 * @version v6
 */
public interface ControllableMobActions {
	/**
	 * Orders the entity to move to the given location.
	 * Uses the {@link ControllableMobActions#getDefaultQueuingFlag() default queuing flag}.
	 * 
	 * @see #moveTo(Location, boolean, double)
	 * @param loc the location the entity will move to.
	 * @return {@link ControllableMobAction}
	 */
	public ControllableMobAction moveTo(Location loc);
	
	/**
	 * Orders the entity to move to the given location, optionally adding the action to the queue.
	 * This action is block accurate, meaning, that the entity will stop once it reached the block the given location is pointing at.
	 * When the action's execution is started, all other movements or attacks caused by the AI are stopped and overridden.
	 * If a target is assigned to the entity, it will not be lost and dealing with it will be resumed when this movement is finished. 
	 * 
	 * @see #moveTo(Location, boolean, double)
	 * @param loc the location the entity will move to.
	 * @param queue whether this action should be added to the queue (true) or executed directly (false).
	 * @return {@link ControllableMobAction}
	 */
	public ControllableMobAction moveTo(Location loc, boolean queue);
	
	/**
	 * Orders the entity to move to the given location, optionally adding the action to the queue.
	 * This action is block accurate, meaning, that the entity will stop once it reached the block the given location is pointing at.
	 * When the action's execution is started, all other movements or attacks caused by the AI are stopped and overridden.
	 * If a target is assigned to the entity, it will not be lost and dealing with it will be resumed when this movement is finished. 
	 * 
	 * @param loc the location the entity will move to.
	 * @param queue whether this action should be added to the queue (true) or executed directly (false).
	 * @param movementSpeedMultiplicator 1.0 for default speed, 2.0 for doubling the movement speed, and so on. Default is 1.0
	 * @return {@link ControllableMobAction}
	 * @throws IllegalArgumentException when movementSpeedMultiplicator is zero or negative
	 */
	public ControllableMobAction moveTo(Location loc, boolean queue, double movementSpeedMultiplicator) throws IllegalArgumentException;
	
	/**
	 * Orders the entity to move to the given location. Uses the {@link ControllableMobActions#getDefaultQueuingFlag() default queuing flag}.
	 * This action is block accurate, meaning, that the entity will stop once it reached the block the given location is pointing at.
	 * When the action's execution is started, all other movements caused by the AI are stopped and overridden.
	 * If the entity has a target locked on, it will interrupt the movement, deal with this target, and then continue moving to the destination.
	 * 
	 * @see #moveToAttacking(Location, boolean, double, double)
	 * @param loc the location the entity will move to.
	 * @return {@link ControllableMobAction}
	 */
	public ControllableMobAction moveToAttacking(Location loc);
	
	/**
	 * Orders the entity to move to the given location, optionally adding the action to the queue.
	 * This action is block accurate, meaning, that the entity will stop once it reached the block the given location is pointing at.
	 * When the action's execution is started, all other movements caused by the AI are stopped and overridden.
	 * If the entity has a target locked on, it will interrupt the movement, deal with this target, and then continue moving to the destination.
	 * 
	 * @see #moveToAttacking(Location, boolean, double, double)
	 * @param loc the location the entity will move to.
	 * @param queue whether this action should be added to the queue (true) or executed directly (false).
	 * @return {@link ControllableMobAction}
	 */
	public ControllableMobAction moveToAttacking(Location loc, boolean queue);
	
	/**
	 * Orders the entity to move to the given location, optionally adding the action to the queue.
	 * This action is block accurate, meaning, that the entity will stop once it reached the block the given location is pointing at.
	 * When the action's execution is started, all other movements caused by the AI are stopped and overridden.
	 * If the entity has a target locked on, it will interrupt the movement, deal with this target, and then continue moving to the destination.
	 * 
	 * @see #moveToAttacking(Location, boolean, double, double)
	 * @param loc the location the entity will move to.
	 * @param queue whether this action should be added to the queue (true) or executed directly (false).
	 * @param maximumDistractionDistance the maximum distance this entity will try to follow targets in order to attack them. If reached, the entity will turn back and continue moving to the destination. Default is 16.0 blocks.
	 * @return {@link ControllableMobAction}
	 * @throws IllegalArgumentException when maximumDistractionDistance is zero or negative
	 */
	public ControllableMobAction moveToAttacking(Location loc, boolean queue, double maximumDistractionDistance) throws IllegalArgumentException;
	
	/**
	 * Orders the entity to move to the given location, optionally adding the action to the queue.
	 * This action is block accurate, meaning, that the entity will stop once it reached the block the given location is pointing at.
	 * When the action's execution is started, all other movements caused by the AI are stopped and overridden.
	 * If the entity has a target locked on, it will interrupt the movement, deal with this target, and then continue moving to the destination.
	 * 
	 * @param loc the location the entity will move to.
	 * @param queue whether this action should be added to the queue (true) or executed directly (false).
	 * @param maximumDistractionDistance the maximum distance this entity will try to follow targets in order to attack them. If reached, the entity will turn back and continue moving to the destination. Default is 16.0 blocks.
	 * @param movementSpeedMultiplicator 1.0 for default speed, 2.0 for doubling the movement speed, and so on. Default is 1.0
	 * @return {@link ControllableMobAction}
	 * @throws IllegalArgumentException when maximumDistractionDistance or movementSpeedMultiplicator is zero or negative
	 */
	public ControllableMobAction moveToAttacking(Location loc, boolean queue, double maximumDistractionDistance, double movementSpeedMultiplicator) throws IllegalArgumentException;
	
	/**
	 * Orders the entity to look at the given location.
	 * Uses the {@link ControllableMobActions#getDefaultQueuingFlag() default queuing flag}.
	 * 
	 * @see ControllableMobActions#lookAt(Location, boolean)
	 * @param loc the location the entity will look at.
	 * @return {@link ControllableMobAction}
	 */
	public ControllableMobAction lookAt(Location loc);
	/**
	 * Orders the entity to look at the given other entity.
	 * Uses the {@link ControllableMobActions#getDefaultQueuingFlag() default queuing flag}.
	 * 
	 * @see ControllableMobActions#lookAt(Entity, boolean)
	 * @param entity the other entity this entity will look at.
	 * @return {@link ControllableMobAction}
	 * @throws IllegalArgumentException when the entity itself is specified as target
	 */
	public ControllableMobAction lookAt(Entity entity) throws IllegalArgumentException;
	/**
	 * Orders the entity to look at the given location, optionally adding the action to the queue.
	 * The entity will look at the location by turning its head to it (animated).
	 * If the entity moves, it will try to look at the location anyway, turning its head further.
	 * This is a background action, it will last until you call lookAt((Location) null), it will not block the queue and other actions can run simultaneously.
	 * 
	 * @param loc the location the entity will look at.
	 * @param queue whether this action should be added to the queue (true) or executed directly (false).
	 * @return {@link ControllableMobAction}
	 */
	public ControllableMobAction lookAt(Location loc, boolean queue);
	/**
	 * Orders the entity to look at the given other entity, optionally adding the action to the queue.
	 * The entity will look at the other entity by turning its head to it (animated).
	 * If the entity moves or the entity it looks at moves, the entity described by this ControllableMob will try to look at the other entity anyway, turning its head further.
	 * This is a background action, it will last until you call lookAt((Entity) null), it will not block the queue and other actions can run simultaneously.
	 * 
	 * @param entity the other entity this entity will look at.
	 * @param queue whether this action should be added to the queue (true) or executed directly (false).
	 * @return {@link ControllableMobAction}
	 * @throws IllegalArgumentException when the entity itself is specified as target
	 */
	public ControllableMobAction lookAt(Entity entity, boolean queue) throws IllegalArgumentException;
	
	/**
	 * The given target is assigned to the entity.
	 * Uses the {@link ControllableMobActions#getDefaultQueuingFlag() default queuing flag}.
	 * 
	 * @see ControllableMobActions#target(LivingEntity, boolean)
	 * @param entity the new target
	 * @return {@link ControllableMobAction}
	 * @throws IllegalArgumentException when the entity itself is specified as target
	 */
	public ControllableMobAction target(LivingEntity entity) throws IllegalArgumentException;
	/**
	 * The given target is assigned to the entity, optionally adding the action to the queue.
	 * The entity will react on this target as declared by its AI.
	 * One of the following target behaviors had to be added previously: {@link de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIAttackMelee}, {@link de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIAttackRanged}.
	 * If none of these have been added, the entity will do nothing with its new target.
	 *  
	 * @param entity the new target
	 * @param queue whether this action should be added to the queue (true) or executed directly (false).
	 * @return {@link ControllableMobAction}
	 * @throws IllegalArgumentException when the entity itself is specified as target
	 */
	public ControllableMobAction target(LivingEntity entity, boolean queue) throws IllegalArgumentException;
	
	/**
	 * Orders the entity to wait before executing following actions.
	 * Uses the {@link ControllableMobActions#getDefaultQueuingFlag() default queuing flag}.
	 * 
	 * @see ControllableMobActions#wait(int, boolean)
	 * @param serverTicks the amount of server ticks the entity will wait. 20 server ticks = 1 second.
	 * @return {@link ControllableMobAction}
	 */
	public ControllableMobAction wait(int serverTicks);
	/**
	 * Orders the entity to wait before executing following actions, optionally this is added to the queue.
	 * The "wait" acts like a "placeholder" - it will prevent the entity to run actions from the queue for the given amount of time.
	 * The "wait" is an action itself and can therefore be added to the queue. 
	 * The AI is unaffected by this and will perform actions normally.
	 * Parallel actions are unaffected too.
	 * 
	 * @param serverTicks the amount of server ticks the entity will wait. 20 server ticks = 1 second.
	 * @param queue whether this action should be added to the queue (true) or executed directly (false).
	 * @return {@link ControllableMobAction}
	 */
	public ControllableMobAction wait(int serverTicks, boolean queue);
	
	/**
	 * The entity dies. Funny, isn't it?
	 * The action will be executed immediately.
	 * 
	 * @see ControllableMobActions#die(boolean)
	 */
	public void die();
	/**
	 * The entity will die, optionally the created "action" will be added to the queue.
	 * This action will reduce the entities current health down to zero.
	 * 
	 * @param queue whether this action should be added to the queue (true) or executed directly (false).
	 * @return {@link ControllableMobAction}
	 */
	public ControllableMobAction die(boolean queue);
	
	/**
	 * The entity will jump one time.
	 * Uses the {@link ControllableMobActions#getDefaultQueuingFlag() default queuing flag}.
	 * 
	 * @see ControllableMobActions#jump(int, boolean)
	 * @return {@link ControllableMobAction}
	 */
	public ControllableMobAction jump();
	/**
	 * The entity will jumps as many times as specified.
	 * Uses the {@link ControllableMobActions#getDefaultQueuingFlag() default queuing flag}.
	 * 
	 * @see ControllableMobActions#jump(int, boolean)
	 * @param times how often the entity should jump continuously.
	 * @return {@link ControllableMobAction}
	 */
	public ControllableMobAction jump(int times);
	/**
	 * The entity will jump one time, optionally the jump is added to the queue.
	 * 
	 * @see ControllableMobActions#jump(int, boolean)
	 * @param queue whether this action should be added to the queue (true) or executed directly (false).
	 * @return {@link ControllableMobAction}
	 */
	public ControllableMobAction jump(boolean queue);
	/**
	 * The entity will jumps as many times as specified, optionally these jumps are added to the queue.
	 * Jumping is independent from movement or attacking, the entity can as well jump and move at the same time.
	 * 
	 * @param times how often the entity should jump continuously.
	 * @param queue whether this action should be added to the queue (true) or executed directly (false).
	 * @return {@link ControllableMobAction}
	 */
	public ControllableMobAction jump(int times, boolean queue);
	
	/**
	 * The entity will follow another entity.
	 * Uses the {@link ControllableMobActions#getDefaultQueuingFlag() default queuing flag}.
	 * Moving with {@link ControllableMobActions#moveTo(Location, boolean)} will override this action.
	 * When following (actually moving), the AI is blocked.
	 * 
	 * @see ControllableMobActions#follow(LivingEntity, boolean, float, float)
	 * @param entity the entity that will be followed
	 * @return {@link ControllableMobAction}
	 */
	public ControllableMobAction follow(LivingEntity entity) throws IllegalArgumentException;
	/**
	 * The entity will follow another entity, optionally this action is added to the queue before executing.
	 * Moving with {@link ControllableMobActions#moveTo(Location, boolean)} will override this action.
	 * When following (actually moving), the AI is blocked.
	 * 
	 * @see ControllableMobActions#follow(LivingEntity, boolean, float, float)
	 * @param entity the entity that will be followed
	 * @param queue whether this action should be added to the queue (true) or executed directly (false).
	 * @return {@link ControllableMobAction}
	 * @throws IllegalArgumentException when entity is the controlled entity itself
	 */
	public ControllableMobAction follow(LivingEntity entity, boolean queue) throws IllegalArgumentException;
	/**
	 * The entity will follow another entity, staying within the given radius, optionally this action is added to the queue before executing.
	 * Moving with {@link ControllableMobActions#moveTo(Location, boolean)} will override this action.
	 * When following (actually moving), the AI is blocked.
	 * 
	 * @see ControllableMobActions#follow(LivingEntity, boolean, float, float)
	 * @param entity the entity that will be followed
	 * @param queue whether this action should be added to the queue (true) or executed directly (false).
	 * @param radius the maximum distance (in blocks) this entity can keep away from the entity it will follow. The default value is 10.0 blocks
	 * @return {@link ControllableMobAction}
	 * @throws IllegalArgumentException when entity is the controlled entity itself or when the radius is smaller than 2.0
	 */
	public ControllableMobAction follow(LivingEntity entity, boolean queue, float radius) throws IllegalArgumentException;
	/**
	 * The entity will follow another entity, staying inside the outerRadius and outside the targetRadius, optionally this action is added to the queue before executing.
	 * Moving with {@link ControllableMobActions#moveTo(Location, boolean)} will override this action.
	 * When following (actually moving), the AI is blocked.
	 * 
	 * @param entity the entity that will be followed
	 * @param queue whether this action should be added to the queue (true) or executed directly (false).
	 * @param outerRadius the maximum distance (in blocks) this entity can keep away from the entity it will follow. The default value is 10.0 blocks
	 * @param targetRadius the target distance (in blocks) between this entity and the entity to follow when moving to it. The default value is 2.0 blocks
	 * @return {@link ControllableMobAction}
	 * @throws IllegalArgumentException when entity is the controlled entity itself or when the outerRadius is smaller than the targetRadius
	 */
	public ControllableMobAction follow(LivingEntity entity, boolean queue, float outerRadius, float targetRadius) throws IllegalArgumentException;
	
	/**
	 * Adds a callback to the action queue.
	 * The callback is invoked when all running actions and all actions added before this callback had been executed or cancelled.
	 * 
	 * @param runnable the callback instance
	 * @return {@link ControllableMobAction}
	 */
	public ControllableMobAction callback(Runnable runnable);
	
	
	/**
	 * Returns whether an action of the specified type is currently running.
	 * 
	 * @param type the type of which actions should be looked up
	 * @return true, when an action of the specified type is running, false otherwise.
	 */
	public boolean isActionRunning(ActionType type);
	
	/**
	 * Removes all actions located in the queue.
	 * All actions which should be executed after the current actions finish, are being removed immediately.
	 */
	public void clearActionQueue();
	/**
	 * Removes all actions which are currently executed but haven't finished yet.
	 * They are being removed immediately and marked as {@link de.ntcomputer.minecraft.controllablemobs.api.actions.ActionState#CANCELLED}.
	 */
	public void clearActionsRunning();
	/**
	 * Removes all running actions and all actions located in the queue.
	 * All manually added actions are stopped.
	 * This is a shorthand for {@link ControllableMobActions#clearActionQueue()}; {@link ControllableMobActions#clearActionsRunning()};
	 */
	public void clearActions();
	
	
	/**
	 * Gets the flag, whether actions should be queued automatically.
	 * 
	 * @return a boolean value describing whether actions should be automatically added to the queue (true) when they are added by a method which doesn't provide a <i>boolean queue</i> argument, or whether they should be executed immediately (false). Default value is false
	 */
	public boolean getDefaultQueuingFlag();
	/**
	 * Sets the flag, whether actions should be queued automatically.
	 * 
	 * @param defaultQueue a boolean value describing whether actions should be automatically added to the queue (true) when they are added by a method which doesn't provide a <i>boolean queue</i> argument, or whether they should be executed immediately (false).
	 */
	public void setDefaultQueuingFlag(boolean defaultQueue);
}
