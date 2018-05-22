package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;

public class ConstantExpr<ReturnT> implements ChoreoExpr<ReturnT> {

	private ReturnT value;

	private Class<? extends ReturnT> returnType;

	public ConstantExpr() {}

	public ConstantExpr(ReturnT value) {
		this.value = value;
	}

	public ReturnT getValue() {
		return value;
	}

	public void setValue(ReturnT value) {
		this.value = value;
	}

	public ReturnT evaluate(RunContext context) {
		return value;
	}

	public void setReturnType(Class<? extends ReturnT> returnType) {
		this.returnType = returnType;
	}

	@SuppressWarnings("unchecked")
	public Class<? extends ReturnT> getReturnType() {
		if(returnType != null)
			return returnType;
		if(value != null)
			return (Class)value.getClass();
		return null;
	}

}
