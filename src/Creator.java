
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Project #5
 * CS 2334, Section 010
 * April 22, 2016
 * <P>
 * Class for storing information about a creator
 * </P>
 */
public class Creator extends Show{

	/** SerialID that lets us implement Serializable */
	private static final long serialVersionUID = 1L;
	/** List that stores information about actors */
	public static ArrayList<Creator> actors = new ArrayList<Creator>();
	/** List that stores information about directors */
	public static ArrayList<Creator> directors = new ArrayList<Creator>();
	/** List that stores information about producers */
	public static ArrayList<Creator> producers = new ArrayList<Creator>();
	/** The name of the creator */
	private String name;
	/** The title of the work that is associated with its creator */
	private String title;
	/** The title of an episode from within a series */
	private String episodeTitle;
	/** The season number and episode number from a given series */
	private String episodeNumber;
	/** The release year */
	private String year;
	/** The type of work (TV or V) */
	private String type;
	/** Determines whether the footage was archived */
	private String archiveFootage;
	/** The name of a character */
	private String charName;
	/** Employee's paycheck */
	private String billingOrder;
	/** Determines if the creator was credited or not */
	private String credit;
	/** The role the creator had in the production */
	private String role;
	/**The job(actor, producer, director) creator do with the media*/
	private String job;
	/**Media type the creator make (MOVIE/SERIES/EPISODE)*/
	public String media;
	/**Boolean that decide the toString format*/ 
	public boolean isNormalPrint = true;
	/** Amount of movie acting credits the creator has */
	private static int mActingCredit = 0;
	/** Amount of series acting credits the creator has */
	private static int sActingCredit = 0;
	/** Amount of movie producing credits the creator has */
	private static int mProducingCredit = 0;
	/** Amount of series producing credits the creator has */
	private static int sProducingCredit = 0;
	/** Amount of movie directing credits the creator has */
	private static int mDirectingCredit = 0;
	/** Amount of series directing credits the creator has */
	private static int sDirectingCredit = 0;
	/**Just print name*/
	public boolean isOnlyName = false;

	/**
	 * Constructor for Creator: actor
	 * 
	 * @param name
	 *            The name of the creator
	 * @param title
	 *            The title of the work associated with the creator
	 * @param year
	 *            The year the work was released
	 * @param type
	 *            The type of work (TV or V)
	 * @param archiveFootage
	 *            Determines if the work was archived
	 * @param charName
	 *            The name of the character being played by an actor
	 * @param billingOrder
	 *            Employee's paycheck
	 */
	public Creator(String name, String title, String year, String episodeTitle, String episodeNumber, String type,
			String role, String archiveFootage, String credit, String charName, String billingOrder) {
		super(name, year, year);
		this.name = name;
		this.year = year;
		this.title = title;
		this.episodeTitle = episodeTitle;
		this.episodeNumber = episodeNumber;
		this.type = type;
		this.role = role;
		this.archiveFootage = archiveFootage;
		this.credit = credit;
		this.charName = charName;
		this.billingOrder = billingOrder;
		this.job = "ACTING";
		super.setShowJob("ACTING");
		if((episodeNumber.isEmpty() || episodeNumber == null)
				&& (episodeTitle.isEmpty() || episodeTitle == null)){
			media = "MOVIE";
			super.setMediaType("MOVIE");
		}
		else {
			media = "EPISODE";
			super.setMediaType("EPISODE");
		}

	}

	/**
	 * Constructor for Creator: director
	 * 
	 * @param name
	 *            The name of the creator
	 * @param title
	 *            The title of the work associated with the creator
	 * @param year
	 *            The year the work was released
	 * @param type
	 *            The type of work (TV or V)
	 * @param credit
	 *            Determines if the creator was credited or not
	 */
	public Creator(String name, String title, String year, String episodeTitle, String episodeNumber, String type,
			String credit) {
		super(name, year, year);
		this.name = name;
		this.year = year;
		this.title = title;
		this.episodeTitle = episodeTitle;
		this.episodeNumber = episodeNumber;
		this.type = type;
		this.credit = credit;
		this.job = "DIRECTING";
		super.setShowJob("DIRECTING");
		if((episodeNumber.isEmpty() || episodeNumber == null)
				&& (episodeTitle.isEmpty() || episodeTitle == null)){
			media = "MOVIE";
			super.setMediaType("MOVIE");
		}
		else {
			media = "EPISODE";
			super.setMediaType("EPISODE");
		}
	}

