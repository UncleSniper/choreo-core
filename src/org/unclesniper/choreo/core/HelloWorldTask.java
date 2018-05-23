package org.unclesniper.choreo.core;

import org.unclesniper.choreo.ChoreoTask;
import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("helloWorld")
public class HelloWorldTask implements ChoreoTask {

	private String who;

	public HelloWorldTask() {}

	public HelloWorldTask(String who) {
		this.who = who;
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public void execute(RunContext context) {
		System.out.println("Hello, " + (who == null ? "world" : who) + '!');
	}

}
