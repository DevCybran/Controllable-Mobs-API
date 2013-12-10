package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system;


public abstract class NativeInterfaceReflection<O extends Object> extends NativeInterface {
	/*private HashMap<String,Field> loadedFields = new HashMap<String,Field>();
	private HashMap<String,Method> loadedMethods = new HashMap<String,Method>();
	private final Class<O> nativeClass;
	
	protected NativeInterfaceReflection() {
		this.nativeClass = null;
	}
	
	protected NativeInterfaceReflection(final Class<O> nativeClass) {
		this.nativeClass = nativeClass;
	}
	
	protected Field getField(final Object object, final String fieldName) throws NoSuchFieldException, SecurityException {
		Field field = this.loadedFields.get(fieldName);
		if(field==null) {
			if(this.nativeClass==null) field = object.getClass().getDeclaredField(fieldName);
			else field = this.nativeClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			this.loadedFields.put(fieldName, field);
		}
		return field;
	}
	
	protected Method getMethod(final String identifier, final Object object, final String methodName, final Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException {
		Method method = this.loadedMethods.get(identifier);
		if(method==null) {
			if(this.nativeClass==null) method = object.getClass().getDeclaredMethod(methodName, parameterTypes);
			else method = this.nativeClass.getDeclaredMethod(methodName, parameterTypes);
			method.setAccessible(true);
			this.loadedMethods.put(identifier, method);
		}
		return method;
	}*/

}
