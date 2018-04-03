import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
/**
 * 
 * @author stefano pirastru
 * 
 * visita di gridworld con depth-forest search
 *
 */
public class Main {
	/**
	 * 
	 * @param colore: vettore colore coordinate WHITE non visitato GREY visitato ma non completato BLACK visitato e completato
	 *
	 */
	public static enum colore{ WHITE, GREY, BLACK};
	public static void move(Grafo<GridWorld.Coordinate> G, GridWorld gw,colore[] marked,GridWorld.Coordinate before, GridWorld.Coordinate after,ArrayList<GridWorld.Coordinate> cell){
		/**
		 * @param G: grafo non orientato
		 * @param gw: istanza di gridworld
		 * @param marked: vettore di colori associato alle celle di gridworld
		 * @param before: dove sono
		 * @param after: dove posso andare
		 * @param cell: vettore delle celle delle celle visitate in precedenza
		 * operazione di base: accessi a marked
		 * costo dfs teta(diffCol) oppure teta(diffRow) 
		 */
		
		int diffCol=after.col-before.col;
		int diffRow=after.row-before.row;
		if(!gw.targetReached()){
			if(diffRow==0&&diffCol!=0){
				if(diffCol>0){
					for(int i=0;i<diffCol;i++){
						
						gw.moveToAdjacentCell(GridWorld.Direction.EAST);
						//System.err.print(gw.getCurrentCell());
						if(!cell.contains(gw.getCurrentCell())){
							cell.add(gw.getCurrentCell());
							//System.err.print(after);
							marked[cell.size()]=colore.GREY;
						}
					}
				}
				if(diffCol<0){
					diffCol*=(-1);
					for(int i=0;i<diffCol;i++){
						gw.moveToAdjacentCell(GridWorld.Direction.WEST);
						//System.out.print(gw.getCurrentCell());
						if(!cell.contains(gw.getCurrentCell())){
							cell.add(gw.getCurrentCell());
							//System.err.print(after);
							marked[cell.size()]=colore.GREY;
						}
					}
				}
			}
			if(diffCol==0&&diffRow!=0){
				if(diffRow>0){
					for(int i=0;i<diffRow;i++){
						gw.moveToAdjacentCell(GridWorld.Direction.SOUTH);
						//System.err.print(gw.getCurrentCell());
						if(!cell.contains(gw.getCurrentCell())){
							cell.add(gw.getCurrentCell());
							//System.err.print(after);
							marked[cell.size()]=colore.GREY;
						}
					}
				}
				if(diffRow<0){
					diffRow*=(-1);
					for(int i=0;i<diffCol;i++){
						gw.moveToAdjacentCell(GridWorld.Direction.NORTH);
						//System.out.print(gw.getCurrentCell());
						if(!cell.contains(gw.getCurrentCell())){
							cell.add(gw.getCurrentCell());
							//System.err.print(after);
							marked[cell.size()]=colore.GREY;
						}
					}
				}
			}
		}
	}
	public static void dfs(GridWorld gw, Grafo<GridWorld.Coordinate> G,colore[] marked,ArrayList<GridWorld.Coordinate> cell){
		/**
		 * implementazione dell'algoritmo depth-first search
		 * @param gw: istanza di gridworld
		 * @param G: grafo
		 * @param marked: vettorecolore associato alle celle
		 * @param cell: lista dei predecessori
		 * 
		 * operazione di base: accessi a marked
		 * costo dfs teta(V)+teta(V)
		 * dove V è il numero di nodi 
		 */
		for(int u=0;u<gw.getMinimumDistanceToTarget()+5;u++){
			marked[u]=colore.WHITE;
			/*
			 * costruisco un albero che ha al massimo 5 passi in più del cammino minimo
			 */
		}
		for(int u=0;u<gw.getMinimumDistanceToTarget()+5;u++){
			if(marked[u]==colore.WHITE){
				dfsVisit(gw,G,u,marked,cell);
			}
		}
	}
	public static void dfsVisit(GridWorld gw, Grafo<GridWorld.Coordinate> G, int u,colore[] marked, ArrayList<GridWorld.Coordinate> cell){
		/**
		 * passo base di visita
		 * @param gw: istanza di gridworld
		 * @param G: grafo di coordinate
		 * @param u: indice della distanza dalla coordinata (0,0)
		 * @param marked: vettore di colori associato alle celle di gridworld
		 * @param cell: vettore predecessori
		 * operazione di base: accessi a marked
		 * costo dfsVisit teta(V)+ teta(E) = teta(V+E)
		 * dove V è il numero di nodi 
		 * E è il numero di archi
		 */
		Iterable<GridWorld.Coordinate> adjCell=gw.getAdjacentFreeCells();
		GridWorld.Coordinate here=gw.getCurrentCell();
		int z=u+1;
		G.V.add(new ArrayList<GridWorld.Coordinate>());
		for(GridWorld.Coordinate b:adjCell){
			G.V.get(z).add(b);
		}
		for(GridWorld.Coordinate b:adjCell){
				if(marked[z]==colore.WHITE){
					move(G,gw,marked,here,b,cell);
					dfsVisit(gw,G,z,marked,cell);
			}
		
			
		}
	}
	public static void bfs(GridWorld gw ,Grafo<GridWorld.Coordinate> G, colore[] marked,ArrayList<GridWorld.Coordinate> cell){
		/**
		 * breadth-first search
		 * visita in profondità
		 * 
		 */
		//System.out.println("sono in bfs"+gw.getCurrentCell()+"(x,y)");
		for(int u=0;u<gw.getMinimumDistanceToTarget();u++){
			marked[u]=colore.WHITE;
		}
		for(int u=0;u<gw.getMinimumDistanceToTarget();u++){
			if(marked[u]==colore.WHITE){
				//System.out.println("sono in bfs"+gw.getCurrentCell()+"(x,y)");
				bfsVisit(gw,G,u,marked,cell);
			}
		}
	}
	public static void bfsVisit(GridWorld gw, Grafo<GridWorld.Coordinate> G, int s,colore[] marked, ArrayList<GridWorld.Coordinate> cell){
		Iterable<GridWorld.Coordinate> adjCell=gw.getAdjacentFreeCells();
		GridWorld.Coordinate here=gw.getCurrentCell();
		int z=s+1;
		PriorityQueue<GridWorld.Coordinate> q=new PriorityQueue<GridWorld.Coordinate>();
		
		
		marked[s]=colore.GREY;
		try{
		q.add(here);		
		}catch(NullPointerException e){
			System.err.println(e.getStackTrace());
			System.out.println("forse qua");
			System.exit(0);
		}
		GridWorld.Coordinate u,v;
		while(!q.isEmpty()){
			u=q.remove();
			for(GridWorld.Coordinate i: adjCell){
				v=i;
				if(marked[z]==colore.WHITE){
					marked[z]=colore.GREY;
					move(G,gw,marked,here,i,cell);
					System.out.println("sono in bfsVisit"+here+"(x,y)");
					q.add(v);
				}
				
			}
			marked[z]=colore.BLACK;
		}
		
	}
	public static void main(String[] args){
		
		if(args.length<1){
			System.err.println("Non bastano i parametri");
			System.out.println("Utilizzo: java Main <dimensione> <densità ostacoli> <seme>");
			System.out.println(" dimensione: intero > 1");
			System.out.println(" densità ostacoli: virgola mobile compreso tra 0 e 1 nell'aperto");
			System.out.println(" seme: long");
			System.exit(0);
		}
		int dim=Integer.parseInt(args[0]);
		if(dim<=1){
			System.out.println(" dimensione: intero > 1");
			System.exit(0);
		}
		assert(dim>1);
		double dens=Double.parseDouble(args[1]);
		if(dens<=0 || dens>=1){
			System.out.println(" densità ostacoli: virgola mobile compreso tra 0 e 1 nell'aperto");
		}
		assert(dens>0 && dens<1);
		long seme=Long.parseLong(args[2]);
		
		/*
		 * istanza gridworld
		 */
		GridWorld gw = new GridWorld(dim,dens,seme);
		//System.out.println("everything seems ok.");
		gw.print();
		/*
		 * mostro il percorso
		 */
		Grafo<GridWorld.Coordinate> G=new Grafo<GridWorld.Coordinate>();
		G.graphInit();
		/*
		 * nuovo grafo con G.V vuoto
		 */
		ArrayList<GridWorld.Coordinate> zero= new ArrayList<GridWorld.Coordinate>();
		zero.add(gw.getCurrentCell());
		G.V.add(zero);
		/*
		 * G.V.Get(0) è un arraylist di un elemento con solo la cella iniziale, cammino di profondità zero
		 */
		ArrayList<GridWorld.Coordinate> luke= new ArrayList<GridWorld.Coordinate>();
		/*
		 * vettore delle celle visitate
		 */
		luke.add(gw.getCurrentCell());
		if(gw.getMinimumDistanceToTarget()==-1){
			/*
			 * se gridworld è una matrice 1x1 o non esiste un percorso
			 */
			System.out.println("Nessun Percorso!");
			System.exit(0);
		}
		assert(gw.getMinimumDistanceToTarget()>0);
		/*
		 * se c'è un percorso
		 */
		colore[] marked=new colore[100];
		System.out.println("premere : <n> <opzione>");
		System.out.println("0 per eseguire una ricerca in ampiezza dfs");
		System.out.println("1 per eseguire una ricerca in profondita' bfs");
		char pin=' ';
		Scanner input= new Scanner(System.in);
		int x=input.nextInt();
		switch(x){
		case 0:
			/*
			 * inizializzo il vettore come se potessi visitare tutte le celle di gridworld
			 */
			dfs(gw,G,marked,luke);
			/*
			 * depth-first search
			 */
			System.out.print("Percorso dfs");
			System.out.print(luke);
			/*
			 * costo totale teta(V)+teta(V)+teta(E)= teta(V+E)
			 */
			break;
		case 1:
			bfs(gw,G,marked,luke);
			/*
			 *breadth-forest search 
			 */
			System.out.println("Percorso bfs");
			System.out.println(luke);
			break;
		default:
			System.out.println(x+"non è un valore accettabile");
			System.exit(0);
		}
		
		
	}
}