	/**
	 * Constructor for Creator: producer
	 * 
	 * @param name
	 *            The name of the creator
	 * @param title
	 *            The title of the work associated with the creator
	 * @param year
	 *            THe year the work was released
	 * @param type
	 *            The type of work (TV or V)
	 * @param role
	 *            The role the creator had in production
	 * @param credit
	 *            Determines if the creator was credited or not
	 */
	public Creator(String name, String title, String year, String episodeTitle, String episodeNumber, String type,
			String role, String credit) {
		super(name, year, year);
		this.name = name;
		this.year = year;
		this.title = title;
		this.type = type;
		this.episodeTitle = episodeTitle;
		this.episodeNumber = episodeNumber;
		this.role = role;
		this.credit = credit;
		this.job = "PRODUCING";
		super.setShowJob("PRODUCING");
		if((episodeNumber.isEmpty() || episodeNumber == null)
				&& (episodeTitle.isEmpty() || episodeTitle == null)){
			media = "MOVIE";
			super.setMediaType("MOVIE");
		}
		else {
			media = "EPISODE";
			super.setMediaType("EPISODE");
		}
	}

	/**
	 * Fills the actor array with actors from a file.
	 * 
	 * @param actor
	 *            Actor file being read through.
	 * @param br
	 *            BufferedReader that reads the file
	 * @return Returns an arraylist of actors to be used later.
	 * @throws IOException
	 */
	public ArrayList<Creator> fillActors(File actor) throws IOException {

		FileReader fr = new FileReader(actor);
		BufferedReader br = new BufferedReader(fr);
		// Initializing string that keeps track of the lines
		String nextLine = null;
		// Creating empty strings to be filled in later
		String name = "";
		String title = "";
		String episodeTitle = "";
		String episodeNumber = "";
		String year = "";
		String type = "";
		String role = "";
		String archiveFootage = "";
		String credit = "";
		String charName = "";
		String billingOrder = "";

		// stops reader if the file is out of bounds
		while (br.ready()) {
			// reads in a line from the file
			nextLine = br.readLine();

			// year is always constant for each type of line being parsed
			if (nextLine.indexOf(")") + 1 > -1 && nextLine.indexOf("(") > -1)
				year = nextLine.substring(nextLine.indexOf("("), nextLine.indexOf(")") + 1);

			// aquires line that has the actor's name present and is followed by
			// a tab
			if (!nextLine.isEmpty()) {
				if(!nextLine.substring(0, 3).equals("\t\t\t")){
					// if billingOrder is present
					if (nextLine.contains("<")) {
						billingOrder = nextLine.substring(nextLine.indexOf("<"), nextLine.indexOf(">") + 1);
					}

					// if charName is present
					if (nextLine.contains("[")) {
						charName = nextLine.substring(nextLine.indexOf("["), nextLine.indexOf("]") + 1);
					}

					// if episode number is only present
					if (nextLine.contains("{(#")) {
						episodeNumber = nextLine.substring(nextLine.indexOf("{"), nextLine.indexOf("}") + 1);
					}

					// if episode information is present
					if (nextLine.contains("{")) {

						// checks for "SUSPENDED"
						if (nextLine.contains("{{")) {
							episodeTitle = "SUSPENDED";
							episodeNumber = "";
						} else {
							episodeTitle = nextLine.substring(nextLine.indexOf("{") + 1, nextLine.indexOf("}"));
							if (episodeTitle.indexOf("(") > 0)
								episodeNumber = episodeTitle.substring(episodeTitle.indexOf("("),
										episodeTitle.indexOf(")") + 1);
						}
						// if episodeNumber is part of episodeTitle
						if (episodeTitle.contains("#")) {
							episodeTitle = episodeTitle.substring(0, episodeTitle.indexOf("(")).trim();
						}
					}

					// if type is present
					if (nextLine.contains("(TV)") || nextLine.contains("(V)")) {
						// gives type the corresponding value
						if (nextLine.contains("(TV)")) {
							type = "(TV)";
						}
						// gives type the corresponding value
						if (nextLine.contains("(V)")) {
							type = "(V)";
						}
					}

					// if role is present, if not role remains empty
					if (nextLine.contains("voice")) {
						role = "(voice)";
					}

					// if credit is present, if not credit remains empty
					if (nextLine.contains("rumored")) {
						credit = "(rumored)";
					}

					if (nextLine.contains("uncredited")) {
						credit = "(uncredited)";
					}

					// reads in last occurance of parentheses
					if (nextLine.contains("(as")) {
						credit = nextLine.substring(nextLine.lastIndexOf("("), nextLine.lastIndexOf(")") + 1);
					}

					// if archiveFootage is present, if not archiveFootage remains
					// empty
					if (nextLine.contains("archive footage")) {
						archiveFootage = "(archive footage)";
					}

					// assigns name a value
					name = nextLine.substring(0, nextLine.indexOf("\t")).trim();

					String[] nameArray = name.split(",");
					if (nameArray.length > 1)
						name = nameArray[1].trim() + " " + nameArray[0].trim();

					// if name has parentheses
					if (name.contains("(")) {
						title = nextLine.substring(nextLine.indexOf("\t"), nextLine.indexOf("(", nextLine.indexOf("(") + 1))
								.trim();
						// change year to appropiate value if parentheses in name is
						// present
						year = nextLine.substring(nextLine.indexOf("(", nextLine.indexOf("(") + 1),
								nextLine.indexOf(")", nextLine.indexOf(")") + 1) + 1).trim();
					} else {
						// assigns title a value
						title = nextLine.substring(nextLine.indexOf("\t"), nextLine.indexOf("(")).trim();
					}

					// checks if credit has other values
					if (credit.equals(episodeNumber) || credit.equals(year) || credit.equals(role)
							|| credit.equals(archiveFootage)) {
						credit = "";
					}

					actors.add(new Creator(name, title, year, episodeTitle, episodeNumber, type, role, archiveFootage,
							credit, charName, billingOrder));
				}
			}
			// if current line is followed by 3 tabs
			if (nextLine.contains("\t\t\t")) {

				// if billingOrder is present
				if (nextLine.contains("<")) {
					billingOrder = nextLine.substring(nextLine.indexOf("<"), nextLine.indexOf(">") + 1);
				}

				// if charName is present
				if (nextLine.contains("[")) {
					charName = nextLine.substring(nextLine.indexOf("["), nextLine.indexOf("]") + 1);
				}

				// if type is present
				if (nextLine.contains("(TV)") || nextLine.contains("(V)")) {
					// gives type the corresponding value
					if (nextLine.contains("(TV)")) {
						type = "(TV)";
					}
					// gives type the corresponding value
					if (nextLine.contains("(V)")) {
						type = "(V)";
					}
				}

				// if episode number is only present
				if (nextLine.contains("{(#")) {
					episodeNumber = nextLine.substring(nextLine.indexOf("{"), nextLine.indexOf("}") + 1);
				}

				// if episode information is present
				if (nextLine.contains("{")) {

					// checks for "SUSPENDED"
					if (nextLine.contains("{{")) {
						episodeTitle = "SUSPENDED";
						episodeNumber = "";
					} else {
						episodeTitle = nextLine.substring(nextLine.indexOf("{") + 1, nextLine.indexOf("}"));
						if (episodeTitle.indexOf("(") > 0)
							episodeNumber = episodeTitle.substring(episodeTitle.indexOf("("),
									episodeTitle.indexOf(")") + 1);
					}
					// if episodeNumber is part of episodeTitle
					if (episodeTitle.contains("#")) {
						episodeTitle = episodeTitle.substring(0, episodeTitle.indexOf("(")).trim();
					}
				}

				// if episode information is "SUSPENDED"
				if (nextLine.contains("SUSPENDED")) {
					episodeTitle = "SUSPENDED";
					episodeNumber = "";
				}

				// checks if episode information is given by YYYY-MM-DD
				if (episodeTitle.contains("-")) {
					// if this is present, then assume there is no episode
					// number and episodeTitle = episodeDate
					if (episodeTitle.indexOf("(") > 0) {
						episodeTitle = episodeTitle.substring(episodeTitle.indexOf("("), episodeTitle.indexOf(")") + 1);
						episodeNumber = "";
					}
				}

				// if role is present, if not role remains empty
				if (nextLine.contains("voice")) {
					role = "(voice)";
				}

				// if credit is present, if not credit remains empty
				if (nextLine.contains("uncredited")) {
					credit = "(uncredited)";
				}

				if (nextLine.contains("rumored")) {
					credit = "(rumored)";
				}

				// if archiveFootage is present, if not archiveFootage remains
				// empty
				if (nextLine.contains("archive footage")) {
					archiveFootage = "(archive footage)";
				}

				// reads in last occurance of parentheses
				if (nextLine.contains("(as")) {
					credit = nextLine.substring(nextLine.lastIndexOf("("), nextLine.lastIndexOf(")") + 1);
				}

				// assigns title a value
				title = nextLine.substring(nextLine.indexOf("\t"), nextLine.indexOf("(")).trim();

				// checks if credit has other values
				if (credit.equals(episodeNumber) || credit.equals(year) || credit.equals(role)
						|| credit.equals(archiveFootage)) {
					credit = "";
				}

				actors.add(new Creator(name, title, year, episodeTitle, episodeNumber, type, role, archiveFootage,
						credit, charName, billingOrder));
			}
			if (nextLine.equals("\n")) {
				nextLine = br.readLine();
			}

		}
		br.close();
		return actors;
	}

