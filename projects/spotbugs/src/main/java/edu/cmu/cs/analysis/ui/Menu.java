package edu.cmu.cs.analysis.ui;
import java.util.InputMismatchException;
import java.util.Scanner;
import edu.cmu.cs.analysis.model.Event;
import edu.cmu.cs.analysis.model.InvalidPathException;

/**
* <b>Description:</b> The class Menu in the package ui.<br>
* @author Johan Giraldo.
*/

public class Menu {
	
//Attributes
	
	private Scanner scanner;
	private Event event;
	
//Constructor
	
	public Menu() {
		
		scanner = new Scanner(System.in);
	}
	
//Methods
	
	public void initialMenu() {
		
		loadFileMenu();
		systemMenu();
	}
	
	public void systemMenu() {
		
		boolean running = true;
		int choice = 0;
		
		while(running) {
			
			choice = systemOptionMenu();
			
			switch(choice) {
			
			case 1:
				
				loadFileMenu();
				
				break;
				
			case 2:
				
				searchSpectatorMenu();				
				
				break;
				
			case 3:
				
				searchCompetitorMenu();
				
				break;
				
			case 4:
				
				showSpectatorsByCountryMenu();
				
				break;
				
			case 5:
				
				showCompetitorsByCountryMenu();
				
				break;
	
			case 6:
				
				running = false;
			
			}
		}
	}
	
	public int systemOptionMenu(){

		boolean running = true;
		int choice = 0;

		while(running){

			System.out.println("1. Load new file");
			System.out.println("2. Search spectator");
			System.out.println("3. Search competitor");
			System.out.println("4. Show spectators by country");
			System.out.println("5. Show competitors by country");
			System.out.println("6. Exit");
						
			try{

				choice = scanner.nextInt();
				scanner.nextLine();
			}
			catch(InputMismatchException e){

				scanner.next();
			}

			if(choice > 0 && choice < 7){

				running = false;
			}
			else{
				
				System.out.println("Please enter a correct value\n");
			}
		}

		return choice;
	}
	
	public void loadFileMenu() {
		
		String path, error;
		
		try {
			
			event = new Event("projects/spotbugs/src/main/java/edu/cmu/cs/analysis/data/data.txt");
			error = event.loadData();
			
			if(!error.equals("")) {
				
				System.out.println("The following IDs are duplicated, please change this IDs: \n" + error);
			}
			event.addCompetitors();
			System.out.println("The file was loaded succesfully\n");
		}
		catch(InvalidPathException e) {
			System.out.println("The file doesn't exist or couldn't be found, please try again\n");
		}
	}
	
	public void searchSpectatorMenu() {

		String id;
		
		System.out.println("Please enter the spectator's id");
		id = scanner.nextLine();
		
		System.out.println(event.searchSpectator(id) + "\n");
	}
	
	public void searchCompetitorMenu() {

		String id;
		
		System.out.println("Please enter the competitor's id");
		id = scanner.nextLine();
		
		System.out.println(event.searchCompetitor(id) + "\n");
	}
	
	public void showCompetitorsByCountryMenu() {

		String country;
		
		System.out.println("Please enter the country");
		country = scanner.nextLine();
		
		System.out.println(event.showCompetitorsByCountryIterative(country) + "\n");
	}
	
	public void showSpectatorsByCountryMenu() {

		String country;
		
		System.out.println("Please enter the country");
		country = scanner.nextLine();
		
		System.out.println(event.showSpectatorsByCountry(country) + "\n");
	}


}
