import java.awt.geom.Point2D;

public class BetterPoint extends Point2D {

	double x, y;

	public BetterPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public void setLocation(double x, double y) {
		// TODO Auto-generated method stub
		this.x = x;
		this.y = y;
	}

}
