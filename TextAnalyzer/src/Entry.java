
public class Entry implements Comparable<Entry> {
		public String key;
		public int    value;
			
		public Entry(String key, int value) {
			this.key = key;
			this.value = value;
		}

		// Il metodo "equals" ridefinisce il metodo corrispondente
		// nella classe base "Object". Le prime tre righe sono
		// standard e devono essere implementate ogni volta che si
		// ridefinisce "equals". Due oggetti "Entry" sono uguali
		// solo se le loro chiavi coincidono (il valore non conta).
		public boolean equals(Object other) {
			if (other == this) return true;
			if (!(other instanceof Entry)) return false;
			Entry o = (Entry) other;
			return (key.equals(o.key));
		}

		// Il metodo "compareTo" implementa il metodo corrispondente
		// definito nell'interfaccia "Comparable". Anche in questo
		// caso, nel confronto tra due oggetti "Entry" conta solo "key".
		public int compareTo(Entry other) {
			return key.compareTo(other.key);
		}

		// Ridefinizione del metodo "toString" della classe Object
		public String toString() {
			return (key + " " + value);
		}
				
}
