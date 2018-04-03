import java.util.ListIterator;
import java.util.ArrayList;

/**
 * Un grafo con le seguenti caratteristiche:
 * - tutti i nodi sono identificati con un indice numerico [0, maxNodes - 1]
 * - maxNodes è il numero massimo di nodi fornito  al costruttore
 * - ogni nodo può essere etichettato con un oggetto di tipo 'NodeInfo'
 * - ogni arco può essere etichettato con un oggetto di tipo 'EdgeInfo'
 * 
 * @author tac
 *
 * @param <NodeInfo> Il tipo di informazione associato al nodo
 * @param <EdgeInfo> Il tipo di informazione associato all'arco
 */
public class BasicGraph <NodeInfo, EdgeInfo> {

	private ArrayList< ArrayList< Label< Integer, EdgeInfo > > >  V;
	private ArrayList<NodeInfo> Vinfo;
	private int                 nodeCount;
	private int                 edgeCount;
	
	/**
	 * Inizializza un grafo con un dato numero massimo di nodi
	 * 
	 * @param maxNodes numero massimo di nodi (e corrispondenti indici)
	 */
	public BasicGraph(int maxNodes) {
		// 'V' è un arrayList di oggetti 'ArrayList' che 
		// rappresentano le liste di adiacenza.
		// Gli elementi sono oggetti 'Pair' formati da un
		// intero (il codice del nodo destinazione) e un oggetto 'EdgeInfo' 
		V = new ArrayList< ArrayList< Label< Integer, EdgeInfo > > >();
		for (int i = 0; i < maxNodes; ++i) {
			V.add(null);
		}
		// Le informazioni associate ai nodi sono memorizzate in 'Vinfo'
		Vinfo = new ArrayList<NodeInfo>();
		for (int i = 0; i < maxNodes; ++i) {
			Vinfo.add(null);
		}
		nodeCount = edgeCount = 0;
	}
	
	/**
	 * Interrogazione del numero di nodi nel grafo
	 * 
	 * @return numero dei nodi nel grafo
	 */
	public int getNodeCount() {
		return nodeCount;
	}
	
	/**
	 * Interrogazione del numero di archi nel grafo
	 * 
	 * @return numero degli archi
	 */
	public int getEdgeCount() {
		return edgeCount;
	}

	/**
	 * Restituisce il minimo identificativo utilizzabile
	 * 
	 * @return valore dell'identificativo
	 */
	public int getMinNodeId() {
		return 0;
	}
	
	/**
	 * Restituisce il massimo identificativo utilizzabile
	 * 
	 * @return valore dell'identificativo
	 */
	public int getMaxNodeId() {
		return Vinfo.size() - 1;
	}

	/**
	 * Predicato per il controllo di un identificativo. Non
	 * viene verificata l'esistenza del nodo, solo la liceità
	 * dell'identificativo.
	 * 
	 * @param id identificativo da controllare
	 * @return true se l'identificativo è lecito
	 */
	public boolean hasNodeId(int id) {
		return ((id >= 0) && (id < V.size()));
	}

	/**
	 * Predicato per il controllo dell'esistenza di un nodo. 
	 * Verifica che l'identificativo sia corretto e che il nodo esista.
	 * 
	 * @param id identificativo del nodo da controllare
	 * @return true se il nodo esiste
	 */
	public boolean hasNode(int id) {
		return (hasNodeId(id) && (V.get(id) != null));
	}
	
	/**
	 * Predicato per il controllo di un arco. Verifica
	 * che i nodi su cui insiste esistano entrambe. Non
	 * viene verificata l'esistenza dell'arco.
	 * @param from
	 * @param to
	 * @return
	 */
	public boolean hasEdgeNodes(int from, int to) {
		return (hasNode(from) && hasNode(to));
	}

