package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes;

import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeMethodPublic;
import net.minecraft.server.v1_6_R2.AttributeModifiable;
import net.minecraft.server.v1_6_R2.IAttribute;

public final class NmsAttributeModifiable {
	public final GetTemplate METHOD_GETATTRIBUTETEMPLATE = new GetTemplate();
	public final GetBasisValue METHOD_GETBASISVALUE = new GetBasisValue();
	
	public final class GetTemplate extends NativeMethodPublic {
		public IAttribute invoke(AttributeModifiable attribute) {
			try {
				return attribute.a();
			} catch(Throwable t) {
				this.handleException(t);
				return null;
			}
		}
	}
	
	public final class GetBasisValue extends NativeMethodPublic {
		public double invoke(AttributeModifiable attribute) {
			try {
				return attribute.b();
			} catch(Throwable t) {
				this.handleException(t);
				return 0;
			}
			
		}
	}

}
