package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes;

import java.util.UUID;

import net.minecraft.server.v1_6_R3.AttributeModifier;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeMethodPublic;

public final class NmsAttributeModifier {
	public final GetName METHOD_GETNAME = new GetName();
	public final GetOperation METHOD_GETOPERATION = new GetOperation();
	public final GetAmount METHOD_GETAMOUNT = new GetAmount();
	public final GetUUID METHOD_GETUUID = new GetUUID();

	public final class GetName extends NativeMethodPublic {
		public String invoke(AttributeModifier modifier) {
			try {
				return modifier.b();
			} catch(Throwable t) {
				this.handleException(t);
				return null;
			}
		}
	}
	
	public final class GetOperation extends NativeMethodPublic {
		public int invoke(AttributeModifier modifier) {
			try {
				return modifier.c();
			} catch(Throwable t) {
				this.handleException(t);
				return -1;
			}
		}
	}
	
	public final class GetAmount extends NativeMethodPublic {
		public double invoke(AttributeModifier modifier) {
			try {
				return modifier.d();
			} catch(Throwable t) {
				this.handleException(t);
				return 0;
			}
		}
	}
	
	public final class GetUUID extends NativeMethodPublic {
		public UUID invoke(AttributeModifier modifier) {
			try {
				return modifier.a();
			} catch(Throwable t) {
				this.handleException(t);
				return null;
			}
		}
	}

}
