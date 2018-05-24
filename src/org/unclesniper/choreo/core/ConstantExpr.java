package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("const")
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

	public int hashCode() {
		return value == null ? 0 : value.hashCode();
	}

	@SuppressWarnings("unchecked")
	public boolean equals(Object other) {
		if(other == null || !other.getClass().equals(getClass()))
			return false;
		ConstantExpr<ReturnT> oce = (ConstantExpr)other;
		if(value == null)
			return oce.value == null;
		if(oce.value == null)
			return false;
		return value.equals(oce.value);
	}

}
