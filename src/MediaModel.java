
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class MediaModel {
	public static ArrayList<Creator> actors = new ArrayList<Creator>();
	/** List that stores information about directors */
	public static ArrayList<Creator> directors = new ArrayList<Creator>();
	/** List that stores information about producers */
	public static ArrayList<Creator> producers = new ArrayList<Creator>();
	/** List that stores information about movies */
	public static ArrayList<Creator> allCreators = new ArrayList<Creator>();
	/** List that stores information about movies */
	public static ArrayList<Show> movies = new ArrayList<Show>();
	/** List that stores information about series */
	public static ArrayList<Show> series = new ArrayList<Show>();
	/** List that stores information about episodes */
	public static ArrayList<Show> episodes = new ArrayList<Show>();
	/** List that stores information about shows */
	public static ArrayList<Show> allShows = new ArrayList<Show>();

	private ArrayList<ActionListener> actionListenerList;
	/** database that read, load, save, edit data*/
	public MovieDatabase database = new MovieDatabase();
	/**Makers database that store the data*/
	public Creator makerDatabase = new Creator("1", "1", "1", "1", "1", "1", "1", "1");
	/**Takes file of movies, parses them, and add them a movie array list
	 * 
	 * @param file Name of File containing movies
	 * @throws IOException 
	 */
	public void readInMoviesFile(File[] files) throws IOException 
	{
		for(int i = 0; i < files.length; i++){
			database.readShowsIntoDB(files[i], true);
		}

		movies = database.getMovies();

		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "movies list changed"));
	}



	/**Takes a selected movie, and searches through the movies array list for that movie
	 * if movie not found, adds movie to array list.
	 * 
	 * @param the selected movie that is being added to the array list of type Show
	 * @return true if selected movie was added, false if the selected movie was a duplicate and was not added
	 */
	public boolean addMovie(Show movie)
	{

		movies.add(movie);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "movies list changed"));


		return true;

	}

	/**
	 * @return the movies array list
	 */
	public ArrayList<Show> getMovies()
	{
		return movies;
	}

	/**Takes a newly edited movie, and index of the movie being edited in the movies array list, 
	 * removes movie at the current index in the array list and replaces it with the newly edited movie.
	 * 
	 * @param the newly edited movie that is being added to the array list of type Show
	 * @param the selected movie being edited in the array list
	 * @return true when movie at current index is replaced with the newly edited movie.
	 */
	public boolean editMovie(Show originalMovie, Show newMovie)
	{
		movies.remove(originalMovie);
		movies.add(newMovie);
		movies.sort(Show.TITLE_COMPARATOR);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "movies list changed"));
		return true;
	}

	/**Takes index of the movie being deleted in the movies array list, removes movie at the index in the array list.
	 * 
	 * @param the selected movie being deleted in the array list
	 * @return true when movie at current index is deleted
	 */
	public boolean deleteMovie(Show indexOfMovie)
	{
		movies.remove(indexOfMovie);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "movies list changed"));
		return true;
	}

	/**Deletes entire movies array list
	 * 
	 * @return true when movies array list is deleted.
	 */
	public boolean clearMovies()
	{
		movies = new ArrayList<Show>();
		ArrayList<Creator> temp = new ArrayList<Creator>();
		for(Creator a : actors){
			if(!a.getMedia().equals("MOVIE"))
				temp.add(a); 
		}
		actors.clear(); 
		actors.addAll(temp);
		temp.clear();
		for(Creator a : directors){
			if(!a.getMedia().equals("MOVIE"))temp.add(a); 
		}
		directors.clear(); 
		directors.addAll(temp);
		temp.clear();

		for(Creator a : producers){
			if(!a.getMedia().equals("MOVIE"))temp.add(a); 
		}
		producers.clear(); 
		producers.addAll(temp);
		temp.clear();
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "clear selected object type"));
		return true;
	}

	/**Takes file of series/episodes, parses them, separates them, 
	 * and adds them to either a series and episodes array list
	 * 
	 * @param file Name of File containing series
	 * @throws IOException 
	 */
	public void readInSeriesFile(File[] seriesFiles) throws IOException
	{
		for(int i = 0; i < seriesFiles.length; i++){
			database.readShowsIntoDB(seriesFiles[i], false);
		}

		series = database.getTVSeries();
		episodes = database.getEpisodes();
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "series list changed"));
	}

	/**Takes a selected series, and searches through the series array list for that series
	 * if series not found, adds series to array list.
	 * 
	 * @param the selected series that is being added to the array list of type Show
	 * @return true if selected series was added, false if the selected series was a duplicate and was not added
	 */
	public boolean addSeries(Show seriesKey)
	{

		series.add(seriesKey);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "series list changed"));

		return true;

	}

	/**
	 * @return the series array list
	 */
	public ArrayList<Show> getSeries()
	{
		return series;
	}

	/**Takes a newly edited series, and index of the series being edited in the series array list, 
	 * removes series at the current index in the array list and replaces it with the newly edited series.
	 * 
	 * @param the newly edited series that is being added to the array list of type Show
	 * @param the selected series being edited in the array list
	 * @return true when series at current index is replaced with the newly edited series.
	 */
	public boolean editSeries(Show sourceSeries, Show newSeries)
	{
		series.remove(sourceSeries);
		series.add(newSeries);
		series.sort(Show.TITLE_COMPARATOR);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "series list changed"));
		return true;
	}

	/**Takes index of the series being deleted in the series array list, removes series at the index in the array list.
	 * 
	 * @param the selected series being deleted in the array list
	 * @return true when series at current index is deleted
	 */
	public boolean deleteSeries(Show indexOfSeries)
	{
		series.remove(indexOfSeries);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "series list changed"));
		return true;
	}

	/**Deletes entire series array list
	 * 
	 * @return true when series array list is deleted.
	 */
	public boolean clearSeries()
	{
		series = new ArrayList<Show>();

		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "series list changed"));
		return true;
	}



	/**Takes a selected episode, and searches through the episodes array list for that episode
	 * if episode not found, adds epidoe to array list.
	 * 
	 * @param the selected episode that is being added to the array list of type Show
	 * @return true if selected episode was added, false if the selected episode was a duplicate and was not added
	 */
	public boolean addEpisode(Show episode)
	{

		episodes.add(episode);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "series list changed"));
		return true;

	}

	/**
	 * @return the episode array list
	 */
	public ArrayList<Show> getEpisode()
	{
		return episodes;
	}

	/**Takes a newly edited episode, and index of the episode being edited in the episodes array list, 
	 * removes episode at the current index in the array list and replaces it with the newly edited episode.
	 * 
	 * @param the newly edited episode that is being added to the array list of type Show
	 * @param the selected episode being edited in the array list
	 * @return true when episode at current index is replaced with the newly edited episode.
	 */
	public boolean editEpisode(Show indexOfEpisode, Show newEpisode)
	{
		episodes.remove(indexOfEpisode);
		episodes.add(newEpisode);
		episodes.sort(Show.TITLE_COMPARATOR);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "series list changed"));
		return true;
	}

	/**Takes index of the episode being deleted in the episodes array list, removes episode at the index in the array list.
	 * 
	 * @param the selected episode being deleted in the array list
	 * @return true when episode at current index is deleted
	 */
	public boolean deleteEpisode(Show indexOfEpisode)
	{
		episodes.remove(indexOfEpisode);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "series list changed"));
		return true;
	}

	/**Deletes entire episodes array list
	 * 
	 * @return true when episodes array list is deleted.
	 */
	public boolean clearEpisodes()
	{
		episodes.clear();

		ArrayList<Creator> temp = new ArrayList<Creator>();
		for(Creator a : actors){
			if(!a.getMedia().equals("EPISODE"))temp.add(a); 
		}
		actors.clear(); 
		actors.addAll(temp);
		temp.clear();
		for(Creator a : directors){
			if(!a.getMedia().equals("EPISODE"))temp.add(a); 
		}
		directors.clear(); 
		directors.addAll(temp);;
		temp.clear();
		for(Creator a : producers){
			if(!a.getMedia().equals("EPISODE"))temp.add(a); 
		}
		producers.clear(); 
		producers.addAll(temp);
		temp.clear();


		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "clear selected object type"));
		return true;
	}
	/**Takes file of actors, parses them, and add them a actors array list
	 * 
	 * @param file Name of File containing actor
	 * @throws IOException 
	 */
	public void readInActorFile(File[] actorFiles) throws IOException{
		for(int i = 0; i < actorFiles.length; i++){
			makerDatabase.fillActors(actorFiles[i]);
		}
		actors = makerDatabase.getActor();

		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "actors list changed"));
	}

	/**Takes a selected actor, and searches through the actor array list for that actor
	 * if actor not found, adds actor to array list.
	 * 
	 * @param the selected actor that is being added to the array list of type Creator
	 * @return true if selected actor was added, false if the selected actor was a duplicate and was not added
	 */
	public boolean addActor(Creator actor)
	{

		actors.add(actor);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "actors list changed"));
		return true;

	}

	/**
	 * @return the actor array list
	 */
	public ArrayList<Creator> getActor()
	{
		return actors;
	}

	/**Takes a newly edited actor, and index of the actor being edited in the actor array list, 
	 * removes actor at the current index in the array list and replaces it with the newly edited actor.
	 * 
	 * @param the newly edited actor that is being added to the array list of type Creator
	 * @param the selected actor being edited in the array list
	 * @return true when actor at current index is replaced with the newly edited actor.
	 */
	public boolean editActor(Creator indexOfActor, Creator newActor)
	{
		actors.remove(indexOfActor);
		actors.add(newActor);
		actors.sort(Creator.TITLE_COMPARATOR);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "actors list changed"));
		return true;
	}

	/**Takes index of the actor being deleted in the actor array list, removes actor at the index in the array list.
	 * 
	 * @param the selected actor being deleted in the array list
	 * @return true when actor at current index is deleted
	 */
	public boolean deleteActor(Creator indexOfActor)
	{
		actors.remove(indexOfActor);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "actors list changed"));
		return true;
	}

	/**Deletes entire actors array list
	 * 
	 * @return true when actors array list is deleted.
	 */
	public boolean clearActors()
	{
		actors = new ArrayList<Creator>();
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "actors list changed"));
		return true;
	}

	/**Takes file of director, parses them, and add them a directors array list
	 * 
	 * @param file Name of File containing directors
	 * @throws IOException 
	 */
	public void readInDirectorFile(File[] directorFiles) throws IOException{
		for(int i = 0; i < directorFiles.length; i++){
			makerDatabase.fillDirectors(directorFiles[i]);
		}
		directors = makerDatabase.getDirector();
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "director list changed"));
	}



	/**Takes a selected director, and searches through the director array list for that director
	 * if director not found, adds director to array list.
	 * 
	 * @param the selected director that is being added to the array list of type Creator
	 * @return true if selected director was added, false if the selected director was a duplicate and was not added
	 */
	public boolean addDirector(Creator director)
	{
		directors.add(director);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "director list changed"));
		return true;
	}


	/**
	 * @return the director array list
	 */
	public ArrayList<Creator> getDirector()
	{
		return directors;
	}

	/**Takes a newly edited director, and index of the director being edited in the directors array list, 
	 * removes director at the current index in the array list and replaces it with the newly edited director.
	 * 
	 * @param the newly edited director that is being added to the array list of type Creator
	 * @param the selected director being edited in the array list
	 * @return true when director at current index is replaced with the newly edited director.
	 */
	public boolean editDirector(Creator indexOfDirector, Creator newDirector)
	{
		directors.remove(indexOfDirector);
		directors.add(newDirector);
		directors.sort(Creator.TITLE_COMPARATOR);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "director list changed"));
		return true;
	}

	/**Takes index of the director being deleted in the directors array list, removes director at the index in the array list.
	 * 
	 * @param the selected director being deleted in the array list
	 * @return true when director at current index is deleted
	 */
	public boolean deleteDirector(Creator indexOfDirector)
	{
		directors.remove(indexOfDirector);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "director list changed"));
		return true;
	}

	/**Deletes entire directors array list
	 * 
	 * @return true when directors array list is deleted.
	 */
	public boolean clearDirectors()
	{
		directors = new ArrayList<Creator>();
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "director list changed"));
		return true;
	}

	/**Takes file of producers, parses them, and add them a producers array list
	 * 
	 * @param file Name of File containing producers
	 * @throws IOException 
	 */
	public void readInProducerFile(File[] producerFiles) throws IOException{
		for(int i = 0; i < producerFiles.length; i++){
			makerDatabase.fillProducers(producerFiles[i]);
		}
		producers = makerDatabase.getProducer();

		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "producer list changed"));
	}


	/**Takes a selected producer, and searches through the producer array list for that producer
	 * if producer not found, adds producer to array list.
	 * 
	 * @param the selected producer that is being added to the array list of type Creator
	 * @return true if selected producer was added, false if the selected producer was a duplicate and was not added
	 */
	public boolean addProducer(Creator producer)
	{
		producers.add(producer);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "producer list changed"));
		return true;
	}

	/**
	 * @return the producer array list
	 */
	public ArrayList<Creator> getProducer()
	{
		return producers;
	}

	/**Takes a newly edited producer, and index of the producer being edited in the producers array list, 
	 * removes producer at the current index in the array list and replaces it with the newly edited producer.
	 * 
	 * @param the newly edited producer that is being added to the array list of type Creator
	 * @param the selected producer being edited in the array list
	 * @return true when producer at current index is replaced with the newly edited producer.
	 */
	public boolean editProducer(Creator indexOfProducer, Creator newProducer)
	{	
		
		producers.remove(indexOfProducer);
		producers.add(newProducer);
		producers.sort(Creator.TITLE_COMPARATOR);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "producer list changed"));
		return true;
	}

	/**Takes index of the producer being deleted in the producers array list, removes producer at the index in the array list.
	 * 
	 * @param the selected producer being deleted in the array list
	 * @return true when producer at current index is deleted
	 */
	public boolean deleteProducer(Creator indexOfProducer)
	{
		producers.remove(indexOfProducer);
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "producer list changed"));
		return true;
	}

	/**Deletes entire producers array list
	 * 
	 * @return true when producers array list is deleted.
	 */
	public boolean clearProducers()
	{
		producers = new ArrayList<Creator>();
		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "producer list changed"));
		return true;
	}

	/**Deletes all array lists
	 * 
	 * @return true when all array lists are deleted.
	 */
	public boolean clearAll(){
		movies = new ArrayList<Show>();
		series = new ArrayList<Show>();
		episodes = new ArrayList<Show>();
		actors = new ArrayList<Creator>();
		directors = new ArrayList<Creator>();
		producers = new ArrayList<Creator>();

		processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "clear all"));
		return true;
	}

	/**
	 * Register an action event listener.
	 */
	public synchronized void addActionListener(ActionListener l) {
		if (actionListenerList == null)
			actionListenerList = new ArrayList<ActionListener>();
		actionListenerList.add(l);
	}

	/**
	 * Remove an action event listener.
	 */
	public synchronized void removeActionListener(ActionListener l) {
		if (actionListenerList != null && actionListenerList.contains(l))
			actionListenerList.remove(l);
	}

	/**
	 * Fire event.
	 */
	private void processEvent(ActionEvent e) {
		ArrayList<ActionListener> list;

		synchronized (this) {
			if (actionListenerList == null) return;
			list = (ArrayList<ActionListener>)actionListenerList.clone();
		}

		for (int i = 0; i < list.size(); i++) {
			ActionListener listener = list.get(i);
			listener.actionPerformed(e);
		}
	}
	/**
	 * Opens a file specified by the user and outputs the creator array lists.
	 * <P>
	 *Algorithm:<br>
	 * 1. Opens a file specified by the user. <br>
	 * 2. Puts the data of creator array lists into file. <br>
	 * 3. Closes the file. <br>
	 * </P>
	 * @param String fileName: The name of the file the media data base is being written to
	 */
	public void saveToFileCreators(File fileName, String creatorType) throws IOException{
		FileOutputStream fileOutputStream = new FileOutputStream(fileName); //opens file
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		ArrayList<Creator> data = new ArrayList<Creator>();
		if(creatorType.equals("Maker")){
			data.addAll(actors);
			data.addAll(directors);
			data.addAll(producers);
		}
		else if(creatorType.equals("Actor")){
			data.addAll(actors);
			}
		else if(creatorType.equals("Director")){
			data.addAll(directors);
			}
		else if(creatorType.equals("Producer")){
			data.addAll(producers);
			}
		for(int i = 0; i < data.size(); i++){
			objectOutputStream.writeObject(data.get(i)); // writes Objects in DB to file
		}
		objectOutputStream.close(); // closes OutputStream
	}//end writeToFile

	/**
	 * Opens a file specified by the user and outputs the shows array lists.
	 * <P>
	 *Algorithm:<br>
	 * 1. Opens a file specified by the user. <br>
	 * 2. Puts the data of the show array lists into file. <br>
	 * 3. Closes the file. <br>
	 * </P>
	 * @param String fileName: The name of the file the media data base is being written to.
	 */
	public  void saveToFileShows(File file, String mediaType) throws IOException{
		FileOutputStream fileOutputStream = new FileOutputStream(file); //opens file
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		ArrayList<Show> data = new ArrayList<Show>();
		if(mediaType.equals("Media")){
			data.addAll(movies);
			data.addAll(series);
			data.addAll(episodes);
		}
		else if(mediaType.equals("Movie")){
			data.addAll(movies);
		}
		else if(mediaType.equals("Series")){
			data.addAll(series);
		}
		else if(mediaType.equals("Episode")){
			data.addAll(episodes);
		}
		for(int i = 0; i < data.size(); i++){
			objectOutputStream.writeObject(data.get(i)); // writes Objects in DB to file
		}
		objectOutputStream.close();
	}//end writeToFile

	/**
	 * This method read object from binary file
	 * @param fileName the file need to read
	 * @return Object get from binary file
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private ArrayList<Show> loadFromFile(File fileName) throws IOException, ClassNotFoundException{
		FileInputStream fileInputStream = new FileInputStream(fileName); //opens file
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		ArrayList<Show> shows = new ArrayList<Show>();
		Show temp;
		try{
			while(true){
				temp = (Show) objectInputStream.readObject();
				shows.add(temp);
			}
		} catch(EOFException e){
		}
		
		objectInputStream.close(); //closes InputStream
		return shows; // returns creator
	}// end readFromFile
	/**
	 * this method load multiple binary files into database
	 * @param files files array that loaded into database
	 * @throws Exception
	 * @throws IOException
	 */
	public void loadBinaryFiles(File[] files) throws Exception, IOException{
		ArrayList<Show> m = new ArrayList<Show>();
		ArrayList<Show> s = new ArrayList<Show>();
		ArrayList<Show> e = new ArrayList<Show>();
		ArrayList<Creator> a = new ArrayList<Creator>();
		ArrayList<Creator> d = new ArrayList<Creator>();
		ArrayList<Creator> p = new ArrayList<Creator>();
		ArrayList<Show> temp = new ArrayList<Show>();
		
		for(int i = 0; i < files.length; i++){
			temp.addAll(loadFromFile(files[i]));
		}
		
		for(int i = 0; i < temp.size(); i++){
			if(temp.get(i).getShowJob() == null){
				if(temp.get(i).getMediaType().equals("MOVIE")){
					m.add(temp.get(i));
				}
				else if(temp.get(i).getMediaType().equals("SERIES")){
					s.add(temp.get(i));
				}
				else if(temp.get(i).getMediaType().equals("EPISODE")){
					e.add(temp.get(i));
				}
			}
			else{
				if(temp.get(i).getShowJob().equals("ACTING")){
					a.add((Creator)temp.get(i));
				}
				else if(temp.get(i).getShowJob().equals("DIRECTING")){
					d.add((Creator)temp.get(i));
				}
				else if(temp.get(i).getShowJob().equals("PRODUCING")){
					p.add((Creator)temp.get(i));
				}
			}
		}
		
		if(m.size() > 0){
			movies = new ArrayList<Show>();
			movies.addAll(m);
			processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "load movies"));
		}
		if(s.size() > 0){
			series = new ArrayList<Show>();
			series.addAll(s);
			processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "load series"));
		}
		if(e.size() > 0){
			episodes = new ArrayList<Show>();
			episodes.addAll(e);
			processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "load episodes"));
		}
		if(a.size() > 0){
			actors = new ArrayList<Creator>();
			actors.addAll(a);
			processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "load actors"));
		}
		if(d.size() > 0){
			directors = new ArrayList<Creator>();
			directors.addAll(d);
			processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "load directors"));
		}
		if(p.size() > 0){
			producers = new ArrayList<Creator>();
			producers.addAll(p);
			processEvent(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, "load producers"));
		}
		
	}
	
	
	/**
	 * The method export file
	 * @param file The file to export
	 * @param data info that save as text data into file
	 * @throws IOException file cannot be written
	 */
	public void exportMediaFile(File file, String type) throws IOException{
		ArrayList<Show> data = new ArrayList<Show>();
		ArrayList<Creator> data2 = new ArrayList<Creator>();
		if(type.equals("Media")){
			data.addAll(movies);
			data.addAll(series);
			data.addAll(episodes);
			data.sort(Show.TITLE_COMPARATOR);
		}
		else if(type.equals("Movie")){
			data.addAll(movies);
			data.sort(Show.TITLE_COMPARATOR);
		}
		else if(type.equals("Series")){
			data.addAll(series);
			data.sort(Show.TITLE_COMPARATOR);
		}
		else if(type.equals("Episode")){
			data.addAll(episodes);
			data.sort(Show.TITLE_COMPARATOR);
		}
		else if(type.equals("Maker")){
			data2.addAll(actors);
			data2.addAll(directors);
			data2.addAll(producers);
			data2.sort(Creator.NAME_JOB_TITLE_COMPARATOR);
		}
		else if(type.equals("Actor")){
			data2.addAll(actors);
			data2.sort(Creator.NAME_JOB_TITLE_COMPARATOR);
		}
		else if(type.equals("Director")){
			data2.addAll(directors);
			data2.sort(Creator.NAME_JOB_TITLE_COMPARATOR);
		}
		else if(type.equals("Producer")){
			data2.addAll(producers);
			data2.sort(Creator.NAME_JOB_TITLE_COMPARATOR);
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		if(data.size() > data2.size()){
			for(Show o : data){
				writer.write(o.toString());
				writer.newLine();
			}
		}
		else{
			String name = "";
			String job = "";

			if(type.equals("Maker")){
				name = data2.get(0).getName();
				job = data2.get(0).getJob();
				writer.write(name);
				writer.newLine();
				writer.write(job);
				writer.newLine();

				for(int i = 0; i < data2.size(); i++){
					data2.get(i).isOnlyName = false;
					data2.get(i).isNormalPrint = true;

					if(name.equals(data2.get(i).getName())){
						if(job.equals(data2.get(i).getJob())){
							writer.write(data2.get(i).toString());
							writer.newLine();
						}
						else{
							job = data2.get(i).getJob();
							writer.write(job);
							writer.newLine();
							writer.write(data2.get(i).toString());
							writer.newLine();
						}
					}
					else{
						writer.newLine();
						name = data2.get(i).getName();
						job = data2.get(i).getJob();
						writer.write(name);
						writer.newLine();
						writer.write(job);
						writer.newLine();
						writer.write(data2.get(i).toString());
						writer.newLine();
					}
				}
			}
			else if(type.equals("Actor")){
				name = data2.get(0).getName();
				writer.write(name);
				writer.newLine();
				writer.write("ACTING");
				writer.newLine();
				for(int i = 0; i < data2.size(); i++){
					data2.get(i).isOnlyName = false;
					data2.get(i).isNormalPrint = true;
					if(name.equals(data2.get(i).getName())){
						writer.write(data2.get(i).toString());
						writer.newLine();

					}
					else{
						writer.newLine();
						name = data2.get(i).getName();
						writer.write(name);
						writer.newLine();
						writer.write("ACTING");
						writer.newLine();
						writer.write(data2.get(i).toString());
						writer.newLine();
					}
				}
			}
			else if(type.equals("Director")){
				name = data2.get(0).getName();
				writer.write(name);
				writer.newLine();
				writer.write("DIRECTING");
				writer.newLine();
				for(int i = 0; i < data2.size(); i++){
					data2.get(i).isOnlyName = false;
					data2.get(i).isNormalPrint = true;
					if(name.equals(data2.get(i).getName())){
						writer.write(data2.get(i).toString());
						writer.newLine();

					}
					else{
						writer.newLine();
						name = data2.get(i).getName();
						writer.write(name);
						writer.newLine();
						writer.write("DIRECTING");
						writer.newLine();
						writer.write(data2.get(i).toString());
						writer.newLine();
					}
				}
			}
			else if(type.equals("Producer")){
				name = data2.get(0).getName();
				writer.write(name);
				writer.newLine();
				writer.write("PRODUCING");
				writer.newLine();
				for(int i = 0; i < data2.size(); i++){
					data2.get(i).isOnlyName = false;
					data2.get(i).isNormalPrint = true;
					if(name.equals(data2.get(i).getName())){
						writer.write(data2.get(i).toString());
						writer.newLine();

					}
					else{
						writer.newLine();
						name = data2.get(i).getName();
						writer.write(name);
						writer.newLine();
						writer.write("PRODUCING");
						writer.newLine();
						writer.write(data2.get(i).toString());
						writer.newLine();
					}
				}
			}
		}
		writer.close();
	}

}
