package org.unclesniper.choreo.core;

import java.util.Set;
import java.util.HashSet;
import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringReplace")
public class StringReplace extends AbstractStringOperation implements ChoreoExpr<String> {

	private static final Set<Class<?>> ALLOWED_PIECE_TYPES;

	static {
		ALLOWED_PIECE_TYPES = new HashSet<Class<?>>();
		ALLOWED_PIECE_TYPES.add(Character.class);
		ALLOWED_PIECE_TYPES.add(Character.TYPE);
		ALLOWED_PIECE_TYPES.add(CharSequence.class);
	}

	private ChoreoExpr<?> needle;

	private ChoreoExpr<?> replacement;

	public StringReplace() {}

	public StringReplace(ChoreoExpr<String> string, ChoreoExpr<?> needle, ChoreoExpr<?> replacement) {
		super(string);
		setNeedle(needle);
		setReplacement(replacement);
	}

	public void setHaystack(ChoreoExpr<String> string) {
		setString(string);
	}

	public void setHaystack(String string) {
		setString(string);
	}

	public ChoreoExpr<?> getNeedle() {
		return needle;
	}

	public void setNeedle(ChoreoExpr<?> needle) {
		if(needle != null) {
			Class<?> ert = needle.getReturnType();
			if(ert != null && !StringReplace.ALLOWED_PIECE_TYPES.contains(ert))
				throw new IllegalArgumentException("Expression must return char or CharSequence, but returns "
						+ ert.getName());
		}
		this.needle = needle;
	}

	public void setNeedle(CharSequence needle) {
		this.needle = needle == null ? null : new ConstantExpr<CharSequence>(needle);
	}

	public void setNeedle(Character needle) {
		this.needle = needle == null ? null : new ConstantExpr<Character>(needle);
	}

	public ChoreoExpr<?> getReplacement() {
		return replacement;
	}

	public void setReplacement(ChoreoExpr<?> replacement) {
		if(replacement != null) {
			Class<?> ert = replacement.getReturnType();
			if(ert != null && !StringReplace.ALLOWED_PIECE_TYPES.contains(ert))
				throw new IllegalArgumentException("Expression must return char or CharSequence, but returns "
						+ ert.getName());
		}
		this.replacement = replacement;
	}

	public void setReplacement(CharSequence replacement) {
		this.replacement = replacement == null ? null : new ConstantExpr<CharSequence>(replacement);
	}

	public void setReplacement(Character replacement) {
		this.replacement = replacement == null ? null : new ConstantExpr<Character>(replacement);
	}

	public Class<? extends String> getReturnType() {
		return String.class;
	}

	public String evaluate(RunContext context) throws ChoreoRunException {
		String ehaystack = string == null ? null : string.evaluate(context);
		Object eneedle = needle == null ? null : needle.evaluate(context);
		Object ereplacement = replacement == null ? null : replacement.evaluate(context);
		if(eneedle instanceof Character) {
			char cneedle = (Character)eneedle;
			if(ereplacement instanceof Character)
				return ehaystack.replace(cneedle, (Character)ereplacement);
			else
				return ehaystack.replace(String.valueOf(cneedle), (CharSequence)ereplacement);
		}
		else {
			CharSequence sneedle = (CharSequence)eneedle;
			if(ereplacement instanceof Character)
				return ehaystack.replace(sneedle, String.valueOf((char)(Character)ereplacement));
			else
				return ehaystack.replace(sneedle, (CharSequence)ereplacement);
		}
	}

}
