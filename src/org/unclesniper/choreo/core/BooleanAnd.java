package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("logicalAnd")
public class BooleanAnd extends ShortCircuitableBooleanOperator {

	public BooleanAnd() {}

	public BooleanAnd(boolean shortCircuit) {
		super(shortCircuit);
	}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		boolean result = true;
		for(ChoreoExpr<Boolean> operand : operands) {
			if(!operand.evaluate(context)) {
				if(shortCircuit)
					return false;
				result = false;
			}
		}
		return result;
	}

}
