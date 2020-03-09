import javax.swing.JFrame;

public class Main {

	public Main() {
		
		JFrame frame = new JFrame();
		GameBoard gameBoard = new GameBoard();
		
		frame.add(gameBoard);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Smoko");
		frame.setLocationRelativeTo(null);
		
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Main();
	}

}
