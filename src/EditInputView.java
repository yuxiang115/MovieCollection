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
 * Class for edit Data input windows(including Maker and Media info edit)
 * </P>
 */
public class EditInputView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Enter movie View Compoenents
	/**The box get series start year/*/
	public JComboBox<String> startYearBox;
	/**The box get media end year/ movie release*/
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
	
	public JButton editButton = new JButton("EDIT");

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
	
	/**Movie object to edit*/
	private Movie movie;
	/**Series object to edit*/
	private TVSeries series;
	/**Episode object to edit*/
	private Episode episode;
	/**Creator object to edit*/
	private Creator creator;
	/**Type of object user editing*/
	private String editType;
	
	
	/**
	 * add data view constructor
	 * @param type which type of add windows want to open
	 */
	public EditInputView(String type){
		year = new String[200];
		year[0] = "????";
		for(int i = 1; i < 200; i++){
			year[i] = Integer.toString(i + 1900);
		}
		if(type.equals("Movie")){
			editMovieFrame();
		}
		else if(type.equals("Series")){
			editSeriesFrame();
		}
		else if(type.equals("Episode")){
			editEpisodeFrame();
		}
		else if(type.equals("Actor")){
			editActorFrame();
		}
		else if(type.equals("Director")){
			editDirectorFrame();
		}
		else if(type.equals("Producer")){
			editProducerFrame();
		}
		//initiate
		movieTitleField = new JTextField(20);
		endYearBox = new JComboBox<String>(year);
		startYearBox = new JComboBox<String>(year);
		releaseYearBox = new JComboBox<String>(year);
		String[] num = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		ronNumBox = new JComboBox<String>(num);
		String[] venue = {"", "(TV)", "(V)"};
		venueBox = new JComboBox<String>(venue);
		seriesTitleField = new JTextField(20);
		episodeTitleField = new JTextField(20);
		episodeNumField = new JTextField(20);
		creatorNameField = new JTextField(20);
		creatorTitleField = new JTextField(20);
		creatorRoleField = new JTextField(20);
		String[] isFootage = {"", "(Archive Footage)"};
		archiveFootageBox = new JComboBox<String>(isFootage);
		String[] credits = {"", "(Credited)" ,"(Uncredited)"};
		creditBox = new JComboBox<String>(credits);
		creatorCharNameField = new JTextField(20);
		creatorBillingOrderField = new JTextField(20);


		
	}
	/**
	 * add data view constructor
	 */
	public EditInputView(){
		year = new String[200];
		year[0] = "????";
		for(int i = 1; i < 200; i++){
			year[i] = Integer.toString(i + 1900);
		}
		
		//initiate
		movieTitleField = new JTextField(20);
		endYearBox = new JComboBox<String>(year);
		startYearBox = new JComboBox<String>(year);
		releaseYearBox = new JComboBox<String>(year);
		String[] num = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		ronNumBox = new JComboBox<String>(num);
		String[] venue = {"", "(TV)", "(V)"};
		venueBox = new JComboBox<String>(venue);
		seriesTitleField = new JTextField(20);
		episodeTitleField = new JTextField(20);
		episodeNumField = new JTextField(20);
		creatorNameField = new JTextField(20);
		creatorTitleField = new JTextField(20);
		creatorRoleField = new JTextField(20);
		String[] isFootage = {"", "(Archive Footage)"};
		archiveFootageBox = new JComboBox<String>(isFootage);
		String[] credits = {"", "(Credited)" ,"(Uncredited)"};
		creditBox = new JComboBox<String>(credits);
		creatorCharNameField = new JTextField(20);
		creatorBillingOrderField = new JTextField(20);

	}
	
	/**
	 * create a add movie windows
	 */
	public void editMovieFrame(){
		setTitle("Edit Movie");
		JButton noButton = new JButton("CANCEL");
		noButton.addActionListener(new CancelListener());
		
		JPanel jplEnter = new JPanel(new GridLayout(5, 0, 5, 10));
		JLabel titleLabel = new JLabel("Title: ", JLabel.RIGHT);
		jplEnter.add(titleLabel);
		jplEnter.add(movieTitleField);
		
		JLabel yearLabel = new JLabel("Year: ", JLabel.RIGHT);
		
		jplEnter.add(yearLabel);
		jplEnter.add(endYearBox);
		
		JLabel venueLabel = new JLabel("venue: ", JLabel.RIGHT);
		jplEnter.add(venueLabel);
		jplEnter.add(venueBox);
		
		JLabel ronManlabel = new JLabel("Roman Number: ", JLabel.RIGHT);
		jplEnter.add(ronManlabel);
		jplEnter.add(ronNumBox);
		
		jplEnter.add(editButton);
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
	public void editSeriesFrame(){
		JButton noButton = new JButton("CANCEL");
		noButton.addActionListener(new CancelListener());
		
		setTitle("Edit Series");
		JLabel titleLabel = new JLabel("Series Title: ", JLabel.RIGHT);
		JLabel startYearLabel = new JLabel("Start Year: ", JLabel.RIGHT);
		JLabel endYearLabel = new JLabel("End Year: ", JLabel.RIGHT);
		
		JPanel jplEnter = new JPanel(new GridLayout(4, 0, 5, 10));
		jplEnter.add(titleLabel);
		jplEnter.add(seriesTitleField);
		
		jplEnter.add(startYearLabel);
		jplEnter.add(startYearBox);
		
		jplEnter.add(endYearLabel);
		jplEnter.add(endYearBox);
		
		jplEnter.add(editButton);
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
	public void editEpisodeFrame(){
		JButton noButton = new JButton("CANCEL");
		noButton.addActionListener(new CancelListener());
		
		setTitle("Edit Episode");
		
		JPanel jplEnter = new JPanel(new GridLayout(7, 0, 5, 10));
		JLabel seriesTitleLabel = new JLabel("Series Title: ", JLabel.RIGHT);
		jplEnter.add(seriesTitleLabel);
		jplEnter.add(seriesTitleField);
		
		JLabel episodeTitleabel = new JLabel("Episode Title: ", JLabel.RIGHT);
		jplEnter.add(episodeTitleabel);
		jplEnter.add(episodeTitleField);
		
		JLabel episodeLabel = new JLabel("Episode Number: ", JLabel.RIGHT);
		jplEnter.add(episodeLabel);
		jplEnter.add(episodeNumField);
		
		JLabel releaseYearLabel = new JLabel("Episode Release Year: ", JLabel.RIGHT);
		jplEnter.add(releaseYearLabel);
		jplEnter.add(releaseYearBox);
		
		JLabel startYearLabel = new JLabel("Series Start Year: ", JLabel.RIGHT);
		jplEnter.add(startYearLabel);
		jplEnter.add(startYearBox);
		
		JLabel endYearLabel = new JLabel("Series End Year: ", JLabel.RIGHT);
		jplEnter.add(endYearLabel);
		jplEnter.add(endYearBox);
		
		jplEnter.add(editButton);
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
	public void editActorFrame(){
		setTitle("Edit Actor");
		
		JPanel jplEnter = new JPanel(new GridLayout(12, 0, 5, 5));
		
		JButton noButton = new JButton("CANCEL");
		noButton.addActionListener(new CancelListener());
		
		JLabel nameLabel = new JLabel("Actor Name: ", JLabel.RIGHT);
		jplEnter.add(nameLabel);
		jplEnter.add(creatorNameField);
		
		JLabel titleLabel = new JLabel("Title: ", JLabel.RIGHT);
		titleLabel.setToolTipText("Movie Title or Series Title");
		jplEnter.add(titleLabel);
		jplEnter.add(creatorTitleField);
		
		JLabel yearLabel = new JLabel("Year: ", JLabel.RIGHT);
		jplEnter.add(yearLabel);
		jplEnter.add(endYearBox);
		
		JLabel episodeTitleLabel = new JLabel("Episode Title: ", JLabel.RIGHT);
		jplEnter.add(episodeTitleLabel);
		jplEnter.add(episodeTitleField);
		
		JLabel episodeNumLabel = new JLabel("Episode Number: ", JLabel.RIGHT);
		jplEnter.add(episodeNumLabel);
		jplEnter.add(episodeNumField);
		
		JLabel type = new JLabel("Venue: ", JLabel.RIGHT);
		jplEnter.add(type);
		jplEnter.add(venueBox);
		
		JLabel role = new JLabel("Role: ", JLabel.RIGHT);
		jplEnter.add(role);
		jplEnter.add(creatorRoleField);
		
	
		JLabel footage = new JLabel("Achive Footage: ", JLabel.RIGHT);
		jplEnter.add(footage);
		jplEnter.add(archiveFootageBox);
		
		JLabel creditLabel = new JLabel("Credit: ", JLabel.RIGHT);
		jplEnter.add(creditLabel);
		jplEnter.add(creditBox);
		
		JLabel charNameLabel = new JLabel("Character Name: ", JLabel.RIGHT);
		jplEnter.add(charNameLabel);
		jplEnter.add(creatorCharNameField);
		
		JLabel billingOrderLabel = new JLabel("Billing Order: ", JLabel.RIGHT);
		jplEnter.add(billingOrderLabel);
		jplEnter.add(creatorBillingOrderField);
		
		jplEnter.add(editButton);
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
	public void editDirectorFrame(){
	setTitle("Edit Director");
		
		JPanel jplEnter = new JPanel(new GridLayout(8, 0, 5, 5));
		
		JButton noButton = new JButton("CANCEL");
		noButton.addActionListener(new CancelListener());
		
		JLabel nameLabel = new JLabel("Director Name: ", JLabel.RIGHT);
		jplEnter.add(nameLabel);
		jplEnter.add(creatorNameField);
		
		JLabel titleLabel = new JLabel("Title: ", JLabel.RIGHT);
		jplEnter.add(titleLabel);
		jplEnter.add(creatorTitleField);
		
		JLabel yearLabel = new JLabel("Year: ", JLabel.RIGHT);
		jplEnter.add(yearLabel);
		jplEnter.add(endYearBox);
		
		JLabel episodeTitleLabel = new JLabel("Episode Title: ", JLabel.RIGHT);
		jplEnter.add(episodeTitleLabel);
		jplEnter.add(episodeTitleField);
		
		JLabel episodeNumLabel = new JLabel("Episode Number: ", JLabel.RIGHT);
		jplEnter.add(episodeNumLabel);
		jplEnter.add(episodeNumField);
		
		JLabel type = new JLabel("Venue: ", JLabel.RIGHT);
		jplEnter.add(type);
		jplEnter.add(venueBox);
		
		JLabel creditLabel = new JLabel("Credit: ", JLabel.RIGHT);
		jplEnter.add(creditLabel);
		jplEnter.add(creditBox);
		
		jplEnter.add(editButton);
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
	public void editProducerFrame(){
		setTitle("Edit Producer");
		
		JPanel jplEnter = new JPanel(new GridLayout(9, 0, 5, 5));
		
		JButton noButton = new JButton("CANCEL");
		noButton.addActionListener(new CancelListener());
		
		JLabel nameLabel = new JLabel("Director Name: ", JLabel.RIGHT);
		jplEnter.add(nameLabel);
		jplEnter.add(creatorNameField);
		
		JLabel titleLabel = new JLabel("Title: ", JLabel.RIGHT);
		jplEnter.add(titleLabel);
		jplEnter.add(creatorTitleField);
		
		JLabel yearLabel = new JLabel("Year: ", JLabel.RIGHT);
		jplEnter.add(yearLabel);
		jplEnter.add(endYearBox);
		
		JLabel episodeTitleLabel = new JLabel("Episode Title: ", JLabel.RIGHT);
		jplEnter.add(episodeTitleLabel);
		jplEnter.add(episodeTitleField);
		
		JLabel episodeNumLabel = new JLabel("Episode Number: ", JLabel.RIGHT);
		jplEnter.add(episodeNumLabel);
		jplEnter.add(episodeNumField);
		
		JLabel type = new JLabel("Venue: ", JLabel.RIGHT);
		jplEnter.add(type);
		jplEnter.add(venueBox);
		
		JLabel roleLabel = new JLabel("Role: ", JLabel.RIGHT);
		jplEnter.add(roleLabel);
		jplEnter.add(creatorRoleField);
		
		JLabel creditLabel = new JLabel("Credit: ", JLabel.RIGHT);
		jplEnter.add(creditLabel);
		jplEnter.add(creditBox);
		
		jplEnter.add(editButton);
		jplEnter.add(noButton);
		
		add(jplEnter);
		setResizable(false);
		setVisible(true);
		pack();
	}
	/**
	 * this method is use for get edited producer object
	 * from user
	 * @return Producer Oject edited by User
	 */
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
	 * show the edit frame for user
	 * in order words, get info from original one to edit
	 * @param source the object user gonna edit
	 */
	public void editObject(Show source){
		if(source.getShowJob() == null){
			if(source.getMediaType().equals("MOVIE")){
				movie = (Movie) source;
				movieTitleField.setText(movie.getName());
				endYearBox.setSelectedItem(movie.getYear());
				venueBox.setSelectedItem(movie.getVenue());
				ronNumBox.setSelectedItem(movie.getRonNum());
				editMovieFrame();
				editType = "Movie";
			}
			else if(source.getMediaType().equals("SERIES")){
				seriesTitleField.setText(source.getName());
				startYearBox.setSelectedItem(source.getYear());
				endYearBox.setSelectedItem(source.getEndYear());
				editSeriesFrame();
				editType = "Series";
			}
			else if(source.getMediaType().equals("EPISODE")){
				episode = (Episode) source;
				seriesTitleField.setText(episode.getSeries().getName());
				episodeTitleField.setText(episode.getName());
				episodeNumField.setText(episode.getEpisodeNum());
				releaseYearBox.setSelectedItem(episode.getYear());
				startYearBox.setSelectedItem(episode.getSeries().getYear());
				endYearBox.setSelectedItem(episode.getSeries().getEndYear());
				editEpisodeFrame();
				editType = "Episode";
			}
		}
		else if(source.getShowJob().equals("ACTING")){
			creator = (Creator) source;
			creatorNameField.setText(creator.getName());
			creatorTitleField.setText(creator.getTitle());
			endYearBox.setSelectedItem(creator.getYear().substring(1, 5));
			episodeTitleField.setText(creator.getEpisodeTitle());
			episodeNumField.setText(creator.getEpisodeNumber());
			venueBox.setSelectedItem(creator.getType());
			creatorRoleField.setText(creator.getRole());
			archiveFootageBox.setSelectedItem(creator.getArchiveFootage());
			creditBox.setSelectedItem(creator.getCredit());
			creatorCharNameField.setText(creator.getCharName());
			creatorBillingOrderField.setText(creator.getBillingOrder());
			editActorFrame();
			editType = "Actor";
		}
		else if(source.getShowJob().equals("DIRECTING")){
			creator = (Creator) source;
			creatorNameField.setText(creator.getName());
			creatorTitleField.setText(creator.getTitle());
			endYearBox.setSelectedItem(creator.getYear().substring(1, 5));
			episodeTitleField.setText(creator.getEpisodeTitle());
			episodeNumField.setText(creator.getEpisodeNumber());
			venueBox.setSelectedItem(creator.getType());
			creatorRoleField.setText(creator.getRole());
			editDirectorFrame();
			editType = "Director";
		}
		else if(source.getShowJob().equals("PRODUCING")){
			creator = (Creator) source;
			creatorNameField.setText(creator.getName());
			creatorTitleField.setText(creator.getTitle());
			endYearBox.setSelectedItem(creator.getYear().substring(1, 5));
			episodeTitleField.setText(creator.getEpisodeTitle());
			episodeNumField.setText(creator.getEpisodeNumber());
			venueBox.setSelectedItem(creator.getType());
			creatorRoleField.setText(creator.getRole());
			creditBox.setSelectedItem(creator.getCredit());
			editProducerFrame();
			editType = "Producer";
		}
		
	}
	/**Get the source edit Movie
	 * 
	 * @return source movie to edit
	 */
	public Movie getEditMovieSource(){
		return movie;
	}
	/**Get the source edit Series
	 * 
	 * @return source series to edit
	 */
	public TVSeries getEditSeriesSource(){
		return series;
	}
	
	/**Get the source edit episode
	 * 
	 * @return source episode to edit
	 */
	public Episode getEditEpisodeSource(){
		return episode;
	}
	
	/**Get the source edit Creator
	 * 
	 * @return source creator to edit
	 */
	public Creator getEditCreatorSource(){
		return creator;
	}
	
	/**
	 * tells system what kind of object type editing
	 * @return Type of object usering editing
	 */
	public String getEditType(){
		return editType;
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
