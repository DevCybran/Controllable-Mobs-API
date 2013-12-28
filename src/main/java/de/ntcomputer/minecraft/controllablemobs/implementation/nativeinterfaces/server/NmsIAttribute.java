package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server;

import net.minecraft.server.v1_7_R1.IAttribute;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeMethodPublic;

public final class NmsIAttribute {
	public final GetName METHOD_GETNAME = new GetName();

	public final class GetName extends NativeMethodPublic {
		public String invoke(IAttribute attribute) {
			try {
				return attribute.a();
			} catch(Throwable t) {
				this.handleException(t);
				return null;
			}
		}
	}
	
}
