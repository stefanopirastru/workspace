
public class Vector {
	public int[] table;
	public int size;
	public int capacity;
	public void insertNaive(Vector a, int k){
		if(a.size== 0){
			a.table = new int[1];
			a.capacity=1;
		}
		if(a.size== a.capacity){
			int[] t=new int[a.size+1];
			for(int i=0;i<a.size;i++){
				t[i]=a.table[i];
			}
			a.table= t;
			a.capacity= t.length;
		}
		a.table[a.size]=k;
		a.size= a.size +1;
	}
	public void insertSmart(Vector a, int k){
		if(a.size== 0){
			a.table= new int[1];
			a.capacity= 2;
		}
		if(a.size== a.capacity){
			int[] t= new int[a.size*2];
			for(int i=0;i<a.size;i++){
				
				t[i]=a.table[i];
			}
			a.table= t;
			a.capacity= t.length;
		}
		a.table[a.size]=k;
		a.size= a.size +1;
	}
}

