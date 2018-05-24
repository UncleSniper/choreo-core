package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;

public interface ChoreoExpr<ReturnT> {

	Class<? extends ReturnT> getReturnType();

	ReturnT evaluate(RunContext context) throws ChoreoRunException;

}
