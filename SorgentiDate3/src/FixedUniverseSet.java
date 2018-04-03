public class FixedUniverseSet {

	private boolean[] table;
	private int       maxCardinality;
	private int       size;
	
	public FixedUniverseSet(int maxCardinality) {
		table = new boolean[maxCardinality];
		for (int i = 0; i < maxCardinality; ++i) {
			table[i] = false;
		}
		this.maxCardinality = maxCardinality;
		size = 0;
	}
	
	public void add(int id)  {
		if (id < maxCardinality) {
			table[id] = true;
			size += 1;
		}
	}
	
	public void remove(int id) {
		if (id < maxCardinality) {
			table[id] = false;
			size -= 1;
		}
	}
	
	public boolean isMember(int id) {
		return ((id < maxCardinality) ? table[id] : false);
	}
	
	public int getSize() {
		return size;
	}
	
	public void union(FixedUniverseSet other) {
		if (other.maxCardinality == this.maxCardinality) {
			size = 0;
			for (int i = 0; i < maxCardinality; ++i) {
				this.table[i] = this.table[i] || other.table[i];
				if (this.table[i]) {
					size += 1;
				}
			}
		}
	}
	
	public void intersection(FixedUniverseSet other) {
		if (other.maxCardinality == this.maxCardinality) {
			size = 0;
			for (int i = 0; i < maxCardinality; ++i) {
				this.table[i] = this.table[i] && other.table[i];
				if (this.table[i]) {
					size += 1;
				}
  			}
		}
	} 
	
	public void difference(FixedUniverseSet other) {
		if (other.maxCardinality == this.maxCardinality) {
			size = 0;
			for (int i = 0; i < maxCardinality; ++i) {
				this.table[i] = this.table[i] && !other.table[i];
				if (this.table[i]) {
					size +=1;
				}
			}
		}
	}
	
	public void complement() {
		for (int i = 0; i < maxCardinality; ++i) {
			this.table[i] = !this.table[i];
		}
		size = maxCardinality - size;
	}
	
}
