package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server;

import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityTypes;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeMethodPublic;

public final class NmsEntityTypes {
	public final GetEntityClassById METHOD_GETCLASSBYID = new GetEntityClassById();

	public final class GetEntityClassById extends NativeMethodPublic {
		public Class<? extends Entity> invoke(int entityTypeId) {
			try {
				return EntityTypes.a(entityTypeId);
			} catch(Throwable e) {
				this.handleException(e);
				return null;
			}
		}
	}

}
