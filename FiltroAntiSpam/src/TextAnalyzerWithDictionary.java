import java.io.*;
import java.util.*;

// Analisi di efficienza: 
// Supponiamo che il dizionario abbia D parole e il file N parole

public class TextAnalyzerWithDictionary {
	
	
	private static ArrayList<Entry> buildDictionary(Scanner in) {
		// Leggo il dizionario da file e inserisco le parole in 'dictionary'

		// Il costo di questa operazione è O(D)
		
		ArrayList<Entry> dictionary = new ArrayList<Entry>();
		
		while (in.hasNext()) {
			
			String w = in.next();

			Entry temp = new Entry(w,0);

			dictionary.add(temp);
		
		}
		
		// Ordino "dictionary" (in modo da poter cercare con metodi efficienti)
		
						// Il metodo utilizzato (vedi https://docs.oracle.com/javase/7/docs/api/java/util/Collections.html)

						// è una (sofisticata) implementazione di merge sort, quindi O(D log D)


		Collections.sort(dictionary);
		
		// Complessivamente, per il teorema della somma degli O, la

									// prestazione di 'buildDictionary' è O(D log D)

		
return dictionary;

		}


	private static void computeWordFrequencies(Scanner in, ArrayList<Entry> dictionary) {

		// Per ogni parola letta nel file eseguo una ricerca nel dizionario

		// Ci sono N parole e "binarySearch" implementa una ricerca dicotomica

		// dal costo log D. Quindi, per il teorema della moltiplicazione degli O,

		// il costo complessivo della funzione è O(N log D).

		while (in.hasNext()) {
			
			String w = in.next();

			Entry temp = new Entry(w,0);

			// Per ogni parola "w", verifico se questa compare
 
			// in "dictionary" o meno

			int wIndex = Collections.binarySearch(dictionary, temp);

			if (wIndex >= 0) {

				// Se "w" compare in "dictionary", salvo in "temp" il

				// riferimento alla entry corrispondente

				temp = dictionary.get(wIndex);

				// Incremento il campo value

				temp.value += 1;

			}

		}

	}

	
	public ArrayList<Entry> riempiLista(String[] vector) {

		
		if (vector.length < 2) {

					System.out.println("Manca il nome del file del dizionario e/o del file da analizzare!");

					System.out.println("Utilizzo: java TextAnalyzerDictionary <dizionario> <file di testo>");

					System.exit(0);

				}

		
		assert(vector.length >= 2);

				// Sappiamo che il programma ha almeno due argomenti

				// da linea di comando, supponiamo che il primo sia il dizionario

				String dictionaryFileName = vector[0];

				String textFileName = vector[1];

		
		// Apro il file del dizionario
		 
		FileReader inFile = null;

				try {

					inFile = new FileReader(dictionaryFileName);

				}catch (Exception e) {

					System.err.println("Non posso aprire il file:" + dictionaryFileName);

					System.exit(0);
			
		}
			
	assert(inFile != null);

		
		// Sappiamo che lo stream di input non è null e quindi posso leggere il dizionario
		
		Scanner scanner = new Scanner(inFile);

				ArrayList<Entry> dictionary  = buildDictionary(scanner);
		
		scanner.close();

		
		// Leggo il file di testo parola per parola

				inFile = null;
		
		try {

					inFile = new FileReader(textFileName);

				} catch (Exception e) {

					System.err.println("Non posso aprire il file:" + textFileName);
		
			System.exit(0);

				}

				assert(inFile != null);


				// Sappiamo che lo stream di input non è null

				scanner = new Scanner(inFile);
		
		computeWordFrequencies(scanner, dictionary);

				scanner.close();

		
		try {

					inFile.close();

				} catch (Exception e) {

					System.err.println("Non posso chiudere il file:" + textFileName);

				}
			return dictionary;
			
		
		// Al termine della lettura del file, "dictionary" contiene

								// l'elenco di tutte le parole *distinte* del file

								// che sono anche nel dizionario, con le relative occorrenze

								// Le parole che sono nel dizionario ma non nel file, hanno value = 0

		/*Iterator<Entry> ai = dictionary.iterator();

							while (ai.hasNext()) {

					Entry t = ai.next();

					// Dato che in "Entry" è stato ridefinito il metodo "toString"

					// questo verrà direttamente invocato sull'oggetto t per eseguire
		 
			// la stampa di t

					if (t.value > 0) System.out.println(t);

				}*/
	
		
		// L'operazione finale di stampa costa O(D)

		
		// Complessivamente, il costo risulta essere:

				// - O(D log D) per la prima parte (lettura e ordinamento dizionario)

				// - O(N log D) per la seconda parte (calcolo delle frequenze)

				// - O(D) per la terza parte (stampa statistiche)

				//

				// Se ipotizziamo che D = O(N), ossia che i file hanno sempre un
		 
		// numero di parole (non necessariamente distinte) almeno pari a
		 
		// al numero di parole del dizionario, allora il costo complessivo,

				// per il teorema della somma degli O,  è O(N log N).

		
	}


}
