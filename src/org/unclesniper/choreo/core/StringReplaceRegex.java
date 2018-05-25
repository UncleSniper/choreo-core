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
		this.regex = regex == null ? null : new ConstantExpr<String>(regex);
	}

	public ChoreoExpr<String> getReplacement() {
		return replacement;
	}

	public void setReplacement(ChoreoExpr<String> replacement) {
		this.replacement = ExprUtils.ensureReturnType(replacement, String.class);
	}

	public void setReplacement(String replacement) {
		this.replacement = replacement == null ? null : new ConstantExpr<String>(replacement);
	}

	public Class<? extends String> getReturnType() {
		return String.class;
	}

	public ChoreoExpr<Boolean> getAll() {
		return all;
	}

	public void setAll(ChoreoExpr<Boolean> all) {
		this.all = ExprUtils.ensureReturnType(all, Boolean.class);
	}

	public void setAll(Boolean all) {
		this.all = all == null ? null : new ConstantExpr<Boolean>(all);
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

	public String evaluate(RunContext context) throws ChoreoRunException {
		String ehaystack = string == null ? null : string.evaluate(context);
		String eregex = regex == null ? null : regex.evaluate(context);
		String ereplacement = replacement == null ? null : replacement.evaluate(context);
		Boolean eall = all == null ? null : all.evaluate(context);
		Boolean equote = quote == null ? null : quote.evaluate(context);
		if(equote != null && equote)
			ereplacement = Matcher.quoteReplacement(ereplacement);
		if(eall != null && eall)
			return ehaystack.replaceAll(eregex, ereplacement);
		else
			return ehaystack.replaceFirst(eregex, ereplacement);
	}

}
