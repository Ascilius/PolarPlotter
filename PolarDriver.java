import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class PolarDriver {

	private static PolarPanel panel;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Network Generator");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		panel = new PolarPanel(screenSize.getWidth(), screenSize.getHeight());
		frame.add(panel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
