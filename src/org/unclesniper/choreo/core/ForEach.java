package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoTask;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;
import org.unclesniper.choreo.annotation.DefaultAdder;

@ElementClass("foreach")
public class ForEach<ReturnT> implements ChoreoTask, ChoreoExpr<ReturnT> {

	private ChoreoExpr<String> key;

	private ChoreoExpr<?> in;

	private ChoreoExpr<? extends ReturnT> body;

	private ChoreoExpr<String> label;

	public ForEach() {}

	public ForEach(ChoreoExpr<String> key, ChoreoExpr<?> in, ChoreoExpr<? extends ReturnT> body) {
		this.key = ExprUtils.ensureReturnType(key, String.class);
		this.in = in;
		this.body = body;
	}

	public ChoreoExpr<String> getKey() {
		return key;
	}

	public void setKey(ChoreoExpr<String> key) {
		this.key = ExprUtils.ensureReturnType(key, String.class);
	}

	public void setKey(String key) {
		this.key = ConstantExpr.from(key);
	}

	public ChoreoExpr<?> getIn() {
		return in;
	}

	public void setIn(ChoreoExpr<?> in) {
		this.in = in;
	}

	public void setCollection(Object collection) {
		in = ConstantExpr.from(collection);
	}

	public ChoreoExpr<? extends ReturnT> getBody() {
		return body;
	}

	@DefaultAdder
	public void setBody(ChoreoExpr<? extends ReturnT> body) {
		this.body = body;
	}

	@DefaultAdder
	public void setTask(ChoreoTask body) {
		this.body = body == null ? null : new Return<ReturnT>(body, null);
	}

	public ChoreoExpr<String> getLabel() {
		return label;
	}

	public void setLabel(ChoreoExpr<String> label) {
		this.label = ExprUtils.ensureReturnType(label, String.class);
	}

	public void setLabel(String label) {
		this.label = ConstantExpr.from(label);
	}

	public Class<? extends ReturnT> getReturnType() {
		return body == null ? null : body.getReturnType();
	}

	public void execute(RunContext context) throws ChoreoRunException {
		evaluate(context);
	}

	public ReturnT evaluate(RunContext context) throws ChoreoRunException {
		String ekey = ExprUtils.reduce(key, context);
		Object collection = ExprUtils.reduce(in, context);
		if(collection == null)
			return null;
		String elabel = ExprUtils.reduce(label, context);
		ReturnT result = null;
		for(;;) {
			//TODO
		}
	}

}
