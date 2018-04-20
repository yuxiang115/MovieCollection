import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;

/**
 * Project #5
 * CS 2334, Section 010
 * April 22, 2016
 * <P>
 * Class for creating show object
 * </P>
 */
class Slice {
	public int value;
	public Color color;
	public String key;

	public Slice(int value, Color color, String key) {
		this.value = value;
		this.color = color;
		this.key = key;
	}
}

/**
 * Project #3 CS 2334, Section 010 Mar 28, 2016
 * <P>
 * The PieChart class creates a pie chart based on the slices.
 * </P>
 * 
 * @author Adrian Phillips, Michael Nguyen, and Jacob Huckabaa
 * @version 1.0
 */
public class PieChart extends JComponent {

	/** Serial ID */
	private static final long serialVersionUID = 4721785685844350799L;

	public Slice[] slices = new Slice[0];

	/**
	 * 
	 * @param newSlices slice array of credits
	 */
	public void setSlices(Slice[] newSlices) {
		slices = newSlices;
	}

	/**
	 * Constructor for PieChart
	 */
	PieChart() {
	}

	public void paint(Graphics g) {
		drawPie((Graphics2D) g, new Rectangle(300, 300), slices);
	}

	/**
	 * 
	 * @param g
	 * @param area
	 * @param slices
	 */
	private void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
		double total = 0.0D;
		for (int i = 0; i < slices.length; i++) {
			total += slices[i].value;
		}
		double curValue = 0.0D;
		int startAngle = 0;
		int index = 0;
		for (int i = 0; i < slices.length; i++) {
			startAngle = (int) (curValue * 360 / total);
			int arcAngle = (int) (slices[i].value * 360 / total);
			g.setColor(slices[i].color);
			if (slices[i].value > 0) {
				Rectangle seriesBox = new Rectangle(320, 50 + index * 20, 10, 10);
				g.draw(seriesBox);
				g.fill(seriesBox);
				g.drawString(slices[i].value + " " + slices[i].key, 350, 60 + index * 20);
				++index;
			}
			g.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
			curValue += slices[i].value;
		}
	}
}
