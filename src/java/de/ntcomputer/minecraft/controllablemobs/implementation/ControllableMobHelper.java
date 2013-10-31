package de.ntcomputer.minecraft.controllablemobs.implementation;

import java.lang.reflect.Field;
import java.util.Map;

import net.minecraft.server.v1_6_R3.EntityTypes;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

public class ControllableMobHelper {
	
	public static Class<? extends net.minecraft.server.v1_6_R3.Entity> getNotchEntityClass(final Class<? extends Entity> entityClass) throws IllegalArgumentException {
		if(entityClass==null) throw new IllegalArgumentException("entityClass must not be null");
		if(entityClass==HumanEntity.class || entityClass==Player.class) return net.minecraft.server.v1_6_R3.EntityHuman.class;
		if(entityClass==Monster.class) return net.minecraft.server.v1_6_R3.EntityMonster.class;
		if(entityClass==Creature.class) return net.minecraft.server.v1_6_R3.EntityCreature.class;
		if(entityClass==Animals.class) return net.minecraft.server.v1_6_R3.EntityAnimal.class;
		if(entityClass==LivingEntity.class) return net.minecraft.server.v1_6_R3.EntityLiving.class;
		
		
		for(EntityType entityType: EntityType.values()) {
			if(entityType.getEntityClass()==null || entityType.getTypeId()==-1) continue;
			if(entityClass.equals(entityType.getEntityClass())) {
				return getNotchEntityClass(entityType);
			}
		}
		
		throw new IllegalArgumentException("Class "+entityClass.getSimpleName()+" is not resolvable to an EntityType");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Class<? extends net.minecraft.server.v1_6_R3.Entity> getNotchEntityClass(final EntityType entityType) throws IllegalArgumentException {
		if(entityType==null) throw new IllegalArgumentException("EntityType must not be null");
		if(entityType==EntityType.PLAYER) return net.minecraft.server.v1_6_R3.EntityHuman.class;
		
		try {
			Field mapField = EntityTypes.class.getDeclaredField("d");
			mapField.setAccessible(true);
			final Class<? extends net.minecraft.server.v1_6_R3.Entity> entityClass = (Class<? extends net.minecraft.server.v1_6_R3.Entity>) ((Map) mapField.get(null)).get(Integer.valueOf(entityType.getTypeId()));
			if(entityClass==null) throw new IllegalArgumentException("EntityType "+entityType.name()+" is not resolvable to a net.minecraft Class");
			return entityClass;
		} catch(Exception e) {
			throw new IllegalArgumentException("EntityType "+entityType.name()+" is not resolvable to a net.minecraft Class", e);
		}
	}

}
