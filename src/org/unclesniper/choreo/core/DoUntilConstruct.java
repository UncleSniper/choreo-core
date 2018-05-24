package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("doUntil")
public class DoUntilConstruct<ReturnT> extends AbstractLoopConstruct<ReturnT> {

	public DoUntilConstruct() {
		this(null, null);
	}

	public DoUntilConstruct(ChoreoExpr<Boolean> condition, ChoreoExpr<? extends ReturnT> body) {
		super(false, true, condition, body);
	}

}
