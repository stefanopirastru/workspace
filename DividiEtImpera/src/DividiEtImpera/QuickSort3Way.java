package DividiEtImpera;

public class QuickSort3Way {
	public void quickSort3Way(int [] a, int lo, int hi){
		int gt=0;
		int lt=0;
		int i=0;
		int v;
		if(hi>=lo){
			lt=lo;
			gt=hi;
			i=lo+1;
			v=a[lo];
			while(i<=gt){
				if(a[i]<v){
					swap(a,lt,i);
					lt++;
					i++;
				}else if(a[i]>v){
					swap(a,i,gt);
					gt--;
					
				}else i++;
			}
			quickSort3Way(a,lo,lt-1);
			quickSort3Way(a,gt+1,hi);
		}
	}
	public void swap(int[] a, int lo, int hi){
		if(a[lo]>a[hi]){
			int temp=a[lo];
			a[lo]=a[hi];
			a[hi]=temp;
		}
	}
}
