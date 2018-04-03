
public class Lista<T>  {
	Nodo <T> head;
	Nodo <T> tail;
	public Lista<T> listInit(){
		Lista<T> lambda= new Lista<T>();
		head = null;
		tail = null;
		return lambda;
	}
	public boolean empty(Lista<T> lambda){
		return (lambda.head==null);
	}
	public T  head(){
		return head.key;
	}
	public T tail(){
		return tail.key;
	}
	public void insertFront(Lista<T> lambda, T item){
		Nodo <T> n= new Nodo<T>();
		n.key =item;
		n.next=lambda.head;
		n.pre= null;
		if(lambda.empty(lambda)){
			lambda.tail=lambda.head=n;
		}
		else
			lambda.head.next=n;
			lambda.head=n;
	}
	public void insertBack(Lista<T> lambda, T item){
		Nodo<T> n= new Nodo<T>();
		n.key= item;
		n.next=null;
		n.pre=lambda.tail;
		if(lambda.empty(lambda)){
			lambda.tail=lambda.head=n;
		}
		else
			lambda.tail.next=n;
			lambda.tail=n;
		}	
	public Boolean search(Lista <T> lambda,T x){
		Nodo <T> n= new Nodo<T>();
		n=lambda.listSearch(lambda, x);
		return (n!=null);
	}
	public T deleteFront(Lista <T> lambda){
		if(lambda==null){
			System.out.println("non puoi cancellare una lista vuota");
			return null;
		}
		else{
		T x= lambda.head.key;
		lambda.listDelete(lambda,lambda.head);
		return x;
		}
	}
	public T deleteBack(Lista<T> lambda){
		if(lambda!=null){
			System.out.println("non puoi cancellare una lista vuota");
			return null;
		}
		else{
			T x= lambda.tail.key;
			lambda.listDelete(lambda,lambda.tail);
			return x;
		}
	}
	public void delete(Lista <T> lambda,T x){
		Nodo n= new Nodo<T>();
		n=lambda.listSearch(lambda,x);
		if(n!=null){
			lambda.listDelete(lambda,n);
		}
	}
	public Nodo listSearch(Lista <T> lambda,T x){
		Nodo n= new Nodo();
		n=lambda.head;
		while((n!=null)&&(n.key!=x)){
			n=n.next;
		}
		return n;
	}
	public void listDelete(Lista <T> lambda,Nodo<T> n){
		if(n.pre!=null){
			n.pre.next=n.next;
		}
		else{
			lambda.head=n.next;
		}
		if(n.next!=null){
			n.next.pre=n.pre;
		}
		else{
			lambda.tail=n.pre;
		}
	}
}
