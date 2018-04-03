import edu.princeton.cs.algs4.StdDraw;

//Obiettivo: confronto di prestazioni tra
//String e StringBuffer per la tabulazione
//di vettori (tabulazione = vettore -> stringa)
public class StringVsStringBuffer {
	//tabula il vettore table usando la classe String
	public static String tabulateWithString(int[] table){
		String result = ""; //1 istanza di string immutabile
		for(int i = 0; i< table.length; i++){
			result= result + " " + table[i];  //all primo giro del loop sono 5 stringhe create!!
						//per i che va da 0 a n = table.length
						//i + 1
		}
		return result;
	
	}
	//quanto vale sum{i=0}^n {i + 1}???
	// somma da i+ 1 vale n
	//risposta il tot è n(n+1) theta (n^2)
	//se cresce in modo quadratico un array di grosse dimensioni impiegherà il quadrato dell'input in tempo
	
	//tabula il vettore table usando oggetti StringBuffer
	public static String tabulateWithStringBuffer(int[] table){
		StringBuffer result = new StringBuffer("");
		//per i che va da 0 a n = table.length
		for(int i = 0; i< table.length; i++){
			// COSTO UNITARIO DI OPERAZIONE
			result= result.append(" " + table[i]);
		}
		//quanto vale sum{i=0}^n {i + 1}???
		//risposta il tot è n+(n+1) theta(n)
		return result.toString();
	}
	
	public static void main(String[] args){ /*non posso chiamare un metodo non statico 
												e devo usare un oggetto della classe per usare il main*/
		int[] prova = {1,2,3,4,5};
		/*String t = tabulateWithString(prova);
		System.out.println(t);   con string*/
		String s = tabulateWithStringBuffer(prova);
		System.out.println(s); //conStringBuffer
		
		
		long start = System.nanoTime();
		String t = tabulateWithString(prova);
		long elapsed1 = System.nanoTime()- start; //durata di esecuzione di String
		
		
		start = System.nanoTime();
		 s = tabulateWithStringBuffer(prova);
		 long elapsed2 = System.nanoTime() - start; //durata esecuzione di StringBuffer
		
		
		System.out.println("String :  " + elapsed1 + "  StringBuffer :" + elapsed2); 
		System.nanoTime();//mi dice il tempo di esecuzione in nanosecondi
		
		int[] prova2 = {0};
		 start = System.nanoTime();
	    t = tabulateWithString(prova2);
		elapsed1 = System.nanoTime()- start; //durata di esecuzione di String
		
		
		start = System.nanoTime();
		 s = tabulateWithStringBuffer(prova2);
		 elapsed2 = System.nanoTime() - start; //durata esecuzione di StringBuffer
		 
		 System.out.println("String :  " + elapsed1 + "  StringBuffer :" + elapsed2); 
		 
		int[] prova3 = new int[10];
		for(int i= 0; i<10; i++){
			prova3[i] =i;
		}
		
		start = System.nanoTime();
		t = tabulateWithString(prova3);
		long elapsed3 = System.nanoTime()- start; //durata di esecuzione di String
		
		
		start = System.nanoTime();
		 s = tabulateWithStringBuffer(prova3);
		 long elapsed4 = System.nanoTime() - start; //durata esecuzione di StringBuffer
		
		System.out.println("String :  " + elapsed1 + "  StringBuffer :" + elapsed2); 
		
		int[] prova4 = new int[100];
		for(int i= 0; i<100; i++){
			prova4[i] =i;
		}
		start = System.nanoTime();
		t = tabulateWithString(prova4);
		long elapsed5 = System.nanoTime()- start; //durata di esecuzione di String
		
		
		start = System.nanoTime();
		 s = tabulateWithStringBuffer(prova4);
		 long elapsed6 = System.nanoTime() - start; //durata esecuzione di StringBuffer
		
		System.out.println("String :  " + elapsed1 + "  StringBuffer :" + elapsed2); 
		
		int[] prova5 = new int[1000];
		for(int i= 0; i<1000; i++){
			prova5[i] =i;
		}
		start = System.nanoTime();
		t = tabulateWithString(prova5);
		long elapsed7 = System.nanoTime()- start; //durata di esecuzione di String
		
		
		start = System.nanoTime();
		 s = tabulateWithStringBuffer(prova5);
		 long elapsed8 = System.nanoTime() - start; //durata esecuzione di StringBuffer
		
		System.out.println("String :  " + elapsed1 + "  StringBuffer :" + elapsed2); 
		
		int[] prova6 = new int[10000];
		for(int i= 0; i<10000; i++){
			prova6[i] =i;
		}
		start = System.nanoTime();
		t = tabulateWithString(prova6);
		long elapsed9 = System.nanoTime()- start; //durata di esecuzione di String
		
		
		start = System.nanoTime();
		 s = tabulateWithStringBuffer(prova6);
		 long elapsed10 = System.nanoTime() - start; //durata esecuzione di StringBuffer
		
		System.out.println("String :  " + elapsed1 + "  StringBuffer :" + elapsed2);
		
		int[] prova7 = new int[100000];
		for(int i= 0; i<100000; i++){
			prova7[i] =i;
		}
		start = System.nanoTime();
		t = tabulateWithString(prova7);
		long elapsed11 = System.nanoTime()- start; //durata di esecuzione di String
		
		
		start = System.nanoTime();
		 s = tabulateWithStringBuffer(prova7);
		 long elapsed12 = System.nanoTime() - start; //durata esecuzione di StringBuffer
		
		System.out.println("String :  " + elapsed1 + "  StringBuffer :" + elapsed2); 
		
		StdDraw.line(0,0,1,elapsed1);
		StdDraw.line(1,elapsed1,10,elapsed3);
		StdDraw.line(10,elapsed3,100,elapsed5);
		StdDraw.line(100,elapsed5,1000,elapsed7);
		StdDraw.line(1000,elapsed7,10000,elapsed9);
		StdDraw.line(10000,elapsed9,1000,elapsed11);
		
		StdDraw.line(0,0,1,elapsed2);
		StdDraw.line(1,elapsed2,10,elapsed4);
		StdDraw.line(10,elapsed4,100,elapsed6);
		StdDraw.line(100,elapsed6,1000,elapsed8);
		StdDraw.line(1000,elapsed8,10000,elapsed10);
		StdDraw.line(10000,elapsed10,100000,elapsed12);
	}
	
	
	
	
	
}
