public class SimpleOperator<ValueType> extends SimpleExpression<Integer> {
	public enum OperatorTag { NOP, ADD, SUB, MUL, DIV };
	
	private OperatorTag               operator;
	private SimpleExpression<Integer> left;
	private SimpleExpression<Integer> right;
	
	public SimpleOperator(OperatorTag operator, 
			SimpleExpression<Integer> left, SimpleExpression<Integer> right) {
		this.operator = operator;
		this.left = left;
		this.right = right;
	}
	
	public Integer evaluate() {
		int valueLeft = left.evaluate();
		int valueRight = right.evaluate();
		switch (operator) {
		case ADD :
			return (valueLeft + valueRight);
		case SUB :
			return (valueLeft - valueRight);
		case MUL :
			return (valueLeft * valueRight);
		case DIV :
			return (valueLeft / valueRight);
		case NOP :
			return null;
		default :
			return null;
		}
	}
	
	public String toString() {
		String valueLeft = left.toString();
		String valueRight = right.toString();
		switch (operator) {
		case ADD :
			return ("(" + valueLeft + " + " + valueRight + ")");
		case SUB :
			return ("(" + valueLeft + " - " + valueRight + ")");
		case MUL :
			return ("(" + valueLeft + " * " + valueRight + ")");
		case DIV :
			return ("(" + valueLeft + " / " + valueRight + ")");
		case NOP :
			return null;
		default :
			return null;
		}
	}

}
