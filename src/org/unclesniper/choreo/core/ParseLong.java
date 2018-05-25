package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;
import org.unclesniper.choreo.annotation.DefaultAdder;

@ElementClass("parseLong")
public class ParseLong implements ChoreoExpr<Long> {

	private ChoreoExpr<?> operand;

	private ChoreoExpr<Integer> radix;

	public ParseLong() {}

	public ParseLong(ChoreoExpr<?> operand) {
		this.operand = operand;
	}

	public ChoreoExpr<?> getOperand() {
		return operand;
	}

	@DefaultAdder
	public void setOperand(ChoreoExpr<?> operand) {
		this.operand = operand;
	}

	public void setOperand(String operand) {
		this.operand = new ConstantExpr<String>(operand);
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

	public Class<Long> getReturnType() {
		return Long.class;
	}

	public Long evaluate(RunContext context) throws ChoreoRunException {
		Object spec = ExprUtils.reduce(operand, context);
		Integer rspec = ExprUtils.reduce(radix, context);
		return Long.valueOf(spec == null ? null : spec.toString(),
				rspec == null || rspec <= 0 ? (Integer)10 : rspec);
	}

}
