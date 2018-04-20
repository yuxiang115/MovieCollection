import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class MovieDatabase {
	/** List containing all the movies and TV shows */
	private ArrayList<Show> mDb = new ArrayList<Show>();
	
	private ArrayList<Show> tVseries = new ArrayList<Show>();

	private ArrayList<Show> movies = new ArrayList<Show>();

	private ArrayList<Show> episodes = new ArrayList<Show>();
	/** LinkedHashMap containing all the actors */
	private LinkedHashMap<String, ArrayList<Creator>> actors = new LinkedHashMap<String, ArrayList<Creator>>();
	/** LinkedHashMap containing all the directors */
	private LinkedHashMap<String, ArrayList<Creator>> directors = new LinkedHashMap<String, ArrayList<Creator>>();
	/** LinkedHashMap containing all the producers */
	private LinkedHashMap<String, ArrayList<Creator>> producers = new LinkedHashMap<String, ArrayList<Creator>>();

	/**
	 * Constructor for MDb class.
	 * 
	 * Just here to make the compiler happy.
	 * 
	 */
	public MovieDatabase() {
		// intentionally empty
	}

	/**
	 * Constructor for MDb class.
	 * 
	 * @param moviesFile
	 *            Name of file containing movies
	 * @param TVFile
	 *            Name of file containing TV shows
	 */
	public MovieDatabase(File moviesFile, File TVFile) {
		try {
			mDb.addAll(readShowsIntoDB(moviesFile, true));
			mDb.addAll(readShowsIntoDB(TVFile, false));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Takes file of movies, parses them, and add them to database
	 * 
	 * @param file
	 *            Name of File containing shows
	 * @param isMovie
	 *            true if file contains movies, false if file containing TV
	 * @throws IOException
	 */
	public ArrayList<Show> readShowsIntoDB(File file, boolean isMovie) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		if (isMovie) {
			ArrayList<Show> movies = new ArrayList<Show>();

			String line = br.readLine();
			while (line != null) {
				movies.add(parseMovie(line));
				line = br.readLine();
			}
			br.close();
			fr.close();
			return movies;
		} else {
			ArrayList<Show> series = new ArrayList<Show>();
			ArrayList<Episode> episodes = new ArrayList<Episode>();
			String line = br.readLine();
			while (line != null && !line.isEmpty()) {
				if (line.contains("{")) {
					Episode toAdd = parseEpisode(line, (TVSeries) (series.get(series.size() - 1)));
					((TVSeries) series.get(series.size() - 1)).addEpisode(toAdd);
					episodes.add(toAdd);
				} else {
					series.add(parseTV(line));
				}
				line = br.readLine();
			}
			br.close();
			fr.close();
			series.addAll(episodes);
			return series;
		}
	}

	/**
	 * Search database for specific shows
	 * 
	 * @param movies
	 *            Search for movies?
	 * @param tv
	 *            Search for TV shows?
	 * @param episodes
	 *            Search for specific episodes? (Can't be true if tv is false)
	 * @param exactMatch
	 *            Only allow if title exactly matches search
	 * @param sortByTitle
	 *            Sort by title (if true) or by year (if false)
	 * @param title
	 *            Title to search for. "'-1'" will be if any
	 * @param years
	 *            Years to search for. "-1" will be in first index if any
	 * @return ArrayList<Show>
	 */
	public ArrayList<Show> searchFiles(boolean movies, boolean tv, boolean episodes, boolean exactMatch,
			boolean sortByTitle, String title, ArrayList<String> years) {

		ArrayList<Show> shows = new ArrayList<Show>();

		for (Show show : mDb) {
			if ((show.toString().toLowerCase().matches(".*" + title.toLowerCase() + ".*") || title.equals("-1"))
					&& (years.contains(show.getYear()) || years.contains(show.getEndYear())
							|| years.get(0).equals("-1"))) {
				if ((movies && show instanceof Movie) || (tv && show instanceof TVSeries)
						|| (episodes && show instanceof Episode)) {
					shows.add(show);
				}
			}
		}

		if (exactMatch) {
			ArrayList<String> titles = new ArrayList<String>();
			for (Show show : mDb) {
				titles.add(show.getName());
			}
			Collections.sort(mDb, Show.TITLE_COMPARATOR);
			int index = Collections.binarySearch(titles, title);
			if (index > 0) {
				shows.add(mDb.get(index));
			}
		}

		if (sortByTitle) {
			Collections.sort(shows, Show.TITLE_COMPARATOR);
		} else {
			Collections.sort(shows, Show.YEAR_COMPARATOR);
		}
		return shows;
	}

	/**
	 * Takes information about movie and converts it into a Movie instance
	 * 
	 * Used code from Dustin Gier's Project 1
	 * 
	 * @param Information
	 *            about movie
	 * @return Returns Movie created based off information in the line
	 */
	private Movie parseMovie(String line) {
		// find where parens with the year released is
		if (line.contains("(TV)") || line.contains("(V)") || line.contains("(T)")) {
			String[] info = line.split("\\s+");
			String title = info[0];
			for (int i = 1; i < info.length - 3; i++) {
				title = title + " " + info[i];

			}
			String ronNum = info[info.length - 3];
			if (ronNum.length() > 6) {
				ronNum = ronNum.substring(5, ronNum.indexOf(")"));
			} else {
				ronNum = "";
			}
			movies.add(new Movie(title, info[info.length - 1], ronNum, info[info.length - 2]));
			return new Movie(title, info[info.length - 1], ronNum, info[info.length - 2]);
		} else {
			String title = "";
			String[] info = line.split("\\s+");
			title = info[0];
			for (int i = 1; i < info.length - 2; i++) {
				title = title + " " + info[i];

			}
			String ronNum = info[info.length - 2];
			if (ronNum.length() > 6) {
				ronNum = ronNum.substring(5, ronNum.indexOf(")"));
			} else {
				ronNum = "";
			}
			movies.add(new Movie(title, info[info.length - 1], ronNum));
			return new Movie(title, info[info.length - 1], ronNum);
		}
	}


	/**
	 * Parses a TVSeries from a TVSeries file
	 * 
	 * @param line String to be parsed
	 * @return TVSeries based on line
	 */
	public TVSeries parseTV(String line) {
		String name = "";
		String year = "";
		String endYear = "";
		
		name = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
		year = line.substring(line.length() - 9, line.length() - 5);
		endYear = line.substring(line.length() - 4, line.length());
		tVseries.add(new TVSeries(name, year, endYear));
		return new TVSeries(name, year, endYear);
	}
	

	/**
	 * Parses episode from TV file
	 * 
	 * @param line
	 *            String to be parsed
	 * @param series
	 *            Series of which this episode is a part
	 * @return Episode based on line
	 */
	private Episode parseEpisode(String line, TVSeries series) {
		String name = "";
		String year = "";
		String episodeNumber = "";

		if (line.contains("#")) {
			if (line.indexOf("{") + 1 < line.indexOf("#") - 2) {
				name = line.substring(line.indexOf("{") + 1, line.indexOf("#") - 2);
			}
			episodeNumber = line.substring(line.indexOf("#"), line.lastIndexOf(")") - 1);
		} else {
			name = line.substring(line.indexOf("{") + 1, line.indexOf("}") - 1);
		}

		year = line.substring(line.indexOf("(") + 1, line.indexOf(")"));

		Episode episode = new Episode(name, year, episodeNumber, series);
		episodes.add(episode);
		return new Episode(name, year, episodeNumber, series);
	}

	/**
	 * Takes in LinkedHashMaps to store in this object. Here because we need an
	 * object to write to a binary file containing all the information needed
	 * for the program to function.
	 * 
	 * @param LinkedHashMap<String,
	 *            ArrayList<Creator>> associated with actors
	 * @param LinkedHashMap<String,
	 *            ArrayList<Creator>> associated with directors
	 * @param LinkedHashMap<String,
	 *            ArrayList<Creator>> associated with producers
	 */
	public void putHashMapIntoDB(LinkedHashMap<String, ArrayList<Creator>> actors,
			LinkedHashMap<String, ArrayList<Creator>> directors, LinkedHashMap<String, ArrayList<Creator>> producers) {
		this.actors = actors;
		this.directors = directors;
		this.producers = producers;
	}

	public void fileToArray(File file, ArrayList<Show> list) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		while (br.ready()) {
			for (int i = 0; i < list.size(); ++i) {
				String nextLine = br.readLine();
				if (nextLine.contains("^[1-9]*$^[1-9]*$^[1-9]*$^[1-9]*$" + "-")) {
					TVSeries series = parseTV(nextLine);
					tVseries.add(series);

					if (nextLine.contains("#^[1-9]*$.")) {
						Episode episode = parseEpisode(nextLine, series);
						episodes.add(episode);
					}
				} else {
					Movie movie = parseMovie(nextLine);
					movies.add(movie);
				}
			}
		}
		br.close();

	}

	/**
	 * @return this object's LinkedHashMap<String, ArrayList<Creator>> ,
	 *         containing an ArrayList of the actors.
	 */
	public LinkedHashMap<String, ArrayList<Creator>> getActorMap() {
		return actors;
	}

	/**
	 * @return this object's LinkedHashMap<String, ArrayList<Creator>> ,
	 *         containing an ArrayList of the directors.
	 */
	public LinkedHashMap<String, ArrayList<Creator>> getDirectorMap() {
		return directors;
	}

	/**
	 * @return this object's LinkedHashMap<String, ArrayList<Creator>> ,
	 *         containing an ArrayList of the producers.
	 */
	public LinkedHashMap<String, ArrayList<Creator>> getProducerMap() {
		return producers;
	}

	/**
	 * Get list of movies
	 * @return movies ArrayList<Show>
	 */
	public ArrayList<Show> getMovies() {
		return movies;
	}

	/**
	 * Get list of series
	 * @return tVseries ArrayList<Show>
	 */
	public ArrayList<Show> getTVSeries() {
		return tVseries;
	}


	/**
	 * Get list of episodes
	 * @return episodes ArrayList<Show>
	 */
	public ArrayList<Show> getEpisodes() {
		return episodes;
	}

}
