import edu.princeton.cs.algs4.StdRandom;

public class StringVsStringBuffer {
	
	public static void fillWithRandomInts(int[] table) {
		for (int i = 0; i < table.length; ++i) {
			table[i] = StdRandom.uniform(Integer.MAX_VALUE);
		}
	}
	
	// Tabulazione di un array utilizzando concatenazione
	// tra stringhe. ATTENZIONE: inefficiente in memoria e tempo
	public static String tabulateWithString(int[] aTable) {
		String result = new String();
		for (int i = 0; i < aTable.length; ++i) {
			result = result + aTable[i] + "\n";
		}
		return result;
	}
	
	// Tabulazione di un array utilizzando StringBuffer
	// StringBuffer è meno efficiente di StringBuilder
	// ma è utilizzabile per modificare il buffer in
	// thread (flussi di esecuzione) diversi.
	public static String tabulateWithStringBuffer(int[] aTable) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < aTable.length; ++i) {
			result.append(aTable[i] + "\n");
		}
		return result.toString();
	}
	
	// Tabulazione di un array utilizzando StringBuilder
	// StringBuilder è più efficiente di StringBuffer
	// ma non è utilizzabile se a modificare il buffer
	// sono thread (flussi di esecuzione) diversi.
	public static String tabulateWithStringBuilder(int[] aTable) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < aTable.length; ++i) {
			result.append(aTable[i] + "\n");
		}
		return result.toString();
	}

	
	public static void main(String[] args) {
		if (args.length < 2) {
			System.exit(0);
		}
		// max: lunghezza massima degli array da generare
		int max = Integer.parseInt(args[0]);
		// step: passo di generazione
		int step = Integer.parseInt(args[1]);
		
		// Genero array da "step" elementi a "max" elementi con passo "step"
		for (int n = step; n < max; n += step) {

			// Genero un array pieno di interi causali nel range 0..Integer.MAX_VALUE
			int[] table = new int[n];
			fillWithRandomInts(table);
			
			// Tabulo il vettore con 'tabulateWithString'
			Timer timer = new Timer();
			timer.start();
			String message1 = tabulateWithString(table);
			timer.stop();
			long elapsed1 = timer.getElapsedMilliSeconds();
			
			// Tabulo il vettore con 'tabulateWithStringBuffer'
			timer.start();
			String message2 = tabulateWithStringBuffer(table);
			timer.stop();
			long elapsed2 = timer.getElapsedMilliSeconds();
			
			// Stampo: dimensione del vettore, tempo di tabulateWithString 
			// e tempo di tabulateWithBuffer
			System.out.println(n + " " + elapsed1 + " " + elapsed2);
		}
		
	}

}