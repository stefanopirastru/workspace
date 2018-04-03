package struttureDati;

public class Set {
	public boolean[] table;
	public int size;
	public Set setInit(int n){
		Set s= new Set();
		s.size=0;
		s.table= new boolean[n];
		int i;
		for(i=0;i<n;i++){
			s.table[i]=false;
		}
		return s;
	}
	public void add(Set s, int e){
		if(e>s.table.length-1){
			System.err.println("Questo indice supera lagrandezza di table");
		}else{
			s.table[e]=true;
			s.size+=1;
		}
	}
	public void remove(Set s, int e){
		if(e>s.table.length-1){
			System.err.println("Questo indice supera lagrandezza di table");
		}else{
			s.table[e]=false;
			s.size-=1;
		}
	}
	public int size(Set a){
		return a.size;
	}
	public boolean isIn(Set a, int e){
		if(e>a.table.length-1){
			System.err.println("Questo indice supera lagrandezza di table");
			return false;
		}
		else{
			return a.table[e];
		}
	}
	public Set union(Set a, Set b){
		//assertEquals(a.table.length!=b.table.length){
			//System.err.println("I due vettori sono di lunghezza differente");
			//return null;
		//}else{
			try{
			int n=a.table.length;
			Set c= new Set();
			c.setInit(n);
			for(int i=0;i<n;i++){
				c.table[i]= a.table[i]||b.table[i];
			}
			return c;
			}catch(Exception e){
				System.err.println("I due vettori sono di lunghezza differente");
				return null;
			}
		}
	public Set intersect(Set a, Set b){
			/*if(a.table.length!=b.table.length){
				
				
			}*/
			try{
				int n=a.table.length;
				Set c= new Set();
				c.setInit(n);
				for(int i=0;i<n;i++){
					c.table[i]= a.table[i]&&b.table[i];
				}
				return c;
			}catch(Exception e){
				System.err.println("I due vettori sono di lunghezza differente");
				return null;
			}
		
	}
	public Set diff(Set a, Set b){
		/*if(a.table.length!=b.table.length){
			
			
		}*/
		try{
			int n=a.table.length;
			Set c= new Set();
			c.setInit(n);
			for(int i=0;i<n;i++){
				c.table[i]= a.table[i]&&!b.table[i];
			}
			return c;
		}catch(Exception e){
			System.err.println("I due vettori sono di lunghezza differente");
			return null;
		}
	
	}
	public Set complement(Set a){
		/*if(a.table.length!=b.table.length){
			
			
		}*/
		try{
			int n=a.table.length;
			Set c= new Set();
			c.setInit(n);
			for(int i=0;i<n;i++){
				c.table[i]= !a.table[i];
			}
			return c;
		}catch(Exception e){
			System.err.println("I due vettori sono di lunghezza differente");
			return null;
		}
	
}

}
