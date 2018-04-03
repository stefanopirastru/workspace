/**
 * Una coppia in cui il primo elemento è 'Comparable' 
 * e definisce l'ordine totale sulle coppie.  
 * 
 * @author tac
 *
 * @param <T1>
 * @param <T2>
 */
public class Label <T1 extends Comparable< T1 >, T2> implements Comparable< Label<T1, T2> >{
	
	public T1 first;
	public T2 second;
	
	public Label(T1 first, T2 second) {
		this.first = first;
		this.second = second;
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof Label)) return false;
		if (other == this) return true;
		Label<T1,T2> o = (Label<T1,T2>) other;
		if (o.first.compareTo(this.first) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int compareTo(Label <T1, T2> other) {
		return this.first.compareTo(other.first);
	}

}