	/**
	 * Fills the director array with directors from a file.
	 * 
	 * @param director
	 *            Director file to be read through.
	 * @param br
	 *            BufferedReader that reads the file.
	 * @return Returns an ArrayList of directors to be used later.
	 * @throws IOException
	 */
	public ArrayList<Creator> fillDirectors(File director) throws IOException {
		FileReader fr = new FileReader(director);
		BufferedReader br = new BufferedReader(fr);
		String nextLine = null;
		String name = "";
		String title = "";
		String year = "";
		String episodeTitle = "";
		String episodeNumber = "";
		String type = "";
		String credit = "";

		while (br.ready()) {
			nextLine = br.readLine();

			// year is always constant for each type of line being parsed
			if (nextLine.indexOf(")") + 1 > -1 && nextLine.indexOf("(") > -1)
				year = nextLine.substring(nextLine.indexOf("("), nextLine.indexOf(")") + 1);
			if (!nextLine.isEmpty()) {
				if(!nextLine.substring(0, 3).equals("\t\t\t")){
					// if type is present
					if (nextLine.contains("(TV)") || nextLine.contains("(V)")) {
						// gives type the corresponding value
						if (nextLine.contains("(TV)")) {
							type = "(TV)";
						}
						// gives type the corresponding value
						if (nextLine.contains("(V)")) {
							type = "(V)";
						}
					}

					// if episode number is only present
					if (nextLine.contains("{(#")) {
						episodeNumber = nextLine.substring(nextLine.indexOf("{"), nextLine.indexOf("}") + 1);
					}

					// if episode information is present
					if (nextLine.contains("{")) {

						// checks for "SUSPENDED"
						if (nextLine.contains("{{")) {
							episodeTitle = "SUSPENDED";
							episodeNumber = "";
						} else {
							episodeTitle = nextLine.substring(nextLine.indexOf("{") + 1, nextLine.indexOf("}"));
							episodeNumber = episodeTitle.substring(episodeTitle.indexOf("("),
									episodeTitle.indexOf(")") + 1);
						}
						// if episodeNumber is part of episodeTitle
						if (episodeTitle.contains("#")) {
							episodeTitle = episodeTitle.substring(0, episodeTitle.indexOf("(")).trim();
						}
					}

					// if episode information is "SUSPENDED"
					if (nextLine.contains("SUSPENDED")) {
						episodeTitle = "SUSPENDED";
						episodeNumber = "";
					}

					// assigns credit a value
					credit = nextLine.substring(nextLine.lastIndexOf("("), nextLine.lastIndexOf(")") + 1);
					// checks if credit has the right information, if not then there
					// is not credit to be given
					if (credit.equals(year) || credit.equals(type)) {
						credit = "";
					}

					// assigns name a value
					name = nextLine.substring(0, nextLine.indexOf("\t")).trim();

					String[] nameArray = name.split(",");

					name = nameArray[1].trim() + " " + nameArray[0].trim();

					// if name has parentheses
					if (name.contains("(")) {
						title = nextLine.substring(nextLine.indexOf("\t"), nextLine.indexOf("(", nextLine.indexOf("(") + 1))
								.trim();
						// change year to appropiate value
						year = nextLine.substring(nextLine.indexOf("(", nextLine.indexOf("(") + 1),
								nextLine.indexOf(")", nextLine.indexOf(")") + 1) + 1).trim();
					} else {
						// assigns title a value
						title = nextLine.substring(nextLine.indexOf("\t"), nextLine.indexOf("(")).trim();
					}
					directors.add(new Creator(name, title, year, episodeTitle, episodeNumber, type, credit));

				}
			}
			if (nextLine.contains("\t\t\t")) {

				// if type is present
				if (nextLine.contains("(TV)") || nextLine.contains("(V)")) {
					// gives type the corresponding value
					if (nextLine.contains("(TV)")) {
						type = "(TV)";
					}
					// gives type the corresponding value
					if (nextLine.contains("(V)")) {
						type = "(V)";
					}
				}

				// if episode number is only present
				if (nextLine.contains("{(#")) {
					episodeNumber = nextLine.substring(nextLine.indexOf("{"), nextLine.indexOf("}") + 1);
				}

				// if episode information is present
				if (nextLine.contains("{")) {

					// checks for "SUSPENDED"
					if (nextLine.contains("{{")) {
						episodeTitle = "SUSPENDED";
						episodeNumber = "";
					} else {
						episodeTitle = nextLine.substring(nextLine.indexOf("{") + 1, nextLine.indexOf("}"));
						episodeNumber = episodeTitle.substring(episodeTitle.indexOf("("),
								episodeTitle.indexOf(")") + 1);
					}
					// if episodeNumber is part of episodeTitle
					if (episodeTitle.contains("#")) {
						episodeTitle = episodeTitle.substring(0, episodeTitle.indexOf("(")).trim();
					}
				}

				// assigns credit a value
				credit = nextLine.substring(nextLine.lastIndexOf("("), nextLine.lastIndexOf(")") + 1);
				// checks if credit has the right information, if not then there
				// is not credit to be given
				if (credit.equals(year) || credit.equals(type)) {
					credit = "";
				}

				// assigns title a value
				title = nextLine.substring(nextLine.indexOf("\t"), nextLine.indexOf("(")).trim();

				directors.add(new Creator(name, title, year, episodeTitle, episodeNumber, type, credit));
			}
			if (nextLine.equals("\n")) {
				nextLine = br.readLine();
			}
		}
		br.close();
		return directors;
	}

