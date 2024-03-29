package main.lisp.evaluator.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.DecimalAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.IntegerAtom;
import main.lisp.parser.terms.NilAtom;

public class SumEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		if (expr instanceof NilAtom || expr.getHead() instanceof NilAtom || expr.getTail() instanceof NilAtom) {
			System.err.println("Missing arguments for operator '+'");
		}
		
		SExpression firstEvaled = expr.getHead().eval(environment);
		SExpression secondEvaled = expr.getTail().eval(environment);
		
		IntegerAtom firstInt = null;
		IntegerAtom secondInt = null;
		DecimalAtom firstDec = null;
		DecimalAtom secondDec = null;
		
		int correctArgs = 0;
		
		if (firstEvaled instanceof IntegerAtom) {
			firstInt = (IntegerAtom)firstEvaled;
			correctArgs++;
		}
		if (firstEvaled instanceof DecimalAtom) {
			firstDec = (DecimalAtom)firstEvaled;
			correctArgs++;
		}
		
		if (secondEvaled instanceof IntegerAtom) {
			secondInt = (IntegerAtom)secondEvaled;
			correctArgs++;
		}
		if (secondEvaled instanceof DecimalAtom) {
			secondDec = (DecimalAtom)secondEvaled;
			correctArgs++;
		}
		
		if (correctArgs != 2) {
			System.err.println("Arguments for operator '+' must both evaluate to numbers");
		}
		
		if (firstInt != null && secondInt != null) {
			long sum = firstInt.getValue() + secondInt.getValue();
			return new IntegerAtom(sum);
		} else {
			double sum = 0;
			if (firstInt != null) {
				sum += firstInt.getValue();
			} else if (firstDec != null) {
				sum += firstDec.getValue();
			}
			if (secondInt != null) {
				sum += secondInt.getValue();
			} else if (secondDec != null) {
				sum += secondDec.getValue();
			}
			return new DecimalAtom(sum);
		}
	}

}
