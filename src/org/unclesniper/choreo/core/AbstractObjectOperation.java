package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.DefaultAdder;

public abstract class AbstractObjectOperation {

	protected ChoreoExpr<?> value;

	public AbstractObjectOperation() {}

	public AbstractObjectOperation(ChoreoExpr<?> value) {
		this.value = value;
	}

	public ChoreoExpr<?> getValue() {
		return value;
	}

	@DefaultAdder
	public void setValue(ChoreoExpr<?> value) {
		this.value = value;
	}

	public void setObject(Object object) {
		value = ConstantExpr.from(object);
	}

}