	/**
	 * Fills the producer array with producers from a file.
	 * 
	 * @param producer
	 *            Producer file to be read through.
	 * @param br
	 *            BufferedReader that reads the file.
	 * @return Returns an arraylist of producers to be used later.
	 * @throws IOException
	 */
	public ArrayList<Creator> fillProducers(File producer) throws IOException {
		FileReader fr = new FileReader(producer);
		BufferedReader br = new BufferedReader(fr);
		String nextLine = null;
		String name = "";
		String title = "";
		String episodeTitle = "";
		String episodeNumber = "";
		String year = "";
		String type = "";
		String role = "";
		String credit = "";

		while (br.ready()) {
			nextLine = br.readLine();

			// year is mostly constant for each type of line being parsed, some
			// exceptions
			if (nextLine.indexOf(")") + 1 > -1 && nextLine.indexOf("(") > -1)
				year = nextLine.substring(nextLine.indexOf("("), nextLine.indexOf(")") + 1);

			if (!nextLine.isEmpty()) {
				if(!nextLine.substring(0, 3).equals("\t\t\t")){
					// if type is present
					if (nextLine.contains("(TV)") || nextLine.contains("(V)")) {
						// gives type the corresponding value
						if (nextLine.contains("(TV)")) {
							type = "(TV)";
						}
						// gives type the corresponding value
						if (nextLine.contains("(V)")) {
							type = "(V)";
						}
					}

					// if episode number is only present
					if (nextLine.contains("{(#")) {
						episodeNumber = nextLine.substring(nextLine.indexOf("{"), nextLine.indexOf("}") + 1);
					}

					// if episode information is present
					if (nextLine.contains("{")) {

						// checks for "SUSPENDED"
						if (nextLine.contains("{{")) {
							episodeTitle = "SUSPENDED";
							episodeNumber = "";
						} else {
							episodeTitle = nextLine.substring(nextLine.indexOf("{") + 1, nextLine.indexOf("}"));
							episodeNumber = episodeTitle.substring(episodeTitle.indexOf("("),
									episodeTitle.indexOf(")") + 1);
						}
						// if episodeNumber is part of episodeTitle
						if (episodeTitle.contains("#")) {
							episodeTitle = episodeTitle.substring(0, episodeTitle.indexOf("(")).trim();
						}
					}

					// if episode information is "SUSPENDED"
					if (nextLine.contains("SUSPENDED")) {
						episodeTitle = "SUSPENDED";
						episodeNumber = "";
					}

					// if role is present with 4 given options
					if (nextLine.contains("(producer)") || nextLine.contains("(executive producer)")
							|| nextLine.contains("(co-producer)") || nextLine.contains("(segment producer)")) {

						// Stores producer
						if (nextLine.contains("(producer)")) {
							role = "producer";
						}

						// Stores executive producer
						if (nextLine.contains("(executive producer)")) {
							role = "executive producer";
						}

						// Stores co-producer
						if (nextLine.contains("(co-producer)")) {
							role = "co-producer";
						}

						// Stores segment producer
						if (nextLine.contains("(segment producer)")) {
							role = "segment producer";
						}
					}

					// assigns credit a value
					credit = nextLine.substring(nextLine.lastIndexOf("(") + 1, nextLine.lastIndexOf(")")).trim();
					// checks if credit has the right information, if not then there
					// is not credit to be given
					if (credit.equals(year) || credit.equals(type) || credit.equals(role)) {
						credit = "";
					}

					// assigns name a value
					name = nextLine.substring(0, nextLine.indexOf("\t")).trim();

					String[] nameArray = name.split(",");

					name = nameArray[1].trim() + " " + nameArray[0].trim();

					// if name has parentheses
					if (name.contains("(")) {
						title = nextLine.substring(nextLine.indexOf("\t"), nextLine.indexOf("(", nextLine.indexOf("(") + 1))
								.trim();
						// change year to appropiate value
						year = nextLine.substring(nextLine.indexOf("(", nextLine.indexOf("(") + 1),
								nextLine.indexOf(")", nextLine.indexOf(")") + 1) + 1).trim();
					} else {
						// assigns title a value
						title = nextLine.substring(nextLine.indexOf("\t"), nextLine.indexOf("(")).trim();
					}

					producers.add(new Creator(name, title, year, episodeTitle, episodeNumber, type, role, credit));

				}
			}
			if (nextLine.contains("\t\t\t")) {

				// if type is present
				if (nextLine.contains("(TV)") || nextLine.contains("(V)")) {
					// gives type the corresponding value
					if (nextLine.contains("(TV)")) {
						type = "(TV)";
					}
					// gives type the corresponding value
					if (nextLine.contains("(V)")) {
						type = "(V)";
					}
				}

				// if episode number is only present
				if (nextLine.contains("{(#")) {
					episodeNumber = nextLine.substring(nextLine.indexOf("{"), nextLine.indexOf("}") + 1);
				}

				// if episode information is present
				if (nextLine.contains("{")) {

					// checks for "SUSPENDED"
					if (nextLine.contains("{{")) {
						episodeTitle = "SUSPENDED";
						episodeNumber = "";
					} else {
						episodeTitle = nextLine.substring(nextLine.indexOf("{") + 1, nextLine.indexOf("}"));
						if (episodeTitle.indexOf("(") > 0)
							episodeNumber = episodeTitle.substring(episodeTitle.indexOf("("),
									episodeTitle.indexOf(")") + 1);
					}
					// if episodeNumber is part of episodeTitle
					if (episodeTitle.contains("#")) {
						episodeTitle = episodeTitle.substring(0, episodeTitle.indexOf("(")).trim();
					}
				}

				// if episode information is "SUSPENDED"
				if (nextLine.contains("SUSPENDED")) {
					episodeTitle = "SUSPENDED";
					episodeNumber = "";
				}

				// if role is present with 4 given options
				if (nextLine.contains("(producer)") || nextLine.contains("(executive producer)")
						|| nextLine.contains("(co-producer)") || nextLine.contains("(segment producer)")) {

					// Stores producer
					if (nextLine.contains("(producer)")) {
						role = "producer";
					}

					// Stores executive producer
					if (nextLine.contains("(executive producer)")) {
						role = "executive producer";
					}

					// Stores co-producer
					if (nextLine.contains("(co-producer)")) {
						role = "co-producer";
					}

					// Stores segment producer
					if (nextLine.contains("(segment producer)")) {
						role = "segment producer";
					}
				}

				// assigns credit a value
				credit = nextLine.substring(nextLine.lastIndexOf("(") + 1, nextLine.lastIndexOf(")")).trim();
				// checks if credit has the right information, if not then there
				// is not credit to be given
				if (credit.equals(year) || credit.equals(type) || credit.equals(role)) {
					credit = "";
				}

				// assigns title a value
				title = nextLine.substring(nextLine.indexOf("\t"), nextLine.indexOf("(")).trim();

				producers.add(new Creator(name, title, year, episodeTitle, episodeNumber, type, role, credit));
			}
			if (nextLine.equals("\n")) {
				nextLine = br.readLine();
			}
		}
		br.close();
		return producers;
	}