	/**
	 * Predicato per il controllo dell'esistenza di un arco.
	 * Verifica che l'arco sia lecito e che esista.
	 * @param from
	 * @param to
	 * @return
	 */
	public boolean hasEdge(int from, int to) {
		if (hasEdgeNodes(from,to)) {
			ArrayList< Label< Integer, EdgeInfo > > adjList = V.get(from);
			Label<Integer, EdgeInfo> dummyKey = new Label<Integer, EdgeInfo>(to, null);
			if (adjList.indexOf(dummyKey) != -1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Ridimensiona un grafo, aumentando il numero massimo di nodi.
	 * Se la dimensione passata è uguale o inferiore a quella esistente
	 * non modifica il grafo.
	 * 
	 * @param maxNodes numero massimo di nodi (e corrispondenti indici)
	 */
	public void resize(int maxNodes) {
		if (maxNodes > (this.getMaxNodeId() + 1)) {
			// Aggiunge spazio per nodi con indici fino a 'maxNodes'
			for (int i = V.size(); i < maxNodes; ++i) {
				V.add(null);
			}
			// Aggiunge spazio per le informazioni relative
			for (int i = Vinfo.size(); i < maxNodes; ++i) {
				Vinfo.add(null);
			}
		}
	}
	
	
	/**
	 * Aggiunta di un nodo con identificatore 'id' 
	 * e informazione associata 'nodeInfo'. Se il nodo
	 * è già presente non ha alcun effetto.
	 * 
	 * @param id identificativo del nodo da aggiungere
	 * @param nodeInfo informazione associata al nodo
	 */
	public void addNode(int id, NodeInfo nodeInfo) {
		if (hasNodeId(id) && (V.get(id) == null)) {
			// Se l'id è valido e il nodo non è già  inserito, viene aggiunto...
			V.set(id, new ArrayList< Label< Integer, EdgeInfo > >());
			Vinfo.set(id, nodeInfo);
			nodeCount += 1;
		} // ... altrimenti il metodo non fa nulla
	}
	
	
	/**
	 * Modifica di un nodo esistente con identificatore 'id'
	 * L'informazione associata al nodo viene sostituita da 'nodeInfo'
	 * 
	 * @param id identificativo del nodo da modificare
	 * @param nodeInfo nuova informazione da associare al nodo 
	 */
	public void updateNode(int id, NodeInfo nodeInfo) {
		if (hasNode(id)) {
			// Se l'id è valido e il nodo è già  inserito, viene modificata
			// l'informazione associata al nodo
			Vinfo.set(id, nodeInfo);
		} // ... altrimenti il metodo non fa nulla
	}
	
	
	/**
	 * Aggiunta di un arco dal nodo 'from' al nodo 'to' con informazione
	 * associata 'edgeInfo'. Se l'arco è già presente non ha alcun effetto.
	 * 
	 * @param from identificativo della coda dell'arco (nodi di partenza)
	 * @param to identificativo della testa dell'arco (nodo di arrivo)
	 * @param edgeInfo informazione associata all'arco
	 */
	public void addEdge(int from, int to, EdgeInfo edgeInfo) {
		if (hasEdgeNodes(from, to)) {
			// Se 'from'  e 'to' sono validi e i relativi nodi esistono
			// aggiungo un elemento alla lista delle adiacenze di 'from'
			// che "punta" al nodo 'to' se non esiste già
			Label<Integer, EdgeInfo> edgeLabel = new Label<Integer, EdgeInfo>(to, edgeInfo);
			if (V.get(from).indexOf(edgeLabel) == -1) {
				V.get(from).add(edgeLabel);
				edgeCount += 1;
			}
		}
	}

	/**
	 * Aggiunta di un arco dal nodo 'from' al nodo 'to' con informazione
	 * associata 'edgeInfo'. Se l'arco è già presente, viene comunque aggiunto.
	 * 
	 * @param from identificativo della coda dell'arco (nodi di partenza)
	 * @param to identificativo della testa dell'arco (nodo di arrivo)
	 * @param edgeInfo informazione associata all'arco
	 */
	public void addEdgeUnchecked(int from, int to, EdgeInfo edgeInfo) {
		if (hasEdgeNodes(from, to)) {
			// Se 'from'  e 'to' sono validi e i relativi nodi esistono
			// aggiungo un elemento alla lista delle adiacenze di 'from'
			// che "punta" al nodo 'to'
			Label<Integer, EdgeInfo> edgeLabel = new Label<Integer, EdgeInfo>(to, edgeInfo);
			V.get(from).add(edgeLabel);
			edgeCount += 1;
		}
	}
	
	/**
	 * Modifica di un arco dal nodo 'from' al nodo 'to' con informazione
	 * associata 'edgeInfo'. Se l'arco non è presente non ha alcun effetto.
	 * 
	 * @param from identificativo della coda dell'arco (nodi di partenza)
	 * @param to identificativo della testa dell'arco (nodo di arrivo)
	 * @param edgeInfo nuova informazione associata all'arco
	 */
	public void updateEdge(int from, int to, EdgeInfo edgeInfo) {
		if (hasEdgeNodes(from,to)) {
			// Se 'from'  e 'to' sono validi e i relativi nodi esistono
			// cerco un elemento nella lista delle adiacenze di 'from'
			// che "punta" al nodo 'to'
			Label<Integer, EdgeInfo> edgeLabel = new Label<Integer, EdgeInfo>(to, edgeInfo);
			int pos = V.get(from).indexOf(edgeLabel);
			if (pos != -1) {
				V.get(from).set(pos, edgeLabel);
			}
		}
	}
	
	
	/**
	 * Rimozione di un nodo con identificativo 'id'. Se il nodo non
	 * esiste non ha alcun effetto.
	 * 
	 * @param id identificativo del nodo
	 */
	public void removeNode(int id) {
		if (hasNode(id) && (V.get(id) != null)) {
			// Se l'id è valido e il nodo è presente, elimino la sua lista
			// delle adiacenze e le informazioni ad esso associate
			V.set(id, null);
			Vinfo.set(id,null);
			// Devo rimuovere anche tutti gli archi entranti
			for (ArrayList< Label< Integer, EdgeInfo > > adjList : V) {
				if (adjList != null) {
					Label<Integer, EdgeInfo> dummyKey = new Label<Integer, EdgeInfo>(id, null);
					adjList.remove(dummyKey);
				}
			}
			nodeCount -= 1;
		}
	}
	
	
	/**
	 * Rimozione di un arco dal nodo 'from' al nodo 'to'. 
	 * Se l'arco non esiste non ha effetto.
	 * 
	 * @param from coda dell'arco (nodo di partenza) 
	 * @param to testa dell'arco (nodo di arrivo)
	 */
	public void removeEdge(int from, int to) {
		if (hasNode(from) && hasNode(to) && 
				(V.get(from) != null) && (V.get(to) != null)) {
			// Se 'from'  e 'to' sono validi e i relativi nodi esistono
			// elimino l'arco dalla lista delle adiacenze di 'from'
			ArrayList< Label< Integer, EdgeInfo > > adjList = V.get(from);
			Label<Integer, EdgeInfo> dummyKey = new Label<Integer, EdgeInfo>(to, null);
			adjList.remove(dummyKey);
			edgeCount -= 1;
		}
	}
	
	
	/**
	 * Restituzione di un iteratore sulla lista delle adiacenze di un nodo.
	 * Restituisce 'null' se il nodo non è presente
	 * 
	 * @param id identificativo del nodo
	 * @return un oggetto di tipo 'ListIterator'
	 */
	public ListIterator< Label< Integer, EdgeInfo > > getEdges(int id) {
		if (hasNode(id) && (V.get(id) != null)) {
			return V.get(id).listIterator();
		} else {
			return null;
		}
	}
	
	
	/**
	 * Restituzione delle informazioni associate ad un nodo. 
	 * Restituisce 'null' se il nodo non è presente
	 *   
	 * @param id identificativo del nodo
	 * @return un oggetto di tipo 'NodeInfo'
	 */
	public NodeInfo getNodeInfo(int id) {
		if (hasNode(id) && (V.get(id) != null)) {
			return Vinfo.get(id);
		} else {
			return null;
		}
	}
	
	
	/**
	 * Restituzione delle informazioni associate ad un arco.
	 * Restituisce 'null' se l'arco non è presente.
	 * 
	 * @param from coda dell'arco (nodo di partenza)
	 * @param to testa dell'arco (nodo di arrivo)
	 * @return un oggetto di tipo 'EdgeInfo'
	 */
	public EdgeInfo getEdgeInfo(int from, int to) {
		if (hasNode(from) && hasNode(to) && 
				(V.get(from) != null) && (V.get(to) != null)) {
			// Se 'from'  e 'to' sono validi e i relativi nodi esistono
			// elimino l'arco dalla lista delle adiacenze di 'from'
			ArrayList< Label< Integer, EdgeInfo > > adjList = V.get(from);
			Label<Integer, EdgeInfo> dummyKey = new Label<Integer, EdgeInfo>(to, null);
			int pos = adjList.indexOf(dummyKey);
			if (pos >= 0) {
				return adjList.get(pos).second;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
}
