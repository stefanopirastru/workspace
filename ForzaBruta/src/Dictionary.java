
public class Dictionary extends Vector {
	public int[] table;
	public int size;
	public int capacity;
	public int sequentialSearch(int[] a, int size, int k){
		int i=0;
		int n=size;
		while((i<n)&&(a[i]!=k)){
			i=i+1;
		}
		return (i<n)?i:-1;
	}
	public void delete(Dictionary a, int x){
		int i= a.sequentialSearch(a.table, a.size, x);
		if(i!=-1){
			a.size=a.size-1;
			a.swap(a.table[i],a.table[size]);
		}
	}
	public void swap(int a, int b){
		int temp=0;
		temp=a;
		a=b;
		b=temp;
	}
	public int min(Dictionary a){
		int minldx= 0;
		for(int i=1; i<a.size; i++){
			if(a.table[i]<a.table[minldx]){
				minldx=i;
			}
		}
		return minldx;
	}
	public int max(Dictionary a){
		int maxldx= 0;
		for(int i=1;i<a.size;i++){
			if(a.table[i]>a.table[maxldx]){
				maxldx=i;
			}
		}
		return maxldx;
	}
	public int floor(Dictionary a, int k){
		int prev= Integer.MIN_VALUE;
		int prevldx= -1;
		for(int i=0;i<a.size;i++){
			if((a.table[i]<k)&&(a.table[i]<prev)){
				prev=a.table[i];
				prevldx=i;
			}
		}
		return prevldx;
	}
	public int ceil(Dictionary a, int k){
		int next=Integer.MAX_VALUE;
		int nextldx=-1;
		for(int i=0;i<a.size;i++){
			if((a.table[i]>k)&&(a.table[i]<next)){
				next=a.table[i];
				nextldx=i;
			}
		}
		return nextldx;
	}
	public int rank(Dictionary a, int k){
		int rank=0;
		for(int i=0;i<a.size;i++){
			if(a.table[i]<k){
				rank=rank+1;
			}
		}
		return rank;
	}
	public int range(Dictionary a, int lo, int hi){
		int r=0;
		for(int i=0; i<a.size;i++){
			if((a.table[i]>lo)&&(a.table[i]<hi)){
				r=r+1;
			}
		}
		return r;
	}
}
