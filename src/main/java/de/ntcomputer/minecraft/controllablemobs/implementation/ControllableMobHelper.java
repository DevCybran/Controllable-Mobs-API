package de.ntcomputer.minecraft.controllablemobs.implementation;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

public class ControllableMobHelper {
	
	@SuppressWarnings("deprecation")
	public static Class<? extends net.minecraft.server.v1_7_R4.Entity> getNmsEntityClass(final Class<? extends LivingEntity> entityClass) throws IllegalArgumentException {
		if(entityClass==null) throw new IllegalArgumentException("entityClass must not be null");
		if(entityClass==HumanEntity.class || entityClass==Player.class) return net.minecraft.server.v1_7_R4.EntityHuman.class;
		if(entityClass==Monster.class) return net.minecraft.server.v1_7_R4.EntityMonster.class;
		if(entityClass==Creature.class) return net.minecraft.server.v1_7_R4.EntityCreature.class;
		if(entityClass==Animals.class) return net.minecraft.server.v1_7_R4.EntityAnimal.class;
		if(entityClass==LivingEntity.class) return net.minecraft.server.v1_7_R4.EntityLiving.class;
		
		for(EntityType entityType: EntityType.values()) {
			if(entityType.getEntityClass()==null || entityType.getTypeId()==-1) continue;
			if(entityClass.equals(entityType.getEntityClass())) {
				return getNmsEntityClass(entityType);
			}
		}
		
		throw new IllegalArgumentException("Class "+entityClass.getSimpleName()+" is not resolvable to an EntityType");
	}
	
	@SuppressWarnings("deprecation")
	public static Class<? extends net.minecraft.server.v1_7_R4.Entity> getNmsEntityClass(final EntityType entityType) throws IllegalArgumentException {
		if(entityType==null) throw new IllegalArgumentException("EntityType must not be null");
		if(entityType==EntityType.PLAYER) return net.minecraft.server.v1_7_R4.EntityHuman.class;
		
		try {
			final Class<? extends net.minecraft.server.v1_7_R4.Entity> entityClass = NativeInterfaces.ENTITYTYPES.METHOD_GETCLASSBYID.invoke(entityType.getTypeId());
			if(entityClass==null) throw new IllegalArgumentException("EntityType "+entityType+" is not resolvable to a net.minecraft Class");
			return entityClass;
		} catch(Exception e) {
			throw new IllegalArgumentException("EntityType "+entityType+" is not resolvable to a net.minecraft Class", e);
		}
	}

}