	/**
	 * Returns a string of a creator
	 */
	public String toString() {
		if(isOnlyName){
			return name; 
		}
		if(isNormalPrint){
			if(media.equals("MOVIE")){
				return media + ": " + title + " " + checkYear(year);
			}
			else{
				return media + ": " + epTitleFormate() +" " + checkYear(year);
			}
		}
		else{
			if(media.equals("MOVIE")){
				return name + "         " + job + " " + media + " " + type + ": " 
						+ title + "  " + checkYear(year);
			}
			else {
				return name + "         " + job + " " + media + type + ": " 
						+  epTitleFormate() + " " + checkYear(year);
			}
		}
	}

	/**
	 * @param episodTitle out put formate
	 * @return episode Title
	 */
	private String epTitleFormate(){
		String s = "";
		if(!title.isEmpty()) s = title;
		if(!episodeTitle.isEmpty()) {
			s = s + ": " +episodeTitle;
		}
		else {
			s = s + episodeNumber;
		}
		return s;
	}



	/**
	 * formate the year
	 * @param year
	 * @return Year formatted 
	 */
	private String checkYear(String year){
		if(year.equals("(????)")){
			return "(UNSPECIFIED)";
		}
		else {
			return year;
		}
	}



	/**
	 * @return the name of a creator
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The title of a movie
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return Year the movie was released
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @return The type of movie (TV or V)
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the archive footage of the movie
	 */
	public String getArchiveFootage() {
		return archiveFootage;
	}

