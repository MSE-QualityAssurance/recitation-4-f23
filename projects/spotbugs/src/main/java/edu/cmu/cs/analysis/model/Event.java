package edu.cmu.cs.analysis.model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
* <b>Description:</b> The class Event in the package model.<br>
* @author Johan Giraldo.
*/

public class Event {
	
//Attributes
	
	private String path;
	private Spectator root;
	private Competitor first;
	
//Constructor
	
	/**
	 * <b>Description:</b> The constructor of the class Spectator.<br>
	 * <b>Post:</b> All attributes of the class are initialized.<br> 
	 * @param path The path of the file that have the information of the event.
	 * @throws InvalidPathException If the file doesn't exist or couldn't be found.
	 */
	
	public Event(String path) throws InvalidPathException {
		
		File file = new File(path);
		
		if(!file.exists()) {
			
			throw new InvalidPathException();
		}
		
		this.path = path;
		root = null;
		first = null;
	}
	
//Getters
	
	/**
	 * <b>Description:</b> This method allows returning the attribute path.<br>
	 * @return The attribute path.
	 */
	
	public String getPath() {
		return path;
	}
	
	/**
	 * <b>Description:</b> This method allows returning the attribute root.<br>
	 * @return The attribute root.
	 */
	
	public Spectator getRoot() {
		return root;
	}
	
	/**
	 * <b>Description:</b> This method allows returning the attribute first.<br>
	 * @return The attribute first.
	 */
	
	public Competitor getFirst() {
		return first;
	}
	
//Setters
	
	/**
	 * <b>Description:</b> This method allows setting the attribute root.<br>
	 * <b>Post:</b> The attribute root will be replaced by the one that enters by parameter.<br>
	 */

	public void setRoot(Spectator root) {
		this.root = root;
	}
	
	/**
	 * <b>Description:</b> This method allows setting the attribute first.<br>
	 * <b>Post:</b> The attribute first will be replaced by the one that enters by parameter.<br>
	 * @param first The new first.
	 */

	public void setFirst(Competitor first) {
		this.first = first;
	}
	
//Methods
	
	/**
	 * <b>Description:</b> This method allows loading the spectator and competitors form a text file.<br>
	 * <b>Post:</b> The spectators and competitor was loaded.<br>
	 * <b>Pre:</b> The file must exist.<br>
	 */
	
