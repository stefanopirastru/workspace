
/**
 * Classe per la gestione di valori numerici interi (pattern Interpreter)
 * 
 * @author Armando Tacchella
 * @see Expression
 *
 */
public class NumberExpr extends Expression {
	private int value;	
	
	public NumberExpr() {
		value = 0;
	}
	
	public NumberExpr(int v) {
		value = v;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int v) {
		value = v;
	}
	
	public void accept(ExpressionVisitor visitor) {
		visitor.visit(this);
	}
}