package org.unclesniper.choreo.core;

import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import org.unclesniper.choreo.Doom;
import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;

public abstract class AbstractRelationalOperator implements ChoreoExpr<Boolean> {

	private enum OperandType {
		BYTE,
		SHORT,
		INT,
		LONG,
		FLOAT,
		DOUBLE,
		CHAR,
		STRING
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
		ALLOWED_OPERAND_TYPES.add(Character.class);
		ALLOWED_OPERAND_TYPES.add(Character.TYPE);
		ALLOWED_OPERAND_TYPES.add(String.class);
	}

	private static final Map<Class<?>, OperandType> OPERAND_TYPES;

	static {
		OPERAND_TYPES = new HashMap<Class<?>, OperandType>();
		OPERAND_TYPES.put(Byte.class, OperandType.BYTE);
		OPERAND_TYPES.put(Byte.TYPE, OperandType.BYTE);
		OPERAND_TYPES.put(Short.class, OperandType.SHORT);
		OPERAND_TYPES.put(Short.TYPE, OperandType.SHORT);
		OPERAND_TYPES.put(Integer.class, OperandType.INT);
		OPERAND_TYPES.put(Integer.TYPE, OperandType.INT);
		OPERAND_TYPES.put(Long.class, OperandType.LONG);
		OPERAND_TYPES.put(Long.TYPE, OperandType.LONG);
		OPERAND_TYPES.put(Float.class, OperandType.FLOAT);
		OPERAND_TYPES.put(Float.TYPE, OperandType.FLOAT);
		OPERAND_TYPES.put(Double.class, OperandType.DOUBLE);
		OPERAND_TYPES.put(Double.TYPE, OperandType.DOUBLE);
		OPERAND_TYPES.put(Character.class, OperandType.CHAR);
		OPERAND_TYPES.put(Character.TYPE, OperandType.CHAR);
		OPERAND_TYPES.put(String.class, OperandType.STRING);
	}

	private final List<ChoreoExpr<?>> operands = new LinkedList<ChoreoExpr<?>>();

	private ChoreoExpr<Double> epsilon;

	public AbstractRelationalOperator() {}

	public ChoreoExpr<Double> getEpsilon() {
		return epsilon;
	}

	public void setEpsilon(ChoreoExpr<Double> epsilon) {
		this.epsilon = ExprUtils.ensureReturnType(epsilon, Double.class);
	}

	public Iterable<ChoreoExpr<?>> getOperands() {
		return operands;
	}

	@DefaultAdder
	public void addOperand(ChoreoExpr<?> operand) {
		if(operand == null)
			return;
		Class<?> ort = operand.getReturnType();
		if(ort != null && !AbstractRelationalOperator.ALLOWED_OPERAND_TYPES.contains(ort))
			throw new IllegalArgumentException("Operand must return byte, short int, long, float, double, "
					+ "char, or String, but returns " + ort.getName());
		operands.add(operand);
	}

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

	public void addCharacter(Character operand) {
		if(operand != null)
			operands.add(new ConstantExpr<Character>(operand));
	}

	public void addString(String operand) {
		if(operand != null)
			operands.add(new ConstantExpr<String>(operand));
	}

