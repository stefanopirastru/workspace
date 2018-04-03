import javax.swing.JOptionPane;

public class ComputeSumGui {

	public static void main(String[] args) {
		String xAsText = 
				JOptionPane.showInputDialog("Primo numero?");
		String yAsText = 
				JOptionPane.showInputDialog("Secondo numero?");
				
		int x = Integer.parseInt(xAsText);
		int y = Integer.parseInt(yAsText);
		int z = x + y;		
		
		JOptionPane.showMessageDialog(null,  "Somma: " + z);
	}

}
