package de.ntcomputer.minecraft.controllablemobs.api.attributes;

public enum ModifyOperation {
	ADD_TO_BASIS_VALUE(0),
	
	ADD_MULTIPLIED_COMBINED_VALUE(1),
	
	MULTIPLY_FINAL_VALUE(2);
	
	private final int intType;
	
	private ModifyOperation(int intType) {
		this.intType = intType;
	}
	
	public int getIntType() {
		return this.intType;
	}
	
	public static ModifyOperation byIntType(int intType) {
		switch(intType) {
		case 0: return ADD_TO_BASIS_VALUE;
		case 1: return ADD_MULTIPLIED_COMBINED_VALUE;
		case 2: return MULTIPLY_FINAL_VALUE;
		default: return null;
		}
	}
	
}
