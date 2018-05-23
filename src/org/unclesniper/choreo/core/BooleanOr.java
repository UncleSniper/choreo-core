package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("logicalOr")
public class BooleanOr extends ShortCircuitableBooleanOperator {

	public BooleanOr() {}

	public BooleanOr(boolean shortCircuit) {
		super(shortCircuit);
	}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		boolean result = false;
		for(ChoreoExpr<Boolean> operand : operands) {
			if(operand.evaluate(context)) {
				if(shortCircuit)
					return true;
				result = true;
			}
		}
		return result;
	}

}
