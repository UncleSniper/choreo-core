package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("shiftRight")
public class ShiftRight extends VariadicIntegralOperator<Boolean> {

	private ChoreoExpr<Boolean> unsigned;

	public ShiftRight() {}

	public ChoreoExpr<Boolean> getUnsigned() {
		return unsigned;
	}

	public void setUnsigned(ChoreoExpr<Boolean> unsigned) {
		this.unsigned = ExprUtils.ensureReturnType(unsigned, Boolean.class);
	}

	public void setUnsigned(Boolean unsigned) {
		this.unsigned = ConstantExpr.from(unsigned);
	}

	protected Boolean evaluateOptions(RunContext context) throws ChoreoRunException {
		Boolean options = ExprUtils.reduce(unsigned, context);
		return options == null ? Boolean.FALSE : options;
	}

	protected int combine(int a, int b, Boolean unsigned) {
		if(b < 0)
			throw new IllegalArgumentException("Cowardly refusing to shift by " + b + " bit positions");
		if(b >= 32)
			return unsigned || a >= 0 ? 0 : -1;
		return unsigned ? a >>> b : a >> b;
	}

	protected long combine(long a, long b, Boolean unsigned) {
		if(b < 0l)
			throw new IllegalArgumentException("Cowardly refusing to shift by " + b + " bit positions");
		if(b >= 64l)
			return unsigned || a >= 0l ? 0l : -1l;
		return unsigned ? a >>> (int)b : a >> (int)b;
	}

}
