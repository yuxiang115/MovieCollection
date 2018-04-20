import java.io.Serializable;

/**
 * Project #5
 * CS 2334, Section 010
 * April 22, 2016
 * <P>
 * Individual episode within a TV series
 * </P>
 */
public class Episode extends Show implements Serializable{
	
	/** SerialID that lets us implement Serializable */
	private static final long serialVersionUID = 1L;
	
	/**Number of episode within series*/
	public String episodeNumber;
	/**Series that this episode is in*/
	public TVSeries series;
	
	/**Constructor for individual episode
	 * 
	 * @param name Name of episode. null if episode has no name.
	 * @param year Year released
	 * @param episodeNumber Number within series
	 */
	public Episode(String name, String year, String episodeNumber, TVSeries series) {
		super(name, year, year);
		this.episodeNumber = episodeNumber;
		this.series = series;
		super.setMediaType("EPISODE");
	}
	/**
	 * episodeNumber Number within series
	 * @return
	 */
	public String getEpisodeNum(){
		return episodeNumber;
	}
	
	/**
	 * @return Series that this episode is in
	 */
	public TVSeries getSeries(){
		return series; 
	}
	/**
	 * formate the year
	 * @param year
	 * @return Year formatted 
	 */
	private String checkYear(String year){
		if(year.equals("????")){
			return "UNSPECIFIED";
		}
		else return year;
	}
	
	/**
	 * Returns a string representing the episode
	 */
	public String toString() {
		if (getName().isEmpty()) {
			return "EPISODE: " + series.getName() + ": " + episodeNumber + " (" + checkYear(getYear()) + ")";
		}
		return "EPISODE: " + series.getName() + ": " + getName() + " (" + checkYear(getYear()) + ")";
	}
}
