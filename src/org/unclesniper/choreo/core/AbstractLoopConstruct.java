package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoTask;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;

public class AbstractLoopConstruct<ReturnT> implements ChoreoTask, ChoreoExpr<ReturnT> {

	private final boolean testFirst;

	private final boolean negateTest;

	private ChoreoExpr<Boolean> condition;

	private ChoreoExpr<? extends ReturnT> body;

	private ChoreoExpr<String> label;

	public AbstractLoopConstruct(boolean testFirst, boolean negateTest) {
		this(testFirst, negateTest, null, null);
	}

	public AbstractLoopConstruct(boolean testFirst, boolean negateTest,
			ChoreoExpr<Boolean> condition, ChoreoExpr<? extends ReturnT> body) {
		this.testFirst = testFirst;
		this.negateTest = negateTest;
		this.condition = ExprUtils.ensureReturnType(condition, Boolean.class);
		this.body = body;
	}

	public boolean isTestFirst() {
		return testFirst;
	}

	public boolean isNegateTest() {
		return negateTest;
	}

	public ChoreoExpr<Boolean> getCondition() {
		return condition;
	}

	public void setCondition(ChoreoExpr<Boolean> condition) {
		this.condition = ExprUtils.ensureReturnType(condition, Boolean.class);
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

	@SuppressWarnings("unchecked")
	public ReturnT evaluate(RunContext context) throws ChoreoRunException {
		if(condition == null)
			return null;
		String elabel = ExprUtils.reduce(label, context);
		ReturnT result = null;
		boolean first = true, testNow;
		for(;;) {
			if(first) {
				first = false;
				testNow = testFirst;
			}
			else
				testNow = true;
			if(testNow) {
				Boolean test = condition.evaluate(context);
				boolean etest = test != null && test;
				if(negateTest ? etest : !etest)
					return result;
			}
			if(body != null) {
				try {
					result = body.evaluate(context);
				}
				catch(LoopInterruptSignal signal) {
					String flabel = signal.getLoopLabel();
					if(flabel != null && !flabel.equals(elabel))
						throw signal;
					result = (ReturnT)signal.getReturnValue();
					if(!signal.isKeepGoing())
						return result;
				}
			}
		}
	}

}
