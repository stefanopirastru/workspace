
public class Lista <T> {
	Nodo<T> tail;
	Nodo<T> head;
	public Lista<T> listInit(){
		Lista<T> l= new Lista<T>();
		head=null;
		tail=null;
		return l;
	}
	public boolean isEmpty(Lista <T> l){
		return(l.head==null);
	}
	public T head(){
		return head.key;
	}
	public T tail(){
		return tail.key;
	}
	public void insertFront(Lista<T> l, T v){
		Nodo<T> n= new Nodo<T>();
		n.key=v;
		n.next=l.head;
		n.pre=null;
		if(l.isEmpty(l)){
			l.tail=l.head=n;
		}else{
			l.head.next=n;
			l.head=n;
		}
	}
	public void insertBack(Lista<T> l, T v){
		Nodo<T> n= new Nodo<T>();
		n.key=v;
		n.pre=l.tail;
		n.next=null;
		if(l.isEmpty(l)){
			l.tail=l.head=n;
		}else{
			l.tail.pre=n;
			l.tail=n;
		}
	}
	public boolean search(Lista<T> l, T x){
		Nodo<T> n=new Nodo<T>();
		n=l.listSearch(l, x);
		return (n!= null);
	}
	public Nodo listSearch(Lista<T> l, T x){
		Nodo<T> n= new Nodo<T>();
		n=l.head;
		while((n!=null)&&(n.key!=x)){
			n=n.next;
		}
		return n;
	}
	public T deleteFront(Lista<T> l){
		if(l==null){
			System.out.println("Non puoi cancellare una lista vuota");
			return null;
		}else{
			T x=l.head.key;
			l.listDelete(l,l.head);
			return x;
		}
	}
	public T deleteBack(Lista<T> l){
		if(l==null){
			System.out.println("Non puoi cancellare una lista vuota");
			return null;
		}else{
			T x=l.tail.key;
			l.listDelete(l,l.tail);
			return x;
		}
	}
	public void listDelete(Lista<T> l, Nodo<T> n){
		if(n.pre!=null){
			n.pre.next=n.next;
		}else{
			l.head=n.next;
		}
		if(n.next!=null){
			n.next.pre=n.pre;
		}else{
			l.tail=n.pre;
		}
	}
	public void delete(Lista<T> l, T x){
		Nodo<T> n= new Nodo<T>();
		n=l.listSearch(l, x);
		if(n!=null){
			l.listDelete(l, n);
		}
	}
}
