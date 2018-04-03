import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class OccurrenceReader {
	private int okCnt;
	private int spamCnt;
	private int DIOdelta;
	private ArrayList<ArrayList<Integer>> okList;
	private ArrayList<ArrayList<Integer>> spamList;
	
	public  OccurrenceReader(){
		okCnt=-1;
		spamCnt=-1;
		okList=new ArrayList<ArrayList<Integer>>();
		spamList=new ArrayList<ArrayList<Integer>>();
	}
	
	public int getDIOdelta() {
		return DIOdelta;
	}
	public void setDIOdelta(int dIOdelta) {
		DIOdelta = dIOdelta;
	}
	//costo O(dictionary.size())
	public double modulo( ArrayList<Integer> A){
		/**
		 * modulo di un vettore d-dimensionale
		 * @param A: lista con le componenti del vettore 
		 */
		Integer part;
		double piece=0;
		Iterator<Integer> ai=A.iterator();
		
		/*if(A.isEmpty()){
			*System.out.print("Y");
		*}
		*	else{
		*		//System.out.print("N");
		*	}*/
		while(ai.hasNext()){
			part=ai.next();
			piece+= Math.pow(part,2);			
		}
		return piece;
		
	}
	
	//costo O(dictionary.size())
	public double distanza(ArrayList<Integer> A, ArrayList<Integer> B){
		/**
		 * distanza di verosimiglianza
		 * tra due vettori
		 * @param A vettore d-dimensionale
		 * @param B vettore d-dimensionale
		 * @return distanza tra due punti
		 */
		Integer partA;
		Integer partB;
		double grassmann=0;
		Iterator<Integer> ah=A.iterator();
		Iterator<Integer> ahAh=B.iterator();
		/*
		 * per scorrere i due arrayList
		 */
		while(ah.hasNext()&& ahAh.hasNext()){
			partA=ah.next();
			partB=ahAh.next();
			grassmann+=Math.pow(partB-partA,2);
			/* 
			 * faccio la sommatoria della differenza tra le occorrenze
			 * elevata al quadrato
			 */   
		}
		 return grassmann;
		 /*
		  * calcolo la radice quadrata
		  */
	}
	
	
	//costo O(fileTest)
	public void classify(ArrayList<Integer> po){
		
		int okCntT=0;
		int spamCntT=0;
		//System.err.print(okList.isEmpty());
		//System.err.print(spamList.isEmpty());
		for(ArrayList<Integer> ol:okList){
			if(distanza(ol,po)<=DIOdelta){
				okCntT++;
			}
		}
		for(ArrayList<Integer> sl:spamList){
			if(distanza(sl,po)<=DIOdelta){
				spamCntT++;
			}
		}
		
		if(spamCntT<okCntT){
			System.out.println("il file è OK");
		
		}
		else{
			System.out.println("questo è un file SPAM");
		
		}
		//System.out.println(" ok "+okCnt+" spam "+spamCnt);
	}
	

	//costo
	//fileTrain=fileOk+fileSpam
	//  O(dictionary.size()*fileTrain^2) + O(fileTrain) =O(dictionary.size()*fileTrain^2)

	public String readAndTrainDIOdelta(Scanner in){
		ArrayList<Integer> innerArray =new ArrayList<Integer>();
		String lettura;
		if(in.hasNext()){
			lettura=in.next();
			//System.out.println("read it "+ lettura);
			if(lettura=="OK"){
				okCnt=0;
			}
		}
		int i=0;
		int findDistMax=0;
		int distMax=0;
		int number;
		//leggo righe file ok  costo O(fileOK)
		while(in.hasNext() && spamCnt==-1){
			if(in.hasNext()){
				String pattern = in.next();
				if(pattern.matches("[a-zA-Z]*")){
					//System.out.println("detro if [a-zA-Z]*"+ pattern);
						spamCnt=0;
					
					
				}else if(pattern.matches("[0-9]+")){
					number=Integer.parseInt(pattern);
					//System.out.print(" "+number+" ");
					
						innerArray.add(number);
						findDistMax+=number;
						//System.out.print(" fdm "+findDistMax+" ");
					
					//System.out.print("number"+ number);
						
				}else {
					okList.add(innerArray);
					innerArray=new ArrayList<Integer>();
					if(distMax<findDistMax){
						distMax=findDistMax;
						findDistMax=0;
					}
					
					
					//System.out.println();
					//System.err.print("errore lettura file "+pattern);
				}
					
			
			}
			
		}
		
		//leggo righe file spam O(fileSpam)
		
		while(in.hasNext()){
			if(in.hasNext()){
				
				String pattern = in.next();
				//System.out.print(" "+pattern+" ");
				if(pattern.matches("[a-zA-Z]*")){
					System.out.println("dentro if [a-zA-Z]*"+ pattern);

				}else if(pattern.matches("[0-9]+")){
					number=Integer.parseInt(pattern);
					//System.out.print(" "+number+" ");
					
						
					
					
						innerArray.add(number);
					
					//System.out.print("number"+ number);
					findDistMax+=number;	
				}else{
					spamList.add(innerArray);
					innerArray=new ArrayList<Integer>();
					if(distMax<findDistMax) {
						distMax=findDistMax;
						findDistMax=0;
					}
						
					
					
					//System.err.print("fino a qui "+ pattern);
				}
			}

		}
		
		//System.out.println(okList);
		//System.err.println(spamList);
		//fileTrain=fileOk+fileSpam
		//cerco delta ottimale  O(fileTrain^2)
		int fp=0;
		i=0;
		int j=0;
		
		int DIOfp=1000000000;
		okCnt=0;
		for(int delta=0;delta<=distMax;delta++){
			for(ArrayList<Integer> p : okList){
				okCnt=0;
				spamCnt=0;
				for(ArrayList<Integer> curr : okList){
					//System.out.print(" dist "+distanza(curr,p)+" delta "+delta);
					if(i!=j && distanza(curr, p)<delta){
						
						okCnt++;
						//System.out.println("distanza ok "+distanza(curr, p));
					}
					j++;
				}
				//System.out.print("isempty? ");
				//System.out.print(spamList.isEmpty());
				for(ArrayList<Integer> dot : spamList){
					//System.err.println(" danza "+distanza(dot,p));
					//System.out.println(" dist "+distanza(dot,p)+" delta "+delta);
					if( distanza(dot, p)<delta){
						
						spamCnt++;
						//System.out.println("distanza "+distanza(curr, p));
					}
				}
				//System.out.println("os OK "+okCnt+" spam "+spamCnt);
				if(spamCnt>=okCnt){
					fp++;
				}
				i++;
			}
			for(ArrayList<Integer> p : spamList){
				okCnt=0;
				spamCnt=0;
				for(ArrayList<Integer> curr : okList){
					if(distanza(curr, p)<delta){
						
						okCnt++;
						//System.out.println("distanza ok "+distanza(curr, p));
					}
				}
				for(ArrayList<Integer> curr : spamList){
					if(i!=j && distanza(curr, p)<delta){
						//System.err.println(curr +"curr "+ p+" p");
						spamCnt++;
						//System.out.println("distanza spam "+distanza(curr, p));
					}
					j++;
				}
				//System.out.println("so OK "+okCnt+" spam "+spamCnt);
				//System.err.print(" "+String.valueOf(fp<DIOfp));
				if(spamCnt>=okCnt){
					fp++;
				}
				i++;
			}
			//System.out.println("fp "+fp+" dD "+delta);
			
			
			if(fp<=DIOfp){
				DIOfp=fp;
				fp=0;
				setDIOdelta(delta);
			}
			//System.out.println("dioDelta "+getDIOdelta());
		}
		spamCnt=0;
		okCnt=0;
		//fin calcolo dio delta;
		
		return "here";
	}
	
	
}
