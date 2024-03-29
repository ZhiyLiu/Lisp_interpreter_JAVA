package main.lisp.evaluator.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;

public class AtomEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		
		SExpression firstEvaled = expr.eval(environment);
		
		if (firstEvaled instanceof Atom) {
			return new IdentifierAtom("T");
		} else {
			return new NilAtom();
		}
	}

}
