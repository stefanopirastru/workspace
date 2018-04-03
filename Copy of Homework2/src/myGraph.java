import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class myGraph {
	
	public class node{
		GridWorld.Coordinate c;
		ArrayList<node> adiacenze;
		
		node(GridWorld.Coordinate m){
			c=m;
			adiacenze=new ArrayList<node>();
		}
		
		public boolean compareTo(node o) {
			if(o.c.row==c.row && o.c.col==c.col)return true;
			return false;
		}
		
		public String toString(){
			return c.toString();
		}
	}
	//classegraph
	
	ArrayList<node> graph;
	Stack<Integer> d;
	Stack<node> p;
	
	
	myGraph(){
		graph=new ArrayList<node>();
		d=new Stack<Integer>();
		p=new Stack<node>();
	}
	
	void addNode(node toadd){
		graph.add(toadd);
		//System.out.println("aggiungo node : " + toadd.toString());
	}
	//aggiunge nodo al grafo O(1)
	
	void addBranch(node a, node b){
		if(!parseadiacenze(a,b.c)&& !a.compareTo(b)){
			a.adiacenze.add(b);
			b.adiacenze.add(a);
			//System.out.println("creo branch"+a+b);
		}
	}
	//aggiunge ramo al grafo O(1)
	
	void readMatrix(GridWorld.Coordinate[][] matrix){
		int ontito=0;
		int [][]onato=new int[matrix.length][matrix[0].length];
		
		for (int i=0; i<matrix.length;i++){
			for(int j=0;j<matrix.length;j++){
				if(matrix[i][j]!=null) {
					addNode(new node(matrix[i][j]));
					onato[i][j]=ontito;
					//System.out.println(matrix[i][j]);
				}else{
					onato[i][j]=0;
					ontito++;
				}
			}
		}
		
		
		for (int i=0; i<matrix.length;i++){
			for(int j=0;j<matrix.length;j++){
				//
				//System.out.println("i "+i +"  j "+j+"  matrix[i][j] "+ matrix[i][j]);
				//System.out.println("i*matrix[0].length: "+ i*matrix[0].length + "   j  "+ j + "    fraph leght  " +graph.size());
				if(matrix[i][j]!=null){
					if(i+1<matrix.length && matrix[i+1][j]!=null){
						//System.out.println("dw "+matrix[i+1][j]);
						addBranch(graph.get((i*matrix.length)+j-onato[i][j]),graph.get(((i+1)*matrix.length)+j-onato[i+1][j]));
					}
					if(j+1<matrix[0].length && matrix[i][j+1]!=null){
						//System.out.println("dx  "+matrix[i][j+1]);
						addBranch(graph.get((i*matrix.length)+j-onato[i][j]),graph.get((i*matrix.length)+j-onato[i][j+1]+1));					
					}
					if((i-1)>0 && matrix[i-1][j]!=null){
						//System.out.println("ip "+matrix[i-1][j]);
						addBranch(graph.get((i*matrix.length)+j-onato[i][j]),graph.get(((i-1)*matrix.length)+j-onato[i-1][j]));
					}
					if(j-1>0 && matrix[i][j-1]!=null){
						//System.out.println("sx "+matrix[i][j-1]);
						addBranch(graph.get((i*matrix.length)+j-onato[i][j]),graph.get((i*matrix.length)+j-onato[i][j-1]));
					}
				}
			}
		}
	}
	//legge una matrice di coordinate e ne crea un grafo O(matrix.lenght*matrix[].lenght)
	
	void bsf(){
		if (graph.size()<2)
			p.push(graph.get(0));
		else{
			int[] marker=new int[graph.size()];
			for (int i=0;i<marker.length;i++){
				marker[i]=0;
			}
			for (int i=0;i<graph.size();i++){
				if(marker[i]==0){
					bsfVisit(graph.get(i),marker);
					//System.out.println("marker " + marker);
				}
			}
		}	
		//System.out.println("bsf");
	}
	//O(V+E) dove V è il numero di celle esplorate ed E è il numero di archi 
	//del grafo
	
	void bsfVisit(node n,int[] marker){
		//System.out.println("bsfVisit ");
		for(int i=0;i<graph.size();i++)marker[i]=0;
		Stack<node> q = new Stack<node>();
		q.push(n);
		p.push(n);
		d.push(0);
		marker[0]=1;
		while(!q.empty()){
			node u=q.remove(0);
			//System.out.println("valuto u " +u.c+"\tindexof(u)"+lastindexof(u));
			Iterator<node> it= u.adiacenze.iterator();
			while(it.hasNext()){
				node v=it.next();
				//System.out.println("nodo while interno da forse pushare in q, v: " +v.c+"\tmarker[lastindexof(v)]"+marker[lastindexof(v)]);
				if(marker[lastindexof(v)]==0){
					d.push(d.get(lastindexofd(u))+1);
					//System.out.println("d  "+d);
					q.push(v);
					p.push(v);
					//System.out.println("push p  "+ p );
					//System.out.println("q  "+q);
					marker[lastindexof(v)]=1;
				}
			}
			marker[lastindexof(u)]=1;
		}
		/*for (int i=0;i<marker.length;i++){
			System.out.print(marker[i]);
		}*////stampa matrice marker
	}
	//vedi bsf
	
	int lastindexof(node n){
		Iterator<node> it= graph.iterator();
		int i=0;
		while(it.hasNext()){
			node t=it.next();
			if(t.c.col==n.c.col && t.c.row==n.c.row) return i;
			i++;
		}
		return 0;
	} 
	//ritorna l'indice di posizione nel grafo Omega(V)
	
	int lastindexofd(node n){
		Iterator<node> it= p.iterator();
		int i=0;
		while(it.hasNext()){
			node t=it.next();
			if(t.c.col==n.c.col && t.c.row==n.c.row) return i;
			i++;
		}
		return 0;
	}
	//ritorna l'indice di posizione nel vettore p Omega(V)
	
	Stack<GridWorld.Coordinate> minWay(GridWorld mondo,GridWorld.Coordinate arrive){
		Stack<GridWorld.Coordinate> way=new Stack<GridWorld.Coordinate>();
		if(graph.size()<2){
			way.push(graph.get(0).c);
		}else{
			GridWorld.Coordinate prev;
			int time;
			while(!p.isEmpty() && !parse(p.peek(),arrive) ){
				//System.out.println("svuoto lista fino a arrivo p.peek().c "+p.peek().c+"\np.pop\nd.pop");
				p.pop();
				d.pop();
			}
			way.push(p.pop().c);
			prev=way.peek();
			time=d.pop();
			//System.out.println("inizio while d empty   way"+way+"\tprev " +prev+ "\ttime "+d);
			//System.out.println("p: "+p);
			while(d.size()>1){
				if(parseadiacenze(p.peek(),prev) && d.peek()==time-1){
					way.push(p.pop().c);
					prev=way.peek();
					time=d.pop();
					//System.out.println("wahhhy  "+way);
					if(!d.isEmpty())//System.out.println("inizio while d empty   way"+way+"\t" +prev+ "\ttime"+d.peek());
					while(d.peek()==time){
						d.pop();
						p.pop();
						//System.out.println("while d svuota  d "+d.peek()+"\tp " +p.peek());
					}
					//System.out.println(p.peek());
				}else{
					d.pop();
					p.pop();
					//System.out.println("else d svuota  d "+d.peek()+"\tp " +p.peek());
				}
			}
			way.push(p.pop().c);
		}
		adjustway(way);
		return way;
	}
	//calcola la strada minima dall'inizio di graph a una qualsiasi coordinata di arrivo 
	//l'argomento si può facilmente convertire in nodo
	//il costo è di O(V)
	
	void adjustway(Stack<GridWorld.Coordinate> way){
		Stack<GridWorld.Coordinate> temp = new Stack<GridWorld.Coordinate>();;
		while(!way.empty()){
			temp.push(way.pop());
		}
		while(!temp.empty()){
			way.push(temp.remove(0));
		}
	}
	//ribalta lo stack di way alla fine di min way
	//Omega(way.size())
	
	boolean parse(node n, GridWorld.Coordinate c){
		if(n.c.col==c.col && n.c.row==c.row){
			//System.out.println("true  "+t.col+c.col + t.row+c.row);
			return true;
		}
		return false;
	}
	//confronta nodo.c e coordinata
	
	boolean parseadiacenze(node n, GridWorld.Coordinate c){
		Iterator<node> it = n.adiacenze.iterator();
		while(it.hasNext() ){
			GridWorld.Coordinate t=it.next().c;
			////System.out.println("parse   "+c + "   "+  n);
			if(t.col==c.col && t.row==c.row){
				//System.out.println("true  "+t.col+c.col + t.row+c.row);
				return true;
			}
		}
		return false;
	}
	//confronta le adiacenze di un nodo con una coordinata
	//Omega(max (nodo[i].adiacenze.size())) nel caso specifico Omega(4)
	


	void bsfMirate(GridWorld.Coordinate arrive){
		if (graph.size()<2)
			p.push(graph.get(0));
		else{
			int[] marker=new int[graph.size()];
			for (int i=0;i<marker.length;i++){
				marker[i]=0;
			}
			for (int i=0;i<graph.size();i++){
				if(marker[i]==0){
					bsfVisitMirate(graph.get(i),marker,arrive);
					//System.out.println("marker " + marker);
				}
			}
		}	
		//System.out.println("bsf");
	}
	//O(V+E) dove V è il numero di celle minimo esplorare per trovare una strada 
	// fino ad arrive V=lastindexof(arrive);
	//ed E è il numero di archi del grafo
	//ammortizzata dal fatto che possa arrestarsi una volta raccolti
	//i dati necessari per trovare una strada da inizio grafo ad arrive
	//inutile ammortizzamento se il nodo da cercare è il piu distante
	//altrimenti E=aV	supposto il numero di adiacenze molto minore di V
	//V 
	//O(V+E)  =>  O(V+aV)  =>  O(V(i+a))  =>  O(V)  => O(lastindexof(arrive))  
	
	void bsfVisitMirate(node n,int[] marker,GridWorld.Coordinate arrive){
		//System.out.println("bsfVisit ");
		for(int i=0;i<graph.size();i++)marker[i]=0;
		Stack<node> q = new Stack<node>();
		q.push(n);
		p.push(n);
		d.push(0);
		marker[0]=1;
		node u=q.peek();
		while(!q.empty()&& !parse(u,arrive)){
			u=q.remove(0);
			//System.out.println("valuto u " +u.c+"\tindexof(u)"+lastindexof(u));
			Iterator<node> it= u.adiacenze.iterator();
			while(it.hasNext()){
				node v=it.next();
				//System.out.println("nodo while interno da forse pushare in q, v: " +v.c+"\tmarker[lastindexof(v)]"+marker[lastindexof(v)]);
				if(marker[lastindexof(v)]==0){
					d.push(d.get(lastindexofd(u))+1);
					//System.out.println("d  "+d);
					q.push(v);
					p.push(v);
					//System.out.println("push p  "+ p );
					//System.out.println("q  "+q);
					marker[lastindexof(v)]=1;
				}
			}
			marker[lastindexof(u)]=1;
		}
		/*for (int i=0;i<marker.length;i++){
			System.out.print(marker[i]);
		}*////stampa matrice marker
	}
	//vedibsfMirate 
}