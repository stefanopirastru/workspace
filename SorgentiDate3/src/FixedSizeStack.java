
public class FixedSizeStack <Item> {
	
	private Item[] table;
	private int    top;
	
	public FixedSizeStack(int n) {
		table = (Item[]) new Object[n];
		top = -1;
	}
	
	public Item top() {
		assert(!isEmpty());
		return table[top];
	}
	
	public void push(Item x) {
		assert(top < table.length);
		table[top] = x;
		top += 1;
	}
	
	public Item pop() {
		assert(!isEmpty());
		Item x = table[top];
		top -= 1;
		return x;
	}
	
	public boolean isEmpty() {
		return (top < 0);
	}

}
