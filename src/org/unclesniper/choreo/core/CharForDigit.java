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
		this.digit = digit == null ? null : new ConstantExpr<Integer>(digit);
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

	public Class<? extends Character> getReturnType() {
		return Character.class;
	}

	public Character evaluate(RunContext context) throws ChoreoRunException {
		Integer dspec = digit == null ? null : digit.evaluate(context);
		Integer rspec = radix == null ? null : radix.evaluate(context);
		return Character.forDigit(dspec, rspec == null || rspec <= 0 ? (Integer)10 : rspec);
	}

}
