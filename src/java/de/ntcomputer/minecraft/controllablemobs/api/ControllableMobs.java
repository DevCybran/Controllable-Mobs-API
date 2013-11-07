package de.ntcomputer.minecraft.controllablemobs.api;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import net.minecraft.server.v1_6_R3.EntityInsentient;
import net.minecraft.server.v1_6_R3.EntityLiving;

import org.bukkit.craftbukkit.v1_6_R3.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.PluginClassLoader;

import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

/**
 * This is a static class which lets you retrieve instances of {@link ControllableMob}.
 * 
 * @author Cybran
 * @version v5
 * 
 */
public final class ControllableMobs {
	private static final Map<LivingEntity,ControllableMob<?>> entities = new HashMap<LivingEntity,ControllableMob<?>>();
	
	static {
		onLoad();
	}
	
	private static void onLoad() {
		try {
			PluginClassLoader cl = (PluginClassLoader) ControllableMobs.class.getClassLoader();
			Plugin[] plugins = NativeInterfaces.JAVAPLUGINLOADER.FIELD_SERVER.get(NativeInterfaces.PLUGINCLASSLOADER.FIELD_LOADER.get(cl)).getPluginManager().getPlugins();
			for(Plugin plugin: plugins) {
				if(plugin.getClass().getClassLoader()==cl) {
					if(plugin.getName().equals("ControllableMobsAPI")) {
						plugin.getLogger().info("initialized API plugin");
					} else {
						plugin.getLogger().info("initialized Controllable Mobs API library");
					}
					return;
				}
			}
		} catch(Throwable t) {}
		Logger.getLogger("Minecraft").info("[ControllableMobsAPI] initialized by an unknown component");
	}
	
	private ControllableMobs() {
		throw new AssertionError();
	}
	
	
	/**
	 * Retrieves whether the specified entity has already been assigned.
	 * 
	 * @param entity to check for a {@link ControllableMob} instance
	 * @return whether the entity has been assigned ({@link ControllableMob} instance is available)
	 * @deprecated use {@link #isUnderControl(LivingEntity)} instead
	 */
	@Deprecated
	public static boolean isAssigned(LivingEntity entity) {
		return isUnderControl(entity);
	}
	
	/**
	 * Retrieves a {@link ControllableMob} instance for an entity that had been assigned previously.
	 * 
	 * @param entity which is already under control
	 * @return the {@link ControllableMob} you can use to control the entity
	 * @throws IllegalStateException when the entity has not been assigned yet
	 * @deprecated use {@link #getControl(LivingEntity)} instead
	 */
	@Deprecated
	public static <E extends LivingEntity> ControllableMob<E> get(E entity) throws IllegalStateException {
		return getControl(entity);
	}
	
	/**
	 * Simply retrieves a {@link ControllableMob} instance if the passed entity has been assigned, or assigns the entity if not.
	 * Combines {@link ControllableMobs#get(LivingEntity)} and {@link ControllableMobs#assign(LivingEntity)}.
	 * 
	 * @param entity which you want to control
	 * @return the {@link ControllableMob} you can use to control the entity
	 * @throws InvalidEntityException when the entity is null or can't be controlled
	 * @deprecated use {@link #getOrPutUnderControl(LivingEntity)} instead
	 */
	@Deprecated
	public static <E extends LivingEntity> ControllableMob<E> getOrAssign(E entity) throws InvalidEntityException {
		return getOrPutUnderControl(entity);
	}
	
	/**
	 * Simply retrieves a {@link ControllableMob} instance if the passed entity has been assigned, or assigns the entity if not.
	 * Combines {@link ControllableMobs#get(LivingEntity)} and {@link ControllableMobs#assign(LivingEntity, boolean)}
	 * Clearing the AI is only being performed when clearAI is true and the entity is being assigned for the first time.
	 * 
	 * @param entity which you want to control
	 * @param clearAI a boolean indicating whether default behaviors should be removed (true) or not (false).
	 * @return the {@link ControllableMob} you can use to control the entity
	 * @throws InvalidEntityException when the entity is null or can't be controlled
	 * @deprecated use {@link #getOrPutUnderControl(LivingEntity, boolean)} instead
	 */
	@Deprecated
	public static <E extends LivingEntity> ControllableMob<E> getOrAssign(E entity, boolean clearAI) throws InvalidEntityException {
		return getOrPutUnderControl(entity, clearAI);
	}
	
