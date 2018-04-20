import java.io.Serializable;

/**
 * Project #5
 * CS 2334, Section 010
 * April 22, 2016
 * <P>
 * Class for create a movie object
 * </P>
 */
public class Movie extends Show implements Serializable{
	
	/** SerialID that lets us implement Serializable */
	private static final long serialVersionUID = 1L;
	
	/**List to store any extra info about the movie*/
	
	private String name;
	private String year;
	public String roman;
	public String venue;
	/**Constructor for movie
	 * 
	 * @param name Name of movie
	 * @param year Year movie was released
	 * @param roman to recognize the movie
	 * @param venue indicate where the movie played
	 */
	public Movie(String name,String year, String roman, String venue) {
		super(name, year, year);
		this.name = name;
		this.year = year;
		this.roman = roman;
		this.venue = venue;
		super.setMediaType("MOVIE");
	}
	/**Constructor for movie
	 * 
	 * @param name Name of movie
	 * @param year Year movie was released
	 * @param roman to recognize the movie
	 */
	
	public Movie(String name,String year, String roman) {
		super(name, year, year);
		this.name = name;
		this.year = year;
		this.roman = roman;
		this.venue = "";
		super.setMediaType("MOVIE");
	}
	/**Return string version of this movie
	 *@param Ronman number to reconize the movie
	 */
	public String isRoMan(String roman){
		if(roman.isEmpty()){
			return "" ;
		}
		else{
			return "/" + roman;
		}
	}
	/**
	 * 
	 * @param venue Venue tells us where the movie is played
	 * @return String for toString
	 */
	private String isVenue(String venue){
		if(venue.isEmpty()){
			return "" ;
		}
		else{
			return " " + venue;
		}
	}
	/**
	 * 
	 * @return Venue of the movie like(V)/(TV)
	 */
	public String getVenue(){
		return venue;
	}
	/**
	 * @return Roman number to regconize movie
	 */
	public String getRonNum(){
		return roman;
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
	
	public String toString(){
		
		return super.getMediaType() + isVenue(venue) + ": " + name + " " + "(" + checkYear(year)  + isRoMan(roman) + ")";
	}
}
