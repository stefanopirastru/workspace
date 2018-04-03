package struttureDati;

public class circularList {
	public Node head;
	public Node tail;
	public Node nil;
	public circularList listInit(){
		circularList l= new circularList();
		
		l.head.prev= l.tail.next= l.nil= new Node();
		l.head=l.tail=null;
		return l;
	}
	public Node listSearch(circularList l, int x){
		Node n=l.nil.next;
		while((n!=l.nil)&&(n.key==x)){
			n=n.next;
		}
		return n;
	}
	public void delete(circularList l,Node n){
		n.prev.next=n.next;
		n.next.prev=n.prev;
	}
	public void insertFront(circularList l, int x){
		Node n= new Node();
		n.key=x;
		n.next=l.nil.next;
		l.nil.next.prev= n;
		l.nil.next= n;
		n.prev= l.nil;
		
	}
	
}

