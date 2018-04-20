

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 * Project #5
 * CS 2334, Section 010
 * April 22, 2016
 * <P>
 * Class for get the info from user input and pass those command to model
 * </P>
 */
public class Controller {
	/**The main frame that diplay result and implement the database*/
	private InputWindowsView input;
	/**The Model that manage the data*/
	private MediaModel model;
	/**Frame that add media/maker and get movie from user*/
	private AddInputView addInput;
	/**radio button's command which selected */
	private String selectedItem;
	/**List of edit frame*/
	ArrayList<EditInputView> editFrames;

	/**
	 * Constructor which is empty
	 */
	public Controller(){
		// intentionally blank
	}
	/**Set the View
	 * 
	 * @param view The main frame that diplay result and implement the database
	 */
	public void setView(InputWindowsView view){
		input = view;

		input.addLoadFilesActionListener(new LoadFilesListener());
		input.addImportActionListener(new ImportFilesListener());
		input.addSaveActionListener(new SaveFileListener());
		input.addExportActionListener(new ExportFileListener());
		input.addAddButtonActionListener(new AddButtonListener());
		input.addEditButtonActionListener(new EditButtonListener());
		input.addDeleteActionListener(new DeleteListener());
		input.addClearActionListener(new ClearListener());
		input.addClearAllActionListener(new ClearAllListener());
	}
	/**
	 * set Model
	 * @param model The Model that manage the data
	 */
	public void setModel(MediaModel model){
		this.model = model;
	}
	/**
	 * Action Listener that provide method load binary file into database
	 * @author yuxiang
	 *
	 */
	private class LoadFilesListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			input.filesChooser.setMultiSelectionEnabled(true);
			input.filesChooser.setDialogTitle("Load binary files");
			input.objectLoaded = "";
			File[] files;
			int fileChooserVal = input.filesChooser.showOpenDialog(null);
			if(fileChooserVal == JFileChooser.APPROVE_OPTION){
				files = input.filesChooser.getSelectedFiles();
				try {
					model.loadBinaryFiles(files);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"Selected files cannot be load into system",
							"Load Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
			if(input.objectLoaded.length() > 1){
				JOptionPane.showMessageDialog(null,input.objectLoaded + " objects have been loaded into system." );
			}
		}
	}
	/**
	 * The action Listener that provide method save binary file
	 * @author yuxiang
	 *
	 */
	private class SaveFileListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			String fileType = "";
			for(int i = 0; i < input.buttonList.size(); i++){
				if (input.buttonList.get(i).isSelected())
					fileType = input.buttonList.get(i).getActionCommand();
			}
			input.filesChooser.setMultiSelectionEnabled(false);
			input.filesChooser.setDialogTitle("Save " + fileType + "'s files");
			
