package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("while")
public class WhileConstruct<ReturnT> extends AbstractLoopConstruct<ReturnT> {

	public WhileConstruct() {
		this(null, null);
	}

	public WhileConstruct(ChoreoExpr<Boolean> condition, ChoreoExpr<? extends ReturnT> body) {
		super(true, false, condition, body);
	}

}
