import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import static java.lang.Math.sqrt;

public class Main {
	public static void main(String args[]){
		 if( args.length == 1){
			 System.out.println("manca il nome del file!!");
			 System.out.println("utilizzo corretto: java TextAnalyzer <nome file input>");
			 System.exit(0);
		 }
		 assert(args.length == 1);
		String dictionnaryFileName = args[0]; //leggo file input
		FileReader input = null;
		try{
			input = new FileReader(dictionnaryFileName);
		}catch(Exception e){
			System.err.println("Non posso aprire il File" + dictionnaryFileName);
			System.exit(0);
		}
		assert(input != null);
		Scanner scanner = new Scanner(input);
		String[] inputBox = new String[10];
		int i = 0;
		while(scanner.hasNext()){
			if(i<10){
				inputBox[i] = scanner.next();
				i++;
			}
		}
		//ora ho un vettore formato da {nomeDizionario FileOk #minFO #maxFO Filespam #minFS #maxFS File? #minF? #maxF?}
		scanner.close();
		Integer estremi = new Integer(0);
		int max, min;
		min = estremi.parseInt(inputBox[3]) ; //converto gli estremi da string a int
		max = estremi.parseInt(inputBox[4]);	//idem
		String[] intestazioneFile = new String[2]; //questo file lo darò in pasto a riempilista (che è il main di TextAnalyzerDictionary)
		intestazioneFile[0]= inputBox[0]; //il nome dizionario
		
		TextAnalyzerWithDictionary ok = new TextAnalyzerWithDictionary();
		ArrayList<Entry> dictionary = null;
		int[] vettoreEnneDimensionale1 = new int[60];
		i=0;
		double[] vettoreDistanze = new double[max];
		Entry appoggio = new Entry("key",0);
		ArrayList <Categoria> fileOk = null;
		for(int j=min;j<=max;j++){
			intestazioneFile[1]= inputBox[1]+j+".txt"; //Per ogni loop sarà un diverso file
			dictionary = ok.riempiLista(intestazioneFile); //chiamata main
			while(i<60){
				appoggio=dictionary.get(i);
				vettoreEnneDimensionale1[i]=appoggio.value; //dalla lista ho estratto SOLO le "coordinate" occorrenze 
				vettoreDistanze[j]+= vettoreEnneDimensionale1[i]*vettoreEnneDimensionale1[i]; //quadrato di ogni occorrenza
				i++;
			}
			vettoreDistanze[j]= sqrt(vettoreDistanze[j]); //norma del vettore ok 
			
		}
		ArrayList <Categoria> fileSpam = null;
		min = estremi.parseInt(inputBox[5]); //estremi spam
		max = estremi.parseInt(inputBox[6]);
		i=0;
		double[] vettoreDistanze1 = new double[max];
		for(int j=min;j<=max;j++){
			intestazioneFile[1]= inputBox[4]+j+".txt";
			dictionary = ok.riempiLista(intestazioneFile);
			while(i<60){
				appoggio=dictionary.get(i);
				vettoreEnneDimensionale1[i]=appoggio.value;
				vettoreDistanze1[j]+= vettoreEnneDimensionale1[i]*vettoreEnneDimensionale1[i];
				i++;
			}
			vettoreDistanze1[j]= sqrt(vettoreDistanze1[j]);
			Categoria support= new Categoria(vettoreDistanze1[j],true);
			fileSpam.add(support);
		
		}
		/*ora ho il vettore file Spam composto da: norma dei file spam e inizializzato ogni elemento a true
		 * e il vettore file Ok composto da norma dei file e ogni elemento false
		 */
		i=0;
		ArrayList <Categoria> fileBoh = null;
		min = estremi.parseInt(inputBox[8]); //estremi boh
		max = estremi.parseInt(inputBox[9]);
		double[] vettoreDistanze2 = new double[max];
		for(int j=min;j<=max;j++){
			intestazioneFile[1]= inputBox[7]+j+".txt";
			dictionary = ok.riempiLista(intestazioneFile);
			while(i<60){
				appoggio=dictionary.get(i);
				vettoreEnneDimensionale1[i]=appoggio.value;
				vettoreDistanze2[j]+= vettoreEnneDimensionale1[i]*vettoreEnneDimensionale1[i];
				i++;
			}
			vettoreDistanze2[j]= sqrt(vettoreDistanze2[j]);

		
		}
		int min1=estremi.parseInt(inputBox[8]);
		int max1=estremi.parseInt(inputBox[9]);
		min=estremi.parseInt(inputBox[2]);
		min=estremi.parseInt(inputBox[3]);
		double[][] deltaPiccolo = new double[max][max1];
		for(int j=min;j<=max;j++){
			for(i=min1;i<=max1;i++){
				deltaPiccolo[j][i]=vettoreDistanze2[i]-vettoreDistanze[j];
				Categoria support= new Categoria(deltaPiccolo[j][i],false); //Il vettore identificherà il file spam con Categoria.spam ==true
				fileOk.add(support);
			}
		}

		min=estremi.parseInt(inputBox[5]);
		min=estremi.parseInt(inputBox[6]);
		double[][] deltaPiccolo1 = new double[max][max1];
		for(int j=min;j<=max;j++){
			for(i=min1;i<=max1;i++){
				deltaPiccolo[j][i]=vettoreDistanze2[i]-vettoreDistanze1[j];
				Categoria support= new Categoria(deltaPiccolo[j][i],true); //Il vettore identificherà il file spam con Categoria.spam ==true
				fileOk.add(support);
			}
		}
		Categoria support = new Categoria(0,true);
		fileSpam.addAll(fileOk);
		Collection.sort(fileSpam);
		int contatoreTrue=0;
		int contatoreFalse=0;
		for(int j=0;j<10;j++){
			if(fileSpam.get(i).spam==true){
				contatoreTrue++;
			}
			else contatoreFalse++;
		}
		if(contatoreTrue<contatoreFalse){
			System.out.print("spam ");
			fileSpam.iterator();
		}
		else{
			System.out.print("ok");
			fileSpam.iterator();
		}
		
	}
	

}
