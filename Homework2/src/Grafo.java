import java.util.ArrayList;


public class Grafo<T> {
	public ArrayList<ArrayList<T>> V;
	public void graphInit(){
		V=new ArrayList<ArrayList<T>>();
	}
	
	public void link(Grafo<T> G, T v, int u){
		G.V.get(u).add(v);
	}
	public void unlink(Grafo<T> G, int u, T v){
		G.V.get(u).remove(v);
	}
	public boolean areLinked(Grafo<T> G, int u, T v){
		return G.V.get(u).contains(v);
	}
	

}
