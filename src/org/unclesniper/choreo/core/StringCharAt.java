package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringCharAt")
public class StringCharAt extends AbstractStringOperation implements ChoreoExpr<Character> {

	private ChoreoExpr<Integer> index;

	public StringCharAt() {}

	public StringCharAt(ChoreoExpr<String> string, ChoreoExpr<Integer> index) {
		super(string);
		this.index = ExprUtils.ensureReturnType(index, Integer.class);
	}

	public ChoreoExpr<Integer> getIndex() {
		return index;
	}

	public void setIndex(ChoreoExpr<Integer> index) {
		this.index = ExprUtils.ensureReturnType(index, Integer.class);
	}

	public void setIndex(Integer index) {
		this.index = ConstantExpr.from(index);
	}

	public Class<Character> getReturnType() {
		return Character.class;
	}

	public Character evaluate(RunContext context) throws ChoreoRunException {
		String estring = ExprUtils.reduce(string, context);
		return estring.charAt(index.evaluate(context));
	}

}
