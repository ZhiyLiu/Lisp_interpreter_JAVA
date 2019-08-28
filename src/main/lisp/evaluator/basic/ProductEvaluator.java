package main.lisp.evaluator.basic;

import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.DecimalAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.IntegerAtom;
import main.lisp.parser.terms.NilAtom;

public class ProductEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr) {
		expr = expr.getTail();
		if (expr instanceof NilAtom || expr.getHead() instanceof NilAtom || expr.getTail() instanceof NilAtom) {
			System.err.println("Missing arguments for operator '*'");
		}
		
		SExpression firstEvaled = expr.getHead().eval();
		SExpression secondEvaled = expr.getTail().eval();
		
		IntegerAtom firstInt = null;
		IntegerAtom secondInt = null;
		DecimalAtom firstDec = null;
		DecimalAtom secondDec = null;
		
		if (firstEvaled instanceof IntegerAtom) {
			firstInt = (IntegerAtom)firstEvaled;
		}
		if (firstEvaled instanceof DecimalAtom) {
			firstDec = (DecimalAtom)firstEvaled;
		}
		
		if (secondEvaled instanceof IntegerAtom) {
			secondInt = (IntegerAtom)secondEvaled;
		}
		if (secondEvaled instanceof DecimalAtom) {
			secondDec = (DecimalAtom)secondEvaled;
		}
		
		if (firstInt == null && secondInt == null && firstDec == null && secondDec == null) {
			System.err.println("Arguments for operator '*' must both evaluate to numbers");
		}
		
		if (firstInt != null && secondInt != null) {
			long product = firstInt.getValue() * secondInt.getValue();
			return new IntegerAtom(product);
		} else {
			double product = 0;
			if (firstInt != null) {
				product = firstInt.getValue();
			} else if (firstDec != null) {
				product = firstDec.getValue();
			}
			if (secondInt != null) {
				product *= secondInt.getValue();
			} else if (secondDec != null) {
				product *= secondDec.getValue();
			}
			return new DecimalAtom(product);
		}
	}

}
