import java.util.Iterator;
import java.util.Stack;

public class robotNavigatorIterative {
	int marker[][];
	
	GridWorld mondo; 
	GridWorld.Coordinate[][] mondoCoordinate;
	Stack<GridWorld.Coordinate> way;
	int size;
	
	robotNavigatorIterative(GridWorld world,int n){		//grid world,int n
		marker =new int [n+2][n+2];
		mondoCoordinate=new GridWorld.Coordinate[n][n];
		for(int i=1;i<n+1;i++){
			for(int j=1;j<n+1;j++){
				marker [i][j]=0;
			}
		}
		for(int i=1;i<n+2;i++){
			marker [1][i]=2;
			marker [n+1][i]=2;
			marker [i][1]=2;
			marker [i][n+1]=2;
		}
		for(int i=1;i<n;i++){
			for(int j=1;j<n;j++){
				mondoCoordinate [i][j]=null;
			}
		}
		mondo=world;	
		way=new Stack<GridWorld.Coordinate>();
		size=n;
	}
	//cosstruttore(gridworld,size di gridworld)
	//inizializzo 2 matrici 
	// O(n*n)
	
	void exploreWorld(){
		while(!mondo.targetReached()){
			int y=mondo.getCurrentCell().row;
			int x=mondo.getCurrentCell().col;
			int my=mondo.getCurrentCell().row+1;
			int mx=mondo.getCurrentCell().col+1;
			//segno che sono nel nodo presente
			marker[mx][my]=1;
			mondoCoordinate[x][y]=mondo.getCurrentCell();
			//coloro di rosso le adiacenze non ancora esplorate
			if (marker[mx][my-1]!=1){//north=false
				marker[mx][my-1]=2;
			}
			if (marker[mx+1][my]!=1){//est=false
				marker[mx+1][my]=2;
			}
			if (marker[mx][my+1]!=1){//south=false
				marker[mx][my+1]=2;
			}
			if (marker[mx-1][my]!=1){//ovest=false
				marker[mx-1][my]=2;
			}
			//System.out.println("attuale  "+mondo.getCurrentCell());
			Iterator<GridWorld.Coordinate> ic= mondo.getAdjacentFreeCells().iterator();
			//System.out.println("controllo adiacenze chiamando iteratore ");
			//riporto a inesplorate le adiacenze valide non ancora esplorate
			while(ic.hasNext()){
			GridWorld.Coordinate c= ic.next();
			//System.out.println("adiacente  "+c);
				//scopro mondo attorno a robot
				if (c.col==x+1 && c.row==y && marker[mx+1][my]!=1){//est=false //destra
					marker[mx+1][my]=0;
					//System.out.println("libero:  dx   ");
				}
				if (c.col==x && c.row==y-1 && marker[mx][my-1]!=1){//north=false
					marker[mx][my-1]=0;
					//System.out.println("libero  up   ");
				}
				if (c.col==x-1 && c.row==y && marker[mx-1][my]!=1){//ovest=false
					marker[mx-1][my]=0;
					//System.out.println("libero  sx   ");
				}
				if (c.col==x && c.row==y+1 && marker[mx][my+1]!=1){//south=false
					marker[mx][my+1]=0;
					//System.out.println("libero  dw   ");
				}
			}
			move();
		}
		mondoCoordinate[size-1][size-1]=mondo.getCurrentCell();
		way.add(mondo.getCurrentCell());
		//adjustWay();
		//System.out.println(way);
		//minWay();
		
	}
	//esplora il mondo fino a trovare il target
	//data la scelta pessima su move per tentare di eplorare piu mondo possibile 
	//la complessità varia si dalla dimensione delle celle libere su gridworld ma 
	//soprattutto dalla densità che ne definisce la parte effettivamente eplorabile
	//O(n*n*density)  density è un \valore che assume gridworld in costruzione e può 
	//variare da 0.1 a 1
	
