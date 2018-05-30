package org.unclesniper.choreo.core;

import org.unclesniper.choreo.Doom;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("relation")
public class NativeRelationalOperator extends AbstractRelationalOperator {

	public enum Operator {
		LESS,
		LESS_EQUAL,
		GREATER,
		GREATER_EQUAL
	}

	private Operator operator;

	public NativeRelationalOperator() {}

	public NativeRelationalOperator(Operator operator) {
		this.operator = operator;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	protected boolean compare(byte a, byte b) {
		switch(operator) {
			case LESS:
				return a < b;
			case LESS_EQUAL:
				return a <= b;
			case GREATER:
				return a > b;
			case GREATER_EQUAL:
				return a >= b;
			default:
				throw new Doom("Unrecognized operator: " + operator.name());
		}
	}

	protected boolean compare(short a, short b) {
		switch(operator) {
			case LESS:
				return a < b;
			case LESS_EQUAL:
				return a <= b;
			case GREATER:
				return a > b;
			case GREATER_EQUAL:
				return a >= b;
			default:
				throw new Doom("Unrecognized operator: " + operator.name());
		}
	}

	protected boolean compare(int a, int b) {
		switch(operator) {
			case LESS:
				return a < b;
			case LESS_EQUAL:
				return a <= b;
			case GREATER:
				return a > b;
			case GREATER_EQUAL:
				return a >= b;
			default:
				throw new Doom("Unrecognized operator: " + operator.name());
		}
	}

	protected boolean compare(long a, long b) {
		switch(operator) {
			case LESS:
				return a < b;
			case LESS_EQUAL:
				return a <= b;
			case GREATER:
				return a > b;
			case GREATER_EQUAL:
				return a >= b;
			default:
				throw new Doom("Unrecognized operator: " + operator.name());
		}
	}

	protected boolean compare(float a, float b) {
		switch(operator) {
			case LESS:
				return a < b;
			case LESS_EQUAL:
				return a <= b;
			case GREATER:
				return a > b;
			case GREATER_EQUAL:
				return a >= b;
			default:
				throw new Doom("Unrecognized operator: " + operator.name());
		}
	}

	protected boolean compare(double a, double b) {
		switch(operator) {
			case LESS:
				return a < b;
			case LESS_EQUAL:
				return a <= b;
			case GREATER:
				return a > b;
			case GREATER_EQUAL:
				return a >= b;
			default:
				throw new Doom("Unrecognized operator: " + operator.name());
		}
	}

	protected boolean compare(char a, char b) {
		switch(operator) {
			case LESS:
				return a < b;
			case LESS_EQUAL:
				return a <= b;
			case GREATER:
				return a > b;
			case GREATER_EQUAL:
				return a >= b;
			default:
				throw new Doom("Unrecognized operator: " + operator.name());
		}
	}

	protected boolean compare(String a, String b) {
		int cmp = a.compareTo(b);
		switch(operator) {
			case LESS:
				return cmp < 0;
			case LESS_EQUAL:
				return cmp <= 0;
			case GREATER:
				return cmp > 0;
			case GREATER_EQUAL:
				return cmp >= 0;
			default:
				throw new Doom("Unrecognized operator: " + operator.name());
		}
	}

}
