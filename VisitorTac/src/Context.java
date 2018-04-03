import java.util.*;

/**
 * Classe per la gestione del contesto (pattern Interpreter)
 * Si tratta di un Wrapper per una tabella dei simboli
 * 
 * @author Armando Tacchella
 *
 */
public class Context {
	
	private HashMap<String, Integer> symbolTable;
	
	public Context() {
		symbolTable = new HashMap<String,Integer>();
	}
	
	public void setVariable(String id, int value) {
		symbolTable.put(id,  value);
	}
	
	public Integer getVariable(String id) {
		return symbolTable.get(id);
	}
	
}