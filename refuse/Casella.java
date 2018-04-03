public class Casella {
	public boolean visited;
	public int x;
	public int y;
	public Casella(){
		visited=false;
		x=0;
		y=0;
	}
	public Casella(int a, int b){
		visited=true;
		x=a;
		y=b;
	}
	public String toString(){
		return ("("+x+","+y+")");
	}
}
