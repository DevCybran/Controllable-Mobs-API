package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives;

import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeFieldPrivate;

public class NativeFieldBoolean<C extends Object> extends NativeFieldPrivate<C> {
	
	public NativeFieldBoolean(final String fieldName) {
		super(fieldName);
	}
	
	public NativeFieldBoolean(final Class<C> nativeClass, final String fieldName) {
		super(nativeClass, fieldName);
	}
	
	public boolean get(final C object) {
		try {
			return this.getField(object).getBoolean(object);
		} catch(Throwable e) {
			this.handleException(e);
			return false;
		}
	}
	
	public void set(final C object, final boolean value) {
		try {
			this.getField(object).setBoolean(object, value);
		} catch(Throwable e) {
			this.handleException(e);
		}
	}

}
