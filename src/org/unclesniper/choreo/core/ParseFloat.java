package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("parseFloat")
public class ParseFloat implements ChoreoExpr<Float> {

	private ChoreoExpr<?> operand;

	public ParseFloat() {}

	public ParseFloat(ChoreoExpr<?> operand) {
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

	public Class<? extends Float> getReturnType() {
		return Float.class;
	}

	public Float evaluate(RunContext context) throws ChoreoRunException {
		Object spec = operand == null ? null : operand.evaluate(context);
		return Float.valueOf(spec == null ? null : spec.toString());
	}

}
