package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("continue")
public class ContinueConstruct<ReturnT> extends AbstractLoopInterruptConstruct<ReturnT> {

	public ContinueConstruct() {
		this(null, null);
	}

	public ContinueConstruct(ChoreoExpr<String> label, ChoreoExpr<?> value) {
		super(true, label, value);
	}

}
