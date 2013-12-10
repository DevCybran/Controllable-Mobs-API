package de.ntcomputer.minecraft.controllablemobs.api.ai;

import org.bukkit.entity.Entity;

/**
 * An interface to filter entities based on custom conditions.
 * 
 * @author Cybran
 * @version 52
 *
 */
public interface EntityFilter {
	/**
	 * Method to retrieve the validity of an entity.
	 * Implement your custom conditions.
	 * 
	 * @param entity the entity which is going to be tested
	 * @return true, if the entity should be accepted, or false otherwise
	 */
	public boolean isEntityValid(Entity entity);
}
