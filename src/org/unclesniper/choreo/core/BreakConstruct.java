package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("break")
public class BreakConstruct<ReturnT> extends AbstractLoopInterruptConstruct<ReturnT> {

	public BreakConstruct() {
		this(null, null);
	}

	public BreakConstruct(ChoreoExpr<String> label, ChoreoExpr<?> value) {
		super(false, label, value);
	}

}
