package struttureDati;

public class GraphList {
	public List[] V;
	public GraphList graphLInit(int n){
		GraphList g= new GraphList();
		g.V= new List[n];
		int i;
		for(i=0;i<n;i++){
			g.V[i]= g.V[i].listInit();
		}
		return g;
	}
	public void link(GraphList g, int u, int v){
		g.V[u].insertFront(g.V[u], v);
	}
	public void unLink(GraphList g, int u, int v){
		g.V[u].delete(g.V[u], v);
	}
	public boolean areLinked(GraphList g, int u, int v){
		return g.V[u].search(g.V[u], v);
	}
	
}
