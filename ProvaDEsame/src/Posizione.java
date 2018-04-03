

public class Posizione implements Iterable {
	int x;
	int y;
	int tipologia;//{-1= MURO, 0=START, 1=FINISH, 2=FREE};

	public Posizione(){
		x=0;
		y=0;
		tipologia=-1;
	}
	public Posizione(int ex, int ypsilon, int a){
		x=ex;
		y=ypsilon;
		tipologia=a;
		
	}
/*	public boolean HasNext(){
		
	}
	public Posizione Next(){
		
	}*/
	

}

