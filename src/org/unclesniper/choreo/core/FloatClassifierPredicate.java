package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;

public abstract class FloatClassifierPredicate implements ChoreoExpr<Boolean> {

	private ChoreoExpr<Float> value;

	public FloatClassifierPredicate() {}

	public FloatClassifierPredicate(ChoreoExpr<Float> value) {
		this.value = ExprUtils.ensureReturnType(value, Float.class);
	}

	public ChoreoExpr<Float> getValue() {
		return value;
	}

	@DefaultAdder
	public void setValue(ChoreoExpr<Float> value) {
		this.value = ExprUtils.ensureReturnType(value, Float.class);
	}

	public void setValue(Float value) {
		this.value = ConstantExpr.from(value);
	}

	public Class<Boolean> getReturnType() {
		return Boolean.class;
	}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		return is(value.evaluate(context));
	}

	protected abstract boolean is(float value);

}
