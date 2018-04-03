import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	/**
	 * La funzione move mi permette di spostarmi da una cella all'altra.
	 * 
	 * @param g
	 * @param before
	 * @param after
	 * @param h
	 * @param cell
	 */
	
	public static void move(GridWorld g, 
			GridWorld.Coordinate before, GridWorld.Coordinate after,
			HashMap<GridWorld.Coordinate, Boolean> h, ArrayList<GridWorld.Coordinate> cell){
		
		int diffRow = after.row - before.row;
		int diffCol = after.col-before.col;
		
		if(diffRow==0 && diffCol!=0){
			if(diffCol>0){
				for(int i=0; i<diffCol; i++){
					g.moveToAdjacentCell(GridWorld.Direction.EAST);
					h.put(after, true);
					if(!cell.contains(after)){
						cell.add(after);
					}
				}
			}
			else {
				diffCol *= (-1);
				for(int i=0; i<diffCol; i++){
					g.moveToAdjacentCell(GridWorld.Direction.WEST);
					h.put(after, true);
					if(!cell.contains(after)){
						cell.add(after);
					}
				}
			}
		}
		
		else if(diffCol==0 && diffRow!=0){
			if(diffRow>0){
				for(int i=0; i<diffRow; i++){
					g.moveToAdjacentCell(GridWorld.Direction.SOUTH);
					h.put(after, true);
					if(!cell.contains(after)){
						cell.add(after);
					}
				}
			}
			else{
				diffRow *= (-1);
				for(int i=0; i<diffRow; i++){
					g.moveToAdjacentCell(GridWorld.Direction.NORTH);
					h.put(after, true);
					if(!cell.contains(after)){
						cell.add(after);
					}
				}
			}
		}
			
	}
	
	/**
	 * Funzione principale
	 *
	 * @param args
	 */
	
	public static void main(String[] args) {
		if (args.length<1){
			System.err.println("Numero di paramentri errato!");
			System.exit(0);
		}
		assert(args.length>=1);
		
		int size = Integer.parseInt(args[0]);
		if (size<1){
			System.err.println("Dimensione GridWorld errata!");
			System.exit(0);
		}
		assert (size>=1);
		// NOTA BENE: 
		// 1) Per size = 1 stampa "Nessun percorso!", in quanto si ottiene
		// una GridWorld dotata di una sola cella corrispondente sia al punto di 
		// partenza sia al punto di arrivo, per cui non esiste un percorso.
		// 2) Per valori di size molto grandi il compilatore impiegherà parecchio
		// tempo per stampare il risultato (per esempio per size = 100 stampa
		// il percorso dopo circa 3 minuti).
		
		// Nel mio personale compilatore devo scrivere 
		// in Run Configuration density come 0.n e non come 0,n
		double density = Double.parseDouble(args[1]);
		if (density<=0||density>=1){
			System.err.println("Densità errata!");
			System.exit(0);
		}
		assert (density>0 && density<1);
		
		long seed = Long.parseLong(args[2]);
		
		GridWorld g = new GridWorld(size, density, seed);
		GridWorld.Coordinate currentPosition = g.getCurrentCell();

		int minDistance = g.getMinimumDistanceToTarget();
		if(minDistance==-1){
			System.out.println("Nessun percorso!");
			System.exit(0);
		}
		
		// Salvo in un hashMap le celle visitate, passo come chiave la coordinata e come valore
		// un boolean che mi indica se la cella è stata visitata o meno.
		// In questo modo svolgo una DepthFirstSearch sulle celle libere.
		HashMap<GridWorld.Coordinate, Boolean> record = new HashMap<GridWorld.Coordinate, Boolean>();
		// Uso un arrayList dedicato al risultato finale, cioè il mio percorso
		ArrayList<GridWorld.Coordinate> cell = new ArrayList<GridWorld.Coordinate>();
		// Contatore delle celle ridondanti, cioè già visitate(si vedrà in seguito)
		int ridond=0;
		// Inserisco il punto di partenza nella mappa e nell'arrayList
		record.put(currentPosition,true);
		cell.add(currentPosition);
		
		// L'arrayList altern , quando mi trovo in un bivio, mi permette di
		// salvare le celle alternative che inizialmente ignoro e non visito
		// ma che in un secondo momento possono servirmi 
		// (quando per esempio mi trovo in un vicolo cieco)
		ArrayList<GridWorld.Coordinate> altern = new ArrayList<GridWorld.Coordinate>();																				
		
		while(true){
			currentPosition = g.getCurrentCell();
			// Lista delle celle libere adiacenti alla posizione corrente 
			Iterable<GridWorld.Coordinate> adjFreeCell = g.getAdjacentFreeCells();
			boolean targetReached = g.targetReached();
			// Contatore per ricavare la dimensione di adjFreeCell
			int dimAfc = 0;
			// Numero di vie alternative
			int bivio = 0;
			// Ogni volta che cambia la cella corrente pulisco il contatore ridond
			ridond=0;
			
			// Se ho trovato un percorso valido e aciclico stampo il risultato
			if(targetReached && g.checkPath(cell) && g.checkPathAcyclic(cell)){
				System.out.print("Percorso: ");
				for(int i=0; i<cell.size(); i++){
					System.out.print(cell.get(i)+" ");
				}
				System.out.println();
				break;
				
				// Se eventualmente volessi verificare se tale percorso è anche minimo
				// potrei sostituire il corpo di if(targetReached && ...) con il codice 
				// commentato qui sotto. Inoltre ho svolto alcune prove empiriche variando
				// i valori dei paramentri size, density e seed e ho appurato che è tanto 
				// probabile ottenere un percorso minimo quanto sono bassi questi valori.
				/*
				if((cell.size()-1)==minDistance){
					System.out.print("Percorso (minimo): ");
					for(int i=0; i<cell.size(); i++){
						System.out.print(cell.get(i)+" ");
					}
					System.out.println();
					break;
				}
				else{
					System.out.print("Percorso (NON minimo): ");
					for(int i=0; i<cell.size(); i++){
						System.out.print(cell.get(i)+" ");
					}
					System.out.println();
					break;
				}
				*/
			}
			
			// Sfoglio adjFreeCell
			for(GridWorld.Coordinate i: adjFreeCell){
				dimAfc++;
				// Se visito una cella contenuta in altern la elimino da altern
				if(altern.contains(i)){
					altern.remove(i);
				}
				// Visita della nuova cella
				if(!record.containsKey(i) || record.get(i)!=true){
					bivio++;
					// Se ho un bivio (almeno due vie alternative)
					// scelgo di visitare la prima diramazione 
					// e salvo a parte le altre non visitate
					if(bivio>1){
						altern.add(i);
					}
					else{
						move(g, currentPosition, i, record, cell);
					}
				}
				// Se ho come alternativa una cella già visitata la
				// ignoro e incremento il contatore delle ridondanze
				else if(record.containsKey(i) && record.get(i)==true){
					ridond++;
				}
			}
			// Se tutte le adiacenze sono già state visitate (vicolo cieco) vuol dire che il conteggio 
			// delle ridondanze è pari alla dimensione di adjFreeCell, devo quindi tornare indietro
			if(ridond==dimAfc){
				cell.remove(cell.size()-1);
				move(g, currentPosition,cell.get(cell.size()-1) ,record, cell);
			}
		}
	}

}
