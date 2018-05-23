package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("parseByte")
public class ParseByte implements ChoreoExpr<Byte> {

	private ChoreoExpr<?> operand;

	private ChoreoExpr<Integer> radix;

	public ParseByte() {}

	public ParseByte(ChoreoExpr<?> operand) {
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

	public Class<? extends Byte> getReturnType() {
		return Byte.class;
	}

	public Byte evaluate(RunContext context) throws ChoreoRunException {
		Object spec = operand == null ? null : operand.evaluate(context);
		Integer rspec = radix == null ? null : radix.evaluate(context);
		return Byte.valueOf(spec == null ? null : spec.toString(),
				rspec == null || rspec <= 0 ? (Integer)10 : rspec);
	}

}
