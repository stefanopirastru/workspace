/**
 * Visitor concreto per la stampa di espressioni in sintassi LISP-like 
 * (pattern Visitor)
 * 
 * @author tac
 *
 */
public class PrintVisitor implements ExpressionVisitor {

	public PrintVisitor() {	}

	public void visit(NumberExpr toVisit) {
		System.out.print(toVisit.getValue());
	}

	public void visit(VariableExpr toVisit) {
		System.out.print(toVisit.getName());
	}

	public void visit(OperatorExpr toVisit) {
		System.out.print("(");
		OperatorExpr.OperatorTag op = toVisit.getOperator(); 
		switch (op) {
		case ADD :
			System.out.print("ADD ");
			break;
		case SUB :
			System.out.print("SUB ");
			break;
		case MUL :
			System.out.print("MUL ");
			break;
		case DIV :
			System.out.print("DIV ");
			break;
		case NOP :
			break;
		}
		Expression exprLeft = toVisit.getLeftOperand();
		Expression exprRight = toVisit.getRightOperand();
		exprLeft.accept(this);
		System.out.print(" ");
		exprRight.accept(this);
		System.out.print(")");
	}

}