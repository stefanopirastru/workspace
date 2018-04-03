import java.util.List;

public class Graph <T>{
	public Lista[] V;
	

	public Graph<T> graphInit(int n){
		Graph G = new Graph<T>(); 
		G.V = new Lista[n];
		for(int i= 0; i<(n-1);i++){
			G.V[i].listInit();
		}
		return G;
	}
	
	public void link(Graph <T> G, int u, int v){
		G.V[u].insertFront(G.V[u], v);
	}
	public void unlink(Graph<T> G, int u, int v){
		G.V[u].delete(G.V[u],v);
	}
	public Boolean areLinked(Graph<T> G, int u, int v){
		return G.V[u].search( G.V[u], v);
	}
}
