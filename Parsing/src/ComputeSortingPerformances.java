import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.lang.reflect.Method;

public class ComputeSortingPerformances {

	private static String[] toTest = {"doMergeSort", "doQuickSort", "doCollectionsSort"}; //, "doQuickSort3way"};

	private static boolean isSorted(ArrayList<Integer> table) {
		for (int i = 1; i < table.size(); ++i) {
			if (table.get(i-1) > table.get(i)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if (args.length < 2) {
			System.out.println("Utilizzo:");
			System.out.println("java ComputeSortingPerformances <n> <rep>");
			System.exit(0);
		}
	
		// n: numero degli elementi da generare
		int n = Integer.parseInt(args[0]);
		
		// rep: numero di ripetizioni del test
		int rep = Integer.parseInt(args[1]);
		
		// Inizializzo generatore casuale
		Random random = new Random();
		
		// Stampo in console l'intestazione della tabella 
		for (String name : toTest) System.out.print(name + " ");
		System.out.println();

		// Creo tanti oggetti timer quanti sono gli algoritmi da testare
		Timer[] timer = new Timer[toTest.length];
		for (int j = 0; j < timer.length; ++j) timer[j] = new Timer();
		
		for (int i = 0; i < rep; ++i) {
			
			// Creo due oggetti: uno per mantenere la versione disordinata ('table')
			// e uno che viene ordinato e sovrascritto ad ogni ciclo ('result')
			ArrayList<Integer> table = new ArrayList(n);
			ArrayList<Integer> running = new ArrayList(n);
			for (int j = 0; j < n; ++j) {
				int s = random.nextInt(n);
				table.add(s);
				running.add(s);
			}
			
			// Pe ognuno degli algoritmi da testare...
			for (int k = 0; k < toTest.length; ++k) {
				// ... sovrascrivo 'running' con la copia disordinata ...
				Collections.copy(running, table);
				// .... invoco il metodo di sorting sulla base del nome utilizzando
				// i metodi per la  "reflection" di Java
				Class<?> sortClass = Class.forName("SortArrayList");
				Method sortMethod = sortClass.getMethod(toTest[k], ArrayList.class);				
				timer[k].start();
				sortMethod.invoke(null, running);
				timer[k].stop();
				// Controllo che l'algoritmo sia eseguito in modo corretto
				assert(isSorted(running));
			}
			
			// Stampo in console il tempo di esecuzione per ogni algoritmo
			for (Timer t : timer) System.out.print(t.getElapsedMilliSeconds() + " ");
			System.out.println();
			
		}
	}

}
