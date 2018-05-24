package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("doWhile")
public class DoWhileConstruct<ReturnT> extends AbstractLoopConstruct<ReturnT> {

	public DoWhileConstruct() {
		this(null, null);
	}

	public DoWhileConstruct(ChoreoExpr<Boolean> condition, ChoreoExpr<? extends ReturnT> body) {
		super(false, false, condition, body);
	}

}
