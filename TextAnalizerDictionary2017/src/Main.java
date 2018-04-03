import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.PrintWriter;

/**
 * 
 * @author stefano
 *
 */
public class Main {
	/**
	 * algoritmo nearest neighbor
	 * 
	 */
	//costo O(DlogD) dove D numero di parole 
	private static ArrayList<Node> buildDictionary(Scanner in){
		
		/**
		 * costruisco il dizionario
		 * @param in : nome file dizionario
		 * @return dizionario con i campi inizializzati a zero
		 */
		
		ArrayList<Node> dictionary = new ArrayList<Node>();
		while(in.hasNext()){
			/*
			 * finchè ci sono parole nel file dizionario
			 *le leggo come stringhe
			 *le incapsulo in un nodo con value nullo  
			 *le aggiungo al dizionario
			 */
			String w= in.next();
			Node temp= new Node(w, 0);
			dictionary.add(temp);
		}
		Collections.sort(dictionary);
		/*
		 * lo ordino per facilitare le ricerche
		 */
		return dictionary;
	}
	//costo O(NlogD)
	private static void quoties(Scanner in, ArrayList<Node> dictionary){
		/**
		 * conto le occorrenze delle parole di un file in un dizionario
		 * 
		 * @param in: stream del file da analizzare
		 * @param dictionary: dizionario con i campi value inizializzati a 0
		 * 
		 */
		
		while(in.hasNext()){
			/*
			 * finchè ci sono parole in un file
			 * faccio una ricerca binaria tra quelle nel dizionario
			 * se la ricerca binaria da risultati maggiori
			 * e uguali a 0
			 * incremento il campo value del dizionario di uno 
			 */
			String w= in.next();
			Node temp= new Node(w, 0);
			int wIdx= Collections.binarySearch(dictionary, temp);
			if(wIdx >= 0){
				temp= dictionary.get(wIdx);
				
				temp.value+= 1;
			}
		}
		
	}
	
	
	public static void main(String[] args){
		if(args.length<6){
			System.out.println("Utilizzo: java Main TRAIN <file_dizionario> <directory_ok> <directory_spam> <file_occorrenze>");
			System.out.println("Utilizzo: java Main TEST <file_dizionario> <file_occorrenze> <directory_testi>");
			System.exit(0);
		}
	//	for(int i=0;i<args.length;i++){
	//	System.out.println("args["+i+"] : "+ args[i]);
	//	}
		assert(args.length>=6);
		switch(args[2]){
		/**
		 * valuto la modalità di esecuzione 
		 *
		 *per la prima parte
		 *
		 */
		//numero parole nei file =P
		//numero file A
		//D numero parole nel dizionario
		//costo O(PlogD)
		case"TRAIN":
		
		
			ReadFolder rd= new ReadFolder();
			/*
			 * per leggere il contenuto delle cartelle
			 * la directory dei files ok
			 * restituisce un arraylist contenente tutti i files presenti in una directory
			 * nome del dizionario
			 * 
			 */
			String okDir= args[4];
			ArrayList<String> f=rd.kItN(okDir);
			String dictionaryFileName=args[3];
			
			/*
			 * la directory dei files spam
			 */
			String spamDir = args[5];
			String noFile = args[6];
			/*
			 * il nome che assegnerò al file delle occorrenze che genererò
			 * */
			try{
				File file = new File(noFile);
				/*
				 * dove/nomefile.formato
				 * istanza classe file
				 */
				file.getParentFile().mkdirs();
				/*
				 * crea la directory dal nome astratto dal pathname della cartella padre
				 */
				file.createNewFile();
				PrintWriter pW= new PrintWriter(file);
				/*
				 *creo un nuovo file
				 *apro il file in scrittura 
				 */
				
				
				//System.out.println(path);
				pW.print("OK");
				/*
				 * inizio sezione 1 del file
				 */
				
				for(int i=0;i<f.size();i++){
					/*
					 * finchè ho nomi di file nella cartella ok
					 * leggo le parole dal dizionario
					 */
					FileReader inFile=null;
					
					try{
						
						inFile= new FileReader(dictionaryFileName);
					}catch(Exception e){
						/*
						 * @throws: IOException
						 */
						System.out.println("Non riesco ad aprire il file"+ dictionaryFileName+ e);
						System.exit(0);
					}
					assert(inFile!= null);
					/*
					 * so che il file che sto leggendo non è null
					 * per scorrerlo
					 * costruisco un dizionario vuoto
					 * 
					 */
					Scanner scan= new Scanner(inFile);
					ArrayList<Node> dictionary = buildDictionary(scan);
					scan.close();
					/*
					 * chiudo lo stream
					 */
					inFile= null;
					/*
					 * reset
					 */
					try{
						/*
						 * apro il file ok in lettura
						 * indirizzo di un file ok/nomefile
						 */
						
						String adrs= okDir+"\\"+f.get(i);
						inFile= new FileReader(adrs);
						
					}catch(Exception e){
						System.err.println("Non riesco ad aprire il file "+ f.get(i));
						System.exit(0);
						/**
						 * @throws nullPointerException
						 */
					}
					assert(inFile != null);
					/*
					 * so che il file ok non è null
					 */
					pW.println();
					pW.print(f.get(i)+" ");
					/*
					 * scrivo il nome del file in lettura nel file in scrittura
					 */
					
					scan= new Scanner(inFile);
					quoties(scan, dictionary); 
					scan.close();
					/*
					 * riempio i contatori del dizionario per questo file
					 * e chiudo lo stream
					 */
					try{
						inFile.close();
						/*
						 * chiudo il file in lettura
						 */
						
					}catch(Exception e){
						System.err.println("Non riesco a chiudere il file "+ f.get(i));
						/**
						 * @throws IOException
						 */
					}
					
					Iterator<Node> ai= dictionary.iterator();
					while (ai.hasNext()){
						Node t = ai.next();
						pW.print(t);
						/*
						 * per scorrere i nodi 
						 * finchè c'è un prossimo elemento della lista
						 * estrapolo il prossimo
						 * posso stampare il nodo perchè
						 * ho ridefinito il metodo toString
						 */
						
						pW.print(" ");
						
						
					}
				}
				pW.println();
				/*
				 * inizio sezione 2 del file
				 */
				pW.print("SPAM");
				f=null;
				/*
				 * reset lettore cartella
				 */
				f=rd.kItN(spamDir);
				/*
				 * tutti i nomi dei files nella cartella spam
				 */
				
				for(int i=0;i<f.size();i++){
					FileReader inFile=null;
					try{
						inFile= new FileReader(dictionaryFileName);
						/*
						 * apro il dizionario in lettura 
						 */
					}catch(Exception e){
						System.out.println("Non riesco ad aprire il file"+ dictionaryFileName+ e);
						System.exit(0);
						/**
						 * @throws nullpointerException 
						 */
					}
					assert(inFile!= null);
					/*
					 * so che il file dizionario non è null
					 */
					Scanner scan= new Scanner(inFile);
					ArrayList<Node> dictionary = buildDictionary(scan);
					/*
					 * dizionario vuoto
					 */
					scan.close();
					/*
					 * chiudo il dizionario in lettura
					 */
					inFile= null;
					try{
						String adrs= spamDir+"\\"+f.get(i);
						/*
						 * leggo l'iesimo file spam in lettura
						 */
						inFile= new FileReader(adrs);
						
						
					}catch(Exception e){
						System.err.println("Non riesco ad aprire il file "+ f.get(i));
						System.exit(0);
						/**
						 * @throws NullPointerException
						 */
						
					}
					assert(inFile != null);
					/*
					 * so che l'i-esimo file spam non è null
					 */
					pW.println();
					pW.print(f.get(i)+" ");
					/*
					 * scrivo nel file occorrenze il nome del corrente file in lettura
					 */
					scan= new Scanner(inFile);
					quoties(scan, dictionary);
					
					scan.close();
					/*
					 * ne conto le occorrenze nel dizionario
					 * chiudo lo stream in lettura
					 */
					try{
						inFile.close();
						/*
						 * chiudo il file in lettura
						 */
						
					}catch(Exception e){
						System.err.println("Non riesco a chiudere il file "+ f.get(i));
						/**
						 * @throws IoException
						 */
					}
					
					Iterator<Node> ai= dictionary.iterator();
					/*
					 * per iterare il dizionario
					 */
					while (ai.hasNext()){
						
						Node t = ai.next();
						
							/*
							 * fino a che esiste il prossimo
							 *posso scrivere perchè ho ridefinito toString
							 */
							pW.print(t);
							pW.print(" ");
						
						
					}
				}
				pW.close();
				/*
				 * chiudo lo stream in scrittura
				 */
				
			}catch(Exception e){
				System.err.println("Non riesco ad aprire\\generare il file testuale"+e);
				System.exit(0);
				/**
				 * in case of fail
				 * @throws IOException
				 */
			}
			break;
		case "TEST":
			/*
			 * fase di testing
			 */
			String dictionaryName=args[3];
			String fileTrainName=args[4];
			String testFolder=args[5];
			FileReader inFile=null;
			 OccurrenceReader a= new OccurrenceReader();
				//System.out.print(a.modulo(temp));
			  Scanner in= null;
				inFile=null;
				
				try{
					inFile= new FileReader(fileTrainName);
				}catch(Exception e){
					System.err.println("Non riesco ad aprire il file occorrenze");
					System.exit(0);
				}
				assert(inFile!=null);
				 in=null;
				try{
				in= new Scanner(inFile);
				}catch(Exception e){
					System.err.println(e);
					System.exit(0);
				}

				a.readAndTrainDIOdelta(in);
				System.err.println("diodelta e' "+a.getDIOdelta());
				in.close();
				try {
					inFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			ReadFolder read= new ReadFolder();
			f=read.kItN(testFolder);
			for(int i=0;i<f.size();i++){
				inFile=null;
				
				try{
						inFile= new FileReader(dictionaryName);
				}catch(Exception e){
					System.err.println("Non riesco ad aprire il dizionario");
					System.exit(0);
				}
				assert(inFile!=null);
				in=null;
				try{
				in= new Scanner(inFile);
				}catch(Exception e){
					System.err.println(e);
					System.exit(0);
				}
				ArrayList<Node> dictionary=buildDictionary(in);
				assert(in!=null);
				in.close();
				try {
					inFile.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				inFile= null;
				try{
					String adrs= testFolder +"\\"+ f.get(i);
					/*
					 * leggo l'iesimo file  in lettura
					 */
					System.out.println(adrs);
					inFile= new FileReader(adrs);
					
					
				}catch(Exception e){
					System.err.println("Non riesco ad aprire il file "+ f.get(i));
					System.exit(0);
					/**
					 * @throws NullPointerException
					 */
					
				}
				assert(inFile != null);
				/*
				 * so che l'i-esimo file test non è null
				 * scrivo il nome del corrente file in lettura
				 */
				in=null;
				in= new Scanner(inFile);
				quoties(in, dictionary);
				in.close();
				/*
				 * ne conto le occorrenze nel dizionario
				 * chiudo lo stream in lettura
				 */
				try{
					inFile.close();
					/*
					 * chiudo il file in lettura
					 */
					
				}catch(Exception e){
					System.err.println("Non riesco a chiudere il file "+ f.get(i));
					/**
					 * @throws IoException
					 */
				}
				
				/*
				 * per iterare il dizionario
				 */			
				ArrayList<Integer> temp=new ArrayList<Integer>();
				for (Node ai : dictionary){
//				for(Iterator<Node> ai= dictionary.iterator();ai.hasNext();){
					Node t = ai;
					
					
						/*
						 * 
						 *costruisco una lista di interi per poter
						 *confrontare i files
						 */
					temp.add(Integer.parseInt(t.toString()));
					
					
				}
				a.classify(temp);
				//System.err.println("HERE");
				
			}
			break;
			default:
				//System.out.println("Goodbye");
				System.exit(0);	
			}
		
		}
		
	}
	

