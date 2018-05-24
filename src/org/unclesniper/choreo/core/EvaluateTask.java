package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;
import org.unclesniper.choreo.annotation.DefaultAdder;

@ElementClass("eval")
public class EvaluateTask {

	private ChoreoExpr<?> expression;

	public EvaluateTask() {}

	public EvaluateTask(ChoreoExpr<?> expression) {
		this.expression = expression;
	}

	public ChoreoExpr<?> getExpression() {
		return expression;
	}

	@DefaultAdder
	public void setExpression(ChoreoExpr<?> expression) {
		this.expression = expression;
	}

	public void execute(RunContext context) throws ChoreoRunException {
		if(expression != null)
			expression.evaluate(context);
	}

}
