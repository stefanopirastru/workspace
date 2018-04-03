
public class Prova {

	public static void main(String[] args){
		ClassA myObject = new ClassA(); // no costruttore: default
		/*ClassB anotherObject = new ClassB(); se esiste un costruttore ma cerco di usare costr. 
												default  ERRORE*/
		
		
		//tutte le classi derivano dalla classe Object e ne ereditano i metodi
		System.out.println(myObject.toString()); //stampa il tipo dell'oggetto myObject e l'indirizzo
		Complex myComplex = new Complex(10,20);
		System.out.println(myComplex.toString()); /*anche se ho fatto l'overriding
		 											stamperà tipo e indirizzo, perchè il binding è 
		 											scelto dalla JVM*/
		
		//come risolverlo?? chiedi al prof
		
		
	}
}
