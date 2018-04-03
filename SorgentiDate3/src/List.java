import java.util.ListIterator;

public class List< Item extends Comparable<Item> > extends BasicList<Item> {
	
	// Metodo per cercare e restituire l'elemento di valore minimo
	// Restituisce "null" se la lista è vuota
	public Item findMin() {
		Item result = null;
		if (getSize() > 1) {
			ListIterator<Item> fw = getIterator();
			result = fw.next();
			while (fw.hasNext()) {
				Item current = fw.next();
				if (current.compareTo(result) < 0) {
					result = current;
				}
			}
		}
		return result;
	}
		
	// Metodo per cercare e restituire l'elemento di valore massimo
	// Restituisce "null" se la lista è vuota
	public Item findMax() {
		Item result = null;
		if (getSize() > 1) {
			ListIterator<Item> fw = getIterator();
			result = fw.next();
			while (fw.hasNext()) {
				Item current = fw.next();
				if (current.compareTo(result) > 0) {
					result = current;
				}
			}
		}
		return result;
	}
		
	// Metodo per cercare e restituire l'elemento con il 
	// valore immediatamente successivo a key 
	// Restituisce "null" se la lista è vuota oppure se
	// key coincide con il massimo elemento
	public Item findCeil(Item key) {
		Item result = findMax();
		if ((result == null) || (key.compareTo(result) == 0)) {
			return null;
		}
		ListIterator<Item> fw = getIterator();
		while (fw.hasNext()) {
			Item current = fw.next();
			if ((current.compareTo(key) > 0) && (current.compareTo(result) < 0)) {
				result = current;
			}
		}
		return result;
	}
		
	// Metodo per cercare e restituire l'elemento con il 
	// valore immediatamente precedente a key
	// Restituisce "null" se la lista è vuota oppure se
	// key coincide con il massimo elemento
	public Item findFloor(Item key) {
		Item result = findMin();
		if ((result == null) || (key.compareTo(result) == 0)) {
			return null;
		}
		ListIterator<Item> fw = getIterator();
		while (fw.hasNext()) {
			Item current = fw.next();
			if ((current.compareTo(key) < 0) && (current.compareTo(result) > 0)) {
				result = current;
			}
		}
		return result;
	}
		
	// Metodo per cercare la posizione del valore di key
	// ossia quanti elementi hanno un valore inferiore a key
	// Se la lista è vuota, oppure key non compare nella lista
	// ritorna l'indice -1.  
	public int findRank(Item key) {
		int result = 0;
		boolean found = false;
		ListIterator<Item> fw = getIterator();
		while (fw.hasNext()) {
			Item current = fw.next();
			if (current.compareTo(key) < 0) {
				result += 1;
			} else if (current.compareTo(key) == 0) {
				found = true;
			}
		}
		return (found ? result : -1);
	}
		
	// Metodo per cercare il numero di chiavi il cui valore
	// è compreso tra il valore di keyLo e il valore di keyHi
	// Se la lista è vuota, keyLo è maggiore di keyHi, 
	// oppure le chiavi non compaiono nella lista, restituisce -1.
	public int findRange(Item keyLo, Item keyHi) {
		if (keyLo.compareTo(keyHi) > 0) {
			return -1;
		} else {
			int found = 0;
			int result = 0;
			ListIterator<Item> fw = getIterator();
			while (fw.hasNext()) {
				Item current = fw.next();
				if ((current.compareTo(keyLo) > 0) && (current.compareTo(keyHi) < 0)) {
					result += 1;
				} else if (current.compareTo(keyLo) == 0) {
					found += 1;
				} else if (current.compareTo(keyHi) == 0) {
					found += 1;
				}
			}
			return (found == 2 ? result : -1);
		}
	}

}
