package org.unclesniper.choreo.core;

import java.util.Locale;
import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringToUpperCase")
public class StringToUpperCase extends AbstractStringOperation implements ChoreoExpr<String> {

	private ChoreoExpr<Locale> locale;

	public StringToUpperCase() {}

	public StringToUpperCase(ChoreoExpr<String> string, ChoreoExpr<Locale> locale) {
		super(string);
		this.locale = ExprUtils.ensureReturnType(locale, Locale.class);
	}

	public ChoreoExpr<Locale> getLocale() {
		return locale;
	}

	public void setLocale(ChoreoExpr<Locale> locale) {
		this.locale = ExprUtils.ensureReturnType(locale, Locale.class);
	}

	public void setLocale(Locale locale) {
		this.locale = ConstantExpr.from(locale);
	}

	public Class<String> getReturnType() {
		return String.class;
	}

	public String evaluate(RunContext context) throws ChoreoRunException {
		String estring = ExprUtils.reduce(string, context);
		Locale elocale = ExprUtils.reduce(locale, context);
		if(elocale == null)
			return estring.toUpperCase();
		else
			return estring.toUpperCase(elocale);
	}

}
