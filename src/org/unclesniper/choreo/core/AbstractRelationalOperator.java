package org.unclesniper.choreo.core;

import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
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
				//TODO
			}
			prevValue = nextValue;
			prevType = nextType;
		}
		return true;
	}

}
