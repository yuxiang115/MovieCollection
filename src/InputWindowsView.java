
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * Project #5
 * CS 2334, Section 010
 * April 22, 2016
 * <P>
 * Class for Show data for user and get command from users
 * </P>
 */
public class InputWindowsView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String selectCommand;
	//selection
	/**Radio Button that represent Media type isSelected*/
	public JRadioButton mediaButton = new JRadioButton("Media");
	/**Radio Button that represent Movie type isSelected*/
	public JRadioButton movieButton = new JRadioButton("Movie");
	/**Radio Button that represent Series type isSelected*/
	public JRadioButton seriesButton = new JRadioButton("Series");
	/**Radio Button that represent Episode type isSelected*/
	public JRadioButton episodeButton = new JRadioButton("Episode");
	/**Radio Button that represent Maker type isSelected*/
	public JRadioButton makersButton = new JRadioButton("Makers");
	/**Radio Button that represent Actor type isSelected*/
	public JRadioButton actorsButton = new JRadioButton("Actors");
	/**Radio Button that represent Directors type isSelected*/
	public JRadioButton directorsButton = new JRadioButton("Directors");
	/**Radio Button that represent Producers type isSelected*/
	public JRadioButton producersButton = new JRadioButton("Producers");
	/**List of Radio buttons*/
	public ArrayList<JRadioButton> buttonList = new ArrayList<JRadioButton>();
	/**Maker list Radio button single choice*/
	public ButtonGroup buttons = new ButtonGroup();

	//file chooser
	/**File Chooser that can get the file to open or save*/
	public JFileChooser filesChooser = new JFileChooser();

	//ouputArea
	/**Label for ouput area title, it is dynamic when different radio button selected*/
	public JLabel outputLabel = new JLabel("Output Title");
	/**List of object display in output area*/
	private DefaultListModel<Show> listModel = new DefaultListModel<Show>();
	/**JList that display data*/
	public JList<Show> objectList = new JList<Show>(listModel);
	/**Make Jlist can be scrolled*/
	private JScrollPane outputScroll = new JScrollPane(objectList);

	//File Menu
	/**File Menu*/
	public JMenu fileMenu = new JMenu("File");
	/**Button in File Menu which load binary files*/
	public JMenuItem load = new JMenuItem("Load");
	/**Button in File Menu which save binary files*/
	public JMenuItem save = new JMenuItem("Save");
	/**Button in File Menu which import text files*/
	public JMenuItem importItem = new JMenuItem("Import");
	/**Button in File Menu which export text files*/
	public JMenuItem export = new JMenuItem("Export");

	//Edit Menu
	/**edit Menu*/
	public JMenu editMenu = new JMenu("Edit");
	/**Button in Edit Menu which add data into system*/
	public JMenuItem add = new JMenuItem("Add");
	/**Button in Edit Menu which edit data from system*/
	public JMenuItem edit = new JMenuItem("Edit");
	/**Button in Edit Menu which delete data from system*/
	public JMenuItem delete = new JMenuItem("Delete");
	/**Button in Edit Menu which clean all data which object type is selected*/
	public JMenuItem clear = new JMenuItem("Clear");
	/**Button in Edit Menu which remove all data from system*/
	public JMenuItem clearAll = new JMenuItem("Clear All");

	//Display Menu
	/**Button in Display Menu which show graph*/
	public JMenu displayMenu = new JMenu("Display");
	/**Button in Display Menu which show pie chart of makers*/
	public JMenuItem pieChart = new JMenuItem("Pie Chart");
	/**Button in Display Menu which show Histogram for makers*/
	public JMenuItem histogram = new JMenuItem("Histogram");
	/**Just a menuBar*/
	public JMenuBar menuBar = new JMenuBar();
	/**Button in Display Menu which show Degrees of Separation frame*/
	public JMenuItem displayDS = new JMenuItem("Separation List");							//====

	/**List of movies from model*/
	private ArrayList<Show> movies = new ArrayList<Show>();
	/**List of series from model*/
	private ArrayList<Show> series = new ArrayList<Show>();
	/**List of episodes from model*/
	private ArrayList<Show> episodes = new ArrayList<Show>();
	/**List of actors from model*/
	private ArrayList<Creator> actors = new ArrayList<Creator>();
	/**List of directors from model*/
	private ArrayList<Creator> directors = new ArrayList<Creator>();
	/**List of producers from model*/
	private ArrayList<Creator> producers = new ArrayList<Creator>();

	/**model that store data and manage data*/
	private MediaModel model;
	/**String that show what types of object users load from binary files */
	public String objectLoaded = "";
	/**
	 * Constructor for creator main frame
	 */
	public InputWindowsView(){

		setTitle("MDb");
		setLayout(new BorderLayout());
		addSeletActionListener(new SelectListener());


		load.setToolTipText("Read files which in using object I/O. ");
		fileMenu.add(load);

		save.setToolTipText("Output data files using object I/O.");
		fileMenu.add(save);

		importItem.setToolTipText("Read data from text files.(It will be active after select radio button)");
		fileMenu.add(importItem);

		export.setToolTipText("Export data as text files.");
		fileMenu.add(export);
		menuBar.add(fileMenu);

		add.setToolTipText("It will active when you select which types of data you want to add.");
		editMenu.add(add);

		edit.setToolTipText("Edit the selected data in the right output area\n, it will be inative onece specified data seleted");

		delete.setToolTipText("Delet the exist related object and data from system, "
				+ "it will be inative onece data loaded, imported or added.");

		clear.setToolTipText("Clear the exist related objects from system,"
				+ " it will be inative onece data loaded, imported or added.");

		clearAll.setToolTipText("Clear all the exist objects. "
				+ "it will be inative onece data loaded, imported or added.");


		editMenu.add(edit);
		editMenu.add(delete);
		editMenu.add(clear);
		editMenu.add(clearAll);
		menuBar.add(editMenu);

		pieChart.addActionListener(new PieChartListener());
		menuBar.add(displayMenu);
		displayMenu.add(pieChart);
		displayMenu.add(histogram);
		histogram.addActionListener(new HistogramListener());
		displayMenu.add(displayDS);
		displayDS.addActionListener(new DSDisplayListener());
		displayDS.setToolTipText("It will be active when you select tow makers from maker's left panel.");
		add(menuBar, BorderLayout.PAGE_START);

		JPanel jplButtons = new JPanel(new GridLayout(9, 0, 5, 30));
		JLabel seletLabel = new JLabel("    Seletion");
		jplButtons.add(seletLabel);

		mediaButton.setToolTipText("Cannot be import or add");
		makersButton.setToolTipText("Cannot be import or add");

		mediaButton.setActionCommand("Media");
		movieButton.setActionCommand("Movie");
		seriesButton.setActionCommand("Series");
		episodeButton.setActionCommand("Episode");
		makersButton.setActionCommand("Maker");
		actorsButton.setActionCommand("Actor");
		directorsButton.setActionCommand("Director");
		producersButton.setActionCommand("Producer");

		buttonList.add(mediaButton);
		buttonList.add(movieButton);
		buttonList.add(seriesButton);
		buttonList.add(episodeButton);
		buttonList.add(makersButton);
		buttonList.add(actorsButton);
		buttonList.add(directorsButton);
		buttonList.add(producersButton);

		ButtonGroup buttons = new ButtonGroup();
		buttons.add(mediaButton);
		buttons.add(movieButton);
		buttons.add(seriesButton);
		buttons.add(episodeButton);
		buttons.add(makersButton);
		buttons.add(actorsButton);
		buttons.add(directorsButton);
		buttons.add(producersButton);


		jplButtons.add(mediaButton);
		jplButtons.add(movieButton);
		jplButtons.add(seriesButton);
		jplButtons.add(episodeButton);
		jplButtons.add(makersButton);
		jplButtons.add(actorsButton);
		jplButtons.add(directorsButton);
		jplButtons.add(producersButton);

		mediaButton.setSelected(true);
		JScrollPane buttonScroll = new JScrollPane(jplButtons);
		add(buttonScroll, BorderLayout.LINE_START);

		JPanel jplOutput = new JPanel(new GridBagLayout());
		GridBagConstraints layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		layoutConst.anchor = GridBagConstraints.CENTER;

		jplOutput.add(outputLabel, layoutConst);

		objectList.setFixedCellWidth(600);
		objectList.addListSelectionListener(new JListSelectListener());
		objectList.setToolTipText("Keep pressing CTRL can select multiple items in output area.");
		objectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 1;
		layoutConst.fill = GridBagConstraints.BOTH;
		layoutConst.weightx = 1.0;
		layoutConst.weighty = 1.0;
		layoutConst.anchor = GridBagConstraints.CENTER;
		jplOutput.add(outputScroll, layoutConst);
		add(jplOutput, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		edit.setEnabled(false);
		add.setEnabled(false);
		save.setEnabled(false);
		export.setEnabled(false);
		clear.setEnabled(false);
		clearAll.setEnabled(false);
		delete.setEnabled(false);
		pieChart.setEnabled(false);
		histogram.setEnabled(false);
		displayDS.setEnabled(false);
		filesChooser.setFileFilter((new FileNameExtensionFilter("Text File(.txt)", "text", "txt")));
		setVisible(true);
		pack();
	}
	/**
	 * Set model method
	 * @param model the model that store data and manage data
	 */
	public void setModel(MediaModel model){
		this.model = model;
		if(model != null){
			model.addActionListener(new ModelUpdateListener());
		}
	}
	/**
	 * JList Selection Listener
	 * @author yuxia_000
	 *
	 */
	private class JListSelectListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			int[] i = objectList.getSelectedIndices();
			if(i.length == 2 && makersButton.isSelected()){
				displayDS.setEnabled(true);
			}
			else{
				displayDS.setEnabled(false);
			}
		}


	}
	/**
	 * Radio button selection listener
	 * @author yuxia_000
	 *
	 */
	private class SelectListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			String title = "";
			int i = 0;

			if(movies.size() == 0 && series.size() == 0 && episodes.size() == 0 
					&& actors.size() == 0 && directors.size() == 0 && producers.size() == 0){
				edit.setEnabled(false);
				save.setEnabled(false);

				export.setEnabled(false);
				clear.setEnabled(false);
				clearAll.setEnabled(false);
				delete.setEnabled(false);
				pieChart.setEnabled(false);
				histogram.setEnabled(false);
			}
			else{
				edit.setEnabled(true);
				save.setEnabled(true);
				export.setEnabled(true);
				clear.setEnabled(true);
				clearAll.setEnabled(true);
				delete.setEnabled(true);
				pieChart.setEnabled(true);
				histogram.setEnabled(true);
			}


			for(JRadioButton b : buttonList){
				if(b.isSelected()){
					title = title + b.getActionCommand() + "s ";
					i++;
				}
			}
			if(title.contains(",")) title = title.substring(0, title.length() - 2);
			if(i == 0) title = "Output Titile";
			outputLabel.setText(title);

			if((!mediaButton.isSelected() && !makersButton.isSelected()) && i > 0){
				add.setEnabled(true);

			}
			else{
				add.setEnabled(false);
			}

			if(makersButton.isSelected()) {
				edit.setEnabled(false);
				clear.setEnabled(false);
				delete.setEnabled(false);
				
			}


			if( actors.size() + directors.size() + producers.size() < 2 || 
					(!makersButton.isSelected() && !actorsButton.isSelected() && !directorsButton.isSelected()
							&& !producersButton.isSelected()) ){
				pieChart.setEnabled(false);
				histogram.setEnabled(false);
			}

			if(makersButton.isSelected() && actors.size() + directors.size() + producers.size() < 2){
				pieChart.setEnabled(false);
				histogram.setEnabled(false);
			}
			else if(actorsButton.isSelected() && actors.size() < 2){
				pieChart.setEnabled(false);
				histogram.setEnabled(false);
			}
			else if(directorsButton.isSelected() && directors.size() < 2){
				pieChart.setEnabled(false);
				histogram.setEnabled(false);
			}
			else if(producersButton.isSelected() && producers.size() < 2){
				pieChart.setEnabled(false);
				histogram.setEnabled(false);
			}

			display();


		}
	}
	/**
	 * Listener that catch the change of model and updata display info
	 * @author yuxia_000
	 *
	 */

	private class PieChartListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(objectList.isSelectionEmpty()) {
				JOptionPane.showMessageDialog(null, "Must select at least one maker.", "Piechart Display Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			ArrayList<Show> selectedObjects = (ArrayList<Show>) objectList.getSelectedValuesList();
			ArrayList<Creator> makers = new ArrayList<Creator>();
			String job = "";
			if(makersButton.isSelected()){
				makers.addAll(actors);
				makers.addAll(directors);
				makers.addAll(producers);
				job = "Making";
			}
			else if(actorsButton.isSelected()){
				makers.addAll(actors);
				job = "Acting";
			}
			else if(directorsButton.isSelected()){
				makers.addAll(directors);
				job = "Directing";
			}
			else if(producersButton.isSelected()){
				makers.addAll(producers);
				job = "Producing";
			}

			for(int i = 0; i < selectedObjects.size(); i++){
				int movieNum = 0;
				int episodeNum = 0;

				int am = 0;
				int ae = 0;
				int dm = 0;
				int de = 0;
				int pm = 0;
				int pe = 0;

				String name = selectedObjects.get(i).getName();
				makers.sort(Creator.NAME_COMPARATOR);
				int index = 0;
				for(int j = 0; j < makers.size(); j++){
					if(name.equals(makers.get(j).getName())){
						index = j;
						break;
					}
				}
				ArrayList<Creator> media = new ArrayList<Creator>();
				media.add(makers.get(index));
				if(makers.get(index).getMediaType().equals("MOVIE")) movieNum = 1;
				else if(makers.get(index).getMediaType().equals("EPISODE")) episodeNum = 1;


				for(int j = index + 1; j < makers.size(); j++ ){
					if(makers.get(j).getName().equals(makers.get(j - 1).getName())){
						media.add(makers.get(j));

						if(makers.get(j).getMediaType().equals("MOVIE")) 
							movieNum++;
						else if(makers.get(j).getMediaType().equals("EPISODE")) 
							episodeNum++;
					}
					else break;
				}


				for(Creator b : media){
					if(b.getJob().equals("ACTING")){
						if(b.getMedia().equals("MOVIE")){
							am++;
						}
						else ae++;
					}
					else if(b.getJob().equals("DIRECTING")){
						if(b.getMedia().equals("MOVIE")){
							dm++;
						}
						else de++;
					}
					else if(b.getJob().equals("PRODUCING")){
						if(b.getMedia().equals("MOVIE")){
							pm++;
						}
						else pe++;
					}
				}

				Slice[] slices;
				if(job.equals("Making")){
					slices = new Slice[6];
					slices[0] = new Slice(ae, Color.RED, "Series Acting Credits");
					slices[1] =	new Slice(am, Color.ORANGE, "Movie Acting Credits");
					slices[2] =	new Slice(de, Color.YELLOW, "Series Directing Credits");
					slices[3] = new Slice(dm, Color.GREEN, "Movie Directing Credits");
					slices[4] = new Slice(pe, Color.CYAN, "Series Producing Credits");
					slices[5] =	new Slice(pm, Color.BLUE, "Movie Producing Credits");

				}
				else {
					slices = new Slice[2];
					slices[0] = new Slice(episodeNum, Color.RED, "Series " + job + " Credits");
					slices[1] =	new Slice(movieNum, Color.ORANGE, "Movie " + job + " Credits");
				}




				JFrame frame = new JFrame("Pie Chart for " + name);
				PieChart pieChart = new PieChart();
				frame.setSize(550, 340);
				pieChart.setSlices(slices);
				frame.setResizable(false);
				frame.getContentPane().add(pieChart, BorderLayout.CENTER);
				frame.setVisible(true);
			}
		}

	}

	private class HistogramListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			

			//
			
		}

	}
	
	private class DSDisplayListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			DSFrame a = new DSFrame();
				
		}

	}
	
	/**
	 * The listener that updata data displayed in output area
	 * when model changed
	 * @author yuxia_000
	 *
	 */
	private class ModelUpdateListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			if (model == null) return;

			if (e.getActionCommand().equals("movies list changed")){
				movies = model.getMovies();
				display();

			}
			else if(e.getActionCommand().equals("series list changed")){
				series = model.getSeries();
				episodes = model.getEpisode();

				display();	
			}
			else if(e.getActionCommand().equals("actors list changed")){
				actors = model.getActor();
				display();
			}
			else if(e.getActionCommand().equals("director list changed")){
				directors = model.getDirector();
				display();
			}
			else if(e.getActionCommand().equals("producer list changed")){
				producers = model.getProducer();
				display();
			}
			else if(e.getActionCommand().equals("clear selected object type")){
				movies = model.getMovies();
				series = model.getSeries();
				episodes = model.getEpisode();
				actors = model.getActor();
				directors = model.getDirector();
				producers = model.getProducer();
				display();

			}
			else if(e.getActionCommand().equals("clear all")){
				movies = new ArrayList<Show>();
				series = new ArrayList<Show>();
				episodes = new ArrayList<Show>();
				actors = new ArrayList<Creator>();
				directors = new ArrayList<Creator>();
				producers = new ArrayList<Creator>();

				display();
			}
			else if(e.getActionCommand().equals("load movies")){
				movies = model.getMovies();
				objectLoaded = "Movie";
				display();
			}
			else if(e.getActionCommand().equals("load series")){
				series = model.getSeries();
				if(objectLoaded.length() > 1){
					objectLoaded = objectLoaded + ", " + "Series";
				}
				else objectLoaded = "Series";
				display();
			}
			else if(e.getActionCommand().equals("load episodes")){
				episodes = model.getEpisode();
				if(objectLoaded.length() > 1){
					objectLoaded = objectLoaded + ", " + "Episode";
				}
				else objectLoaded = "Episode";
				display();
			}
			else if(e.getActionCommand().equals("load actors")){
				actors = model.getActor();
				if(objectLoaded.length() > 1){
					objectLoaded = objectLoaded + ", " + "Actor";
				}
				else objectLoaded = "Actor";
				display();
			}
			else if(e.getActionCommand().equals("load directors")){
				directors = model.getDirector();
				if(objectLoaded.length() > 1){
					objectLoaded = objectLoaded + ", " + "directors";
				}
				else objectLoaded = "Director";
				display();
			}
			else if(e.getActionCommand().equals("load producers")){
				producers = model.getProducer();
				if(objectLoaded.length() > 1){
					objectLoaded = objectLoaded + ", " + "producers";
				}
				else objectLoaded = "Producer";
				display();
			}

			if(movies.size() == 0 && series.size() == 0 && episodes.size() == 0 
					&& actors.size() == 0 && directors.size() == 0 && producers.size() == 0){
				edit.setEnabled(false);
				save.setEnabled(false);

				export.setEnabled(false);
				clear.setEnabled(false);
				clearAll.setEnabled(false);
				delete.setEnabled(false);
				pieChart.setEnabled(false);
				histogram.setEnabled(false);
			}
			else{
				edit.setEnabled(true);
				save.setEnabled(true);
				export.setEnabled(true);
				clear.setEnabled(true);
				clearAll.setEnabled(true);
				delete.setEnabled(true);
				pieChart.setEnabled(true);
				histogram.setEnabled(true);
			}

			if( actors.size() + directors.size() + producers.size() < 2 || 
					(!makersButton.isSelected() && !actorsButton.isSelected() && !directorsButton.isSelected()
							&& !producersButton.isSelected()) ){
				pieChart.setEnabled(false);
				histogram.setEnabled(false);
			}

			if(makersButton.isSelected() && actors.size() + directors.size() + producers.size() < 2){
				pieChart.setEnabled(false);
				histogram.setEnabled(false);
			}
			else if(actorsButton.isSelected() && actors.size() < 2){
				pieChart.setEnabled(false);
				histogram.setEnabled(false);
			}
			else if(directorsButton.isSelected() && directors.size() < 2){
				pieChart.setEnabled(false);
				histogram.setEnabled(false);
			}
			else if(producersButton.isSelected() && producers.size() < 2){
				pieChart.setEnabled(false);
				histogram.setEnabled(false);
			}
		}
	}
	/**
	 * add Action Listener into Radio buttons
	 * @param e Action Listener
	 */
	public void addSeletActionListener(ActionListener e){
		mediaButton.addActionListener(e);
		movieButton.addActionListener(e);
		seriesButton.addActionListener(e);
		episodeButton.addActionListener(e);
		makersButton.addActionListener(e);
		actorsButton.addActionListener(e);
		directorsButton.addActionListener(e);
		producersButton.addActionListener(e);
	}
	/**
	 * The method add ActionListener for add button
	 * @param Action listener
	 */
	public void addAddButtonActionListener(ActionListener e){
		add.addActionListener(e);
	}
	/**
	 * The method add ActionListener for edit button
	 * @param Action listener
	 */
	public void addEditButtonActionListener(ActionListener e){
		edit.addActionListener(e);
	}
	/**
	 * The method add ActionListener for load button
	 * @param Action listener
	 */
	public void addLoadFilesActionListener(ActionListener e){
		load.addActionListener(e);
	}
	/**
	 * The method add ActionListener for save button
	 * @param Action listener
	 */
	public void addSaveActionListener(ActionListener e){
		save.addActionListener(e);
	}
	/**
	 * The method add ActionListener for import button
	 * @param Action listener
	 */
	public void addImportActionListener(ActionListener e){
		importItem.addActionListener(e);
	}
	/**
	 * The method add ActionListener for export button
	 * @param Action listener
	 */
	public void addExportActionListener(ActionListener e){
		export.addActionListener(e);
	}
	/**
	 * The method add ActionListener for delete button
	 * @param Action listener
	 */
	public void addDeleteActionListener(ActionListener e){
		delete.addActionListener(e);
	}
	/**
	 * The method add ActionListener for clear button
	 * @param Action listener
	 */
	public void addClearActionListener(ActionListener e){
		clear.addActionListener(e);
	}
	/**
	 * The method add ActionListener for clear all button
	 * @param Action listener
	 */
	public void addClearAllActionListener(ActionListener e){
		clearAll.addActionListener(e);
	}
	/**
	 * The method add ActionListener for show Pie chart button
	 * @param Action listener
	 */
	public void addPieChartActionListener(ActionListener e){
		pieChart.addActionListener(e);
	}
	/**
	 * The method add ActionListener for show histogram button
	 * @param Action listener
	 */
	public void addhistogramActionListener(ActionListener e){
		histogram.addActionListener(e);
	}
	/**
	 * The method that display data that from model
	 */
	private void display(){
		if(mediaButton.isSelected()){
			ArrayList<Show> temp = new ArrayList<Show>();
			if(movies != null) temp.addAll(movies);
			if(series != null) temp.addAll(series);
			if(series != null) temp.addAll(episodes);

			Collections.sort(temp, Show.TITLE_COMPARATOR);
			if(temp.size() > 0){
				listModel.clear();
				for(int i = 0; i < temp.size(); i++){
					listModel.addElement(temp.get(i));
				}
			}
			else{
				listModel.clear();
			}
		}
		else if(movieButton.isSelected()){

			if(movies != null){
				listModel.clear();
				for(int i = 0; i < movies.size(); i++){
					listModel.addElement(movies.get(i));
				}
			}
			else{
				listModel.clear();
			}
		}

		else if(seriesButton.isSelected()){

			if(series != null){
				listModel.clear();
				for(int i = 0; i < series.size(); i++){
					listModel.addElement(series.get(i));
				}
			}
			else {
				listModel.clear();
			}
		}
		else if(episodeButton.isSelected()){

			if(episodes != null){
				listModel.clear();
				for(int i = 0; i < episodes.size(); i++){
					listModel.addElement(episodes.get(i));
				}
			}
			else{
				listModel.clear();
			}
		}

		else if(makersButton.isSelected()){
			ArrayList<Creator> temp = new ArrayList<Creator>();
			temp.addAll(actors);
			temp.addAll(directors);
			temp.addAll(producers);
			if(temp.size() == 0){
				listModel.removeAllElements();
				return;
			}
			temp.sort(Creator.NAME_COMPARATOR);
			ArrayList<Creator> temp1 = new ArrayList<Creator>();
			temp1.add(temp.get(0));

			for(int i = 1; i < temp.size(); i++){
				if (!temp.get(i).getName().equalsIgnoreCase(temp1.get(temp1.size() - 1).getName())){
					temp1.add(temp.get(i));
				}
			}

			if(temp1.size() > 0){
				listModel.clear();
				for(int i = 0; i < temp1.size(); i++){
					temp1.get(i).isOnlyName = true;
					listModel.addElement(temp1.get(i));
				}
			}
			else{
				listModel.clear();
			}
		}


		else if(actorsButton.isSelected()){
			if(actors != null){
				listModel.clear();

				for(int i = 0; i < actors.size(); i++){
				actors.get(i).isNormalPrint = false;
				actors.get(i).isOnlyName = false;
					listModel.addElement(actors.get(i));
				}
			}
			else {
				listModel.clear();
			}
		}
		else if(directorsButton.isSelected()){
			if(directors != null){
				listModel.clear();
				for(int i = 0; i < directors.size(); i++){
					directors.get(i).isNormalPrint = false;
					directors.get(i).isOnlyName = false;
					listModel.addElement(directors.get(i));
				}
			}
			else {
				listModel.clear();
			}
		}
		else if(producersButton.isSelected()){
			if(producers != null){
				listModel.clear();
				for(int i = 0; i < producers.size(); i++){
					producers.get(i).isNormalPrint = false;
					producers.get(i).isOnlyName = false;
					listModel.addElement(producers.get(i));
				}
			}
			else {
				listModel.clear();
			}
		}
	}
}
