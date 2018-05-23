package org.unclesniper.choreo.core;

import java.util.List;
import java.util.LinkedList;
import org.unclesniper.choreo.annotation.DefaultAdder;

public abstract class VariadicBooleanOperator implements ChoreoExpr<Boolean> {

	protected final List<ChoreoExpr<Boolean>> operands = new LinkedList<ChoreoExpr<Boolean>>();

	public VariadicBooleanOperator() {}

	public Iterable<ChoreoExpr<Boolean>> getOperands() {
		return operands;
	}

	@DefaultAdder
	public void addOperand(ChoreoExpr<Boolean> operand) {
		if(operand != null)
			operands.add(ExprUtils.ensureReturnType(operand, Boolean.class));
	}

	@DefaultAdder
	public void addOperand(Boolean operand) {
		if(operand != null)
			operands.add(new ConstantExpr<Boolean>(operand));
	}

	public boolean removeOperand(ChoreoExpr<Boolean> operand) {
		return operands.remove(operand);
	}

	public void clearOperands() {
		operands.clear();
	}

	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

}
