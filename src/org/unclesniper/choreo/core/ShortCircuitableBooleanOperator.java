package org.unclesniper.choreo.core;

public abstract class ShortCircuitableBooleanOperator extends VariadicBooleanOperator {

	protected boolean shortCircuit = true;

	public ShortCircuitableBooleanOperator() {}

	public ShortCircuitableBooleanOperator(boolean shortCircuit) {
		this.shortCircuit = shortCircuit;
	}

	public boolean isShortCircuit() {
		return shortCircuit;
	}

	public void setShortCircuit(boolean shortCircuit) {
		this.shortCircuit = shortCircuit;
	}

}
