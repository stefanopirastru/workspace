/**
 * Classe per la gestione delle variabili (identificatori - pattern Interpreter)
 * 
 * @author Armando Tacchella
 *
 */
public class VariableExpr extends Expression {
	
	private String name; //qui salvo tutti tra numeri, operatori e parentesi
	
	VariableExpr() {
		name = null;
	}
	
	VariableExpr(String id) {
		name = id;
	}
	
	String getName() {
		return name;
	}
	
	public void accept(ExpressionVisitor visitor) {
		visitor.visit(this);
	}
}