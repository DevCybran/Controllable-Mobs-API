package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes;

import net.minecraft.server.v1_6_R3.IAttribute;
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
