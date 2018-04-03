
public class Node implements Comparable<Node>{
	//struttura dati per costituire un elemento del dizionario
	public String key;
	//@param key: parola del dizionario
	public int value;
	//@param value: occorrenza della parola
	public Node(String k, int v){
		//costruttore con parametri
		this.key=k;
		this.value=v;
	}
	public boolean equals(Object other){
		//override comparable
		if(other!=this) return true;
		if(!(other instanceof Node)) return false;
		Node o= (Node) other;
		return(key.equals(o.key));
	}
	public int compareTo(Node other){
		//override comparable
		return key.compareTo(other.key);
	}
	public String toString(){
		//per poterla stampare
		return Integer.toString(value);
	}
}
