package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import net.minecraft.server.v1_6_R3.AttributeModifiable;
import net.minecraft.server.v1_6_R3.AttributeModifier;
import net.minecraft.server.v1_6_R3.IAttribute;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeMethodPublic;

public final class NmsAttributeModifiable {
	public final GetTemplate METHOD_GETATTRIBUTETEMPLATE = new GetTemplate();
	public final GetBasisValue METHOD_GETBASISVALUE = new GetBasisValue();
	public final GetModifiers METHOD_GETMODIFIERS = new GetModifiers();
	public final GetModifiersByType METHOD_GETMODIFIERSBYTYPE = new GetModifiersByType();
	public final AddModifier METHOD_ADDMODIFIER = new AddModifier();
	public final RemoveModifier METHOD_REMOVEMODIFIER = new RemoveModifier();
	public final GetModifierByUUID METHOD_GETMODIFIERBYUUID = new GetModifierByUUID();
	
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
	
	public final class GetModifiers extends NativeMethodPublic {
		@SuppressWarnings("unchecked")
		public Collection<AttributeModifier> invoke(AttributeModifiable attribute) {
			try {
				return attribute.c();
			} catch(Throwable t) {
				this.handleException(t);
				return Collections.EMPTY_SET;
			}
		}
	}
	
	public final class GetModifiersByType extends NativeMethodPublic {
		@SuppressWarnings("unchecked")
		public Collection<AttributeModifier> invoke(AttributeModifiable attribute, int type) {
			try {
				return attribute.a(type);
			} catch(Throwable t) {
				this.handleException(t);
				return Collections.EMPTY_SET;
			}
		}
	}
	
	public final class AddModifier extends NativeMethodPublic {
		public void invoke(AttributeModifiable attribute, AttributeModifier modifier) throws IllegalArgumentException {
			try {
				attribute.a(modifier);
			} catch(IllegalArgumentException e) {
				throw e;
			} catch(Throwable t) {
				this.handleException(t);
			}
		}
	}
	
	public final class RemoveModifier extends NativeMethodPublic {
		public void invoke(AttributeModifiable attribute, AttributeModifier modifier) throws IllegalArgumentException {
			try {
				attribute.b(modifier);
			} catch(IllegalArgumentException e) {
				throw e;
			} catch(Throwable t) {
				this.handleException(t);
			}
		}
	}
	
	public final class GetModifierByUUID extends NativeMethodPublic {
		public AttributeModifier invoke(AttributeModifiable attribute, UUID uuid) throws IllegalArgumentException {
			try {
				return attribute.a(uuid);
			} catch(Throwable t) {
				this.handleException(t);
				return null;
			}
		}
	}

}
