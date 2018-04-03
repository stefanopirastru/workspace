import java.util.*;
public class BasicGraph<NodeInfo , EdgeInfo> {
	
	/**
	 * 
	 * Classe base per rappresentare grafi
	 * 
	 * I nodi sono identificati univocamente con un indice numerico[0, maxNodes-1]
	 * maxNodes è il parametro che deve essere fornito al costruttore
	 * ogni nodo può essere etichettato con un oggetto 'NodeInfo'
	 * ogni arco può essere ettichettato con un oggetto 'EdgeInfo' 
	 * 
	 * @param <EdgeInfo> informazione associata all'arco
	 * @param <NodeInfo> informazione associata al nodo
	 */
	private ArrayList<ArrayList<Label<Integer, EdgeInfo>>> V;
	private ArrayList<NodeInfo> Vinfo;
	private int nodeCount;
	private int edgeCount;
	public void BasicGraph(int a){
		this.edgeCount=a;
	}
	public int getNodeCount(){
		return this.nodeCount;
	}
	public void setNodeCount(int b){
		this.nodeCount=b;
	}
	public int getEdgeCount(){
		return this.edgeCount;
	}
	public int setEdgeCount(int c){
		this.edgeCount=c;
	}
	public int getMinNodeId(){
		
	}
	public int getMaxNodeId(){
		
	}
	public boolean hasNodeId(int a){
		
	}
	public boolean hasNode(){
		
	}
	public boolean hasEdgeNodes(int a, int b){
		
	}
	public boolean hasEdge(int a, int b){
		
	}
	public void resize(int a){
		
	}
	public void addNode(int a, NodeInfo b){
		
	}
	public void updateNode(int a, NodeInfo b){
		
	}
	public void addEdge(int a, int b, EdgeInfo c){
		
	}
	public void addEdgeUnchecked(int a, int b, EdgeInfo c){
		
	}
	public void updateEdge(int a, int b, EdgeInfo c){
		
	}
	public void removeNode(int a){
	
	}
	public void removeEdge(int a, int b){
		
	}
	public ListIterator<Label<Integer, EdgeInfo>> getEdges(int a){
		
	}
	public NodeInfo  getNodeInfo(int a){
		
		
	}
}
