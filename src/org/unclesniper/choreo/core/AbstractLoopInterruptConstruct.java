package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoTask;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;

public class AbstractLoopInterruptConstruct<ReturnT> implements ChoreoTask, ChoreoExpr<ReturnT> {

	private final boolean keepGoing;

	private ChoreoExpr<String> label;

	private ChoreoExpr<?> value;

	public AbstractLoopInterruptConstruct(boolean keepGoing) {
		this(keepGoing, null, null);
	}

	public AbstractLoopInterruptConstruct(boolean keepGoing, ChoreoExpr<String> label, ChoreoExpr<?> value) {
		this.keepGoing = keepGoing;
		this.label = ExprUtils.ensureReturnType(label, String.class);
		this.value = value;
	}

	public boolean isKeepGoing() {
		return keepGoing;
	}

	public ChoreoExpr<String> getLabel() {
		return label;
	}

	public void setLabel(ChoreoExpr<String> label) {
		this.label = ExprUtils.ensureReturnType(label, String.class);
	}

	public void setLabel(String label) {
		this.label = label == null ? null : new ConstantExpr<String>(label);
	}

	public ChoreoExpr<?> getValue() {
		return value;
	}

	@DefaultAdder
	public void setValue(ChoreoExpr<?> value) {
		this.value = value;
	}

	public void setObject(Object value) {
		this.value = value == null ? null : new ConstantExpr<Object>(value);
	}

	public Class<? extends ReturnT> getReturnType() {
		return null;
	}

	public void execute(RunContext context) throws ChoreoRunException {
		evaluate(context);
	}

	public ReturnT evaluate(RunContext context) throws ChoreoRunException {
		String elabel = label == null ? null : label.evaluate(context);
		Object evalue = value == null ? null : value.evaluate(context);
		throw new LoopInterruptSignal(keepGoing, elabel, evalue);
	}

}
