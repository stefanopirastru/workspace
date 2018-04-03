import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * Classe per la realizzazione di un grafo  
 * con archi e nodi etichettati con oggetti generici
 * 
 * @author tac
 *
 */
public class MyGraph extends BasicGraph<Object, Object> {
	
	public enum GraphType {
		UNDIRECTED,
		DIRECTED
	}
	
	private GraphType graphType;
	
	/**
	 * Costruisce un grafo vuoto con un dato numero massimo di nodi
	 * 
	 * @param maxNodes numero massimo di nodi
	 * @param gt tipo di grafo (DIRECTED oppure UNDIRECTED)
	 */
	public MyGraph(int maxNodes, GraphType gt) {
		super(maxNodes);
		graphType = gt;
	}
	
	/**
	 * Costruisce un grafo leggendolo da un file di testo
	 * Assume che la prima riga contenga il numero massimo di Nodi
	 * e che le righe successive contengano coppie di identificativi di nodi
	 * separati da spazio per denotare gli archi (una coppia per riga) 
	 * 
	 * @param inputFileName nome del file di input completo di percorso
	 * @param gt tipo di grafo (DIRECTED oppure UNDIRECTED)
	 */
	public MyGraph(String inputFileName, GraphType gt) {
		// Costruisco un grafo senza nodi
		super(0);
		graphType = gt;
		FileReader inputFileReader = null;
		try { 
			inputFileReader = new FileReader(inputFileName);
		} catch (Exception e) {
			return;
		}
		// inputFileReader è sicuramente diverso da null
		Scanner inputScanner = new Scanner(inputFileReader);
		// Leggo il numero di nodi del grafo e lo dimensiono correttamente
		int maxNodes = Integer.parseInt(inputScanner.nextLine());
		resize(maxNodes);
		// Leggo il file di input riga per riga, ed estraggo le informazioni
		// sugli archi. 
		while (inputScanner.hasNextLine()) {
			String inputLine = inputScanner.nextLine();
			// Il metodo 'split' separa la linea in un vettore di stringhe
			String[] edge = inputLine.split(" ");
			// Il primo e il secondo elemento del vettore 'edge' sono i
			// nodi di partenza e arrivo dell'arco
			int from = Integer.parseInt(edge[0]);
			int to = Integer.parseInt(edge[1]);
			// Aggiungo i nodi (se i nodi sono già presenti il metodo non fa nulla)
			addNode(from, null);
			addNode(to, null);
			// Aggiungo l'arco (assumo che il file in input possa contenere duplicati)
			if (graphType == GraphType.DIRECTED) {
				if (!hasEdge(from,to)) {
					addEdgeUnchecked(from, to, null);
				}
			} else {
				if (!hasEdge(from,to)) { // && !hasEdge(to,from)) { 
					addEdgeUnchecked(from, to, null);
					addEdgeUnchecked(to, from, null);
				}
			}
		}
		inputScanner.close();
	}
	
	/**
	 * Costruzione di un grafo secondo il modello di Gilbert. Tutti gli archi
	 * hanno la stessa probabilità di essere presenti, indipendentemente dagli altri.
	 * Il grafo viene creato con maxNodes nodi che possono risultare o meno connessi.
	 * 
	 * @param maxNodes il numero massimo di nodi
	 * @param probability la probabilità di connessione fra due qualsiasi nodi 
	 * (deve essere un numero compreso tra 0 e 1)
	 */
	public MyGraph(int maxNodes, double probability, GraphType gt) {
		super(maxNodes);
		graphType = gt;
		
		// Inizializzo tutti i nodi
		for (int i = 0; i < maxNodes; ++i) {
			addNode(i, null);
		}
		Random generator = new Random();
		// Per ogni arco possibile, estraggo un numero casuale tra 0 e 1.
		// Se il numero estratto è minore di 'probability' connetto 
		if (graphType == GraphType.UNDIRECTED) {
			// Se il grafo non è diretto, devo evitare archi duplicati
			for (int from = 0; from < (maxNodes - 1); ++from) {
				for (int to = from + 1; to < maxNodes; ++to) {
					if (generator.nextDouble() < probability) {
						addEdgeUnchecked(from, to, null);
						addEdgeUnchecked(to, from, null);
					}
				}
			}
		} else {
			for (int from = 0; from < maxNodes; ++from) {
				for (int to = 0; to < maxNodes; ++to) {
					if (generator.nextDouble() < probability) {
						addEdgeUnchecked(from, to, null);
					}
				}
			}
		}
	}
	
	/**
	 * Stampa il grafo nel formato DOT richiesto dal 
	 * visualizzatore dotty (parte del package graphviz)
	 * 
	 * @param outFileName nome del file di output completo di percorso
	 */
	public void writeAsDotty(String outFileName) {
		FileWriter outFileWriter = null;
		try {
			outFileWriter = new FileWriter(outFileName);
		} catch (Exception e) {
			return;
		}
		PrintWriter pw = new PrintWriter(outFileWriter);

		String sep;
		if (graphType == GraphType.DIRECTED) {
			pw.println("digraph G {");
			sep = " -> ";
		} else {
			pw.println("strict graph G {");
			sep = " -- ";
		}
		for (int id = 0; id < getMaxNodeId(); ++id) {
			ListIterator<Label<Integer,Object>> adjListItr = getEdges(id);
			if (adjListItr != null) {
				while (adjListItr.hasNext()) {
					Label<Integer, Object> p = adjListItr.next();
					pw.println(id + sep + p.first + ";");
				}
			}
		}
		pw.println("}");
		pw.close();
		try {
			outFileWriter.close();
		} catch (Exception e) {
			return;
		}
	}
	
	/**
	 * Stampa il grafo in un file di testo compatibile con
	 * il costruttore che legge il grafo da file di testo.
	 * 
	 * @param outFileName
	 */
	public void writeAsText(String outFileName) {
		FileWriter outFileWriter = null;
		try {
			outFileWriter = new FileWriter(outFileName);
		} catch (Exception e) {
			return;
		}
		PrintWriter pw = new PrintWriter(outFileWriter);

		pw.println(getMaxNodeId() + 1);
		for (int id = 0; id < getMaxNodeId(); ++id) {
			ListIterator<Label<Integer,Object>> adjListItr = getEdges(id);
			if (adjListItr != null) {
				while (adjListItr.hasNext()) {
					Label<Integer, Object> p = adjListItr.next();
					pw.println(id + " " + p.first);
				}
			}
		}
		pw.close();
		try {
			outFileWriter.close();
		} catch (Exception e) {
			return;
		}
	}


}
