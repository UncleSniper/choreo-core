package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("charToLowerCase")
public class CharToLowerCase extends CharTransform {

	public CharToLowerCase() {}

	public CharToLowerCase(ChoreoExpr<Character> character) {
		super(character);
	}

	protected char map(char c) {
		return Character.toLowerCase(c);
	}

}
