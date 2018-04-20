import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * NOT Finished due to dont know how to draw Histogram :(
 * @author yuxia_000
 *
 */
public class Histogram {
	public Histogram(){
		Image image = null;
		try {
		    URL url = new URL(
		    		"http://www.jfree.org/jfreechart/api/javadoc/images/BarRendererSample.png");
		    image = ImageIO.read(url);
		} catch (IOException e) {
		}

		// Use a label to display the image
		JFrame frame = new JFrame();

		JLabel lblimage = new JLabel(new ImageIcon(image));
		frame.getContentPane().add(lblimage, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
	}
}
