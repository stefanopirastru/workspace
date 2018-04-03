package struttureDati;

public class stack {
	public int[] table;
	public int top;
	public stack stackInit(int n){
		stack s = new stack();
		s.table= new int[n];
		s.top=-1;
		return s;
	}
	
	public boolean isEmpty(stack a){
		return a.top < 0;
	}
	public boolean isFull(stack a){
		return a.top == a.table.length-1;
	}
	public void push(stack a, int i){
		if(isFull(a)){
			System.out.println("Sorry, buffer overflow");
		}else{
			a.top += 1;
			a.table[a.top]=i;
		}
	}
	public int top(stack s){
		if(isEmpty(s)){
			System.out.println("Grab that cash with both hand and make a stack");
			return 0;
		}else{
			return s.table[s.top];
		}
	}
	public int pop(stack s){
		if(isEmpty(s)){
			System.out.println("Consider that you have to put in something");
			return 0;
		}else{
			int x = s.table[s.top];
			s.top -= 1;
			return x;
		}
	}
	public int size(stack s){
		return s.top + 1;
	}
	
}
