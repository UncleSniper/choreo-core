package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;

public interface ChoreoExpr<ReturnT> {

	ReturnT evaluate(RunContext context) throws ChoreoRunException;

	Class<? extends ReturnT> getReturnType();

}
