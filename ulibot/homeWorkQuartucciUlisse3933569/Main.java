
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
			r.minWay();//O(V+E) dove V � il numero di celle esplorate ed E � il numero di archi del grafo
			r.printWay();//O(r.way.size())		*/
			
		} catch (Exception e) {
			System.err.println("Error");
			System.exit(0);
		}
	}
}
//per grandi mondi (size>100) con alta densit� (density>0.5) la complessit� di minway richiede un tempo esorbitante di 
//elaborazione dato che costruisce un grafo e ne computa una bsf O(V+E)
//dove V sono nodi in numero pari alle celle visistabili su gridworld 
// E la somma dei rami di adiacenza contenuti in ogni nodo 
//la complessit� varia sia il numero di celle libere su gridworld ma 
//soprattutto dalla densit� che ne definisce la parte effettivamente eplorabile (V) supposta
//(n*n*density) dove density � il valore che assume gridworld in costruzione e pu� 
//variare da 0.1 a 1

//caso pessimo:
//dato un mondo a densit� 1 (completamente sgombro) V=n*n dove n � size di gridworld
//supposto che al massimo ogni V ha 4 adiacenze quindi i rami totali di grafo sono V*4
//O(V+E)  =>  O(5V)  =>  O(V)  =>  O(n*n*1)  =>  O(n*n)

//complessit� generale
//prese vere le supposizioni =>  O(n*n*density)

//in oltre alloca moltaa piu memoria di firtsWaytoTarget poich� 
//usa una matrice piu una struttura grafo mentre la seconda necessita solamente di una matrice di interi

//per mondi con alta densit� si consiglia di usare 
//		r.firstWayToTarget per una computazione meno precisa ma molto piu rapida
//README.txt per statistiche