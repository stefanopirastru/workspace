package DividiEtImpera;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSortRnd {
	public void quickSortRnd(int[] a, int lo, int hi){
		int s=0;
		if(lo<hi){
			s=partitionRnd(a,lo,hi);
			quickSortRnd(a,lo,s-1);
			quickSortRnd(a,s,hi);
		}
	}
	public int partitionRnd(int[] a, int lo, int hi){
		int i=ThreadLocalRandom.current().nextInt(lo, hi + 1);
		swap(a,i,hi);
		return partitionRnd(a,lo,hi);
	}
	
	public void swap(int[] a, int lo, int hi){
		if(a[lo]>a[hi]){
			int temp=a[lo];
			a[lo]=a[hi];
			a[hi]=temp;
		}
	}
}
