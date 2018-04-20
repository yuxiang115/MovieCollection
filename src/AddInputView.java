import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



/**
 * Project #5
 * CS 2334, Section 010
 * April 22, 2016
 * <P>
 * Class for addData input windows(including Maker and Media info input)
 * </P>
 */
public class AddInputView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Enter movie View Compoenents
	/**The box get series start year*/
	public JComboBox<String> startYearBox;
	/**The box get media end year*/
	public JComboBox<String> endYearBox;
	/**The box get episode release year*/
	public JComboBox<String> releaseYearBox;
	//movie info
	/**The Filed get movie title*/
	public JTextField movieTitleField;
	/**The Box get movie ronNum*/
	public JComboBox<String> ronNumBox;
	/**The Box get movie venue*/
	public JComboBox<String> venueBox;
	
	public JButton addButton = new JButton("ADD");

	//Series info
	/**The Field get series title*/
	public JTextField seriesTitleField;
	
	
	//episode info
	/**The Field get episode's title*/
	public JTextField episodeTitleField;
	/**The Field get episode's number*/
	public JTextField episodeNumField;
	
	//Creator info
	/**The Field get creator's name*/
	public JTextField creatorNameField;
	/**The Field get creator's title of the work associated with the creator*/
	public JTextField creatorTitleField;
	/**The Field get creator's role play in media*/
	public JTextField creatorRoleField;
	/**The box get creator's Archive Footage*/
	public JComboBox<String> archiveFootageBox;
	/**The box get creator's Credit*/
	public JComboBox<String> creditBox;
	/**The box get creator's character name*/
	public JTextField creatorCharNameField;
	/**The Field creator's paycheck */
	public JTextField creatorBillingOrderField;
	
	/**The years value */
	private String[] year;
	
	
	/**
	 * add data view constructor
	 * @param type which type of add windows want to open
	 */
	public AddInputView(String type){
		year = new String[200];
		year[0] = "????";
		for(int i = 1; i < 200; i++){
			year[i] = Integer.toString(i + 1900);
		}
		if(type.equals("Movie")){
			addMovieFrame();
		}
		else if(type.equals("Series")){
			addSeriesFrame();
		}
		else if(type.equals("Episode")){
			addEpisodeFrame();
		}
		else if(type.equals("Actor")){
			addActorFrame();
		}
		else if(type.equals("Director")){
			addDirectorFrame();
		}
		else if(type.equals("Producer")){
			addProducerFrame();
		}
	}
	/**
	 * add data view constructor
	 */
	public AddInputView(){
		year = new String[200];
		year[0] = "????";
		for(int i = 1; i < 200; i++){
			year[i] = Integer.toString(i + 1900);
		}
	}
	
	/**
	 * create a add movie windows
	 */
	public void addMovieFrame(){
		setTitle("Add Movie");
		JButton noButton = new JButton("CANCEL");
		noButton.addActionListener(new CancelListener());
		
		JPanel jplEnter = new JPanel(new GridLayout(5, 0, 5, 10));
		JLabel titleLabel = new JLabel("Title: ", JLabel.RIGHT);
		movieTitleField = new JTextField(20);
		jplEnter.add(titleLabel);
		jplEnter.add(movieTitleField);
		
		JLabel yearLabel = new JLabel("Year: ", JLabel.RIGHT);
		
		endYearBox = new JComboBox<String>(year);
		endYearBox.setSelectedIndex(0);
		jplEnter.add(yearLabel);
		jplEnter.add(endYearBox);
		
		JLabel venueLabel = new JLabel("venue: ", JLabel.RIGHT);
		String[] venue = {"", "(TV)", "(V)"};
		venueBox = new JComboBox<String>(venue);
		jplEnter.add(venueLabel);
		jplEnter.add(venueBox);
		
		JLabel ronManlabel = new JLabel("Roman Number: ", JLabel.RIGHT);
		String[] num = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		ronNumBox = new JComboBox<String>(num);
		jplEnter.add(ronManlabel);
		jplEnter.add(ronNumBox);
		
		jplEnter.add(addButton);
		jplEnter.add(noButton);
		add(jplEnter);
		setResizable(false);
		setVisible(true);
		pack();	
	}
	
	/**
	 * this method get movie info from user input 
	 * @return Movie object
	 */
	public Movie getMovie(){
		
		String title = movieTitleField.getText();
		String year = (String) endYearBox.getSelectedItem();
		String venue = (String) venueBox.getSelectedItem();
		String ronNum = (String) ronNumBox.getSelectedItem();
		return new Movie(title, year, ronNum, venue);
	}
	
	/**
	 * create a add series windows
	 */
	public void addSeriesFrame(){
		JButton noButton = new JButton("CANCEL");
		noButton.addActionListener(new CancelListener());
		
		setTitle("Add Series");
		JLabel titleLabel = new JLabel("Series Title: ", JLabel.RIGHT);
		JLabel startYearLabel = new JLabel("Start Year: ", JLabel.RIGHT);
		JLabel endYearLabel = new JLabel("End Year: ", JLabel.RIGHT);
		
		JPanel jplEnter = new JPanel(new GridLayout(4, 0, 5, 10));
		seriesTitleField = new JTextField(20);
		jplEnter.add(titleLabel);
		jplEnter.add(seriesTitleField);
		
		startYearBox = new JComboBox<String>(year);
		jplEnter.add(startYearLabel);
		jplEnter.add(startYearBox);
		
		endYearBox = new JComboBox<String>(year);
		jplEnter.add(endYearLabel);
		jplEnter.add(endYearBox);
		
		jplEnter.add(addButton);
		jplEnter.add(noButton);
		
		add(jplEnter);
		setResizable(false);
		setVisible(true);
		pack();
	}
	/**
	 * The method return Series Info from user
	 * @return Series Object
	 */
	public TVSeries getSeries(){
		String title = seriesTitleField.getText();
		String startYear = (String) startYearBox.getSelectedItem();
		String endYear = (String) endYearBox.getSelectedItem();
		
		return new TVSeries(title, startYear, endYear);
	}
	
	/**
	 * create a add episode windows
	 */
	public void addEpisodeFrame(){
		JButton noButton = new JButton("CANCEL");
		noButton.addActionListener(new CancelListener());
		
		setTitle("Add Episode");
		
		JPanel jplEnter = new JPanel(new GridLayout(7, 0, 5, 10));
		JLabel seriesTitleLabel = new JLabel("Series Title: ", JLabel.RIGHT);
		seriesTitleField = new JTextField(20);
		jplEnter.add(seriesTitleLabel);
		jplEnter.add(seriesTitleField);
		
		JLabel episodeTitleabel = new JLabel("Episode Title: ", JLabel.RIGHT);
		episodeTitleField = new JTextField(20);
		jplEnter.add(episodeTitleabel);
		jplEnter.add(episodeTitleField);
		
		JLabel episodeLabel = new JLabel("Episode Number: ", JLabel.RIGHT);
		episodeNumField = new JTextField(20);
		jplEnter.add(episodeLabel);
		jplEnter.add(episodeNumField);
		
		JLabel releaseYearLabel = new JLabel("Episode Release Year: ", JLabel.RIGHT);
		releaseYearBox = new JComboBox<String>(year);
		jplEnter.add(releaseYearLabel);
		jplEnter.add(releaseYearBox);
		
		JLabel startYearLabel = new JLabel("Series Start Year: ", JLabel.RIGHT);
		startYearBox = new JComboBox<String>(year);
		jplEnter.add(startYearLabel);
		jplEnter.add(startYearBox);
		
		JLabel endYearLabel = new JLabel("Series End Year: ", JLabel.RIGHT);
		endYearBox = new JComboBox<String>(year);
		jplEnter.add(endYearLabel);
		jplEnter.add(endYearBox);
		
		jplEnter.add(addButton);
		jplEnter.add(noButton);
		add(jplEnter);
		setResizable(false);
		setVisible(true);
		pack();
	}
	/**
	 * get episode from user
	 * @return Episode object get from user
	 */
	public Episode getEpisode(){
		String seriesTitle = seriesTitleField.getText();
		String episodeTitle = episodeTitleField.getText();
		String episodeNum = episodeNumField.getText();
		String startYear = (String)startYearBox.getSelectedItem();
		String endYear = (String)endYearBox.getSelectedItem();
		String releaseYear = (String)releaseYearBox.getSelectedItem();
		
		if(!episodeNum.isEmpty()){
			if (!episodeNum.contains("{")) episodeNum = "{" + episodeNum;
			if (!episodeNum.contains("(")) episodeNum = "{(" + episodeNum.substring(1);
			if (!episodeNum.contains("#")) episodeNum = "{(#" + episodeNum.substring(2);
			if (!episodeNum.contains(")")) episodeNum = episodeNum + ")";
			if (!episodeNum.contains("}")) episodeNum = episodeNum + "}";
		}
		
		return new Episode(episodeTitle, releaseYear, episodeNum, new TVSeries(seriesTitle, startYear, endYear));
	}
	/**
	 * create a add actor windows
	 */
	public void addActorFrame(){
		setTitle("Add Actor");
		
		JPanel jplEnter = new JPanel(new GridLayout(12, 0, 5, 5));
		
		JButton noButton = new JButton("CANCEL");
		noButton.addActionListener(new CancelListener());
		
		creatorNameField = new JTextField(20);
		JLabel nameLabel = new JLabel("Actor Name: ", JLabel.RIGHT);
		jplEnter.add(nameLabel);
		jplEnter.add(creatorNameField);
		
		creatorTitleField = new JTextField(20);
		JLabel titleLabel = new JLabel("Title: ", JLabel.RIGHT);
		titleLabel.setToolTipText("Movie Title or Series Title");
		jplEnter.add(titleLabel);
		jplEnter.add(creatorTitleField);
		
		endYearBox = new JComboBox<String>(year);
		JLabel yearLabel = new JLabel("Year: ", JLabel.RIGHT);
		jplEnter.add(yearLabel);
		jplEnter.add(endYearBox);
		
		episodeTitleField = new JTextField(20);
		JLabel episodeTitleLabel = new JLabel("Episode Title: ", JLabel.RIGHT);
		jplEnter.add(episodeTitleLabel);
		jplEnter.add(episodeTitleField);
		
		episodeNumField = new JTextField(20);
		JLabel episodeNumLabel = new JLabel("Episode Number: ", JLabel.RIGHT);
		jplEnter.add(episodeNumLabel);
		jplEnter.add(episodeNumField);
		
		String[] venue = {"", "(TV)", "(V)"};
		venueBox = new JComboBox<String>(venue);
		JLabel type = new JLabel("Venue: ", JLabel.RIGHT);
		jplEnter.add(type);
		jplEnter.add(venueBox);
		
		creatorRoleField = new JTextField(20);
		JLabel role = new JLabel("Role: ", JLabel.RIGHT);
		jplEnter.add(role);
		jplEnter.add(creatorRoleField);
		
		String[] isFootage = {"", "(Archive Footage)"};
		archiveFootageBox = new JComboBox<String>(isFootage);
		JLabel footage = new JLabel("Achive Footage: ", JLabel.RIGHT);
		jplEnter.add(footage);
		jplEnter.add(archiveFootageBox);
		
		String[] credits = {"", "(Credited)" ,"(Uncredited)"};
		creditBox = new JComboBox<String>(credits);
		JLabel creditLabel = new JLabel("Credit: ", JLabel.RIGHT);
		jplEnter.add(creditLabel);
		jplEnter.add(creditBox);
		
		creatorCharNameField = new JTextField(20);
		JLabel charNameLabel = new JLabel("Character Name: ", JLabel.RIGHT);
		jplEnter.add(charNameLabel);
		jplEnter.add(creatorCharNameField);
		
		creatorBillingOrderField = new JTextField(20);
		JLabel billingOrderLabel = new JLabel("Billing Order: ", JLabel.RIGHT);
		jplEnter.add(billingOrderLabel);
		jplEnter.add(creatorBillingOrderField);
		
		jplEnter.add(addButton);
		jplEnter.add(noButton);
		
		add(jplEnter);
		setResizable(false);
		setVisible(true);
		pack();
	}
	/**
	 * get actor info from user
	 * @return Actor Object from user
	 */
	public Creator getActor(){
		String name = creatorNameField.getText();
		String title = creatorTitleField.getText();
		String year = "(" + (String) endYearBox.getSelectedItem() + ")";
		String episodeTitle = episodeTitleField.getText();
		String episodeNum = episodeNumField.getText();
		String venue = (String) venueBox.getSelectedItem();
		String role = creatorRoleField.getText();
		String footage = (String) archiveFootageBox.getSelectedItem();
		String credit = (String) creditBox.getSelectedItem();
		String charName = creatorCharNameField.getText();
		String billing = creatorBillingOrderField.getText();
		
		//formate the EPnum info
		if(!episodeNum.isEmpty()){
			if (!episodeNum.contains("{")) episodeNum = "{" + episodeNum;
			if (!episodeNum.contains("(")) episodeNum = "{(" + episodeNum.substring(1);
			if (!episodeNum.contains("#")) episodeNum = "{(#" + episodeNum.substring(2);
			if (!episodeNum.contains(")")) episodeNum = episodeNum + ")";
			if (!episodeNum.contains("}")) episodeNum = episodeNum + "}";
		}
		//formate role 
		if(!role.isEmpty()){
			if(!role.contains("(")) role = "(" + role;
			if(!role.contains(")")) role = role + ")";
		}
		//formate chrname
		if(!charName.isEmpty()){
			if(!charName.contains("[")) charName = "[" + charName;
			if(!charName.contains("]")) charName = charName + "]";
		}
		//formate billing
		if(!billing.isEmpty()){
			if(!billing.contains("<")) billing = "<" + billing;
			if(!billing.contains(">")) billing = billing + ">";
		}
		
		return new Creator(name, title, year,episodeTitle, episodeNum, venue, role, footage, credit, charName, billing);
	}
	
	
	/**
	 * create add Director window
	 */
	public void addDirectorFrame(){
	setTitle("Add Director");
		
		JPanel jplEnter = new JPanel(new GridLayout(8, 0, 5, 5));
		
		JButton noButton = new JButton("CANCEL");
		noButton.addActionListener(new CancelListener());
		
		creatorNameField = new JTextField(20);
		JLabel nameLabel = new JLabel("Director Name: ", JLabel.RIGHT);
		jplEnter.add(nameLabel);
		jplEnter.add(creatorNameField);
		
		creatorTitleField = new JTextField(20);
		JLabel titleLabel = new JLabel("Title: ", JLabel.RIGHT);
		jplEnter.add(titleLabel);
		jplEnter.add(creatorTitleField);
		
		endYearBox = new JComboBox<String>(year);
		JLabel yearLabel = new JLabel("Year: ", JLabel.RIGHT);
		jplEnter.add(yearLabel);
		jplEnter.add(endYearBox);
		
		episodeTitleField = new JTextField(20);
		JLabel episodeTitleLabel = new JLabel("Episode Title: ", JLabel.RIGHT);
		jplEnter.add(episodeTitleLabel);
		jplEnter.add(episodeTitleField);
		
		episodeNumField = new JTextField(20);
		JLabel episodeNumLabel = new JLabel("Episode Number: ", JLabel.RIGHT);
		jplEnter.add(episodeNumLabel);
		jplEnter.add(episodeNumField);
		
		String[] venue = {"", "(TV)", "(V)"};
		venueBox = new JComboBox<String>(venue);
		JLabel type = new JLabel("Venue: ", JLabel.RIGHT);
		jplEnter.add(type);
		jplEnter.add(venueBox);
		
		String[] credits = {"", "(Credited)" ,"(Uncredited)"};
		creditBox = new JComboBox<String>(credits);
		JLabel creditLabel = new JLabel("Credit: ", JLabel.RIGHT);
		jplEnter.add(creditLabel);
		jplEnter.add(creditBox);
		
		jplEnter.add(addButton);
		jplEnter.add(noButton);
		
		add(jplEnter);
		setResizable(false);
		setVisible(true);
		pack();
	}
	/**
	 * get Director info to creator its object
	 * @return Director object from user
	 */
	public Creator getDirector(){
		String name = creatorNameField.getText();
		String title = creatorTitleField.getText();
		String year = "(" + (String) endYearBox.getSelectedItem() + ")";
		String epTitle = episodeTitleField.getText();
		String episodeNum = episodeNumField.getText();
		String venue = (String) venueBox.getSelectedItem();
		String credit = (String) creditBox.getSelectedItem();
		
		//formate the EPnum info
		if(!episodeNum.isEmpty()){
			if (!episodeNum.contains("{")) episodeNum = "{" + episodeNum;
			if (!episodeNum.contains("(")) episodeNum = "{(" + episodeNum.substring(1);
			if (!episodeNum.contains("#")) episodeNum = "{(#" + episodeNum.substring(2);
			if (!episodeNum.contains(")")) episodeNum = episodeNum + ")";
			if (!episodeNum.contains("}")) episodeNum = episodeNum + "}";
		}
		
		return new Creator(name, title, year, epTitle, episodeNum, venue, credit);
				
	}
	/**
	 * create a producer input windows 
	 */
	public void addProducerFrame(){
		setTitle("Add Producer");
		
		JPanel jplEnter = new JPanel(new GridLayout(9, 0, 5, 5));
		
		JButton noButton = new JButton("CANCEL");
		noButton.addActionListener(new CancelListener());
		
		creatorNameField = new JTextField(20);
		JLabel nameLabel = new JLabel("Director Name: ", JLabel.RIGHT);
		jplEnter.add(nameLabel);
		jplEnter.add(creatorNameField);
		
		creatorTitleField = new JTextField(20);
		JLabel titleLabel = new JLabel("Title: ", JLabel.RIGHT);
		jplEnter.add(titleLabel);
		jplEnter.add(creatorTitleField);
		
		endYearBox = new JComboBox<String>(year);
		JLabel yearLabel = new JLabel("Year: ", JLabel.RIGHT);
		jplEnter.add(yearLabel);
		jplEnter.add(endYearBox);
		
		episodeTitleField = new JTextField(20);
		JLabel episodeTitleLabel = new JLabel("Episode Title: ", JLabel.RIGHT);
		jplEnter.add(episodeTitleLabel);
		jplEnter.add(episodeTitleField);
		
		episodeNumField = new JTextField(20);
		JLabel episodeNumLabel = new JLabel("Episode Number: ", JLabel.RIGHT);
		jplEnter.add(episodeNumLabel);
		jplEnter.add(episodeNumField);
		
		String[] venue = {"", "(TV)", "(V)"};
		venueBox = new JComboBox<String>(venue);
		JLabel type = new JLabel("Venue: ", JLabel.RIGHT);
		jplEnter.add(type);
		jplEnter.add(venueBox);
		
		creatorRoleField = new JTextField(20);
		JLabel roleLabel = new JLabel("Role: ", JLabel.RIGHT);
		jplEnter.add(roleLabel);
		jplEnter.add(creatorRoleField);
		
		String[] credits = {"", "(Credited)" ,"(Uncredited)"};
		creditBox = new JComboBox<String>(credits);
		JLabel creditLabel = new JLabel("Credit: ", JLabel.RIGHT);
		jplEnter.add(creditLabel);
		jplEnter.add(creditBox);
		
		jplEnter.add(addButton);
		jplEnter.add(noButton);
		
		add(jplEnter);
		setResizable(false);
		setVisible(true);
		pack();
	}

	public Creator getProducer(){
		String name = creatorNameField.getText();
		String title = creatorTitleField.getText();
		String year = "(" + (String) endYearBox.getSelectedItem() + ")";
		String episodeTitle = episodeTitleField.getText();
		String episodeNum = episodeNumField.getText();
		String venue = (String) venueBox.getSelectedItem();
		String role = creatorRoleField.getText();
		String credit = (String) creditBox.getSelectedItem();
		
		//formate the EPnum info
		if(!episodeNum.isEmpty()){
			if (!episodeNum.contains("{")) episodeNum = "{" + episodeNum;
			if (!episodeNum.contains("(")) episodeNum = "{(" + episodeNum.substring(1);
			if (!episodeNum.contains("#")) episodeNum = "{(#" + episodeNum.substring(2);
			if (!episodeNum.contains(")")) episodeNum = episodeNum + ")";
			if (!episodeNum.contains("}")) episodeNum = episodeNum + "}";
		}
		//formate role 
		if(!role.isEmpty()){
			if(!role.contains("(")) role = "(" + role;
			if(!role.contains(")")) role = role + ")";
		}
		return new Creator(name, title, year, episodeTitle, episodeNum, venue, role, credit);
	}
	
	
	/**
	 * Listener class that close the windows
	 * @author yuxia_000
	 *
	 */
	private class CancelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
