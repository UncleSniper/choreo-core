package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("logicalXor")
public class BooleanXor extends VariadicBooleanOperator {

	public BooleanXor() {}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		boolean result = false, first = true;
		for(ChoreoExpr<Boolean> operand : operands) {
			boolean oresult = operand.evaluate(context);
			if(first) {
				first = false;
				result = oresult;
			}
			else
				result = Boolean.logicalXor(result, oresult);
		}
		return false;
	}

}
