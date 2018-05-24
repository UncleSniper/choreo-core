package org.unclesniper.choreo.core;

public class LoopInterruptSignal extends RuntimeException {

	private final boolean keepGoing;

	private final String loopLabel;

	private final Object returnValue;

	public LoopInterruptSignal(boolean keepGoing, String loopLabel, Object returnValue) {
		super("Loop interrupt signal (\"" + (keepGoing ? "continue" : "break") + "\")"
				+ (loopLabel == null ? "" : " for loop with label '" + loopLabel + '\''));
		this.keepGoing = keepGoing;
		this.loopLabel = loopLabel;
		this.returnValue = returnValue;
	}

	public boolean isKeepGoing() {
		return keepGoing;
	}

	public String getLoopLabel() {
		return loopLabel;
	}

	public Object getReturnValue() {
		return returnValue;
	}

}
