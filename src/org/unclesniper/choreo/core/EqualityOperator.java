package org.unclesniper.choreo.core;

import java.util.List;
import java.util.LinkedList;
import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("equality")
public class EqualityOperator implements ChoreoExpr<Boolean> {

	public enum Operator {
		EQUAL,
		UNEQUAL
	}

	private Operator operator;

	private final List<ChoreoExpr<?>> operands = new LinkedList<ChoreoExpr<?>>();

	private ChoreoExpr<Double> epsilon;

	public EqualityOperator() {}

	public EqualityOperator(Operator operator) {
		this.operator = operator;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Iterable<ChoreoExpr<?>> getOperands() {
		return operands;
	}

	@DefaultAdder
	public void addOperand(ChoreoExpr<?> operand) {
		operands.add(operand);
	}

	public void addValue(Object value) {
		operands.add(ConstantExpr.from(value));
	}

	public boolean removeOperand(ChoreoExpr<?> operand) {
		return operands.remove(operand);
	}

	public void clearOperands() {
		operands.clear();
	}

	public ChoreoExpr<Double> getEpsilon() {
		return epsilon;
	}

	public void setEpsilon(ChoreoExpr<Double> epsilon) {
		this.epsilon = ExprUtils.ensureReturnType(epsilon, Double.class);
	}

	public void setEpsilon(Double epsilon) {
		this.epsilon = ConstantExpr.from(epsilon);
	}

	public Class<Boolean> getReturnType() {
		return Boolean.class;
	}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		Double eepsilon = ExprUtils.reduce(epsilon, context);
		double vepsilon = eepsilon == null ? 0.0 : eepsilon.doubleValue();
		float fepsilon = (float)vepsilon;
		boolean hasPrev = false;
		Object prevValue = null;
		for(ChoreoExpr<?> operand : operands) {
			Object nextValue = ExprUtils.reduce(operand, context);
			if(hasPrev) {
				boolean intermediate;
				if((prevValue instanceof Double) && (nextValue instanceof Double))
					intermediate = Math.abs((Double)prevValue - (Double)nextValue) <= vepsilon;
				else if((prevValue instanceof Float) && (nextValue instanceof Float))
					intermediate = Math.abs((Float)prevValue - (Float)nextValue) <= fepsilon;
				else
					intermediate = prevValue.equals(nextValue);
				if(operator == Operator.UNEQUAL ? intermediate : !intermediate)
					return false;
			}
			else
				hasPrev = true;
			prevValue = nextValue;
		}
		return true;
	}

}
