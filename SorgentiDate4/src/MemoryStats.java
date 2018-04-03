// Classe per interrogare i dati riguardanti la memoria 
public class MemoryStats {
	
	private static int MB = 1024 * 1024;
	     
	public static String getStatistics() {
		StringBuffer result = new StringBuffer();
		
		// La classe Runtime ha un metodo statico 'getRuntime()' che restituisce
		// un nuovo oggetto della classe Runtime ("factory method")
		// Per maggiori dettagli su Runtime: http://docs.oracle.com/javase/7/docs/api/java/lang/Runtime.html
	    Runtime runtime = Runtime.getRuntime();
	        
	    // Memoria attualmente in uso (totale - libera)
	    result.append("Memoria utilizzata:" + (runtime.totalMemory() - runtime.freeMemory()) / MB + "MB\n");
	        
	    // Memoria attualmente libera nella JVM
	    result.append("Memoria libera:" + runtime.freeMemory() / MB + "MB\n");
	    
	    // Memoria totale  nella JVM
	    result.append("Memoria totale:" + runtime.totalMemory() / MB + "MB\n");
	    
	    // Memoria massima che la JVM può allocare
	    result.append("Memria massima:" + runtime.maxMemory() / MB + "MB\n");
	        
	    return result.toString();
	}

}