	void move(){
		if(marker[mondo.getCurrentCell().col+1][mondo.getCurrentCell().row]==0){//north
			//System.out.println("muovo  dx  to  ("+y+","+(x+1));
			way.push(mondo.getCurrentCell());//posizione attuale
			mondo.moveToAdjacentCell(c2d(mondo.getCurrentCell().col, mondo.getCurrentCell().row-1));//muoviti a cond if
			//System.out.println("muovo  dx   to "+mondo.getCurrentCell());
			//way.pop();
		}
		else if(marker[mondo.getCurrentCell().col][mondo.getCurrentCell().row+1]==0){//west
			way.push(mondo.getCurrentCell());//-posizione attuale
			//System.out.println("muovo  dw  to  ("+(y+1)+","+(x));
			mondo.moveToAdjacentCell(c2d(mondo.getCurrentCell().col-1,mondo.getCurrentCell().row));//muoviti a cond if
			//System.out.println("mosso  dw   to "+mondo.getCurrentCell());
			//way.pop();
		}
		else if(marker[mondo.getCurrentCell().col+1][mondo.getCurrentCell().row+2]==0){//south
			way.push(mondo.getCurrentCell());//posizione attuale
			//System.out.println("muovo  sx  to("+y+","+(x-1));
			mondo.moveToAdjacentCell(c2d(mondo.getCurrentCell().col,mondo.getCurrentCell().row+1));//muoviti a cond if
			//System.out.println("mosso  sx   to "+mondo.getCurrentCell());
			//way.pop();
		}
		else if(marker[mondo.getCurrentCell().col+2][mondo.getCurrentCell().row+1]==0){//est
			way.push(mondo.getCurrentCell());//posizione attuale
			//System.out.println("muovo  up   to("+(y-1)+","+(x));
			mondo.moveToAdjacentCell(c2d(mondo.getCurrentCell().col+1,mondo.getCurrentCell().row));//muoviti a cond if
			//System.out.println("mosso  up  to  "+mondo.getCurrentCell());
			//way.pop();
		}
		//per tutte le direzioni
		//System.out.println("peek " +way.peek()+"   attuale "+mondo.getCurrentCell());
		//System.out.println("strada " +way);
		//way.push(mondo.getCurrentCell());
		else if(!way.empty()){  
			mondo.moveToAdjacentCell(c2d(way.peek()));
			way.pop();
		}
	}
	//funzione che decide il movimento del robot
	//O(1)
	
	public void minWay(){
		myGraph g=new myGraph();
		g.readMatrix(mondoCoordinate);
		g.bsfMirate(way.peek());
		//System.out.println("fine bsf ");
		way=g.minWay(mondo,way.peek());
		//System.out.println("min way"+way);
		/*if(mondo.checkPathAcyclic(way)){
			System.out.println("min way"+way);
			//System.out.println("way.size "+way.size());
		}*/
	}
	//crea grafo e esegue bsf e sostituisce way con la via minima dalla partenza
	//fino ad una qualsiasi coordinata(way.peek() restituisce subito dopo l'esplorazione
	//la coordinata target di arrivo)
	//calcolo strada minima O(V+E) dove V è il numero di celle esplorate ed E è il numero di archi 
	//del grafo E si puo stimare in base alla densità di gridworld 
	//molto approssimativamente pensando idealmente che ogni cella ha solo 4 adiacenze
	//allora avrò E=V*4*density		dove density è la densità assegnata a gridworld
	//quindi il costo totale sarebbe O(V+4V)
	
	GridWorld.Direction c2d (GridWorld.Coordinate c){
		if (mondo.getCurrentCell().col==c.col && mondo.getCurrentCell().row==c.row+1) return GridWorld.Direction.NORTH;
		if (mondo.getCurrentCell().col==c.col && mondo.getCurrentCell().row==c.row-1) return GridWorld.Direction.SOUTH;
		if (mondo.getCurrentCell().col==c.col+1 && mondo.getCurrentCell().row==c.row) return GridWorld.Direction.WEST;
		if (mondo.getCurrentCell().col==c.col-1 && mondo.getCurrentCell().row==c.row) return GridWorld.Direction.EAST;
		return null;
	}
	//converte una coordinata in una direzione in confronto alla cella corrente
	//O(1)
	
	GridWorld.Direction c2d (int x,int y){
		if (mondo.getCurrentCell().col==x && mondo.getCurrentCell().row==y+1) return GridWorld.Direction.NORTH;
		if (mondo.getCurrentCell().col==x && mondo.getCurrentCell().row==y-1) return GridWorld.Direction.SOUTH;
		if (mondo.getCurrentCell().col==x+1 && mondo.getCurrentCell().row==y) return GridWorld.Direction.WEST;
		if (mondo.getCurrentCell().col==x-1 && mondo.getCurrentCell().row==y) return GridWorld.Direction.EAST;
		return null;
	}
	//converte una coordinata in una direzione in confronto alla cella corrente
	//O(1)
	
	void printWay(){
		for(int i=0;i<way.size();i++){
			System.out.print(way.get(i)+" ");
		}
		//System.out.println("way.size():\t"+way.size() );
	}
	//stampa way
	//O(way.size)
	
