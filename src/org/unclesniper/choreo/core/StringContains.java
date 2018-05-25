package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringContains")
public class StringContains extends AbstractStringOperation implements ChoreoExpr<Boolean> {

	private ChoreoExpr<? extends CharSequence> subseq;

	public StringContains() {}

	public StringContains(ChoreoExpr<String> string, ChoreoExpr<? extends CharSequence> subseq) {
		super(string);
		this.subseq = ExprUtils.ensureReturnTypeE(subseq, CharSequence.class);
	}

	public ChoreoExpr<? extends CharSequence> getSubseq() {
		return subseq;
	}

	public void setSubseq(ChoreoExpr<? extends CharSequence> subseq) {
		this.subseq = ExprUtils.ensureReturnTypeE(subseq, CharSequence.class);
	}

	public void setSubseq(CharSequence subseq) {
		this.subseq = ConstantExpr.from(subseq);
	}

	public Class<Boolean> getReturnType() {
		return Boolean.class;
	}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		String estring = ExprUtils.reduce(string, context);
		return estring.contains(subseq.evaluate(context));
	}

}
