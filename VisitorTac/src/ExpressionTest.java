
public class ExpressionTest {

	public static void main(String[] args) {
		Context symbolTable = new Context();
		
		// Imposta il valore di x=20 e il valore di y=13
		symbolTable.setVariable("x", 20);
		symbolTable.setVariable("y", 13);
		
		// Crea due nuove espressioni per x e y
		VariableExpr x = new VariableExpr("x");
		VariableExpr y = new VariableExpr("y");
		
		// Costruisce l'espressione (x + 10)
		OperatorExpr expr = new OperatorExpr(OperatorExpr.OperatorTag.ADD, x, new NumberExpr(10));
		
		// Costruisce l'espressione (y * (x + 10))
		expr = new OperatorExpr(OperatorExpr.OperatorTag.MUL, y, expr); 
		
		// Stampa l'espressione utilizzando un oggetto PrintVisitor
		PrintVisitor printVisitor = new PrintVisitor();
		System.out.println("L'espressione costruita è:");
		expr.accept(printVisitor);
		System.out.println();
		
		// Valuta l'espressione utilizzando un ogetto EvaluationVisitor
		EvaluationVisitor evalVisitor = new EvaluationVisitor(symbolTable);
		System.out.println("L'espressione valutata è: ");
		expr.accept(evalVisitor);
		System.out.println(evalVisitor.getValue());
		System.out.println();		

	}

}