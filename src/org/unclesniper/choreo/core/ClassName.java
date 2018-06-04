package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;
import org.unclesniper.choreo.annotation.DefaultAdder;

@ElementClass("className")
public class ClassName implements ChoreoExpr<String> {

	private ChoreoExpr<Class<?>> clazz;

	public ClassName() {}

	public ClassName(ChoreoExpr<Class<?>> clazz) {
		setClazz(clazz);
	}

	public ChoreoExpr<Class<?>> getClazz() {
		return clazz;
	}

	@DefaultAdder
	@SuppressWarnings("unchecked")
	public void setClazz(ChoreoExpr<Class<?>> clazz) {
		this.clazz = ExprUtils.ensureReturnType(clazz, (Class<Class<?>>)(Class)Class.class);
	}

	@DefaultAdder
	public void setClazz(Class<?> clazz) {
		this.clazz = ConstantExpr.from(clazz);
	}

	public Class<String> getReturnType() {
		return String.class;
	}

	public String evaluate(RunContext context) throws ChoreoRunException {
		return ExprUtils.reduce(clazz, context).getName();
	}

}