	void firstWayToTarget(){
		while(!mondo.targetReached()){
			int y=mondo.getCurrentCell().row;
			int x=mondo.getCurrentCell().col;
			int my=mondo.getCurrentCell().row+1;
			int mx=mondo.getCurrentCell().col+1;
			//segno che sono nel nodo presente
			marker[mx][my]=1;
			//coloro di rosso le adiacenze non ancora esplorate
			if (marker[mx][my-1]!=1){//north=false
				marker[mx][my-1]=2;
			}
			if (marker[mx+1][my]!=1){//est=false
				marker[mx+1][my]=2;
			}
			if (marker[mx][my+1]!=1){//south=false
				marker[mx][my+1]=2;
			}
			if (marker[mx-1][my]!=1){//ovest=false
				marker[mx-1][my]=2;
			}
			//System.out.println("attuale  "+mondo.getCurrentCell());
			Iterator<GridWorld.Coordinate> ic= mondo.getAdjacentFreeCells().iterator();
			//System.out.println("controllo adiacenze chiamando iteratore ");
			//riporto a inesplorate le adiacenze valide non ancora esplorate
			while(ic.hasNext()){
			GridWorld.Coordinate c= ic.next();
			//System.out.println("adiacente  "+c);
				//scopro mondo attorno a robot
				if (c.col==x+1 && c.row==y && marker[mx+1][my]!=1){//est=false //destra
					marker[mx+1][my]=0;
					//System.out.println("libero:  dx   ");
				}
				if (c.col==x && c.row==y-1 && marker[mx][my-1]!=1){//north=false
					marker[mx][my-1]=0;
					//System.out.println("libero  up   ");
				}
				if (c.col==x-1 && c.row==y && marker[mx-1][my]!=1){//ovest=false
					marker[mx-1][my]=0;
					//System.out.println("libero  sx   ");
				}
				if (c.col==x && c.row==y+1 && marker[mx][my+1]!=1){//south=false
					marker[mx][my+1]=0;
					//System.out.println("libero  dw   ");
				}
			}
			moveFirst();
		}
		way.add(mondo.getCurrentCell());
		
	}
	//esplora il mondo fino a trovare il target
	//data la scelta ottima su move per tentare di eplorare il meno  mondo possibile 
	//la complessità varia per il numero di celle libere su gridworld ma 
	//è poco sensibille alla densità che ne definisce la parte effettivamente eplorabile
	//omega(n*n*density/2) 
	
	void moveFirst(){
		if (marker[mondo.getCurrentCell().col+2][mondo.getCurrentCell().row+1]==0){//est
			way.push(mondo.getCurrentCell());//posizione attuale
			//System.out.println("muovo  up   to("+(y-1)+","+(x));
			mondo.moveToAdjacentCell(c2d(mondo.getCurrentCell().col+1,mondo.getCurrentCell().row));//muoviti a cond if
			//System.out.println("mosso  up  to  "+mondo.getCurrentCell());
			//way.pop();
			
		}
		else if(marker[mondo.getCurrentCell().col+1][mondo.getCurrentCell().row+2]==0){//south
			way.push(mondo.getCurrentCell());//posizione attuale
			//System.out.println("muovo  sx  to("+y+","+(x-1));
			mondo.moveToAdjacentCell(c2d(mondo.getCurrentCell().col,mondo.getCurrentCell().row+1));//muoviti a cond if
			//System.out.println("mosso  sx   to "+mondo.getCurrentCell());
			//way.pop();
		}
		else if(marker[mondo.getCurrentCell().col][mondo.getCurrentCell().row+1]==0){//west
			way.push(mondo.getCurrentCell());//-posizione attuale
			//System.out.println("muovo  dw  to  ("+(y+1)+","+(x));
			mondo.moveToAdjacentCell(c2d(mondo.getCurrentCell().col-1,mondo.getCurrentCell().row));//muoviti a cond if
			//System.out.println("mosso  dw   to "+mondo.getCurrentCell());
			//way.pop();
		}
		else if(marker[mondo.getCurrentCell().col+1][mondo.getCurrentCell().row]==0){//north
			//System.out.println("muovo  dx  to  ("+y+","+(x+1));
			way.push(mondo.getCurrentCell());//posizione attuale
			mondo.moveToAdjacentCell(c2d(mondo.getCurrentCell().col, mondo.getCurrentCell().row-1));//muoviti a cond if
			//System.out.println("muovo  dx   to "+mondo.getCurrentCell());
			//way.pop();
		}
		//per tutte le direzioni
		//System.out.println("peek " +way.peek()+"   attuale "+mondo.getCurrentCell());
		//System.out.println("strada " +way);
		//way.push(mondo.getCurrentCell());
		else if(!way.empty()){  
			mondo.moveToAdjacentCell(c2d(way.peek()));
			way.pop();
		}
	}
	//funzione che decide il movimento del robot
	//O(1)
	
	void resetRobotNavigator(){
		if(!way.empty()){
			way.pop();
			while(!way.empty()){
				mondo.moveToAdjacentCell(c2d(way.pop()));
			}
		}
		for(int i=1;i<size+1;i++){
			for(int j=1;j<size+1;j++){
				marker [i][j]=0;
			}
		}
		for(int i=1;i<size+2;i++){
			marker [1][i]=2;
			marker [size+1][i]=2;
			marker [i][1]=2;
			marker [i][size+1]=2;
		}
		for(int i=1;i<size;i++){
			for(int j=1;j<size;j++){
				mondoCoordinate [i][j]=null;
			}
		}
		
	}
	//riporta il robot allo stato inizale del costruttore
	//O(size*size)
	
}
