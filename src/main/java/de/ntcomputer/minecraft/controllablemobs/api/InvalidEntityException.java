package de.ntcomputer.minecraft.controllablemobs.api;

import org.bukkit.entity.LivingEntity;

/**
 * An exception thrown if the entity you want to control is null or has an uncontrollable implementation.
 * 
 * @author Cybran
 * @version v4
 *
 */
public class InvalidEntityException extends IllegalArgumentException {
	private static final long serialVersionUID = -1454685423522552612L;
	private final LivingEntity entity;

	public InvalidEntityException(String message, LivingEntity entity) {
		super(message);
		this.entity = entity;
	}
	
	/**
	 * @return the entity which you wanted to control
	 */
	public LivingEntity getInvalidEntity() {
		return this.entity;
	}

}
