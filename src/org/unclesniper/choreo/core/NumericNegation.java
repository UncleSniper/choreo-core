package org.unclesniper.choreo.core;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import org.unclesniper.choreo.Doom;
import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("negate")
public class NumericNegation implements ChoreoExpr<Number> {

	private enum NumberType {
		BYTE,
		SHORT,
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
		NUMBER_TYPES.put(Byte.class, NumberType.BYTE);
		NUMBER_TYPES.put(Byte.TYPE, NumberType.BYTE);
		NUMBER_TYPES.put(Short.class, NumberType.SHORT);
		NUMBER_TYPES.put(Short.TYPE, NumberType.SHORT);
		NUMBER_TYPES.put(Integer.class, NumberType.INT);
		NUMBER_TYPES.put(Integer.TYPE, NumberType.INT);
		NUMBER_TYPES.put(Long.class, NumberType.LONG);
		NUMBER_TYPES.put(Long.TYPE, NumberType.LONG);
		NUMBER_TYPES.put(Float.class, NumberType.FLOAT);
		NUMBER_TYPES.put(Float.TYPE, NumberType.FLOAT);
		NUMBER_TYPES.put(Double.class, NumberType.DOUBLE);
		NUMBER_TYPES.put(Double.TYPE, NumberType.DOUBLE);
	}

	private ChoreoExpr<? extends Number> operand;

	public NumericNegation() {}

	public NumericNegation(ChoreoExpr<? extends Number> operand) {
		setOperand(operand);
	}

	public ChoreoExpr<? extends Number> getOperand() {
		return operand;
	}

	@DefaultAdder
	public void setOperand(ChoreoExpr<? extends Number> operand) {
		if(operand == null) {
			this.operand = null;
			return;
		}
		Class<? extends Number> ort = operand.getReturnType();
		if(ort != null && !NumericNegation.ALLOWED_OPERAND_TYPES.contains(ort))
			throw new IllegalArgumentException("Operand must return byte, short, int, long, "
					+ "float, or double, but returns " + ort.getName());
		this.operand = operand;
	}

	public void setByte(Byte operand) {
		this.operand = ConstantExpr.from(operand);
	}

	public void setShort(Short operand) {
		this.operand = ConstantExpr.from(operand);
	}

	public void setInteger(Integer operand) {
		this.operand = ConstantExpr.from(operand);
	}

	public void setLong(Long operand) {
		this.operand = ConstantExpr.from(operand);
	}

	public void setFloat(Float operand) {
		this.operand = ConstantExpr.from(operand);
	}

	public void setDouble(Double operand) {
		this.operand = ConstantExpr.from(operand);
	}

	public Class<? extends Number> getReturnType() {
		return operand == null ? null : operand.getReturnType();
	}

	public Number evaluate(RunContext context) throws ChoreoRunException {
		Number ov = operand.evaluate(context);
		NumberType nt = NumericNegation.NUMBER_TYPES.get(ov.getClass());
		if(nt == null)
			throw new ClassCastException("Operand is not a known numeric type: " + ov);
		switch(nt) {
			case BYTE:
				return -(Byte)ov;
			case SHORT:
				return -(Short)ov;
			case INT:
				return -(Integer)ov;
			case LONG:
				return -(Long)ov;
			case FLOAT:
				return -(Float)ov;
			case DOUBLE:
				return -(Double)ov;
			default:
				throw new Doom("Unrecognized numeric type: " + nt.name());
		}
	}

}
