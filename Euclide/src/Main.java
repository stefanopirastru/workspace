
public class Main {

	public static int mcd(int m,int n){
		if(m < 0 ||  n <0){
			System.out.println("Error, number must be positive");
			return 0;
		}
		
		else {
			if(( m == 0  )&&( n==0)){
				System.out.println("Error, m must be different from zero");
			}
			else{
				while(n!=0){
					int r = m%n;
					m=n;
					n=r;
					return m;
				}
			}
		}
		return n;
		
	}
	
	public static boolean allEven(int a[]){
		int i=0;
		for(i=0;i<(a.length -1);i++){
			if (a[i]%2 != 0){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String args[]){
		int a=3;
		int b=9;
		int c = mcd(a,b);
		System.out.println(c);
		
		int[] Eve = {2,4,8,16,32,64,128,256,516};
		int[] Odd = {1,1,2,3,5,8,13,21,34};
		System.out.println(allEven(Odd));
		System.out.println(allEven(Eve));
	}
}
