package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoTask;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;
import org.unclesniper.choreo.annotation.DefaultAdder;

@ElementClass("let")
public class Let implements ChoreoTask {

	private ChoreoExpr<String> key;

	private ChoreoExpr<?> value;

	public Let() {}

	public Let(ChoreoExpr<String> key, ChoreoExpr<?> value) {
		this.key = ExprUtils.ensureReturnType(key, String.class);
		this.value = value;
	}

	public ChoreoExpr<String> getKey() {
		return key;
	}

	@DefaultAdder
	public void setKey(ChoreoExpr<String> key) {
		this.key = ExprUtils.ensureReturnType(key, String.class);
	}

	@DefaultAdder
	public void setKey(String key) {
		this.key = ConstantExpr.from(key);
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

	public void execute(RunContext context) throws ChoreoRunException {
		String ekey = ExprUtils.reduce(key, context);
		Object evalue = ExprUtils.reduce(value, context);
		context.getServiceRegistry().putServiceObject(ekey, evalue);
	}

}
