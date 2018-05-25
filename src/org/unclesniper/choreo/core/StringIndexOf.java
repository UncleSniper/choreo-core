package org.unclesniper.choreo.core;

import java.util.Set;
import java.util.HashSet;
import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringIndexOf")
public class StringIndexOf extends AbstractStringOperation implements ChoreoExpr<Integer> {

	private static final Set<Class<?>> ALLOWED_NEEDLE_TYPES;

	static {
		ALLOWED_NEEDLE_TYPES = new HashSet<Class<?>>();
		ALLOWED_NEEDLE_TYPES.add(Character.class);
		ALLOWED_NEEDLE_TYPES.add(Character.TYPE);
		ALLOWED_NEEDLE_TYPES.add(Integer.class);
		ALLOWED_NEEDLE_TYPES.add(Integer.TYPE);
		ALLOWED_NEEDLE_TYPES.add(String.class);
	}

	private ChoreoExpr<?> needle;

	private ChoreoExpr<Integer> from;

	private ChoreoExpr<Boolean> last;

	public StringIndexOf() {}

	public StringIndexOf(ChoreoExpr<String> string, ChoreoExpr<?> needle) {
		this(string, needle, null, null);
	}

	public StringIndexOf(ChoreoExpr<String> string, ChoreoExpr<?> needle,
			ChoreoExpr<Integer> from, ChoreoExpr<Boolean> last) {
		super(string);
		setNeedle(needle);
		this.from = ExprUtils.ensureReturnType(from, Integer.class);
		this.last = ExprUtils.ensureReturnType(last, Boolean.class);
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
			if(ert != null && !StringIndexOf.ALLOWED_NEEDLE_TYPES.contains(ert))
				throw new IllegalArgumentException("Expression must return char, int, or String, but returns "
						+ ert.getName());
		}
		this.needle = needle;
	}

	public ChoreoExpr<Integer> getFrom() {
		return from;
	}

	public void setFrom(ChoreoExpr<Integer> from) {
		this.from = ExprUtils.ensureReturnType(from, Integer.class);
	}

	public ChoreoExpr<Boolean> getLast() {
		return last;
	}

	public void setLast(ChoreoExpr<Boolean> last) {
		this.last = ExprUtils.ensureReturnType(last, Boolean.class);
	}

	public Class<Integer> getReturnType() {
		return Integer.class;
	}

	public Integer evaluate(RunContext context) throws ChoreoRunException {
		String ehaystack = ExprUtils.reduce(string, context);
		Object eneedle = ExprUtils.reduce(needle, context);
		Integer efrom = ExprUtils.reduce(from, context);
		Boolean elast = ExprUtils.reduce(last, context);
		if(eneedle instanceof String) {
			String sneedle = (String)eneedle;
			if(efrom == null)
				return elast == null || !elast ? ehaystack.indexOf(sneedle) : ehaystack.lastIndexOf(sneedle);
			else
				return elast == null || !elast
						? ehaystack.indexOf(sneedle, efrom) : ehaystack.lastIndexOf(sneedle, efrom);
		}
		int ineedle = eneedle instanceof Character ? (int)(Character)eneedle : (Integer)eneedle;
		if(efrom == null)
			return elast == null || !elast ? ehaystack.indexOf(ineedle) : ehaystack.lastIndexOf(ineedle);
		else
			return elast == null || !elast
					? ehaystack.indexOf(ineedle, efrom) : ehaystack.lastIndexOf(ineedle, efrom);
	}

}
