package DividiEtImpera;

public class MergeSort {
	public void mergeSort(int[] A){
		int n=A.length;
		if(n>1){
			double temp = n/2;
			int half =(int)temp;
			int mc = n-half;
			int[] B = new int[half];
			int[] C = new int[mc];
			copy(A,B,0,half-1);
			copy(A,C,half,n-1);
			mergeSort(B);
			mergeSort(C);
			merge(A,B,C);			
		}
	}
	public void copy(int[] A,int[] B,int lo, int hi){
		int j=0;
		for(int i=lo;i<hi;i++){
			B[j]=A[i];
			j++;
		}
	}
	public void merge(int[] a, int[] b, int[] c){
		int mb= b.length;
		int mc= c.length;
		int i= 0;
		int j= 0;
		int k= 0;
		while((i<mb)&&(j<mc)){
			if(b[i]<=c[j]){
				a[k]=b[i];
				i++;
			}else{
				a[k]=c[j];
				j++;
			}
			if(i==mb){
				while(j<mc){
					a[k]=c[j];
					j++;
					k++;
				}
			}else{
				while(i<mb){
					a[k]=b[i];
					i++;
					k++;
				}
			}
		}
	}
}
