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
		this.locale = locale == null ? null : new ConstantExpr<Locale>(locale);
	}

	public ChoreoExpr<String> getFormat() {
		return format;
	}

	public void setFormat(ChoreoExpr<String> format) {
		this.format = ExprUtils.ensureReturnType(format, String.class);
	}

	public void setFormat(String format) {
		this.format = format == null ? null : new ConstantExpr<String>(format);
	}

	public Class<? extends String> getReturnType() {
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
		arguments.add(argument == null ? null : new ConstantExpr<Object>(argument));
	}

	public boolean removeArgument(ChoreoExpr<?> argument) {
		return arguments.remove(argument);
	}

	public void clearArguments() {
		arguments.clear();
	}

	public String evaluate(RunContext context) throws ChoreoRunException {
		Locale elocale = locale == null ? null : locale.evaluate(context);
		String eformat = format == null ? null : format.evaluate(context);
		Object[] eargs = new Object[arguments.size()];
		int index = -1;
		for(ChoreoExpr<?> argument : arguments)
			eargs[++index] = argument == null ? null : argument.evaluate(context);
		if(elocale == null)
			return String.format(eformat, eargs);
		else
			return String.format(elocale, eformat, eargs);
	}

}
