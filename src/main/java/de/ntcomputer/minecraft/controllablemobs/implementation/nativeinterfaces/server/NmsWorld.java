package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server;

import java.util.Collections;
import java.util.List;

import net.minecraft.server.v1_7_R1.AxisAlignedBB;
import net.minecraft.server.v1_7_R1.Entity;
import net.minecraft.server.v1_7_R1.IEntitySelector;
import net.minecraft.server.v1_7_R1.World;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeMethodPublic;

public class NmsWorld {
	public final SearchEntities METHOD_SEARCHENTITIES = new SearchEntities();
	
	public class SearchEntities extends NativeMethodPublic {
		@SuppressWarnings("unchecked")
		public <E extends Entity> List<E> invoke(World world, Class<E> classLookingFor, AxisAlignedBB area, IEntitySelector entitySelector) {
			try {
				return world.a(classLookingFor, area, entitySelector);
			} catch(Throwable e) {
				this.handleException(e);
				return Collections.emptyList();
			}
		}
	}

}
