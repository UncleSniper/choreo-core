package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("charForDigit")
public class CharForDigit implements ChoreoExpr<Character> {

	private ChoreoExpr<Integer> digit;

	private ChoreoExpr<Integer> radix;

	public CharForDigit() {}

	public CharForDigit(ChoreoExpr<Integer> digit) {
		this.digit = ExprUtils.ensureReturnType(digit, Integer.class);
	}

	public ChoreoExpr<Integer> getDigit() {
		return digit;
	}

	@DefaultAdder
	public void setDigit(ChoreoExpr<Integer> digit) {
		this.digit = ExprUtils.ensureReturnType(digit, Integer.class);
	}

	public void setDigit(Integer digit) {
		this.digit = ConstantExpr.from(digit);
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

	public Class<Character> getReturnType() {
		return Character.class;
	}

	public Character evaluate(RunContext context) throws ChoreoRunException {
		Integer dspec = ExprUtils.reduce(digit, context);
		Integer rspec = ExprUtils.reduce(radix, context);
		return Character.forDigit(dspec, rspec == null || rspec <= 0 ? (Integer)10 : rspec);
	}

}
