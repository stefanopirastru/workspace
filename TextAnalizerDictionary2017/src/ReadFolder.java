import java.io.File;

import java.util.*;

/**
 * 
 * @author stefano
 *
 */
public class ReadFolder  {
	
	public ArrayList<String> kItN(String c){
		/**
		 * @param c nome della cartella dove cercare files
		 * @return lista di files presenti
		 */
		File folder = new File(c);
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> v= new ArrayList<String>();
	
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  /*
		    	   * se il contenuto della cartella è un file
		    	   */
		        //System.out.println("File " + listOfFiles[i].getName());
		        v.add( listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
		    	  /*
		    	   * se il contenuto della cartella è una cartella
		    	   */
		        //System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }
		    /*
		     * li riordino e restituisco l'arraylist ordinato
		     */
		 Collections.sort(v);
		// for(String counter : v){
		//	 System.out.println(counter);
		// }
		 return v;
	}
}
