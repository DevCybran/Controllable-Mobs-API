package de.ntcomputer.minecraft.controllablemobs.api.attributes;

/**
 * All possible ways how an {@link AttributeModifier} can modify an {@link Attribute}.
 * 
 * @author Cybran
 * @version v4
 *
 */
public enum ModifyOperation {
	/**
	 * The first applied operation. Simply adds the modifier value to the basis value.
	 * The result of the first operation will look like this: result1 = basisValue + modifierValue1 + modifierValue2 + ... + modifierValueN 
	 */
	ADD_TO_BASIS_VALUE(0),
	
	/**
	 * The second applied operation. Multiplies the first operation's result and adds it up to a new result buffer.
	 * The result of the second operation will look like this: result2 = modifierValue1 * result1 + modifierValue2 * result1 + ... + modifierValueN * result1
	 */
	ADD_MULTIPLIED_COMBINED_VALUE(1),
	
	/**
	 * The third applied operation. Multiplies the final result.
	 * The result of the third operation will look like this: result3 = result2 * modifierValue1 * modifierValue2 * ... * modifierValueN
	 */
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
