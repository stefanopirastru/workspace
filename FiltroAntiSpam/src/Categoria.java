
public class Categoria implements Comparable <Categoria> {
	public double distanza;
	public boolean spam;
	
	public Categoria(){
		distanza= 0;
		spam = true;
	}
	public Categoria(double d, boolean sp){
		distanza = d;
		spam = sp;
		
	}
	public boolean equals(Object other) {
		if (other == this) return true;
		if (!(other instanceof Categoria)) return false;
		Categoria o = (Categoria) other;
		return (distanza.equals(o.distanza));
	}
	public int compareTo(Categoria b){
		if(this.distanza < b.distanza){
			return -1;
		}
		else if(this.distanza == b.distanza){
			return 0;
		}
		else return 1;
	}
	
	

}
