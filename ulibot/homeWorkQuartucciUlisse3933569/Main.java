
public class Main {
	public static void main(String[] args) {
		try{
			Integer size =Integer.parseInt(args[0]);
			Double density=Double.parseDouble(args[1]);
			Long seed =Long.parseLong(args[2]);
			GridWorld gw= new GridWorld(size, density, seed);
			//System.out.println("densisty:  "+ density+ "   size "+size);
			robotNavigatorIterative r = new robotNavigatorIterative(gw,size);
			/*
			r.firstWayToTarget();
			r.printWay();//*/
			
			
			r.exploreWorld();//O(n*n*density)
			r.minWay();//O(V+E) dove V è il numero di celle esplorate ed E è il numero di archi del grafo
			r.printWay();//O(r.way.size())		*/
			
		} catch (Exception e) {
			System.err.println("Error");
			System.exit(0);
		}
	}
}
//per grandi mondi (size>100) con alta densità (density>0.5) la complessità di minway richiede un tempo esorbitante di 
//elaborazione dato che costruisce un grafo e ne computa una bsf O(V+E)
//dove V sono nodi in numero pari alle celle visistabili su gridworld 
// E la somma dei rami di adiacenza contenuti in ogni nodo 
//la complessità varia sia il numero di celle libere su gridworld ma 
//soprattutto dalla densità che ne definisce la parte effettivamente eplorabile (V) supposta
//(n*n*density) dove density è il valore che assume gridworld in costruzione e può 
//variare da 0.1 a 1

//caso pessimo:
//dato un mondo a densità 1 (completamente sgombro) V=n*n dove n è size di gridworld
//supposto che al massimo ogni V ha 4 adiacenze quindi i rami totali di grafo sono V*4
//O(V+E)  =>  O(5V)  =>  O(V)  =>  O(n*n*1)  =>  O(n*n)

//complessità generale
//prese vere le supposizioni =>  O(n*n*density)

//in oltre alloca moltaa piu memoria di firtsWaytoTarget poichè 
//usa una matrice piu una struttura grafo mentre la seconda necessita solamente di una matrice di interi

//per mondi con alta densità si consiglia di usare 
//		r.firstWayToTarget per una computazione meno precisa ma molto piu rapida
//README.txt per statistiche