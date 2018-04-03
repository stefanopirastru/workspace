package struttureDati;

public class Queue  {
	public int[] table;
	public int head;
	public int tail;
	public Queue QueueInit(int n){
		Queue q = new Queue();
		q.table = new int[n];
		q.head = q.tail = 0;	
		return q;
	}
	public boolean Empty(Queue q){
		return (q.head==q.tail);
	}
	public int Head(Queue q){
		return q.table[q.head];
	}
	public int Tail(Queue q){
		return q.table[q.tail];
	}
	public void enQueue(int x, Queue q){
		if(((q.tail+1)%q.table.length)!=q.head){
			q.table[q.tail] = x;
			q.tail=(q.tail+1)%q.table.length;
		}
		else {
			System.out.println("Error");
		}
	}
	public int deQueue(Queue q){
		if(Empty(q)){
			System.err.println("Non Funziona");
			return 0;
		}else{
			int x=q.table[q.head];
			q.head=(q.head%q.table.length);
			return x;
		}
	}
}
