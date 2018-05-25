package org.unclesniper.choreo.core;

import java.util.regex.Matcher;
import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringReplaceRegex")
public class StringReplaceRegex extends AbstractStringOperation implements ChoreoExpr<String> {

	private ChoreoExpr<String> regex;

	private ChoreoExpr<String> replacement;

	private ChoreoExpr<Boolean> all;

	private ChoreoExpr<Boolean> quote;

	public StringReplaceRegex() {}

	public StringReplaceRegex(ChoreoExpr<String> string, ChoreoExpr<String> regex, ChoreoExpr<String> replacement) {
		this(string, regex, replacement, null, null);
	}

	public StringReplaceRegex(ChoreoExpr<String> string, ChoreoExpr<String> regex, ChoreoExpr<String> replacement,
			ChoreoExpr<Boolean> all, ChoreoExpr<Boolean> quote) {
		super(string);
		this.regex = ExprUtils.ensureReturnType(regex, String.class);
		this.replacement = ExprUtils.ensureReturnType(replacement, String.class);
		this.all = ExprUtils.ensureReturnType(all, Boolean.class);
		this.quote = ExprUtils.ensureReturnType(quote, Boolean.class);
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

	public ChoreoExpr<String> getReplacement() {
		return replacement;
	}

	public void setReplacement(ChoreoExpr<String> replacement) {
		this.replacement = ExprUtils.ensureReturnType(replacement, String.class);
	}

	public void setReplacement(String replacement) {
		this.replacement = ConstantExpr.from(replacement);
	}

	public ChoreoExpr<Boolean> getAll() {
		return all;
	}

	public void setAll(ChoreoExpr<Boolean> all) {
		this.all = ExprUtils.ensureReturnType(all, Boolean.class);
	}

	public void setAll(Boolean all) {
		this.all = ConstantExpr.from(all);
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

	public Class<String> getReturnType() {
		return String.class;
	}

	public String evaluate(RunContext context) throws ChoreoRunException {
		String ehaystack = ExprUtils.reduce(string, context);
		String eregex = ExprUtils.reduce(regex, context);
		String ereplacement = ExprUtils.reduce(replacement, context);
		Boolean eall = ExprUtils.reduce(all, context);
		Boolean equote = ExprUtils.reduce(quote, context);
		if(equote != null && equote)
			ereplacement = Matcher.quoteReplacement(ereplacement);
		if(eall != null && eall)
			return ehaystack.replaceAll(eregex, ereplacement);
		else
			return ehaystack.replaceFirst(eregex, ereplacement);
	}

}
