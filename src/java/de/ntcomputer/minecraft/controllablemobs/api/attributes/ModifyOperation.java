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
	
}
