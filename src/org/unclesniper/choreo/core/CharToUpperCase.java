package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("charToUpperCase")
public class CharToUpperCase extends CharTransform {

	public CharToUpperCase() {}

	public CharToUpperCase(ChoreoExpr<Character> character) {
		super(character);
	}

	protected char map(char c) {
		return Character.toUpperCase(c);
	}

}
