import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DSFrame extends Frame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**List of object display in output area*/
	private DefaultListModel<Show> listModela = new DefaultListModel<Show>();
	/**JList that display data*/
	public JList<Show> objectLista = new JList<Show>(listModela);
	/**Make Jlist can be scrolled*/
	private JScrollPane outputScrolla = new JScrollPane(objectLista);
	
	/**List of object display in output area*/
	private DefaultListModel<Show> listModelb = new DefaultListModel<Show>();
	/**JList that display data*/
	public JList<Show> objectListb = new JList<Show>(listModelb);
	/**Make Jlist can be scrolled*/
	private JScrollPane outputScrollb = new JScrollPane(objectListb);
	
	
	public JPanel ab = new JPanel();
	public JPanel abc = new JPanel();
	
	JLabel a = new JLabel("First maker");
	JLabel b = new JLabel("Second maker");
	JLabel c = new JLabel("Third maker");
	
	
	
	public DSFrame(){
		GridBagConstraints layoutConst;
		ab.setLayout(new GridBagLayout());
		
		
		
		layoutConst = new GridBagConstraints();
		
		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.insets = new Insets(10, 10, 0, 0);
		add(a, layoutConst);
		
		layoutConst.gridx = 0;
		layoutConst.gridy = 1;
		layoutConst.insets = new Insets(0, 10, 10, 0);
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		add(b, layoutConst);
		
		
		layoutConst.gridx = 1;
		layoutConst.gridy = 0;
		layoutConst.fill = GridBagConstraints.BOTH;
		layoutConst.insets = new Insets(10, 10, 0, 0);
		layoutConst.gridheight = 2;
		add(objectLista, layoutConst);
		
		setResizable(false);
		setVisible(true);
		pack();
		
	}

}
