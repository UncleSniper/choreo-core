package org.unclesniper.choreo.core;

import java.util.Set;
import java.util.HashSet;
import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;

public abstract class VariadicIntegralOperator<OptionsT> extends AbstractVariadicNumericOperator {

	private static final class State {

		public boolean wide;

		public int intResult;

		public long longResult;

		public State(int intResult) {
			this.intResult = intResult;
		}

		public State(long longResult) {
			this.longResult = longResult;
			wide = true;
		}

	}

	private static final Set<Class<?>> ALLOWED_OPERAND_TYPES;

	static {
		ALLOWED_OPERAND_TYPES = new HashSet<Class<?>>();
		ALLOWED_OPERAND_TYPES.add(Byte.class);
		ALLOWED_OPERAND_TYPES.add(Byte.TYPE);
		ALLOWED_OPERAND_TYPES.add(Short.class);
		ALLOWED_OPERAND_TYPES.add(Short.TYPE);
		ALLOWED_OPERAND_TYPES.add(Integer.class);
		ALLOWED_OPERAND_TYPES.add(Integer.TYPE);
		ALLOWED_OPERAND_TYPES.add(Long.class);
		ALLOWED_OPERAND_TYPES.add(Long.TYPE);
	}

	public VariadicIntegralOperator() {}

	public void addByte(Byte operand) {
		if(operand != null)
			operands.add(new ConstantExpr<Byte>(operand));
	}

	public void addShort(Short operand) {
		if(operand != null)
			operands.add(new ConstantExpr<Short>(operand));
	}

	public void addInteger(Integer operand) {
		if(operand != null)
			operands.add(new ConstantExpr<Integer>(operand));
	}

	public void addLong(Long operand) {
		if(operand != null)
			operands.add(new ConstantExpr<Long>(operand));
	}

	protected void ensureReturnType(ChoreoExpr<? extends Number> operand) {
		Class<? extends Number> ort = operand.getReturnType();
		if(ort == null)
			return;
		if(!VariadicIntegralOperator.ALLOWED_OPERAND_TYPES.contains(ort))
			throw new IllegalArgumentException("Operand must return byte, short, int, or long, but returns "
					+ ort.getName());
	}

	public Class<? extends Number> getReturnType() {
		for(ChoreoExpr<? extends Number> operand : operands) {
			Class<? extends Number> ort = operand.getReturnType();
			if(ort == null)
				return Number.class;
			if(ort.equals(Long.class) || ort.equals(Long.TYPE))
				return Long.class;
		}
		return Integer.class;
	}

	public Number evaluate(RunContext context) throws ChoreoRunException {
		OptionsT options = evaluateOptions(context);
		State state = null;
		for(ChoreoExpr<? extends Number> operand : operands) {
			Number n = operand.evaluate(context);
			if(n instanceof Long) {
				long nv = (Long)n;
				if(state == null)
					state = new State(nv);
				else if(state.wide)
					state.longResult = combine(state.longResult, nv, options);
				else {
					state.longResult = combine((long)state.intResult, nv, options);
					state.wide = true;
				}
			}
			else {
				int nv = n.intValue();
				if(state == null)
					state = new State(nv);
				else if(state.wide)
					state.longResult = combine(state.longResult, (long)nv, options);
				else
					state.intResult = combine(state.intResult, nv, options);
			}
		}
		if(state == null)
			return 0;
		if(state.wide)
			return state.longResult;
		return state.intResult;
	}

	protected OptionsT evaluateOptions(RunContext context) throws ChoreoRunException {
		return null;
	}

	protected abstract int combine(int a, int b, OptionsT options);

	protected abstract long combine(long a, long b, OptionsT options);

}
