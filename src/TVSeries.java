import java.io.Serializable;
import java.util.ArrayList;

/**
 * Project #5
 * CS 2334, Section 010
 * April 22, 2016
 * <P>
 * lass for storing information about TVShows
 * </P>
 */
public class TVSeries extends Show implements Serializable{
	
	/** SerialID that lets us implement Serializable */
	private static final long serialVersionUID = 1L;

	/**ArrayList of episodes within a series*/
	ArrayList<Episode> episodes = new ArrayList<Episode>();
	
	/**Constructor for TV Series
	 * 
	 * @param name Name of series
	 * @param year Year series was first produced
	 * @param endYear Year series was last produced
	 */
	public TVSeries(String name, String year, String endYear) {
		super(name, year, endYear);
		super.setMediaType("SERIES");
	}
	
	/**Constructor for TV Series
	 * 
	 * @param name Name of series
	 * @param year Year series was first produced
	 * @param endYear Year series was last produced
	 * @param episodes Individual episodes within a series
	 */
	public TVSeries(String name, String year, String endYear, ArrayList<Episode> episodes) {
		super(name, year, endYear);
		this.episodes = episodes;
		super.setMediaType("SERIES");
	}

	/**
	 * Adds episode to the series
	 * 
	 * @param episode Episode object to add
	 */
	public void addEpisode(Episode episode) {
		episodes.add(episode);
	}
	/**
	 * formated the year
	 * Return year formated
	 */
	private String checkYear(String year){
		if(year.equals("????")){
			return "UNSPECIFIED";
		}
		else return year;
	}
	/**
	 * print object to string
	 */
	public String toString() {
		return "SERIES: " + getName() + " (" + checkYear(getYear()) + "-" + checkYear(getEndYear()) + ")";
	}
	
}
