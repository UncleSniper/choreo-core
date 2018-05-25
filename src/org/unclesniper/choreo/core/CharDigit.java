package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("charDigit")
public class CharDigit implements ChoreoExpr<Integer> {

	private ChoreoExpr<Character> character;

	private ChoreoExpr<Integer> radix;

	public CharDigit() {}

	public CharDigit(ChoreoExpr<Character> character) {
		this.character = ExprUtils.ensureReturnType(character, Character.class);
	}

	public ChoreoExpr<Character> getCharacter() {
		return character;
	}

	@DefaultAdder
	public void setCharacter(ChoreoExpr<Character> character) {
		this.character = ExprUtils.ensureReturnType(character, Character.class);
	}

	public void setCharacter(Character character) {
		this.character = ConstantExpr.from(character);
	}

	public ChoreoExpr<Integer> getRadix() {
		return radix;
	}

	public void setRadix(ChoreoExpr<Integer> radix) {
		this.radix = ExprUtils.ensureReturnType(radix, Integer.class);
	}

	public void setRadix(Integer radix) {
		this.radix = new ConstantExpr<Integer>(radix);
	}

	public Class<Integer> getReturnType() {
		return Integer.class;
	}

	public Integer evaluate(RunContext context) throws ChoreoRunException {
		Character cspec = ExprUtils.reduce(character, context);
		Integer rspec = ExprUtils.reduce(radix, context);
		return Character.digit(cspec, rspec == null || rspec <= 0 ? (Integer)10 : rspec);
	}

}
