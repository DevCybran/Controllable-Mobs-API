package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives;

import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeFieldPrivate;

public class NativeFieldFloat<C extends Object> extends NativeFieldPrivate<C> {
	
	public NativeFieldFloat(final String fieldName) {
		super(fieldName);
	}
	
	public NativeFieldFloat(final Class<C> nativeClass, final String fieldName) {
		super(nativeClass, fieldName);
	}
	
	public float get(final C object) {
		try {
			return this.getField(object).getFloat(object);
		} catch(Throwable e) {
			this.handleException(e);
			return 0;
		}
	}
	
	public void set(final C object, final float value) {
		try {
			this.getField(object).setFloat(object, value);
		} catch(Throwable e) {
			this.handleException(e);
		}
	}

}
