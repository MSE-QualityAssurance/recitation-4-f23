package edu.cmu.cs.analysis.model;

public class Competitor extends Person {
	
//Attributes
	
	private Competitor next;
	private Competitor prev;
	
//Constructor
	
	/**
	 * <b>Description:</b> The constructor of the class Spectator.<br>
	 * <b>Pre:</b> No one parameter can be null.<br>
	 * <b>Post:</b> All attributes of the class are initialized.<br> 
	 * @param id The competitor's id.
	 * @param firstName The competitor's first name.
	 * @param lastName The competitor's last name.
	 * @param email The competitor's email.
	 * @param gender The competitor's gender.
	 * @param nationality The competitor's nationality.
	 * @param birthdate The competitor's birthdate.
	 */
		
	public Competitor(String id, String firstName, String lastName, String email, String gender, String nationality, String birthdate) {
			
		super(id, firstName, lastName, email, gender, nationality, birthdate);
		next = null;
		prev = null;
	}
	
//Setters
	
	/**
	 * <b>Description:</b> This method allows setting the attribute prev.<br>
	 * <b>Post:</b> The attribute prev will be replaced by the one that enters by parameter.<br>
	 * @param prev The new prev.
	 */
	
	public void setPrev(Competitor prev) {
		this.prev = prev;
	}
	
//Methods
	
	/**
	 * <b>Description:</b> This method allows adding a competitor in the doubly linked list of competitors.<br>
	 * <b>Post:</b> The competitor was added on the doubly linked list of competitors.<br>
	 * @param competitor The competitor that will be added - competitor != null.
	 */
	
	public void addCompetitor(Competitor competitor) {
		
		if(next == null) {
			
			next = competitor;
			competitor.setPrev(this);
		}
		else {
			
			next.addCompetitor(competitor);
		}
	}
	
	/**
	 * <b>Description:</b> This method allows getting the size of the doubly linked list of competitors.<br>
	 * @return The size of the doubly linked list of competitors.
	 */
	
	public int getSize() {
		
		int size = 0;
		
		if(next == null) {
			
			size = 1;
		}
		else {
			
			size = 1 + next.getSize();
		}
		
		return size;
	}
	
	/**
	 * <b>Description:</b> This method allows getting a competitor of the doubly linked list of competitors.<br>
	 * @param id The competitor's id.
	 * @return The competitor if it could be found, otherwise return null.
	 */
	
	public Competitor getCompetitorById(String id) {
		
		Competitor competitor = null;
		
		if(id.compareTo(this.getId())== 0){
			
			competitor = this;
		}
		else {
			
			if(next != null) {
				
				competitor = next.getCompetitorById(id);
			}
		}
		
		return competitor;
	}
	
	/**
	 * <b>Description:</b> This method converting the competitor's attributes in a String.<br>
	 * @return A String with the competitor's attributes.
	 */
	
	public String showCompetitor() {
		
		String msg = "| ID: " + getId() + 
				", FIRST NAME: " + getFirstName() + 
				", LAST NAME: " + getLastName() + 
				", EMAIL: " + getEmail() + 
				", GENDER: " + getGender() + 
				", COUNTRY: " + getNationality() + 
				", BIRTHDATE: " + getBirthdate() + " | -------> ";
		
		return msg;
	}
	
	/**
	 * <b>Description:</b> This method allows showing all the competitors of a country.<br>
	 * @param country The country.
	 * @return A message with all the competitor of that country.
	 */
	
	public String showCompetitorsByCountry(String country) {
		
		String msg = "";
		StringBuilder msg2 = new StringBuilder();
		
		if(country.equals(getNationality())) {
			
			msg2.append(showCompetitor());
		}
		if(next != null) {
			
			msg2.append(next.showCompetitorsByCountry(country));
		}
		
		msg = msg2.toString();
		
		return msg;
	}

	public Competitor getNext() {
		return next;
	}

	public Competitor clone() {
		return this;
	}
}
