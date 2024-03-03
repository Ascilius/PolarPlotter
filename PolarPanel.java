import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

public class PolarPanel extends JPanel {

	// screen stuff
	private double screenWidth, screenHeight, scale, theta, step;
	private ArrayList<BetterPoint> graph = new ArrayList<BetterPoint>();
	private boolean continueGraph = true;
	private InputHandler inputHandler;
	
	private Color color = Color.RED; // graph color

	public PolarPanel(double screenWidth, double screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		scale = screenHeight / 2;
		step = Math.PI / 256;
		this.inputHandler = new InputHandler();
		addKeyListener(this.inputHandler);
		setFocusable(true);
	}

	public void paintComponent(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, (int) screenWidth, (int) screenHeight);
		
		// text
		g.setColor(Color.BLACK);
		if (continueGraph == true) {
			g.drawString("Drawing...Please Wait", 10, 20);
		} else {
			g.drawString("Polar Graphs by Jason Kim", 10, 20);
		}
		
		// controls
		g.drawString("Shift - Zoom In", 10, 40);
		g.drawString("Control - Zoom Out", 10, 60);
		g.drawString("H - Increase Precision", 10, 80);
		g.drawString("N - Decrease Precision", 10, 100);

		g.translate(screenWidth / 2, screenHeight / 2);
		
		// graph
		g.setColor(color);
		g.setStroke(new BasicStroke(1));
		if (continueGraph == true) {
			graph.add(new BetterPoint(Math.cos(theta) * function(theta) * scale, Math.sin(theta) * function(theta) * scale * -1));
			if (theta >= 2 * Math.PI && graph.size() > 1 && new Point((int) (graph.get(graph.size() - 1).getX()), (int) (graph.get(graph.size() - 1).getY())).equals(new Point((int) (graph.get(0).getX()), (int) (graph.get(0).getY()))) == true) {
				continueGraph = false;
			}
		}
		for (int i = 0; i < graph.size() - 1; i++) {
			g.drawLine((int) (graph.get(i).getX()), (int) (graph.get(i).getY()), (int) (graph.get(i + 1).getX()), (int) (graph.get(i + 1).getY()));
		}

		theta += step;

		repaint();
	}

	public double function(double theta) { // the equation that is drawn
		double a = 1.0;
		double b = 1.0;
		double c = 1.0;
		// r =
		return a + b * Math.pow(theta, 1 / c);
	}

	// Butterfly Curve
	// Math.pow(Math.E, Math.sin(theta)) - 2 * Math.cos(4 * theta) + Math.pow(Math.sin((2 * theta - Math.PI) / 24), 5)

	// Cannabis Curve
	// (1 + 0.9 * Math.cos(8 * theta)) * (1 + 0.1 * Math.cos(24 * theta)) * (0.9 + 0.5 * Math.cos(200 * theta)) * (1 + Math.sin(theta))

	public void increasePrecision() {
		step /= 2;
		theta = 0;
		graph.clear();
		continueGraph = true;
	}

	public void decreasePrecision() {
		step *= 2;
		theta = 0;
		graph.clear();
		continueGraph = true;
	}

	public void zoomIn() {
		scale *= 2;
		for (int i = 0; i < graph.size(); i++) {
			graph.add(i, new BetterPoint(graph.get(i).getX() * 2, graph.remove(i).getY() * 2));
		}
	}

	public void zoomOut() {
		scale /= 2;
		for (int i = 0; i < graph.size(); i++) {
			graph.add(i, new BetterPoint(graph.get(i).getX() / 2, graph.remove(i).getY() / 2));
		}
	}

	class InputHandler extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			if (arg0.getKeyCode() == KeyEvent.VK_H) {
				increasePrecision();
			} else if (arg0.getKeyCode() == KeyEvent.VK_N) {
				decreasePrecision();
			} else if (arg0.getKeyCode() == KeyEvent.VK_SHIFT) {
				zoomIn();
			} else if (arg0.getKeyCode() == KeyEvent.VK_CONTROL) {
				zoomOut();
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}
	}
}
