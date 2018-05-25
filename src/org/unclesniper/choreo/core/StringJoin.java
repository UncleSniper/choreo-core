package org.unclesniper.choreo.core;

import java.util.List;
import java.util.LinkedList;
import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;

public class StringJoin implements ChoreoExpr<String> {

	private ChoreoExpr<? extends CharSequence> delimiter;

	private final List<ChoreoExpr<? extends CharSequence>> elements
			= new LinkedList<ChoreoExpr<? extends CharSequence>>();

	public StringJoin() {}

	public StringJoin(ChoreoExpr<? extends CharSequence> delimiter) {
		this.delimiter = ExprUtils.ensureReturnTypeE(delimiter, CharSequence.class);
	}

	public ChoreoExpr<? extends CharSequence> getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(ChoreoExpr<? extends CharSequence> delimiter) {
		this.delimiter = ExprUtils.ensureReturnTypeE(delimiter, CharSequence.class);
	}

	public void setDelimiter(CharSequence delimiter) {
		this.delimiter = delimiter == null ? null : new ConstantExpr<CharSequence>(delimiter);
	}

	public Iterable<ChoreoExpr<? extends CharSequence>> getElements() {
		return elements;
	}

	@DefaultAdder
	public void addElement(ChoreoExpr<? extends CharSequence> element) {
		if(element != null)
			elements.add(element);
	}

	@DefaultAdder
	public void addElement(CharSequence element) {
		if(element != null)
			elements.add(new ConstantExpr<CharSequence>(element));
	}

	public void clearElements() {
		elements.clear();
	}

	public Class<? extends String> getReturnType() {
		return String.class;
	}

	public String evaluate(RunContext context) throws ChoreoRunException {
		CharSequence edelemiter = delimiter == null ? null : delimiter.evaluate(context);
		List<CharSequence> eelements = new LinkedList<CharSequence>();
		for(ChoreoExpr<? extends CharSequence> element : elements) {
			CharSequence eelement = element.evaluate(context);
			if(eelement != null)
				eelements.add(eelement);
		}
		return String.join(edelemiter == null ? "" : edelemiter, eelements);
	}

}