	public String loadData() {
		
		FileReader file;
		BufferedReader reader;
		String[] attributes;
		String line;
		String msg = "";
		
		try{
			
			file = new FileReader(path);
			reader = new BufferedReader(file);
			attributes = new String[7];
			
			while((line = reader.readLine()) != null){
				
					attributes = line.split(",");
					Spectator spectator = new Spectator(attributes[0], attributes[1], attributes[2], attributes[3], attributes[4], attributes[5], attributes[6]);
					
					try {
						
						addSpectator(spectator);
					}
					catch(InvalidIdException e) {
						
					}
			}
			
			reader.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		return msg;
	}
	
	/**
	 * <b>Description:</b> This method allows adding a spectator in the BST of spectators.<br>
	 * <b>Post:</b> The spectator was added on the BST of spectators.<br>
	 * @param spectator The spectator that will be added - spectator != null.
	 * @throws InvalidIdException If spectator has the same id that other spectator that already is in the BST.
	 */
	
	public void addSpectator(Spectator spectator) throws InvalidIdException {
		
		if(root == null) {
			
			root = spectator;
		}
		else {
			
			root.addSpectator(spectator);
		}
	}
	
	/**
	 * <b>Description:</b> This method allows adding a competitor in the doubly linked list of competitors.<br>
	 * <b>Post:</b> The competitor was added on the doubly linked list of competitors.<br>
	 * @param competitor The competitor that will be added - competitor != null.
	 */
	
	public void addCompetitor(Competitor competitor) {
		
		if(first == null) {
			
			first = competitor;
		}
		else {
			
			first.addCompetitor(competitor);
		}
	}
	
	/**
	 * <b>Description:</b> This method allows getting a spectator of the BST of spectators by the id.<br> 
	 * @param id The spectator's id - spectator != null.
	 * @return The spectator if it could be found or null if it couldn't be found.
	 */
	
	public Spectator getSpectatorById(String id) {
		
		Spectator spectator = null;
		
		if(root != null) {
			
			spectator = new Spectator(id, "", "", "", "", "", "");
			spectator = root.getSpectatorById(spectator);
			
		}
		
		return spectator;
	}
	
	public Competitor getCharacter(int index) {
		
		Competitor character = getFirst();
		int counter = 0;
		
		while(counter < index) {
			
			character = character.getNext();
			counter++;
		}
		
		return character;
	}
	
	public int getSizeIterative() {
		
		int size = 0;
		Competitor tmp = getFirst();
		
		if(tmp != null) {
			
			size++;
			
			while((tmp = tmp.getNext()) != null) {
				
				size++;
			}
		}
		
		return size;
	}
	
	public Competitor getCompetitorByIdIterative(String id) {
		
		Competitor character = null;
		boolean running = true;
		
		for(int i = 0; i < getSizeIterative() && running; i++) {
			
			if(getCharacter(i).getId().equals(id)) {
				
				character = getCharacter(i);
				running = false;
			}
		}
		
		return character;
	}
	
	public String showCompetitorsByCountryIterative(String country) {
		
		String msg;
		StringBuilder msg2 = new StringBuilder();
		
		for(int i = 0; i < getSizeIterative(); i++) {
			
			if(getCharacter(i).getNationality().equals(country)) {
				
				msg2.append(getCharacter(i).showCompetitor());
			}
		}
		
		msg = msg2.toString();
		return msg;
	}
	
	/**
	 * <b>Description:</b> This method allows getting a random spectator of the BST of spectators.<br>
	 * @return a random spectator of the BST of spectators, if the BST is empty, return null.
	 */
	
	public Spectator selectRandomSpectator() {
		
		Spectator spectator = null;
		
		if(root != null) {
			
			spectator = root.selectRandomSpectator();
		}
		
		return spectator;
	}
	
	/**
	 * <b>Description:</b> This method allows getting the spectator's father.<br> 
	 * @param spectator The spectator that do you want to find the father - spectator != null.
	 * @return The spectator's father if it could be found, null if not.
	 */
	
	public Spectator getFather(Spectator spectator) {
		
		Spectator father = null;
		
		if(root != null) {
			
			father = root.getFather(spectator, null);
		}
		
		return father;
	}
	
	/**
	 * <b>Description:</b> This method allows getting a the BST of spectators size.<br>
	 * @return the BST of spectators size.
	 */
	
	public int getSizeBST() {
		
		int size = 0;
		
		if(root != null) {
			
			size = root.getSizeBST(root);
		}
		
		return size;
	}
	
	/**
	 * <b>Description:</b> This method allows deleting a spectator of the BST of spectators.<br>
	 * <b>Post:</b> The spectator was deleted of the BST of spectators.<br>
	 * @param spectator The spectator that will be deleted - spectator != null.
	 */
	
	public void deleteSpectator(Spectator spectator) {
		
		Spectator tmp;
		
		if(spectator == root) {
			
			tmp = spectator.deleteSpectator();
			
			if(tmp == root) {
				
				setRoot(null);
				
			}
			else {
				
				setRoot(tmp);
			}
		}
		else {
			
			tmp = spectator.deleteSpectator();
			Spectator father = getFather(spectator);
			
			if(spectator == father.getLeftChild()) {
				
				if(tmp == spectator) {
					
					father.setLeftChild(null);
				}
				else {
					
					father.setLeftChild(tmp);
				}
			}
			else {
				
				if(tmp == spectator) {
					
					father.setRightChild(null);
				}
				else {
					
					father.setRightChild(tmp);
				}
			}
		}
	}
	
	/**
	 * <b>Description:</b> This method allows getting the size of the doubly linked list of competitors.<br>
	 * @return The size of the doubly linked list of competitors.
	 */
	
	public int getSize() {
		
		int size = 0;
		
		if(first != null) {
			
			size = first.getSize();
		}
		
		return size;
	}
	
	/**
	 * <b>Description:</b> This method allows adding randomly in the doubly linked list of competitors the 50% of the spectators in the BST of spectators as competitors.<br>
	 * <b>Post:</b> The competitors was added in the doubly linked list of competitors.<br>
	 */
	
	public void addCompetitors() {
		
		int sizeBST = (getSizeBST() / 2);
		int counter = 0;
		
		while(counter < sizeBST) {
			
			Spectator tmp = selectRandomSpectator();
//			deleteSpectator(tmp);
			addCompetitor(new Competitor(tmp.getId(), tmp.getFirstName(), tmp.getLastName(), tmp.getEmail(), tmp.getGender(), tmp.getNationality(), tmp.getBirthdate()));
			counter++;
		}
	}
	
	/**
	 * <b>Description:</b> This method allows showing a spectator and the time that took found it.<br>
	 * @param id The spectator's id.
	 * @return The spectator's attributes and the time that took found it.
	 */
	
	public String searchSpectator(String id) {
		
		String msg = "";
		long t1, t2, delta;
		
		t1 = System.nanoTime();
		
		Spectator spectator = getSpectatorById(id);
		
		t2 = System.nanoTime();
		
		delta = t2 - t1;
		
		if(spectator != null) {
			
			msg = spectator.toString() + "\n";
			msg += "The search took: " + delta + "ns";
		}else {
			
			msg = "The spectator couldn't be found.\n\nThe search took: " + delta + "ns";
		}
		
		return msg;
	}
	
	/**
	 * <b>Description:</b> This method allows showing a competitor and the time that took found it.<br>
	 * @param id The competitor's id.
	 * @return The competito's attributes and the time that took found it.
	 */
	
	public String searchCompetitor(String id) {
		
		String msg = "";
		long t1, t2, delta;
		
		t1 = System.nanoTime();
		
		Competitor competitor = getCompetitorByIdIterative(id);
		
		t2 = System.nanoTime();
		
		delta = t2 - t1;
		
		if(competitor != null) {
			
			msg = competitor.toString() + "\n";
			msg += "The search took: " + delta + "ns";
		}else {
			
			msg = "The competitor couldn't be found.\n\nThe search took: " + delta + "ns";
		}
		
		return msg;
	}
	
	/**
	 * <b>Description:</b> This method allows getting a competitor of the doubly linked list of competitors.<br>
	 * @param id The competitor's id.
	 * @return The competitor if it could be found, otherwise return null.
	 */
	
	public Competitor getCompetitorById(String id) {
		
		Competitor competitor = null;
		
		if(first != null) {
			
			competitor = first.getCompetitorById(id);
		}
		
		return competitor;
	}
	
	/**
	 * <b>Description:</b> This method allows showing all the competitors of a country.<br>
	 * @param country The country.
	 * @return A message with all the competitor of that country.
	 */
	
	public String showCompetitorsByCountry(String country) {
		
		String msg = "";
		
		if(first != null) {
			
			msg = first.showCompetitorsByCountry(country);
		}
		
		return msg;
	}
	
	/**
	 * <b>Description:</b> This method allows returning an ArrayList with all the spectator of the country.<br>
	 * @param country The spectator's country.
	 * @return An ArrayList with all the spectator of that country.
	 */
	
	public ArrayList<Spectator> createBSTByCountry(String country) {
		
		ArrayList<Spectator> spectators = new ArrayList<Spectator>();
		
		if(root != null) {
			
			spectators = root.createBSTByCountry(country);
		}
		
		return spectators;
	}
	
	/**
	 * <b>Description:</b> This method allows creating a new BST with all the spectators of that country.<br>
	 * @param country The spectator's country.
	 * @return The root of the new BST of spectator of that country.
	 */
	
	public Spectator getSpectatorsByCountry(String country) {
		
		ArrayList<Spectator> spectators = createBSTByCountry(country);
		Spectator first = null;
		
		if(!spectators.isEmpty()) {
			
			first = spectators.get(0);
			
			for(Spectator s : spectators) {
				
				first.reAddSpectator(s);
			}
		}
		
		return first;
	}
	
	/**
	 * <b>Description:</b> This method allows showing the spectators of the BST of spectators.<br>
	 * @param country The spectato's country.
	 * @return A message with all the spectators of that country.
	 */
	
	public String showSpectatorsByCountry(String country) {
		
		Spectator root = getSpectatorsByCountry(country);
		
		String msg = "";
		
		if(root != null) {
			
			msg = root.showSpectators(0);
		}
		
		return msg;
	}
}
