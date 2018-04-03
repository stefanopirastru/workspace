import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class RemoveDuplicates {
	
	public static int[] readRecordsFromFile(String fileName) {
		FileReader textFileReader = null;
		try {
			textFileReader = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			// Se non si riesce ad aprire il file in input
			// si fallisce restituendo null (per controllo nel main)
			return null;
		}
		assert(textFileReader != null);
		Scanner in = new Scanner(textFileReader);
		int numberOfRecords = in.nextInt();
		int[] result = new int[numberOfRecords];
		for (int i = 0; i < numberOfRecords; ++i) {
			result[i] = in.nextInt();
		}
		in.close();
		try {
			textFileReader.close();
		} catch (IOException e) {
			// Non si è riusciti a chiudere il file in lettura,
			// ma i dati sono stati acquisiti, quindi non si fa nulla
		}
		return result;
	}
	
	public static int[] removeDuplicates(int[] records) {
		// Funziona in tre passi:
	    // - Ordino il vettore in input
		// - Conto i duplicati e dimensiono il risultato
	    // - Copio i record unici nel risultato
		// Il conteggio dei record unici è necessario perchè
		// gli array sono strutture di lunghezza definita
		
		// Passo 1
		Arrays.sort(records,0,records.length);
		
		// Passo 2
		int countUnique = 1;
		for (int i = 1; i < records.length; ++ i) {
			if (records[i] != records[i-1]) {
				countUnique += 1;
			}
		}
		int[] result = new int[countUnique];
		
		// Passo 3
		result[0] = records[0];
		int j = 1;
		for (int i = 1; i < records.length; ++i) {
			if (records[i] != records[i-1]) {
				result[j] = records[i];
				j += 1;
			}
		}
		
		return result;
	}
	
	private static void writeRecordsToFile(int[] records, String fileName) {
		FileWriter textFileWriter = null;
		try {
			textFileWriter = new FileWriter(fileName);
		} catch (IOException e) {
			// Se non si riesce ad aprire il file, si
			// fallisce in modo silente 
			return;
		}
		assert(textFileWriter != null);
		
		PrintWriter pw = new PrintWriter(textFileWriter);
		pw.println(records.length);
		for (int i = 0; i < records.length; ++i) {
			pw.println(records[i]);
		}
		pw.close();

		try {
			textFileWriter.close();
		} catch (IOException e) {
			// Se non si riusce a chiudere il file in scrittura, 
			// il file potrebbe non essere completamente scritto.
			// Si tenta ancora un "flush" dello stream
			try {
				textFileWriter.flush();
			} catch (IOException f) {
				// Non ci sono altre azioni che si possono tentare.
			}
		}
	}
	
	public static void main(String[] args) {
		if (args.length < 2) {
			System.exit(0);
		}
		
		// Assumo che il file contenga numeri interi
		int[] records = readRecordsFromFile(args[0]);
		if (records != null) {
			int[] uniqueRecords = removeDuplicates(records);
			writeRecordsToFile(uniqueRecords, args[1]);
		}

	}

}
