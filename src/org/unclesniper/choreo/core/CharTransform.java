package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;

public abstract class CharTransform implements ChoreoExpr<Character> {

	private ChoreoExpr<Character> character;

	public CharTransform() {}

	public CharTransform(ChoreoExpr<Character> character) {
		this.character = ExprUtils.ensureReturnType(character, Character.class);
	}

	public ChoreoExpr<Character> getCharacter() {
		return character;
	}

	@DefaultAdder
	public void setCharacter(ChoreoExpr<Character> character) {
		this.character = ExprUtils.ensureReturnType(character, Character.class);
	}

	public void setCharacter(Character character) {
		this.character = ConstantExpr.from(character);
	}

	public Class<Character> getReturnType() {
		return Character.class;
	}

	public Character evaluate(RunContext context) throws ChoreoRunException {
		return map(character.evaluate(context));
	}

	protected abstract char map(char c);

}
