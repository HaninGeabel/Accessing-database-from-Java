package sait.mms.problemdomain;



/**
 * @author Deborah Odoh
 * @author Hanin Geabel
 * @author Henry Shi
 *
 */
public class Movie {
	
	/**
	 * instance variables
	 */
	private int id;
	private int duration;
	private String title;
	private int year;
	
	
	/**
	 * default constructor
	 */
	public Movie() {		
	}
	
	
	/**
	 * This constructor creates a movie object based on the id, duration, title, and year
	 * @param id
	 * @param duration
	 * @param title
	 * @param year
	 */
	public Movie(int id,int duration,String title,int year ) {
		super();
		this.id = id;
		this.duration = duration;
		this.title = title;
		this.year = year;
	}

	/**
	 * returns the movie id
	 * @return
	 */
	public int getId() {
		return id;
	}


	/**
	 * sets the movie id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	
	/**
	 * returns the movie duration
	 * @return
	 */
	public int getDuration() {
		return duration;
	}


	/**
	 * sets the movie duration
	 * @param duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}


	/**
	 * returns the movie title
	 * @return
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * sets the movie title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * returns the year the movie was released
	 * @return
	 */
	public int getYear() {
		return year;
	}


	/**
	 * sets the year the movie was released
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}


	@Override
	public String toString() {
		return "Movie [id=" + id + ", duration=" + duration + ", title=" + title + ", year=" + year + "]";
	}

}
