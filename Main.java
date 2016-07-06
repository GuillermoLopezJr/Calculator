import javax.swing.JFrame;

public class Main {
	
	private static final int WIN_WIDTH = 500;
	private static final int WIN_HEIGHT = 700;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIN_WIDTH, WIN_HEIGHT);
		frame.add(new Calculator());
		frame.setVisible(true);
	}
}
