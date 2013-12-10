package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives;

import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeFieldPrivate;

public class NativeFieldInt<C extends Object> extends NativeFieldPrivate<C> {
	
	public NativeFieldInt(final String fieldName) {
		super(fieldName);
	}
	
	public NativeFieldInt(final Class<C> nativeClass, final String fieldName) {
		super(nativeClass, fieldName);
	}
	
	public int get(final C object) {
		try {
			return this.getField(object).getInt(object);
		} catch(Throwable e) {
			this.handleException(e);
			return 0;
		}
	}
	
	public void set(final C object, final int value) {
		try {
			this.getField(object).setInt(object, value);
		} catch(Throwable e) {
			this.handleException(e);
		}
	}

}
