package de.ntcomputer.minecraft.controllablemobs.api;

import org.bukkit.entity.LivingEntity;

/**
 * The "main" interface to control a mob.
 * This interface will return objects to control the properties and AI of the underlying entity or assign actions.
 * You can retrieve an instance of ControllableMob by using the {@link ControllableMobs} class.
 * 
 * @author Cybran
 * @version v2
 *
 * @param <C> the bukkit entity class of the entity which is being controlled.
 */
public interface ControllableMob<C extends LivingEntity> {
	
	/**
	 * Gets the entity which is being controlled.
	 * This will make things easier for you, because you don't have to keep a reference to the controller and the entity itself in memory.
	 * @return the LivingEntity which is being controlled.
	 */
	public C getEntity();
	
	/**
	 * @return the property management object for this ControllableMob.
	 */
	public ControllableMobProperties getProperties();
	
	/**
	 * @return the AI management object for this ControllableMob.
	 */
	public ControllableMobAI getAI();
	
	/**
	 * @return the action management object for this ControllableMob.
	 */
	public ControllableMobActions getActions();

}
