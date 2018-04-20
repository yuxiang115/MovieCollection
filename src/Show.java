import java.io.Serializable;
import java.util.Comparator;

/**
 * Project #5
 * CS 2334, Section 010
 * April 22, 2016
 * <P>
 * Class for creating show object
 * </P>
 */
public class Show implements Serializable{

	/** SerialID that lets us implement Serializable */
	private static final long serialVersionUID = 1L;
	
	/**Name of show*/
	private String name;
	/**Year show is first released*/
	private String year;
	/**Year show is last released. Equal to year if movie*/
	private String endYear;
	/**Media type tell us if it is Movie or Episode*/
	private String mediaType = "";
	/**Job (Act, direct, produce) creator do in making this show*/
	private String jobShow = null;
	
	/**Constructor for generic show
	 * 
	 * @param name Name of show
	 * @param year Release year of show
	 * @param endYear Last year of show
	 */
	public Show(String name, String year, String endYear) {
		this.name = name;
		this.year = year;
		this.endYear = endYear;
	}

	/**Return string of show
	 * @Override
	 */
	public String toString() {
		return name + " " + year + "-" + endYear;
	}
	
	/**
	 * @return Name of show
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return Year first released
	 */
	public String getYear() {
		return year;
	}
	
	/**
	 * @return Year last released
	 */
	public String getEndYear() {
		return endYear;
	}
	/**
	 * 
	 * @return Media type indicate if this object is Movie or Series
	 */
	public String getMediaType(){
		return mediaType;
	}
	/**
	 * @param mediaType Media type indicate if this object is Movie or Series
	 */
	public void setMediaType(String mediaType){
		this.mediaType = mediaType;
	}
	/** 
	 * @return Job (Act, direct, produce) creator do in making this show
	 */
	public String getShowJob(){
		return jobShow;
	}
	/**
	 * set crator the position making this show
	 * @param Job (Act, direct, produce) creator do in making this show
	 */
	public void setShowJob(String job){
		this.jobShow = job;
	}
	public static final Comparator<Show> TITLE_COMPARATOR = new Comparator<Show>() {
		@Override
		public int compare(Show o1, Show o2) {
			return o1.name.compareTo(o2.name); 
		}
	};
	
	public static final Comparator<Show> YEAR_COMPARATOR  = new Comparator<Show>() {
		@Override
		public int compare(Show o1, Show o2) {
			return o1.year.compareTo(o2.year);
		}
	};
}
