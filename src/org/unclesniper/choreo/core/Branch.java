package org.unclesniper.choreo.core;

import org.unclesniper.choreo.ChoreoTask;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("branch")
public final class Branch<ReturnT> {

	private ChoreoExpr<Boolean> condition;

	private ChoreoExpr<? extends ReturnT> value;

	public Branch() {}

	public Branch(ChoreoExpr<Boolean> condition, ChoreoExpr<? extends ReturnT> value) {
		this.condition = ExprUtils.ensureReturnType(condition, Boolean.class);
		this.value = value;
	}

	public ChoreoExpr<Boolean> getCondition() {
		return condition;
	}

	public void setCondition(ChoreoExpr<Boolean> condition) {
		this.condition = ExprUtils.ensureReturnType(condition, Boolean.class);
	}

	public ChoreoExpr<? extends ReturnT> getValue() {
		return value;
	}

	public void setValue(ChoreoExpr<? extends ReturnT> value) {
		this.value = value;
	}

	public void setTask(ChoreoTask task) {
		this.value = task == null ? null : new Return<ReturnT>(task, null);
	}

	public int hashCode() {
		int chc = condition == null ? 0 : condition.hashCode();
		int vhc = value == null ? 0 : value.hashCode();
		return ((chc << 16) | (chc >>> 16)) ^ vhc;
	}

	@SuppressWarnings("unchecked")
	public boolean equals(Object other) {
		if(other == null || !(other instanceof Branch))
			return false;
		Branch<ReturnT> ob = (Branch)other;
		if(condition == null) {
			if(ob.condition != null)
				return false;
		}
		else {
			if(ob.condition == null)
				return false;
			if(!condition.equals(ob.condition))
				return false;
		}
		if(value == null)
			return ob.value == null;
		if(ob.value == null)
			return false;
		return value.equals(ob.value);
	}

}
