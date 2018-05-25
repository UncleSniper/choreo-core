package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;
import org.unclesniper.choreo.annotation.DefaultAdder;

@ElementClass("parseDouble")
public class ParseDouble implements ChoreoExpr<Double> {

	private ChoreoExpr<?> operand;

	public ParseDouble() {}

	public ParseDouble(ChoreoExpr<?> operand) {
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

	public Class<Double> getReturnType() {
		return Double.class;
	}

	public Double evaluate(RunContext context) throws ChoreoRunException {
		Object spec = ExprUtils.reduce(operand, context);
		return Double.valueOf(spec == null ? null : spec.toString());
	}

}
