package org.unclesniper.choreo.core;

import java.util.regex.Pattern;
import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringSplit")
public class StringSplit extends AbstractStringOperation implements ChoreoExpr<String[]> {

	private ChoreoExpr<String> regex;

	private ChoreoExpr<Boolean> quote;

	private ChoreoExpr<Integer> limit;

	public StringSplit() {}

	public StringSplit(ChoreoExpr<String> string, ChoreoExpr<String> regex) {
		this(string, regex, null, null);
	}

	public StringSplit(ChoreoExpr<String> string, ChoreoExpr<String> regex,
			ChoreoExpr<Boolean> quote, ChoreoExpr<Integer> limit) {
		super(string);
		this.regex = ExprUtils.ensureReturnType(regex, String.class);
		this.quote = ExprUtils.ensureReturnType(quote, Boolean.class);
		this.limit = ExprUtils.ensureReturnType(limit, Integer.class);
	}

	public ChoreoExpr<String> getRegex() {
		return regex;
	}

	public void setRegex(ChoreoExpr<String> regex) {
		this.regex = ExprUtils.ensureReturnType(regex, String.class);
	}

	public void setRegex(String regex) {
		this.regex = ConstantExpr.from(regex);
	}

	public ChoreoExpr<Boolean> getQuote() {
		return quote;
	}

	public void setQuote(ChoreoExpr<Boolean> quote) {
		this.quote = ExprUtils.ensureReturnType(quote, Boolean.class);
	}

	public void setQuote(Boolean quote) {
		this.quote = ConstantExpr.from(quote);
	}

	public ChoreoExpr<Integer> getLimit() {
		return limit;
	}

	public void setLimit(ChoreoExpr<Integer> limit) {
		this.limit = ExprUtils.ensureReturnType(limit, Integer.class);
	}

	public void setLimit(Integer limit) {
		this.limit = ConstantExpr.from(limit);
	}

	public Class<String[]> getReturnType() {
		return String[].class;
	}

	public String[] evaluate(RunContext context) throws ChoreoRunException {
		String ehaystack = ExprUtils.reduce(string, context);
		String eregex = ExprUtils.reduce(regex, context);
		Boolean equote = ExprUtils.reduce(quote, context);
		Integer elimit = ExprUtils.reduce(limit, context);
		if(equote != null && equote)
			eregex = Pattern.quote(eregex);
		if(elimit != null)
			return ehaystack.split(eregex, elimit);
		else
			return ehaystack.split(eregex);
	}

}
