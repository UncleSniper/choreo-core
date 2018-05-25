package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringStartsWith")
public class StringStartsWith extends AbstractStringOperation implements ChoreoExpr<Boolean> {

	private ChoreoExpr<String> prefix;

	private ChoreoExpr<Integer> offset;

	public StringStartsWith() {}

	public StringStartsWith(ChoreoExpr<String> string, ChoreoExpr<String> prefix) {
		this(string, prefix, null);
	}

	public StringStartsWith(ChoreoExpr<String> string, ChoreoExpr<String> prefix, ChoreoExpr<Integer> offset) {
		super(string);
		this.prefix = ExprUtils.ensureReturnType(prefix, String.class);
		this.offset = ExprUtils.ensureReturnType(offset, Integer.class);
	}

	public ChoreoExpr<String> getPrefix() {
		return prefix;
	}

	public void setPrefix(ChoreoExpr<String> prefix) {
		this.prefix = ExprUtils.ensureReturnType(prefix, String.class);
	}

	public void setPrefix(String prefix) {
		this.prefix = ConstantExpr.from(prefix);
	}

	public ChoreoExpr<Integer> getOffset() {
		return offset;
	}

	public void setOffset(ChoreoExpr<Integer> offset) {
		this.offset = ExprUtils.ensureReturnType(offset, Integer.class);
	}

	public Class<Boolean> getReturnType() {
		return Boolean.class;
	}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		String estring = ExprUtils.reduce(string, context);
		String eprefix = ExprUtils.reduce(prefix, context);
		Integer eoffset = ExprUtils.reduce(offset, context);
		if(eoffset != null && eoffset >= 0)
			return estring.startsWith(eprefix, eoffset);
		else
			return estring.startsWith(eprefix);
	}

}
