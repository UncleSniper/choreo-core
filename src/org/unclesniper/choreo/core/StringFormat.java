package org.unclesniper.choreo.core;

import java.util.List;
import java.util.Locale;
import java.util.LinkedList;
import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringFormat")
public class StringFormat implements ChoreoExpr<String> {

	private ChoreoExpr<Locale> locale;

	private ChoreoExpr<String> format;

	private final List<ChoreoExpr<?>> arguments = new LinkedList<ChoreoExpr<?>>();

	public StringFormat() {}

	public StringFormat(ChoreoExpr<String> format) {
		this(null, format);
	}

	public StringFormat(ChoreoExpr<Locale> locale, ChoreoExpr<String> format) {
		this.locale = ExprUtils.ensureReturnType(locale, Locale.class);
		this.format = ExprUtils.ensureReturnType(format, String.class);
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

	public ChoreoExpr<String> getFormat() {
		return format;
	}

	public void setFormat(ChoreoExpr<String> format) {
		this.format = ExprUtils.ensureReturnType(format, String.class);
	}

	public void setFormat(String format) {
		this.format = ConstantExpr.from(format);
	}

	public Class<String> getReturnType() {
		return String.class;
	}

	public Iterable<ChoreoExpr<?>> getArguments() {
		return arguments;
	}

	@DefaultAdder
	public void addArgument(ChoreoExpr<?> argument) {
		arguments.add(argument);
	}

	public void addArgumentValue(Object argument) {
		arguments.add(ConstantExpr.from(argument));
	}

	public boolean removeArgument(ChoreoExpr<?> argument) {
		return arguments.remove(argument);
	}

	public void clearArguments() {
		arguments.clear();
	}

	public String evaluate(RunContext context) throws ChoreoRunException {
		Locale elocale = ExprUtils.reduce(locale, context);
		String eformat = ExprUtils.reduce(format, context);
		Object[] eargs = new Object[arguments.size()];
		int index = -1;
		for(ChoreoExpr<?> argument : arguments)
			eargs[++index] = ExprUtils.reduce(argument, context);
		if(elocale == null)
			return String.format(eformat, eargs);
		else
			return String.format(elocale, eformat, eargs);
	}

}
