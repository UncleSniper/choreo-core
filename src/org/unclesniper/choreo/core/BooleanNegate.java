package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("logicalNot")
public class BooleanNegate implements ChoreoExpr<Boolean> {

	private ChoreoExpr<Boolean> operand;

	public BooleanNegate() {}

	public BooleanNegate(ChoreoExpr<Boolean> operand) {
		this.operand = ExprUtils.ensureReturnType(operand, Boolean.class);
	}

	public BooleanNegate(Boolean operand) {
		this.operand = new ConstantExpr<Boolean>(operand);
	}

	public ChoreoExpr<Boolean> getOperand() {
		return operand;
	}

	@DefaultAdder
	public void setOperand(ChoreoExpr<Boolean> operand) {
		this.operand = ExprUtils.ensureReturnType(operand, Boolean.class);
	}

	@DefaultAdder
	public void setOperand(Boolean operand) {
		this.operand = operand == null ? null : new ConstantExpr<Boolean>(operand);
	}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		return !operand.evaluate(context);
	}

	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

}
