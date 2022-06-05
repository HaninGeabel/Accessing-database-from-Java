package sait.mms.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.Scanner;

import sait.mms.contracts.DatabaseDriver;
import sait.mms.drivers.MariaDBDriver;
import sait.mms.problemdomain.Movie;

/**
 * @author Deborah Odoh
 * @author Hanin Geabel
 * @author Henry Shi
 *
 */
public class MovieManagmentSystem {
	private DatabaseDriver db;
	Scanner in = new Scanner(System.in);

	/**
	 * Call to establish a connection to database
	 * Returns an sql exception if an exception occurs
	 * @throws SQLException
	 */
	public MovieManagmentSystem() throws SQLException {
		this.db = new MariaDBDriver();
		this.db.connect();
	}

	/**
	 * Displays the menu for users
	 * Users can choose between options 1 to 5
	 */
	public void displayMenu(){
		try {
		int option = 7;		
		while (option != 0) {

			System.out.println("\nJim's Movie Manager");
			System.out.printf("%-5d %s\n", 1, "Add New Movie");
			System.out.printf("%-5d %s\n", 2, "Print movies released in year");
			System.out.printf("%-5d %s\n", 3, "Print random list of movies");
			System.out.printf("%-5d %s\n", 4, "Delete a movie");
			System.out.printf("%-5d %s\n\n", 5, "Exit");
			System.out.print("Enter an option: ");
			option = in.nextInt();
			switch (option) {
			case 1:
				addMovie();
				break;
			case 2:
				printMoviesInYear();
				break;
			case 3:
				printRandomMovies();
				break;
			case 4:
				deleteMovie();
				break;
			case 5:
				System.out.println("\nGoodbye!");
				break;
			default:
				System.out.println("\nPlease try again and enter one of the options. ");
			}
		}
		}catch(Exception exception) {
			System.out.println("you entered an invalid input. Duration must be number and Year is a number of 4 digits ");

		}
		
	}

	

	/**
	 * Inserts a movie to the movies table
	 * Users will enter the movie title, duration, and year
	 * The id is automatically set to the next available id 
	 */
	public void addMovie()  {
		Scanner in = new Scanner(System.in);
		
			System.out.print("\nEnter movie title: ");
			String title = in.nextLine();
			System.out.print("Enter duration: ");
			int duration = in.nextInt();
			System.out.print("Enter year: ");
			int year1 = in.nextInt();
			
			String query = "insert into Movies (duration, title,year) values (" + duration + ",'" + title + "', "
					+ year1 + " )";
			try {
			db.update(query);
			System.out.println("Added movie to database.");

		} catch (SQLException e) {
			System.out.println("\nCheck your SQL statment\n");
		}

	}

	/**
	 * Displays all the movies from the movies table based on the year entered by the user 
	 * Displays the total duration of movies in that year
	 * @throws SQLException
	 */
	public void printMoviesInYear() throws SQLException{

		System.out.print("\nEnter in year: ");
		int year = in.nextInt();
		int dur = 0;
		ResultSet result = null;

		String queryForYear = "SELECT * FROM `cprg251`.`movies` WHERE year LIKE '" + year + "';";
		try {
			result = db.get(queryForYear);
			System.out.print("\nMovie List\n");
			System.out.printf("%s %7s\t %s\n", "Duration", "Year", "Title");
			while (result.next()) {
				System.out.printf("%-5d %10d\t %s\n", result.getInt("duration"),
						 result.getInt("year"), result.getString("title"));
				dur += result.getInt("duration");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		result.close();
		System.out.println("\nTotal duration: "+ dur + " minutes");

	}

	/**
	 * Displays a random number of movies based on the number specified by the user
	 * Displays the total duration of all the movies displayed
	 * @throws SQLException
	 */
	public void printRandomMovies() throws SQLException {

		System.out.print("\nEnter number of movies: ");
		int number=in.nextInt();
		int dur = 0;
		ResultSet result=null;

		String queryForRandom = "SELECT * FROM cprg251.movies ORDER BY  RAND() LIMIT " + number +";";

		try {
			result = db.get(queryForRandom);
			System.out.println("Movie List");
			System.out.printf("%s %7s\t %s\n", "Duration", "Year", "Title");
			while (result.next()) {
				System.out.printf("%-5d %10d\t %s\n", result.getInt("duration"),
						 result.getInt("year"), result.getString("title"));
				dur += result.getInt("duration");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\nTotal duration: "+ dur + " minutes");
		result.close();
	}

	/**
	 * Deletes a movie from the movies table based on the id of the movie specified by the user
	 */
	public void deleteMovie() {
		int movieId;
		try {
			System.out.print("\nEnter the movie ID that you want to delete: ");
			movieId = in.nextInt();
			String query = "delete from cprg251.Movies where id = '" + movieId + "';";
			db.update(query);
			System.out.println("\nMovie "+ movieId+ " is deleted.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
