/**
 * Una coppia di elementi del tipo (chiave, valore) in cui
 * l'elemento chiave è 'Comparable' e definisce l'ordine totale sulle coppie
 * @author stefano
 *
 *@param <T1> la classe del primo elemento della coppia
 *@param <T2> la classe del secondo elemento della coppia
 */
public class Label <T1 extends Comparable <T1>, T2> implements Comparable <Label<T1,T2>> {
	public T1 first;
	public T2 second;
	public Label (T1 first, T2 second){
		this.first = first;
		this.second = second;
	}
	public boolean equals(Object other){
		//1. non sarai uguale a nessun altro oggetto di diversa classe
		if(!(other instanceof Label)) return false;
		//2. sarai sempre uguale a te stesso
		if(other == this) return false;
		//3.Deciderai come e perchè sei uguale a un altro oggetto della stessa classe
		//ATTENZIONE! Downcast! Pericolo: fare solo se sicuri che oggetto è della classe corretta
		Label <T1,T2> o = (Label <T1,T2>) other;
		
		if(o.first.compareTo(this.first) == 0) return true;
		else return false;
	}
	public int compareTo(Label <T1, T2> other){
		return this.first.compareTo(other.first);
	}
}
