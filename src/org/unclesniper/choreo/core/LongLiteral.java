package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.annotation.ElementClass;
import org.unclesniper.choreo.annotation.DefaultAdder;

@ElementClass("long")
public class LongLiteral implements ChoreoExpr<Long> {

	private long value;

	public LongLiteral() {}

	public LongLiteral(long value) {
		this.value = value;
	}

	public long getValue() {
		return value;
	}

	@DefaultAdder
	public void setValue(long value) {
		this.value = value;
	}

	public Class<Long> getReturnType() {
		return Long.class;
	}

	public Long evaluate(RunContext context) {
		return value;
	}

}
