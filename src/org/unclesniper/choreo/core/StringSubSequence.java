package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringSubSeq")
public class StringSubSequence extends AbstractStringOperation implements ChoreoExpr<CharSequence> {

	private ChoreoExpr<Integer> begin;

	private ChoreoExpr<Integer> end;

	public StringSubSequence() {}

	public StringSubSequence(ChoreoExpr<String> string, ChoreoExpr<Integer> begin) {
		this(string, begin, null);
	}

	public StringSubSequence(ChoreoExpr<String> string, ChoreoExpr<Integer> begin, ChoreoExpr<Integer> end) {
		super(string);
		this.begin = ExprUtils.ensureReturnType(begin, Integer.class);
		this.end = ExprUtils.ensureReturnType(end, Integer.class);
	}

	public ChoreoExpr<Integer> getBegin() {
		return begin;
	}

	public void setBegin(ChoreoExpr<Integer> begin) {
		this.begin = ExprUtils.ensureReturnType(begin, Integer.class);
	}

	public void setBegin(Integer begin) {
		this.begin = ConstantExpr.from(begin);
	}

	public ChoreoExpr<Integer> getEnd() {
		return end;
	}

	public void setEnd(ChoreoExpr<Integer> end) {
		this.end = ExprUtils.ensureReturnType(end, Integer.class);
	}

	public void setEnd(Integer end) {
		this.end = ConstantExpr.from(end);
	}

	public Class<? extends CharSequence> getReturnType() {
		return CharSequence.class;
	}

	public CharSequence evaluate(RunContext context) throws ChoreoRunException {
		String estring = ExprUtils.reduce(string, context);
		Integer ebegin = ExprUtils.reduce(begin, context);
		Integer eend = ExprUtils.reduce(end, context);
		if(eend != null || eend >= 0)
			return estring.subSequence(ebegin, eend);
		else
			return estring.subSequence(ebegin, estring.length());
	}

}
