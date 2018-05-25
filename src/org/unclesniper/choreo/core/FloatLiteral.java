package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.annotation.ElementClass;
import org.unclesniper.choreo.annotation.DefaultAdder;

@ElementClass("float")
public class FloatLiteral implements ChoreoExpr<Float> {

	private float value;

	public FloatLiteral() {}

	public FloatLiteral(float value) {
		this.value = value;
	}

	public float getValue() {
		return value;
	}

	@DefaultAdder
	public void setValue(float value) {
		this.value = value;
	}

	public Class<Float> getReturnType() {
		return Float.class;
	}

	public Float evaluate(RunContext context) {
		return value;
	}

}
