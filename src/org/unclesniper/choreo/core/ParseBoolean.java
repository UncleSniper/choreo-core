package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("parseBoolean")
public class ParseBoolean implements ChoreoExpr<Boolean> {

	private ChoreoExpr<?> operand;

	public ParseBoolean() {}

	public ParseBoolean(ChoreoExpr<?> operand) {
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

	public Class<Boolean> getReturnType() {
		return Boolean.class;
	}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		Object spec = ExprUtils.reduce(operand, context);
		return Boolean.valueOf(spec == null ? null : spec.toString());
	}

}