	/**
	 * Simply puts the entity under your control.
	 * Will not change any default behaviors, the entity will continue to act normally.
	 * 
	 * @param entity an instance of a subclass of LivingEntity - the entity you want to control
	 * @return the {@link ControllableMob} you can use to control the entity
	 * @throws InvalidEntityException when the entity is null or can't be controlled
	 * @throws IllegalStateException when the entity is already being controlled
	 * @deprecated use {@link #putUnderControl(LivingEntity)} instead
	 */
	@Deprecated
	public static <E extends LivingEntity> ControllableMob<E> assign(E entity) throws IllegalStateException, InvalidEntityException {
		return putUnderControl(entity);
	}
	
	/**
	 * Simply puts the entity under your control, optionally clearing its AI.
	 * If you decide to clear its AI, the entity will stop moving and attacking and stand still until you order it to execute any actions.
	 * 
	 * @param entity entity an instance of a subclass of LivingEntity - the entity you want to control
	 * @param clearAI a boolean indicating whether default behaviors should be removed (true) or not (false)
	 * @return the {@link ControllableMob} you can use to control the entity
	 * @throws InvalidEntityException when the entity is null or can't be controlled
	 * @throws IllegalStateException when the entity is already being controlled
	 * @deprecated use {@link #putUnderControl(LivingEntity, boolean)} instead
	 */
	@Deprecated
	public static <E extends LivingEntity> ControllableMob<E> assign(E entity, boolean clearAI) throws IllegalStateException, InvalidEntityException {
		return putUnderControl(entity, clearAI);
	}
	
	/**
	 * Releases the entity from control and restores default behaviors.
	 * All actions will be stopped immediately, all custom AI behaviors will be removed and default attributes and behaviors will be restored. Frees memory.
	 * After having this method called, nothing will show that the entity was once controlled.
	 * 
	 * @see ControllableMobs#unassign(ControllableMob, boolean)
	 * @param controllableMob the controller which should be unassigned
	 * @throws IllegalStateException when the controllableMob is already unassigned
	 * @deprecated use {@link #releaseControl(ControllableMob)} instead
	 */
	@Deprecated
	public static void unassign(ControllableMob<?> controllableMob) throws IllegalStateException {
		releaseControl(controllableMob);
	}
	
	/**
	 * Releases the entity from control and restores default behaviors.
	 * All actions will be stopped immediately, all custom AI behaviors will be removed and default behaviors (and attributes, if specified) will be restored. Frees memory.
	 * After having this method called, nothing will show that the entity was once controlled.
	 * 
	 * @param controllableMob the controller which should be unassigned
	 * @param resetAttributes whether to also reset attributes (movement speed, attack damage, etc.) to their default values. Removes custom modifiers. Default ist true.
	 * @throws IllegalStateException when the controllableMob is already unassigned
	 * @deprecated use {@link #releaseControl(ControllableMob, boolean)} instead
	 */
	@Deprecated
	public static void unassign(ControllableMob<?> controllableMob, boolean resetAttributes) throws IllegalStateException {
		releaseControl(controllableMob, resetAttributes);
	}
	
	
	
	
	
