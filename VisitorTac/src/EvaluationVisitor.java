import java.util.*;

/**
 * Visitor concreto per la valutazione di espressioni (pattern Visitor)
 * Visita l'albero e valuta le espressioni utilizzando le definizioni
 * di variabili contenute in un oggetto <code>Context</code>.
 * Valuta le espressioni utilizzando un accumulatore a stack (meccanismo
 * simile a quello utilizzato dalla JVM).
 * 
 * @author ArmandoTacchella
 *
 */
public class EvaluationVisitor implements ExpressionVisitor {
	/**
	 * Accumulatore per la valutazione
	 */
	private Stack<Integer>  accumulator;

	/**
	 * Tabella dei simboli per valutazione delle variabili 
	 */
	private Context         context;
	
	/**
	 * Costruttore. Non c'è costruttore di default in quanto è
	 * necessario un oggetto Context per la valutazione. 
	 * 
	 * @param c l'oggetto Context con il mapping tra variabili e valori
	 */
	public EvaluationVisitor(Context c) {
		accumulator = new Stack<Integer>();
		context = c;
	}
	
	public void visit(NumberExpr toVisit) {
		accumulator.push(toVisit.getValue());
	}

	public void visit(VariableExpr toVisit) {
		Integer value = context.getVariable(toVisit.getName());
		accumulator.push(value);
	}

	public void visit(OperatorExpr toVisit) {
		Expression exprLeft = toVisit.getLeftOperand();
		Expression exprRight = toVisit.getRightOperand();
		exprLeft.accept(this);
		exprRight.accept(this);
		int valueRight = accumulator.pop();
		int valueLeft = accumulator.pop();
		OperatorExpr.OperatorTag op = toVisit.getOperator(); 
		switch (op) {
		case ADD :
			accumulator.push(valueLeft + valueRight);
			break;
		case SUB :
			accumulator.push(valueLeft - valueRight);
			break;
		case MUL :
			accumulator.push(valueLeft * valueRight);
			break;
		case DIV :
			accumulator.push(valueLeft / valueRight);
			break;
		case NOP :
			break;
		}
	}

	/**
	 * Metodo per l'interrogazione del valore dell'espressione.
	 * @return Il valore sulla cima dello stack
	 */
	public int getValue() {
		return accumulator.lastElement();
	}
	
	/**
	 * Cancella i contenuti dell'accumulatore interno. Per come funziona
	 * l'accumulatore, se un'espressione viene valutata più volte, è 
	 * l'ultima valutazione quella che viene prelevata da <code>getValue</code>
	 */
	public void reset() {
		accumulator.clear();
	}

}