package org.unclesniper.choreo.core;

import java.util.List;
import java.util.LinkedList;
import org.unclesniper.choreo.annotation.DefaultAdder;

public abstract class AbstractVariadicNumericOperator implements ChoreoExpr<Number> {

	protected final List<ChoreoExpr<? extends Number>> operands = new LinkedList<ChoreoExpr<? extends Number>>();

	public AbstractVariadicNumericOperator() {}

	protected abstract void ensureReturnType(ChoreoExpr<? extends Number> operand);

	public Iterable<ChoreoExpr<? extends Number>> getOperands() {
		return operands;
	}

	@DefaultAdder
	public void addOperand(ChoreoExpr<? extends Number> operand) {
		if(operand != null) {
			ensureReturnType(operand);
			operands.add(operand);
		}
	}

	public boolean removeOperand(ChoreoExpr<? extends Number> operand) {
		return operands.remove(operand);
	}

	public void clearOperands() {
		operands.clear();
	}

}
