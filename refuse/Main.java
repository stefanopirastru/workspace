import java.util.Iterator;

/**
 * 
 * @author Stefano Pirastru
 * Classe che realizza un algoritmo di navigazione utilizzando il simulatore GridWorld
 * Il simulatore è già stato incluso nel progetto. 
 * Non saranno forniti i sorgenti di Gridworld
 * Fare riferimento alla documentazione presente in doc (index.html)
 *
 */

public class Main {
	public enum color{BLACK, GREY, WHITE}
	static public void dfs(GridWorld gw,Grafo<Casella> G, color[] marked){
		for(int u=0;u<G.V.size()-1;u++){
			marked[u]=color.WHITE;
		}
		for(int u=0;u<G.V.size()-1;u++){
			if(marked[u]==color.WHITE){
				dfsVisit(gw,G,u, marked);
			}
		}
	}
	static public void dfsVisit(GridWorld gw, Grafo<Casella> G, int count, color[] marked){
		marked[count]=color.GREY;
		Casella v= new Casella();
		for(GridWorld.Coordinate a :gw.getAdjacentFreeCells()){
			v.x=a.col;
			v.y=a.row;
			v.tag=count;
			
			
			if(!G.areLinked(G, count, v)){
				G.V.get(count).add(v);
				System.out.println(v);
				count++;
				}
			if(marked[count]==color.WHITE) dfsVisit(gw,G,count,marked);				
		}
		marked[count]=color.BLACK;
	}
	public static void dfs2(GridWorld gw, Grafo<Casella> G,DfsInfo r, int i){
		for(int u=0; u<G.V.size();u++){
			r.marked[u]= DfsInfo.color.WHITE;
			r.pig[u]=-1;
		}
		r.tick=0;
		for(GridWorld.Coordinate v : gw.getAdjacentFreeCells()){
			System.out.print("next: ("+v.col+", "+v.row+") ");
			if(v.col>gw.getCurrentCell().col){
				gw.moveToAdjacentCell(GridWorld.Direction.EAST);
				System.out.print("dx ");
			}
			if(v.row>gw.getCurrentCell().row){
				gw.moveToAdjacentCell(GridWorld.Direction.SOUTH);
				System.out.print("g ");
			}
			if(r.marked[i]==DfsInfo.color.WHITE){
				dfsVisit2(gw,gw.getCurrentCell(),G,i,r);
				
			}
			
		}
		System.err.println("Fine dfs");
	}
	public static void dfsVisit2(GridWorld gw, GridWorld.Coordinate here,Grafo<Casella> G,int u,DfsInfo r){
		GridWorld.Direction e= GridWorld.Direction.EAST;
		GridWorld.Direction w= GridWorld.Direction.WEST;
		GridWorld.Direction s= GridWorld.Direction.SOUTH;
		GridWorld.Direction n= GridWorld.Direction.NORTH;
		r.marked[u]=DfsInfo.color.GREY;
		int z=u+1;
		r.tick=r.tick+1;
		r.d[u]=r.tick;
		Casella v=new Casella(gw.getCurrentCell().col,gw.getCurrentCell().row,r.tick);
		for(GridWorld.Coordinate i: gw.getAdjacentFreeCells()){
			v=new Casella(r.tick, i.col,i.row);
			G.V.get(u).add(v);
		}
		for(GridWorld.Coordinate i: gw.getAdjacentFreeCells()){
			if(!(r.marked[z]==DfsInfo.color.BLACK)){
				
				if(i.col> here.col){
					//System.err.print("x");
					gw.moveToAdjacentCell(e);
					if(r.marked[z]==DfsInfo.color.WHITE && !gw.targetReached()){
						r.pig[z]=u;
						System.err.print(gw.getCurrentCell());
						dfsVisit2(gw, gw.getCurrentCell(),G,z,r);
					}
					
				}
				if(i.row> here.row){
					gw.moveToAdjacentCell(s);
					if(r.marked[z]==DfsInfo.color.WHITE && !gw.targetReached()){
						r.pig[z]=u;
						System.err.print(gw.getCurrentCell());
						dfsVisit2(gw, gw.getCurrentCell(),G,z,r);
					}
					
				}
				if((i.col<here.col)){
					gw.moveToAdjacentCell(w);
					if(r.marked[z]==DfsInfo.color.WHITE && !gw.targetReached()){
						r.pig[z]=u;
						System.err.print(gw.getCurrentCell());
						dfsVisit2(gw, gw.getCurrentCell(),G,z,r);
					}
					
				}
				 if((i.row<here.row)){
					if(r.marked[z]==DfsInfo.color.WHITE && !gw.targetReached()){
						gw.moveToAdjacentCell(n);
						r.pig[z]=u;
						System.err.print(gw.getCurrentCell());
						dfsVisit2(gw, gw.getCurrentCell(),G,z,r);
					}
					
					
				}
				if(gw.targetReached()){
						System.out.println("DIO");
						r.addP(gw.getCurrentCell());
					}
				}
				
				
			
			r.marked[z]=DfsInfo.color.BLACK;
			r.tick=r.tick+1;
			r.f[z]=r.tick;
			
		}
		r.printing();
	}

	public static void main(String[] args) {
	// Costruttore Gridworld
		GridWorld gw = new GridWorld(10,0.1,1500);
		System.out.println("everything seems ok.");
		gw.print();
		Grafo<Casella> G=new Grafo<Casella>();
		int i=0;
		G=G.graphInit(100);
		System.out.print("Percorso: ");
		//dfs(gw,G,marked);
		Casella p=new Casella(gw.getCurrentCell().col,gw.getCurrentCell().row, i);
		System.out.println(gw.getCurrentCell());
		G.V.get(i).add(p);
		DfsInfo info= new DfsInfo(100);
		dfs2(gw,G,info,i);
		
	}
}
