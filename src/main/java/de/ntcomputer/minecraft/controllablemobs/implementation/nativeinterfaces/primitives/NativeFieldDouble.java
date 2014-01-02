package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives;

import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeFieldPrivate;

public class NativeFieldDouble<C> extends NativeFieldPrivate<C> {
	
	public NativeFieldDouble(final String fieldName) {
		super(fieldName);
	}
	
	public NativeFieldDouble(final Class<C> nativeClass, final String fieldName) {
		super(nativeClass, fieldName);
	}
	
	public double get(final C object) {
		try {
			return this.getField(object).getDouble(object);
		} catch(Throwable e) {
			this.handleException(e);
			return 0;
		}
	}
	
	public void set(final C object, final double value) {
		try {
			this.getField(object).setDouble(object, value);
		} catch(Throwable e) {
			this.handleException(e);
		}
	}

}
