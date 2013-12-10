package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system;

import java.lang.reflect.Field;


public abstract class NativeFieldPrivate<C extends Object> extends NativeField {
	private Field field;
	private final Class<C> nativeClass;
	private final String fieldName;

	protected NativeFieldPrivate(final String fieldName) {
		this.nativeClass = null;
		this.fieldName = fieldName;
	}
	
	protected NativeFieldPrivate(final Class<C> nativeClass, final String fieldName) {
		this.nativeClass = nativeClass;
		this.fieldName = fieldName;
	}
	
	
	@Override
	protected String getParameters() {
		return "class "+(this.nativeClass==null?"null":this.nativeClass.getSimpleName())+", field "+this.fieldName;
	}
	
	protected Field getField(final C object) throws NoSuchFieldException, SecurityException {
		if(this.field==null) {
			if(this.nativeClass!=null) this.field = this.nativeClass.getDeclaredField(this.fieldName);
			else this.field = object.getClass().getDeclaredField(this.fieldName);
			this.field.setAccessible(true);
		}
		return this.field;
	}

}
