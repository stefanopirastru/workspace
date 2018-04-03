
/**
 * Generazione di grafi casuali secondo il modello di Gilbert
 * Il modello di Gilber prevede che ogni arco tra quelli possibili
 * sia generato con probabilità 'p' uniforme e indipendente dagli
 * altri archi già generati. Il grafo viene generato non orientato
 * 
 * @author tac
 *
 */
public class GilbertGraphGenerator {

	public static void main(String args[]) {
		// Se non vengono forniti almeno tre parametri segnala un problema
		if (args.length < 3) {
			System.out.println("Sintassi:");
			System.out.println("java GilbertGraphGenerator <nodi> <probabilità> <nome_file>");
			System.out.println("\n Dove:");
			System.out.println("<nodi> è il numero di nodi con identificativo [0,nodi - 1]");
			System.out.println("<probabilità> è un numero reale nell'intervallo [0,1]");
			System.out.println("<nome_file> è il nome del file (SENZA ESTENSIONE) su cui registrare il grafo\n");
			System.exit(0);
		}
		
		// Controlla che il primo parametro sia un numero di nodi lecito
		int maxNodes = 0;
		try {
			maxNodes = Integer.parseInt(args[0]);
			if (maxNodes <= 0) throw new Exception();
		} catch (Exception e) {
			System.out.println(args[0] + " non è un numero di nodi lecito!");
			System.exit(0);
		}
		
		// Controlla che il secondo parametro sia una probabilità lecita
		double probability = 0;
		try {
			probability = Double.parseDouble(args[1]);
			if ((probability < 0) || (probability > 1)) throw new Exception();
		} catch (Exception e) {
			System.out.println(args[0] + " non è una probabilità lecita!");
			System.exit(0);
		}
		
		// Crea il grafo con il modello di Gilbert e lo scrive su file
		// Utilizza un grafo non orientato
		MyGraph graph = new MyGraph(maxNodes, probability, MyGraph.GraphType.UNDIRECTED);
		graph.writeAsText(args[2] + ".txt");
		graph.writeAsDotty(args[2] + ".dot");
	}
	
}