	/**
	 * @return The character name of the creator
	 */
	public String getCharName() {
		return charName;
	}

	/** 
	 * @return The job creator do in making this media(Acting, Directing or Producing)
	 */
	public String getJob(){
		return job;
	}
	/**
	 * @return the type of media creator made
	 */
	public String getMedia(){
		return media;
	}
	/**
	 * set media(MOVIE/EPISODE) type creator made
	 * @param media(MOVIE/EPISODE) type creator made 
	 */
	public void setMedia(String media){
		this.media = media;
	}
	public void directorCredits(BufferedReader br) throws IOException {
		while (br.ready()) {
			String nextLine = br.readLine();
			int lastTab = nextLine.lastIndexOf("/t");
			String credit = nextLine.substring(lastTab);
			if (credit.contains("{")) {
				sDirectingCredit = sDirectingCredit + 1;
			} else {
				mDirectingCredit = mDirectingCredit + 1;
			}

		}
	}

	public void producerCredits(BufferedReader br) throws IOException {
		while (br.ready()) {
			String nextLine = br.readLine();
			int lastTab = nextLine.lastIndexOf("/t");
			String credit = nextLine.substring(lastTab);
			if (credit.contains("{")) {
				sProducingCredit = sProducingCredit + 1;
			} else {
				mProducingCredit = mProducingCredit + 1;
			}

		}
	}

