/**
 * Classe base per la gerarchia delle Espressioni (pattern Interpreter)
 * 
 * @author Armando Tacchella
 *
 */
public abstract class Expression {

	/**
	 * Metodo astratto accept per un oggetto che implementa l'interfaccia <code>ExpressionVisitor</code>. 
	 * Le sottoclassi di <code>Expression</code> ridefiniscono il metodo tutte allo stesso modo. 
	 * 
	 * @param visitor il riferimento al visitor 
	 */
	public abstract void accept(ExpressionVisitor visitor);

}
