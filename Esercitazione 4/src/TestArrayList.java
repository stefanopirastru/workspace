import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class TestArrayList {

	public static void main(String[] args) {
		//si può usare per leggere file senza sapere quanti elementi ci sono dentro (senza scorrerlo prima)
		
		//Suppongo che il nome del file sia dato
		//come parametro da linea di comando
		if(args.length < 1){
			System.exit(0);
		}
		
	//creo un ArrayList di stringhe per leggere
		//e memorizzare il contenuto del file
		ArrayList<String> table = new ArrayList<String>();
		
		//apro un file di testo in lettura e associo ad esso uno scanner
		
		FileReader textFileReader = null;
		
		//quanti oggetti ho creato con questa istruzione?
		//nessuno! Perchè esso èun reference e null equivale a nessun oggetto
		//non si riferisce a nessun oggetto, non c'è new!!
		try {//provo
			textFileReader = new FileReader(args[0]);
			//creo l'oggetto
		} catch(FileNotFoundException e){
		//in caso non riesca lancio un eccezione 
		System.out.println("File" + args[0] + "non trovato!");
		System.exit(0);
		//in cui esco dal programma semplicemente
		}
		if(textFileReader != null){
			//apertura file con successo
			Scanner in = new Scanner(textFileReader);
			while (in.hasNext()) { //finchè c'è qualcosa
				table.add(in.next()); //mettilo nella lista di string
			}
			in.close();
			try{
				textFileReader.close(); //provo a chiudere il file
			} catch (IOException e){
				//se non riesco
				System.out.println(e.getMessage());
				//mi dice il nome dell'errore!!
				//ma vado avanti perchè il file è stato letto
			}
			
		}
		for(String s : table){ //"loop for each" utile per i contenitori 
			System.out.println(s);
			//stampo i contenuti del file
		}
		
	}

}