	public void actorCredits(BufferedReader br) throws IOException {
		while (br.ready()) {
			String nextLine = br.readLine();
			int lastTab = nextLine.lastIndexOf("/t");
			String credit = nextLine.substring(lastTab);
			if (credit.contains("{")) {
				sActingCredit = sActingCredit + 1;
			} else {
				mActingCredit = mActingCredit + 1;
			}

		}
	}

	/**
	 * @return double for the amount of series acting credits this creator has
	 */
	public static double getSAC() {
		return (double) sActingCredit;
	}

	/**
	 * @return double for the amount of movie acting credits this creator has
	 */
	public static double getMAC() {
		return (double) mActingCredit;
	}

	/**
	 * @return double for the amount of series directing credits this creator
	 *         has
	 */
	public static double getSDC() {
		return (double) sDirectingCredit;
	}

	/**
	 * @return double for the amount of movie directing credits this creator has
	 */
	public static double getMDC() {
		return (double) mDirectingCredit;
	}

	/**
	 * @return double for the amount of series producing credits this creator
	 *         has
	 */
	public static double getSPC() {
		return (double) sProducingCredit;
	}

	/**
	 * @return double for the amount of movie producing credits this creator has
	 */
	public static double getMPC() {
		return (double) mProducingCredit;
	}

