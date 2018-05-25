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
		this.regex = regex == null ? null : new ConstantExpr<String>(regex);
	}

	public ChoreoExpr<Boolean> getQuote() {
		return quote;
	}

	public void setQuote(ChoreoExpr<Boolean> quote) {
		this.quote = ExprUtils.ensureReturnType(quote, Boolean.class);
	}

	public void setQuote(Boolean quote) {
		this.quote = quote == null ? null : new ConstantExpr<Boolean>(quote);
	}

	public ChoreoExpr<Integer> getLimit() {
		return limit;
	}

	public void setLimit(ChoreoExpr<Integer> limit) {
		this.limit = ExprUtils.ensureReturnType(limit, Integer.class);
	}

	public void setLimit(Integer limit) {
		this.limit = limit == null ? null : new ConstantExpr<Integer>(limit);
	}

	public Class<? extends String[]> getReturnType() {
		return String[].class;
	}

	public String[] evaluate(RunContext context) throws ChoreoRunException {
		String ehaystack = string == null ? null : string.evaluate(context);
		String eregex = regex == null ? null : regex.evaluate(context);
		Boolean equote = quote == null ? null : quote.evaluate(context);
		Integer elimit = limit == null ? null : limit.evaluate(context);
		if(equote != null && equote)
			eregex = Pattern.quote(eregex);
		if(elimit != null)
			return ehaystack.split(eregex, elimit);
		else
			return ehaystack.split(eregex);
	}

}
