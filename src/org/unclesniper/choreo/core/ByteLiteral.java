package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.annotation.DefaultAdder;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("byte")
public class ByteLiteral implements ChoreoExpr<Byte> {

	private byte value;

	public ByteLiteral() {}

	public ByteLiteral(byte value) {
		this.value = value;
	}

	public byte getValue() {
		return value;
	}

	@DefaultAdder
	public void setValue(byte value) {
		this.value = value;
	}

	public Class<? extends Byte> getReturnType() {
		return Byte.class;
	}

	public Byte evaluate(RunContext context) {
		return value;
	}

}
