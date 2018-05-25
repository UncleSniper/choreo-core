package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;
import org.unclesniper.choreo.annotation.DefaultAdder;

@ElementClass("charToString")
public class CharToString implements ChoreoExpr<String> {

	private ChoreoExpr<Character> character;

	public CharToString() {}

	public CharToString(ChoreoExpr<Character> character) {
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

	public Class<String> getReturnType() {
		return String.class;
	}

	public String evaluate(RunContext context) throws ChoreoRunException {
		return Character.toString(character.evaluate(context));
	}

}
