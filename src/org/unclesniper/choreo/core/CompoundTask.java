package org.unclesniper.choreo.core;

import java.util.List;
import java.util.LinkedList;
import org.unclesniper.choreo.ChoreoTask;
import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("seq")
public class CompoundTask implements ChoreoTask {

	private final List<ChoreoTask> steps = new LinkedList<ChoreoTask>();

	public CompoundTask() {}

	public Iterable<ChoreoTask> getSteps() {
		return steps;
	}

	@DefaultAdder
	public void addStep(ChoreoTask step) {
		if(step != null)
			steps.add(step);
	}

	public boolean removeStep(ChoreoTask step) {
		return steps.remove(step);
	}

	public void clearSteps() {
		steps.clear();
	}

	public void execute(RunContext context) throws ChoreoRunException {
		for(ChoreoTask step : steps)
			step.execute(context);
	}

}
