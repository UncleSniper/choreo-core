package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.annotation.ElementClass;
import org.unclesniper.choreo.annotation.DefaultAdder;

@ElementClass("double")
public class DoubleLiteral implements ChoreoExpr<Double> {

	private double value;

	public DoubleLiteral() {}

	public DoubleLiteral(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	@DefaultAdder
	public void setValue(double value) {
		this.value = value;
	}

	public Class<Double> getReturnType() {
		return Double.class;
	}

	public Double evaluate(RunContext context) {
		return value;
	}

}
