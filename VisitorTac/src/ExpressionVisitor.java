/**
 * Interfaccia per oggetti visitor (pattern Visitor).
 * Dichiara un metodo <code>visit</code> specializzato per ogni elemento
 * della gerarchia <code>Expression</code>.
 * 
 * @author Armando Tacchella
 *
 */
public interface ExpressionVisitor {
	public void visit(NumberExpr toVisit);
	public void visit(VariableExpr toVisit);
	public void visit(OperatorExpr toVisit);
}