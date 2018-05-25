package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoTask;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("return")
public class Return<ReturnT> implements ChoreoExpr<ReturnT> {

	private ChoreoTask task;

	private ChoreoExpr<? extends ReturnT> returnValue;

	public Return() {}

	public Return(ChoreoTask task, ChoreoExpr<? extends ReturnT> returnValue) {
		this.task = task;
		this.returnValue = returnValue;
	}

	public ChoreoTask getTask() {
		return task;
	}

	public void setTask(ChoreoTask task) {
		this.task = task;
	}

	public ChoreoExpr<? extends ReturnT> getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(ChoreoExpr<? extends ReturnT> returnValue) {
		this.returnValue = returnValue;
	}

	public void setReturnObject(ReturnT value) {
		this.returnValue = ConstantExpr.from(value);
	}

	public Class<? extends ReturnT> getReturnType() {
		return returnValue == null ? null : returnValue.getReturnType();
	}

	public ReturnT evaluate(RunContext context) throws ChoreoRunException {
		if(task != null)
			task.execute(context);
		return ExprUtils.reduce(returnValue, context);
	}

}