	/**
	 * @return the creator's paycheck
	 */
	public String getBillingOrder() {
		return billingOrder;
	}

	/**
	 * @return creator's credit
	 */
	public String getCredit() {
		return credit;
	}

	/**
	 * @return the role the creator played in the movie production
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @return The title of an episode
	 */
	public String getEpisodeTitle() {
		return episodeTitle;
	}

	/**
	 * @return The season number and episode number of an episode
	 */
	public String getEpisodeNumber() {
		return episodeNumber;
	}
	/**
	 * get the Actors list
	 * @return The list of Actors
	 */
	public ArrayList<Creator> getActor()
	{
		return actors;
	}
	/**
	 * get the Directors list
	 * @return The list of Directors
	 */
	public ArrayList<Creator> getDirector()
	{
		return directors;
	}
	/**
	 * get the Producers list
	 * @return The list of Producers
	 */
	public ArrayList<Creator> getProducer()
	{
		return producers;
	}
	/**
	 * Comparator for name of creator
	 */
	public static final Comparator<Creator> NAME_COMPARATOR  = new Comparator<Creator>() {
		@Override
		public int compare(Creator o1, Creator o2) {
			return o1.name.compareTo(o2.name);
		}
	};
	/**
	 * Comparator for sort creators by name first ====> their position maker the 
	 * media(actor/producer/diercting) =====> Title the media title they make
	 */
	public static final Comparator<Creator> NAME_JOB_TITLE_COMPARATOR  = new Comparator<Creator>() {
		@Override
		public int compare(Creator o1, Creator o2) {
			int i = o1.title.compareTo(o1.title);
			int j = o1.name.compareTo(o2.name);
			int k = o1.job.compareTo(o2.job);
			int result = 0;
			if(i < 0){
				result = -1;
			} else if( i > 0) {
				result = 1;
			} else {
				if(j < 0){
					result = -1;
				}else if(j > 0){
					result = 1;
				} else{
					if(k < 0){
						result = -1;
					} else if(k > 0){
						result = 1;
					} else{
						result = 0;
					}
				}
			}
			return result;
		}
	};

}
