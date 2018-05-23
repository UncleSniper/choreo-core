package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;

public abstract class DoubleClassifierPredicate implements ChoreoExpr<Boolean> {

	private ChoreoExpr<Double> value;

	public DoubleClassifierPredicate() {}

	public DoubleClassifierPredicate(ChoreoExpr<Double> value) {
		this.value = ExprUtils.ensureReturnType(value, Double.class);
	}

	public ChoreoExpr<Double> getValue() {
		return value;
	}

	@DefaultAdder
	public void setValue(ChoreoExpr<Double> value) {
		this.value = ExprUtils.ensureReturnType(value, Double.class);
	}

	public void setValue(Double value) {
		this.value = value == null ? null : new ConstantExpr<Double>(value);
	}

	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		return is(value.evaluate(context));
	}

	protected abstract boolean is(double value);

}
