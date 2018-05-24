package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("until")
public class UntilContruct<ReturnT> extends AbstractLoopConstruct<ReturnT> {

	public UntilContruct() {
		this(null, null);
	}

	public UntilContruct(ChoreoExpr<Boolean> condition, ChoreoExpr<? extends ReturnT> body) {
		super(true, true, condition, body);
	}

}