	public Class<Boolean> getReturnType() {
		return Boolean.class;
	}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		Double eepsilon = ExprUtils.reduce(epsilon, context);
		double vepsilon = eepsilon == null ? 0.0 : eepsilon.doubleValue();
		Object prevValue = null;
		OperandType prevType = null;
		for(ChoreoExpr<?> operand : operands) {
			Object nextValue = operand.evaluate(context);
			OperandType nextType = AbstractRelationalOperator.OPERAND_TYPES.get(nextValue.getClass());
			if(nextType == null)
				throw new ClassCastException("Operand is not a recognized ordered type: " + nextValue);
			if(prevType != null) {
				boolean cmp;
				switch(prevType) {
					case BYTE:
						cmp = forByte((Byte)prevValue, nextValue, nextType);
						break;
					case SHORT:
						cmp = forShort((Short)prevValue, nextValue, nextType);
						break;
					case INT:
						cmp = forInt((Integer)prevValue, nextValue, nextType);
						break;
					case LONG:
						cmp = forLong((Long)prevValue, nextValue, nextType);
						break;
					case FLOAT:
						cmp = forFloat((Float)prevValue, nextValue, nextType);
						break;
					case DOUBLE:
						cmp = forDouble((Double)prevValue, nextValue, nextType);
						break;
					case CHAR:
						cmp = forChar((Character)prevValue, nextValue, nextType);
						break;
					case STRING:
						cmp = forString((String)prevValue, nextValue, nextType);
						break;
					default:
						throw new Doom("Unrecognized operand type: " + prevType.name());
				}
				if(!cmp)
					return false;
			}
			prevValue = nextValue;
			prevType = nextType;
		}
		return true;
	}

	private void noString(Object value) {
		throw new IllegalArgumentException("Cowardly refusing to compare a String to a "
				+ value.getClass().getName());
	}

	private boolean forByte(byte prevValue, Object nextValue, OperandType nextType) {
		switch(nextType) {
			case BYTE:
				return compare(prevValue, (Byte)nextValue);
			case SHORT:
				return compare((short)prevValue, (Short)nextValue);
			case INT:
				return compare((int)prevValue, (Integer)nextValue);
			case LONG:
				return compare((long)prevValue, (Long)nextValue);
			case FLOAT:
				return compare((float)prevValue, (Float)nextValue);
			case DOUBLE:
				return compare((double)prevValue, (Double)nextValue);
			case CHAR:
				return compare((char)((prevValue + 256) % 256), (Character)nextValue);
			case STRING:
				noString(prevValue);
			default:
				throw new Doom("Unrecognized operand type: " + nextType.name());
		}
	}

	private boolean forShort(short prevValue, Object nextValue, OperandType nextType) {
		switch(nextType) {
			case BYTE:
				return compare(prevValue, (short)(Byte)nextValue);
			case SHORT:
				return compare(prevValue, (Short)nextValue);
			case INT:
				return compare((int)prevValue, (Integer)nextValue);
			case LONG:
				return compare((long)prevValue, (Long)nextValue);
			case FLOAT:
				return compare((float)prevValue, (Float)nextValue);
			case DOUBLE:
				return compare((double)prevValue, (Double)nextValue);
			case CHAR:
				return compare((char)prevValue, (Character)nextValue);
			case STRING:
				noString(prevValue);
			default:
				throw new Doom("Unrecognized operand type: " + nextType.name());
		}
	}

	private boolean forInt(int prevValue, Object nextValue, OperandType nextType) {
		switch(nextType) {
			case BYTE:
				return compare(prevValue, (int)(Byte)nextValue);
			case SHORT:
				return compare(prevValue, (int)(Short)nextValue);
			case INT:
				return compare(prevValue, (Integer)nextValue);
			case LONG:
				return compare((long)prevValue, (Long)nextValue);
			case FLOAT:
				return compare((float)prevValue, (Float)nextValue);
			case DOUBLE:
				return compare((double)prevValue, (Double)nextValue);
			case CHAR:
				return compare(prevValue, (int)(Character)nextValue);
			case STRING:
				noString(prevValue);
			default:
				throw new Doom("Unrecognized operand type: " + nextType.name());
		}
	}

	private boolean forLong(long prevValue, Object nextValue, OperandType nextType) {
		switch(nextType) {
			case BYTE:
				return compare(prevValue, (long)(Byte)nextValue);
			case SHORT:
				return compare(prevValue, (long)(Short)nextValue);
			case INT:
				return compare(prevValue, (long)(Integer)nextValue);
			case LONG:
				return compare(prevValue, (Long)nextValue);
			case FLOAT:
				return compare((double)prevValue, (double)(Float)nextValue);
			case DOUBLE:
				return compare((double)prevValue, (Double)nextValue);
			case CHAR:
				return compare(prevValue, (long)(Character)nextValue);
			case STRING:
			default:
				throw new Doom("Unrecognized operand type: " + nextType.name());
		}
	}

	private boolean forFloat(float prevValue, Object nextValue, OperandType nextType) {
		switch(nextType) {
			case BYTE:
				return compare(prevValue, (float)(Byte)nextValue);
			case SHORT:
				return compare(prevValue, (float)(Short)nextValue);
			case INT:
				return compare(prevValue, (float)(Integer)nextValue);
			case LONG:
				return compare((double)prevValue, (double)(Long)nextValue);
			case FLOAT:
				return compare(prevValue, (Float)nextValue);
			case DOUBLE:
				return compare((double)prevValue, (Double)nextValue);
			case CHAR:
				return compare(prevValue, (float)(Character)nextValue);
			case STRING:
				noString(prevValue);
			default:
				throw new Doom("Unrecognized operand type: " + nextType.name());
		}
	}

	private boolean forDouble(double prevValue, Object nextValue, OperandType nextType) {
		switch(nextType) {
			case BYTE:
				return compare(prevValue, (double)(Byte)nextValue);
			case SHORT:
				return compare(prevValue, (double)(Short)nextValue);
			case INT:
				return compare(prevValue, (double)(Integer)nextValue);
			case LONG:
				return compare(prevValue, (double)(Long)nextValue);
			case FLOAT:
				return compare(prevValue, (double)(Float)nextValue);
			case DOUBLE:
				return compare(prevValue, (Double)nextValue);
			case CHAR:
				return compare(prevValue, (double)(Character)nextValue);
			case STRING:
				noString(prevValue);
			default:
				throw new Doom("Unrecognized operand type: " + nextType.name());
		}
	}

	private boolean forChar(char prevValue, Object nextValue, OperandType nextType) {
		switch(nextType) {
			case BYTE:
				return compare(prevValue, (char)(((Byte)nextValue + 256) % 256));
			case SHORT:
				return compare(prevValue, (char)(short)(Short)nextValue);
			case INT:
				return compare((int)prevValue, (Integer)nextValue);
			case LONG:
				return compare((long)prevValue, (Long)nextValue);
			case FLOAT:
				return compare((float)prevValue, (Float)nextValue);
			case DOUBLE:
				return compare((double)prevValue, (Double)nextValue);
			case CHAR:
				return compare(prevValue, (Character)nextValue);
			case STRING:
				noString(prevValue);
			default:
				throw new Doom("Unrecognized operand type: " + nextType.name());
		}
	}

	private boolean forString(String prevValue, Object nextValue, OperandType nextType) {
		switch(nextType) {
			case BYTE:
			case SHORT:
			case INT:
			case LONG:
			case FLOAT:
			case DOUBLE:
			case CHAR:
				noString(nextValue);
			case STRING:
				return compare(prevValue, (String)nextValue);
			default:
				throw new Doom("Unrecognized operand type: " + nextType.name());
		}
	}

	protected abstract boolean compare(byte a, byte b);

	protected abstract boolean compare(short a, short b);

	protected abstract boolean compare(int a, int b);

	protected abstract boolean compare(long a, long b);

	protected abstract boolean compare(float a, float b);

	protected abstract boolean compare(double a, double b);

	protected abstract boolean compare(char a, char b);

	protected abstract boolean compare(String a, String b);

}