	/**
	 * Retrieves whether the specified entity has already been assigned.
	 * 
	 * @param entity to check for a {@link ControllableMob} instance
	 * @return whether the entity has been put under control ({@link ControllableMob} instance is available)
	 */
	public static boolean isUnderControl(LivingEntity entity) {
		return entities.containsKey(entity);
	}
	
	
	/**
	 * Retrieves a {@link ControllableMob} instance for an entity that had been put under control previously.
	 * 
	 * @param entity which is already under control
	 * @return the {@link ControllableMob} you can use to control the entity
	 * @throws IllegalStateException when the entity has not been put under control yet
	 */
	@SuppressWarnings("unchecked")
	public static <E extends LivingEntity> ControllableMob<E> getControl(E entity) throws IllegalStateException {
		if(!entities.containsKey(entity)) throw new IllegalStateException("entity "+entity.toString()+" is not put under control yet");
		return (ControllableMob<E>) entities.get(entity);
	}
	
	
	/**
	 * Simply retrieves a {@link ControllableMob} instance if the passed entity has been put under control, or puts it under control.
	 * Combines {@link ControllableMobs#getControl(LivingEntity)} and {@link ControllableMobs#putUnderControl(LivingEntity)}.
	 * 
	 * @param entity which you want to control
	 * @return the {@link ControllableMob} you can use to control the entity
	 * @throws InvalidEntityException when the entity is null or can't be controlled
	 */
	@SuppressWarnings("unchecked")
	public static <E extends LivingEntity> ControllableMob<E> getOrPutUnderControl(E entity) throws InvalidEntityException {
		if(entities.containsKey(entity)) return (ControllableMob<E>) entities.get(entity);
		else return putUnderControl(entity);
	}
	
	
	/**
	 * Simply retrieves a {@link ControllableMob} instance if the passed entity has been put under control, or puts it under control.
	 * Combines {@link ControllableMobs#getControl(LivingEntity)} and {@link ControllableMobs#putUnderControl(LivingEntity, boolean)}
	 * Clearing the AI is only being performed when clearAI is true and the entity is being assigned for the first time.
	 * 
	 * @param entity which you want to control
	 * @param clearAI a boolean indicating whether default behaviors should be removed (true) or not (false).
	 * @return the {@link ControllableMob} you can use to control the entity
	 * @throws InvalidEntityException when the entity is null or can't be controlled
	 */
	@SuppressWarnings("unchecked")
	public static <E extends LivingEntity> ControllableMob<E> getOrPutUnderControl(E entity, boolean clearAI) throws InvalidEntityException {
		if(entities.containsKey(entity)) return (ControllableMob<E>) entities.get(entity);
		else return putUnderControl(entity, clearAI);
	}
	
	
	/**
	 * Simply puts the entity under your control.
	 * Will not change any default behaviors, the entity will continue to act normally.
	 * 
	 * @param entity an instance of a subclass of LivingEntity - the entity you want to control
	 * @return the {@link ControllableMob} you can use to control the entity
	 * @throws InvalidEntityException when the entity is null or can't be controlled
	 * @throws IllegalStateException when the entity is already being controlled
	 */
	public static <E extends LivingEntity> ControllableMob<E> putUnderControl(E entity) throws IllegalStateException, InvalidEntityException {
		return putUnderControl(entity, false);
	}
	
	
	/**
	 * Simply puts the entity under your control, optionally clearing its AI.
	 * If you decide to clear its AI, the entity will stop moving and attacking and stand still until you order it to execute any actions.
	 * 
	 * @param entity an instance of a subclass of LivingEntity - the entity you want to control
	 * @param clearAI a boolean indicating whether default behaviors should be removed (true) or not (false)
	 * @return the {@link ControllableMob} you can use to control the entity
	 * @throws InvalidEntityException when the entity is null or can't be controlled
	 * @throws IllegalStateException when the entity is already being controlled
	 */
	public static <E extends LivingEntity> ControllableMob<E> putUnderControl(E entity, boolean clearAI) throws IllegalStateException, InvalidEntityException {
		if(entity==null) throw new InvalidEntityException("entity must not be null",entity);
		EntityLiving notchEntity = ((CraftLivingEntity) entity).getHandle();
		if(!(notchEntity instanceof EntityInsentient)) throw new InvalidEntityException("the entity "+entity.toString()+" can't be controlled",entity);
		if(entities.containsKey(entity)) throw new IllegalStateException("entity "+entity.toString()+" is already a controlled entity");
		
		ControllableMob<E> controllableMob = new CraftControllableMob<E>(entity, (EntityInsentient) notchEntity);
		if(clearAI) controllableMob.getAI().clear();
		
		entities.put(entity,controllableMob);
		return controllableMob;
	}
	
	
	/**
	 * Releases the entity from control and restores default behaviors.
	 * All actions will be stopped immediately, all custom AI behaviors will be removed and default attributes and behaviors will be restored. Frees memory.
	 * After having this method called, nothing will show that the entity was once controlled.
	 * 
	 * @see ControllableMobs#unassign(ControllableMob, boolean)
	 * @param controllableMob the controller which should be unassigned
	 * @throws IllegalStateException when the controllableMob is already unassigned
	 */
	public static void releaseControl(ControllableMob<?> controllableMob) throws IllegalStateException {
		releaseControl(controllableMob, true);
	}
	
	
	/**
	 * Releases the entity from control and restores default behaviors.
	 * All actions will be stopped immediately, all custom AI behaviors will be removed and default behaviors (and attributes, if specified) will be restored. Frees memory.
	 * After having this method called, nothing will show that the entity was once controlled.
	 * 
	 * @param controllableMob the controller which should be unassigned
	 * @param resetAttributes whether to also reset attributes (movement speed, attack damage, etc.) to their default values. Removes custom modifiers. Default ist true.
	 * @throws IllegalStateException when the controllableMob is already unassigned
	 */
	public static void releaseControl(ControllableMob<?> controllableMob, boolean resetAttributes) throws IllegalStateException {
		if(!entities.containsKey(controllableMob.getEntity())) throw new IllegalStateException("entity "+controllableMob.toString()+" is already released from control");
		entities.remove(controllableMob.getEntity());
		((CraftControllableMob<?>) controllableMob).unassign(resetAttributes);
	}

}
