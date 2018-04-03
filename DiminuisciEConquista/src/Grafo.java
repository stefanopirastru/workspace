import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Grafo<T> {
	public ArrayList<ArrayList<T>> V;
	public Grafo<T> graphInit(int n){
		Grafo<T> G= new Grafo<T>();
		G.V=new ArrayList<ArrayList<T>>();
		ArrayList<T> innerArray=null;
		for(int i=0;i<(n-1);i++){
			 G.V.add(new ArrayList<T>());
		}
		return G;
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
