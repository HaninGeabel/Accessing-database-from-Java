package sait.mms.application;

import java.sql.SQLException;

import sait.mms.managers.MovieManagmentSystem;

public class appDriver {

	public appDriver() {
	}

	/**
	 * Main method/Entry point
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		MovieManagmentSystem mv= new MovieManagmentSystem();
		mv.displayMenu();

	}


}
