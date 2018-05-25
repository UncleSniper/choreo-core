package org.unclesniper.choreo.core;

import java.util.List;
import java.util.LinkedList;
import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoTask;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("if")
public class IfConstruct<ReturnT> implements ChoreoTask, ChoreoExpr<ReturnT> {

	private final List<Branch<? extends ReturnT>> branches = new LinkedList<Branch<? extends ReturnT>>();

	private ChoreoExpr<? extends ReturnT> elseBranch;

	public IfConstruct() {}

	public Iterable<Branch<? extends ReturnT>> getBranches() {
		return branches;
	}

	@DefaultAdder
	public void addBranch(Branch<? extends ReturnT> branch) {
		if(branch != null)
			branches.add(branch);
	}

	public boolean removeBranch(Branch<? extends ReturnT> branch) {
		return branches.remove(branch);
	}

	public void clearBranches() {
		branches.clear();
	}

	public ChoreoExpr<? extends ReturnT> getElse() {
		return elseBranch;
	}

	public void setElse(ChoreoExpr<? extends ReturnT> elseBranch) {
		this.elseBranch = elseBranch;
	}

	public void setElseObject(ReturnT value) {
		this.elseBranch = ConstantExpr.from(value);
	}

	@SuppressWarnings("unchecked")
	public Class<? extends ReturnT> getReturnType() {
		Class returnType = null;
		for(Branch<? extends ReturnT> branch : branches) {
			ChoreoExpr<? extends ReturnT> value = branch.getValue();
			if(value == null)
				continue;
			Class brt = value.getReturnType();
			if(brt == null)
				return null;
			if(returnType == null)
				returnType = brt;
			else
				// Technically, this need not necessarily work, since
				// ReturnT might be on an auxiliary path (i.e. along
				// 'implements' links, not just 'extends' links).
				// On the other hand, all this stuff *is* meant to
				// be used at runtime, so what the hey, right?
				returnType = ExprUtils.commonAncestor(returnType, brt);
		}
		return returnType;
	}

	public void execute(RunContext context) throws ChoreoRunException {
		evaluate(context);
	}

	public ReturnT evaluate(RunContext context) throws ChoreoRunException {
		for(Branch<? extends ReturnT> branch : branches) {
			ChoreoExpr<Boolean> condition = branch.getCondition();
			if(condition == null)
				continue;
			Boolean test = condition.evaluate(context);
			if(test == null || !test)
				continue;
			ChoreoExpr<? extends ReturnT> body = branch.getValue();
			return ExprUtils.reduce(body, context);
		}
		return ExprUtils.reduce(elseBranch, context);
	}

}
