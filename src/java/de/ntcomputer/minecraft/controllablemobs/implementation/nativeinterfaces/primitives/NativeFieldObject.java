package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives;

import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeFieldPrivate;

public class NativeFieldObject<C extends Object, R extends Object> extends NativeFieldPrivate<C> {

	public NativeFieldObject(final String fieldName) {
		super(fieldName);
	}
	
	public NativeFieldObject(final Class<C> nativeClass, final String fieldName) {
		super(nativeClass, fieldName);
	}
	
	@SuppressWarnings("unchecked")
	public R get(final C object) {
		try {
			return (R) this.getField(object).get(object);
		} catch(Throwable e) {
			this.handleException(e);
			return null;
		}
	}
	
	public void set(final C object, final R value) {
		try {
			this.getField(object).set(object, value);
		} catch(Throwable e) {
			this.handleException(e);
		}
	}
	
}
