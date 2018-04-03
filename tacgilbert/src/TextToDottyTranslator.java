
/**
 * Traduce un file da formato testo a formato DOT. Gestisce il 
 * tipo di grafo passato come input cambiando l'output di conseguenza. 
 * 
 * @author tac
 *
 */
public class TextToDottyTranslator {

	public static void main(String[] args) {
		// Se non vengono forniti almeno due parametri segnala un problema
		if (args.length < 3) {
			System.out.println("Sintassi:");
			System.out.println("java TextToDottyTranslator <grafoText> <grafoDOT> <tipo>");
			System.out.println("\n Dove:");
			System.out.println("<grafoText> è il nome del file del grafo in formato testo");
			System.out.println("<grafoDOT> è il nome del file del grafo in formato DOT");
			System.out.println("<tipo> è 'O' per Orientato oppure 'N' per Non orientato\n");
			System.exit(0);
		}
				
		MyGraph.GraphType gt = 
				(args[2].equals("O") ? MyGraph.GraphType.DIRECTED : MyGraph.GraphType.UNDIRECTED); 
		
		// Legge il grafo in formato testo utilizzando il costruttore
		MyGraph graph = new MyGraph(args[0],gt);
		
		// Stampa il grafo in formato DOT
		graph.writeAsDotty(args[1]);
	}	

}