			int fileChooserVal = input.filesChooser.showOpenDialog(null);
			if(fileChooserVal == JFileChooser.APPROVE_OPTION){
				File file = input.filesChooser.getSelectedFile();
				if(input.filesChooser.getFileFilter().getDescription().equals("Text File(.txt)"))
					if(!(file.getAbsolutePath().endsWith(".txt") || file.getAbsolutePath().endsWith(".text"))){
						String path = file.getAbsolutePath() + ".txt";
						file = new File(path);
					}
				try{
					if(fileType.equals("Media") || fileType.equals("Movie") || 
							fileType.equals("Series") || fileType.equals("Episode")){
						model.saveToFileShows(file, fileType);
					}
					else {
						model.saveToFileCreators(file, fileType);
					}
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, fileType + "cannot be saved as file",
							"Import Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}
	/**
	 * The listener that provide export text file method
	 * @author yuxia_000
	 *
	 */
	private class ExportFileListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			String type = "";
			for(int i = 0; i < input.buttonList.size(); i++){
				if (input.buttonList.get(i).isSelected())
					type = input.buttonList.get(i).getActionCommand();
			}
			input.filesChooser.setMultiSelectionEnabled(false);
			input.filesChooser.setDialogTitle("Export " + type + "'s files");

			int fileChooserVal = input.filesChooser.showOpenDialog(null);
			if(fileChooserVal == JFileChooser.APPROVE_OPTION){
				File file = input.filesChooser.getSelectedFile();
				if(input.filesChooser.getFileFilter().getDescription().equals("Text File(.txt)"))
					if(!(file.getAbsolutePath().endsWith(".txt") || file.getAbsolutePath().endsWith(".text"))){
						String path = file.getAbsolutePath() + ".txt";
						file = new File(path);
					}
				try {
					model.exportMediaFile(file, type);
				} catch (Throwable e1) {
					JOptionPane.showMessageDialog(null, "The file cannot be exported as " + type + "'s file ",
							"Import Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
					return;
				}

			}
		}
	}

	/**
	 * The Action listener that provide import files method
	 * @author yuxiang
	 *
	 */
	private class ImportFilesListener implements ActionListener{
		@Override 
		public void actionPerformed(ActionEvent e){
			File[] files;
			String[] choose = {"Movie", "Series", "Episode", "Actor", "Director", "Producer"};
			String fileType = (String)JOptionPane.showInputDialog(null, "Choose file type...", 
					"Choose what type files you want to import", JOptionPane.QUESTION_MESSAGE, null
					,choose, choose[0]);
			input.filesChooser.setMultiSelectionEnabled(true);
			input.filesChooser.setDialogTitle("Import " + fileType + "'s files");
			if(fileType != null){ 

				int fileChooserVal = input.filesChooser.showOpenDialog(null);
				if(fileChooserVal == JFileChooser.APPROVE_OPTION){
					files = input.filesChooser.getSelectedFiles();
					try {
						if(fileType.equals(choose[0])){	
							model.readInMoviesFile(files);
						}
						else if(fileType.equals(choose[1]) || fileType.equals(choose[2])){
							model.readInSeriesFile(files);
						}
						else if(fileType.equals(choose[3])){
							model.readInActorFile(files);
						}
						else if(fileType.equals(choose[4])){
							model.readInDirectorFile(files);
						}
						else if(fileType.equals(choose[5])){
							model.readInProducerFile(files);
						}
					} catch (Throwable e1) {
						JOptionPane.showMessageDialog(null, "The file cannot be read as " + fileType + "'s file ",
								"Import Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		}
	}

	/**
	 * press add button menu listener 
	 * @author yuxiang
	 *
	 */
	private class AddButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {	
			for(JRadioButton b : input.buttonList){
				if (b.isSelected()) selectedItem = b.getActionCommand();
			}
			if(addInput != null) addInput.dispose();
			addInput = new AddInputView(selectedItem);
			addInput.addButton.addActionListener(new AddDataListener());

		}
	}
	/**
	 * provide add data method into database
	 * @author yuxia_000
	 *
	 */
	public class AddDataListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Movie m;
			TVSeries t;
			Episode ep;
			Creator maker;

			if(selectedItem.equals("Movie")){
				m = addInput.getMovie();
				if(m.getName().isEmpty()){ 	
					JOptionPane.showMessageDialog(null, "Title cannot be empty!");
					return;
				}
				model.addMovie(m);
			}
			else if(selectedItem.equals("Series")){
				t = addInput.getSeries();
				if(t.getName().isEmpty()){ 	
					JOptionPane.showMessageDialog(null, "Title cannot be empty!");
					return;
				}
				model.addSeries(t);
			}
			else if(selectedItem.equals("Episode")){
				ep = addInput.getEpisode();
				if(ep.getSeries().getName().isEmpty()){ 	
					JOptionPane.showMessageDialog(null, "Series Title cannot be empty!");
					return;
				}
				model.addEpisode(ep);
			}

			else if(selectedItem.equals("Actor")){
				maker = addInput.getActor();
				if(maker.getName().isEmpty() || maker.getTitle().isEmpty()){ 	
					JOptionPane.showMessageDialog(null, "Maker's name or Title  cannot be empty!");
					return;
				}

				model.addActor(maker);
			}

			else if(selectedItem.equals("Director")){
				maker = addInput.getDirector();
				if(maker.getName().isEmpty() || maker.getTitle().isEmpty()){ 	
					JOptionPane.showMessageDialog(null, "Maker's name or Title  cannot be empty!");
					return;
				}

				model.addDirector(maker);
			}
			else if(selectedItem.equals("Producer")){
				maker = addInput.getProducer();
				if(maker.getName().isEmpty() || maker.getTitle().isEmpty()){ 	
					JOptionPane.showMessageDialog(null, "Maker's name or Title  cannot be empty!");
					return;
				}
				model.addProducer(maker);			
			}	
		}

	}

	/**Show edit frame listener
	 * @author yuxiang
	 *
	 */
	private class EditButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(editFrames != null){
				for(int i = 0; i < editFrames.size(); i++){
					editFrames.get(i).dispose();
				}
			}

			editFrames = new ArrayList<EditInputView>();
			if(input.objectList.isSelectionEmpty()) {
				JOptionPane.showMessageDialog(null, "You have to select at least one object to edit.");
				return;
			}
			ArrayList<Show> selectedObjects = (ArrayList<Show>) input.objectList.getSelectedValuesList();

			for(int i = 0; i < selectedObjects.size(); i++){
				EditInputView editView = new EditInputView();
				editView.editButton.setActionCommand(Integer.toString(i));
				editView.editButton.addActionListener(new EditDataListener());
				editView.editObject(selectedObjects.get(i));
				editFrames.add(editView);
			}
		}
	}
	/**edit data and add it into model listener
	 * @author yuxiang
	 *
	 */
	private class EditDataListener implements ActionListener{
		Movie m;
		TVSeries t;
		Episode ep;
		Creator maker;

		@Override
		public void actionPerformed(ActionEvent e){
			int i = Integer.parseInt(e.getActionCommand());

			if(editFrames.get(i).getEditType().equals("Movie")){
				m = editFrames.get(i).getMovie();
				if(m.getName().isEmpty()){ 	
					JOptionPane.showMessageDialog(null, "Title cannot be empty!");
					return;
				}
				//find index of edit source in model list

				model.editMovie((Show)editFrames.get(i).getEditMovieSource(), m);
				input.objectList.setSelectedValue(m, true);
				editFrames.get(i).dispose();
			}
			else if(editFrames.get(i).getEditType().equals("Series")){
				t = editFrames.get(i).getSeries();
				if(t.getName().isEmpty()){ 	
					JOptionPane.showMessageDialog(null, "Title cannot be empty!");
					return;
				}
				model.editSeries((Show)editFrames.get(i).getEditSeriesSource(), t);
				input.objectList.setSelectedValue(t, true);
				editFrames.get(i).dispose();
			}
			else if(editFrames.get(i).getEditType().equals("Episode")){
				ep = editFrames.get(i).getEpisode();
				if(ep.getSeries().getName().isEmpty()){ 	
					JOptionPane.showMessageDialog(null, "Title cannot be empty!");
					return;
				}
				model.editEpisode((Show)editFrames.get(i).getEditEpisodeSource(), ep);
				input.objectList.setSelectedValue(ep, true);
				editFrames.get(i).dispose();
			}

			else if(editFrames.get(i).getEditType().equals("Actor")){
				maker = editFrames.get(i).getActor();
				if(maker.getName().isEmpty() || maker.getTitle().isEmpty()){ 	
					JOptionPane.showMessageDialog(null, "Maker's name or Title  cannot be empty!");
					return;
				}

				model.editActor(editFrames.get(i).getEditCreatorSource(), maker);
				input.objectList.setSelectedValue(maker, true);
				editFrames.get(i).dispose();
			}

			else if(editFrames.get(i).getEditType().equals("Director")){
				maker = editFrames.get(i).getDirector();
				if(maker.getName().isEmpty() || maker.getTitle().isEmpty()){ 	
					JOptionPane.showMessageDialog(null, "Maker's name or Title  cannot be empty!");
					return;
				}

				model.editDirector(editFrames.get(i).getEditCreatorSource(), maker);
				input.objectList.setSelectedValue(maker, true);
				editFrames.get(i).dispose();
			}
			else if(editFrames.get(i).getEditType().equals("Producer")){
				maker = editFrames.get(i).getProducer();
				if(maker.getName().isEmpty() || maker.getTitle().isEmpty()){ 	
					JOptionPane.showMessageDialog(null, "Maker's name or Title  cannot be empty!");
					return;
				}

				model.editProducer(editFrames.get(i).getEditCreatorSource(), maker);;
				input.objectList.setSelectedValue(maker, true);
				editFrames.get(i).dispose();
			}
		}
	} 
	/**
	 * delete data listener
	 * @author yuxiang
	 *
	 */
	private class DeleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if(JOptionPane.showConfirmDialog(null, "Are you sure delete this object?",
					"Delete Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION){
				return;
			}
			
			ArrayList<Show> selectedObjects = (ArrayList<Show>) input.objectList.getSelectedValuesList();
			if(selectedObjects.size() == 0){
				JOptionPane.showMessageDialog(null, "You must select at least one object in "
						+ "the left output area to delete","Selection Error", JOptionPane.ERROR_MESSAGE);
			}

			for(int i = 0; i < input.buttonList.size(); i++){
				if(input.buttonList.get(i).isSelected()){
					if(i == 0){
						for(int j = 0; j < selectedObjects.size(); j++){
							if(selectedObjects.get(j).getMediaType().equals("MOVIE")){
								model.deleteMovie(selectedObjects.get(j));
							}
							else if(selectedObjects.get(i).getMediaType().equals("SERIES")){
								model.deleteSeries(selectedObjects.get(i));
							}
							else if(selectedObjects.get(i).getMediaType().equals("EPISODE")){
								model.deleteEpisode(selectedObjects.get(i));
							}


						}
					}
					else if(i == 1){
						for(int j = 0; j < selectedObjects.size(); j++){
							model.deleteMovie(selectedObjects.get(j));
						}
						break;
					}
					else if(i == 2){
						for(int j = 0; j < selectedObjects.size(); j++){
							model.deleteSeries(selectedObjects.get(j));
						}
						break;
					}
					else if(i == 3){
						for(int j = 0; j < selectedObjects.size(); j++){
							model.deleteEpisode(selectedObjects.get(j));
						}
						break;
					}
					else if(i == 4){
						for(int j = 0; j < selectedObjects.size(); j++){
							if(selectedObjects.get(j).getShowJob().equals("ACTING")){
								model.deleteActor((Creator) selectedObjects.get(j));
							}
							else if(selectedObjects.get(j).getShowJob().equals("DIRECTING")){
								model.deleteDirector((Creator) selectedObjects.get(j));
							}
							else if(selectedObjects.get(j).getShowJob().equals("PRODUCING")){
								model.deleteProducer((Creator) selectedObjects.get(j));
							}
						}
					}

					else if(i == 5){
						for(int j = 0; j < selectedObjects.size(); j++){
							model.deleteActor((Creator)selectedObjects.get(j));
						}
						break;
					}
					else if(i == 6){
						for(int j = 0; j < selectedObjects.size(); j++){
							model.deleteDirector((Creator)selectedObjects.get(j));
						}
						break;
					}
					else if(i == 7){
						for(int j = 0; j < selectedObjects.size(); j++){
							model.deleteProducer((Creator)selectedObjects.get(j));
						}
						break;
					}
				}
			}			

		}
	}
	/**
	 * Action Listener that clear the object type selected
	 * @author yuxiang
	 *
	 */
	private class ClearListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			if(JOptionPane.showConfirmDialog(null, "Are you sure Clear this this type?",
					"Clear Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION){
				return;
			}
			int n = 0;
			for(int i = 0; i < input.buttonList.size(); i++){
				if(input.buttonList.get(i).isSelected()) n++;
			}
			if(n == 0){
				JOptionPane.showMessageDialog(null, "You must select which type of object you want to clear", "Clear Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			else {
				if(input.movieButton.isSelected()){
					model.clearMovies();
				}
				else if(input.seriesButton.isSelected()){
					model.clearSeries();
				}
				else if(input.episodeButton.isSelected()){
					model.clearEpisodes();
				}
				else if(input.actorsButton.isSelected()){
					model.clearActors();
				}
				else if(input.directorsButton.isSelected()){
					model.clearDirectors();
				}
				else if(input.producersButton.isSelected()){
					model.clearProducers();
				}
			}
		}
	}
	/**
	 * Action Listener for clear all data 
	 * @author yuxia_000
	 *
	 */
	private class ClearAllListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			if(JOptionPane.showConfirmDialog(null, "Are you sure Clear all data?",
					"Clear ALl Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION){
				return;
			}
			model.clearAll();
		}
	}
}
