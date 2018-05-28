package org.unclesniper.choreo.core;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import org.unclesniper.choreo.Doom;
import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;

public abstract class VariadicNumericOperator extends AbstractVariadicNumericOperator {

	private static final class State {

		public boolean wide;

		public boolean fractional;

		public int intResult;

		public long longResult;

		public float floatResult;

		public double doubleResult;

		public State(int intResult) {
			this.intResult = intResult;
		}

		public State(long longResult) {
			this.longResult = longResult;
			wide = true;
		}

		public State(float floatResult) {
			this.floatResult = floatResult;
			fractional = true;
		}

		public State(double doubleResult) {
			this.doubleResult = doubleResult;
			wide = fractional = true;
		}

	}

	private enum NumberType {
		INT,
		LONG,
		FLOAT,
		DOUBLE
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
		ALLOWED_OPERAND_TYPES.add(Float.class);
		ALLOWED_OPERAND_TYPES.add(Float.TYPE);
		ALLOWED_OPERAND_TYPES.add(Double.class);
		ALLOWED_OPERAND_TYPES.add(Double.TYPE);
	}

	private static final Map<Class<?>, NumberType> NUMBER_TYPES;

	static {
		NUMBER_TYPES = new HashMap<Class<?>, NumberType>();
		NUMBER_TYPES.put(Byte.class, NumberType.INT);
		NUMBER_TYPES.put(Byte.TYPE, NumberType.INT);
		NUMBER_TYPES.put(Short.class, NumberType.INT);
		NUMBER_TYPES.put(Short.TYPE, NumberType.INT);
		NUMBER_TYPES.put(Integer.class, NumberType.INT);
		NUMBER_TYPES.put(Integer.TYPE, NumberType.INT);
		NUMBER_TYPES.put(Long.class, NumberType.LONG);
		NUMBER_TYPES.put(Long.TYPE, NumberType.LONG);
		NUMBER_TYPES.put(Float.class, NumberType.FLOAT);
		NUMBER_TYPES.put(Float.TYPE, NumberType.FLOAT);
		NUMBER_TYPES.put(Double.class, NumberType.DOUBLE);
		NUMBER_TYPES.put(Double.TYPE, NumberType.DOUBLE);
	}

	public VariadicNumericOperator() {}

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

	public void addFloat(Float operand) {
		if(operand != null)
			operands.add(new ConstantExpr<Float>(operand));
	}

	public void addDouble(Double operand) {
		if(operand != null)
			operands.add(new ConstantExpr<Double>(operand));
	}

	protected void ensureReturnType(ChoreoExpr<? extends Number> operand) {
		Class<? extends Number> ort = operand.getReturnType();
		if(ort == null)
			return;
		if(!VariadicNumericOperator.ALLOWED_OPERAND_TYPES.contains(ort))
			throw new IllegalArgumentException("Operand must return byte, short, int, long, "
					+ "float, or double, but returns " + ort.getName());
	}

	public Class<? extends Number> getReturnType() {
		NumberType aggregate = NumberType.INT;
		for(ChoreoExpr<? extends Number> operand : operands) {
			Class<? extends Number> ort = operand.getReturnType();
			NumberType nt = VariadicNumericOperator.NUMBER_TYPES.get(ort);
			if(ort == null || nt == null)
				return Number.class;
			switch(nt) {
				case INT:
					break;
				case LONG:
					if(aggregate == NumberType.INT)
						aggregate = NumberType.LONG;
					break;
				case FLOAT:
					if(aggregate == NumberType.INT || aggregate == NumberType.LONG)
						aggregate = NumberType.FLOAT;
					break;
				case DOUBLE:
					return Double.class;
				default:
					throw new Doom("Unrecognized principal numeric type: " + nt.name());
			}
		}
		switch(aggregate) {
			case INT:
				return Integer.class;
			case LONG:
				return Long.class;
			case FLOAT:
				return Float.class;
			case DOUBLE:
				return Double.class;
			default:
				throw new Doom("Unrecognized principal numeric type: " + aggregate.name());
		}
	}

	public Number evaluate(RunContext context) throws ChoreoRunException {
		State state = null;
		for(ChoreoExpr<? extends Number> operand : operands) {
			Number n = operand.evaluate(context);
			NumberType nt = VariadicNumericOperator.NUMBER_TYPES.get(n.getClass());
			if(nt == null)
				throw new ClassCastException("Operand is not an primitive integer or floating-point number: " + n);
			switch(nt) {
				case INT:
					if(state == null)
						state = new State(n.intValue());
					else
						VariadicNumericOperator.mangle(state, n.intValue());
					break;
				case LONG:
					if(state == null)
						state = new State((Long)n);
					else
						VariadicNumericOperator.mangle(state, (Long)n);
					break;
				case FLOAT:
					if(state == null)
						state = new State((Float)n);
					else
						VariadicNumericOperator.mangle(state, (Float)n);
					break;
				case DOUBLE:
					if(state == null)
						state = new State((Double)n);
					else
						VariadicNumericOperator.mangle(state, (Double)n);
					break;
				default:
					throw new Doom("Unrecognized principal numeric type: " + nt.name());
			}
		}
		if(state == null)
			return 0;
		if(state.fractional) {
			if(state.wide)
				return state.doubleResult;
			return state.floatResult;
		}
		else {
			if(state.wide)
				return state.longResult;
			return state.intResult;
		}
	}

	private static void mangle(State state, int nv) {
		if(state.fractional) {
			if(state.wide)
				state.doubleResult += (double)nv;
			else
				state.floatResult += (float)nv;
		}
		else {
			if(state.wide)
				state.longResult += (long)nv;
			else
				state.intResult += nv;
		}
	}

	private static void mangle(State state, long nv) {
		if(state.fractional) {
			if(state.wide)
				state.doubleResult += (double)nv;
			else
				state.floatResult += (float)nv;
		}
		else {
			if(state.wide)
				state.longResult += nv;
			else {
				state.longResult = (long)state.intResult + nv;
				state.wide = true;
			}
		}
	}

	private static void mangle(State state, float nv) {
		if(state.fractional) {
			if(state.wide)
				state.doubleResult += (double)nv;
			else
				state.floatResult += nv;
		}
		else {
			if(state.wide) {
				state.floatResult = (float)state.longResult + nv;
				state.wide = false;
			}
			else
				state.floatResult = (float)state.intResult + nv;
			state.fractional = true;
		}
	}

	private static void mangle(State state, double nv) {
		if(state.fractional) {
			if(state.wide)
				state.doubleResult += nv;
			else {
				state.doubleResult = (double)state.floatResult + nv;
				state.wide = true;
			}
		}
		else {
			if(state.wide)
				state.doubleResult = (double)state.longResult + nv;
			else {
				state.doubleResult = (double)state.intResult + nv;
				state.wide = true;
			}
			state.fractional = true;
		}
	}

	protected abstract int combine(int a, int b);

	protected abstract long combine(long a, long b);

	protected abstract float combine(float a, float b);

	protected abstract double combine(double a, double b);

}
