package edu.cmu.cs.analysis.model;

import java.util.ArrayList;

/**
* <b>Description:</b> The class Spectator in the package model.<br>
* @author Johan Giraldo.
*/

public class Spectator extends Person implements Comparable <Spectator> {
	
//Attributes
	
	private Spectator leftChild;
	private Spectator rightChild;
	
//Constructor
	
	/**
	 * <b>Description:</b> The constructor of the class Spectator.<br>
	 * <b>Pre:</b> No one parameter can be null.<br>
	 * <b>Post:</b> All attributes of the class are initialized.<br> 
	 * @param id The spectator's id.
	 * @param firstName The spectator's first name.
	 * @param lastName The spectator's last name.
	 * @param email The spectator's email.
	 * @param gender The spectator's gender.
	 * @param nationality The spectator's nationality.
	 * @param birthdate The spectator's birthdate.
	 */
	
	public Spectator(String id, String firstName, String lastName, String email, String gender, String nationality, String birthdate) {
		
		super(id, firstName, lastName, email, gender, nationality, birthdate);
		leftChild = null;
		rightChild = null;
	}
	
//Getters
	
	/**
	 * <b>Description:</b> This method allows returning the attribute leftChild.<br>
	 * @return The attribute leftChild.
	 */

	public Spectator getLeftChild() {
		return leftChild;
	}
	
	/**
	 * <b>Description:</b> This method allows returning the attribute rightChild.<br>
	 * @return The attribute rightChild.
	 */

	public Spectator getRightChild() {
		return rightChild;
	}
	
//Setters
	
	/**
	 * <b>Description:</b> This method allows setting the attribute leftChild.<br>
	 * <b>Post:</b> The attribute leftChild will be replaced by the one that enters by parameter.<br>
	 * @param leftChild The new leftChild.
	 */
	
	public void setLeftChild(Spectator leftChild) {
		this.leftChild = leftChild;
	}
	
	/**
	 * <b>Description:</b> This method allows setting the attribute rightChild.<br>
	 * <b>Post:</b> The attribute rightChild will be replaced by the one that enters by parameter.<br>
	 * @param rightChild The new rightChild.
	 */
	
	public void setRightChild(Spectator rightChild) {
		this.rightChild = rightChild;
	}
	
//Methods
	
	/**
	 * <b>Description:</b> This method allows comparing a spectator with other spectator by the id.<br>
	 * @param spectator The spectator with which it compares - spectator != null. 
	 * @return 0 if the IDs are equals, 1  if the spectator's id is major than the spectator's id which it compares, -1 if the spectator's id is minor than the spectator's id which it compares.
	 */

	@Override
	public int compareTo(Spectator spectator) {
		
		int id = Integer.parseInt(getId());
		int id2 = Integer.parseInt(spectator.getId());
		int result = 0;
		
		if(id > id2) {
			
			result = 1;
		}
		else if(id < id2) {
			
			result = -1;
		}
		
		return result;
	}
	
	/**
	 * <b>Description:</b> This method allows adding a spectator in the BST of spectators.<br>
	 * <b>Post:</b> The spectator was added on the BST of spectators.<br>
	 * @param spectator The spectator that will be added - spectator != null.
	 * @throws InvalidIdException If spectator has the same id that other spectator that already is in the BST.
	 */
	
	public void addSpectator(Spectator spectator) throws InvalidIdException {
		
		if(spectator.compareTo(this) == 1) {
			
			if(rightChild == null) {
				
				rightChild = spectator;
			}
			else {
				
				rightChild.addSpectator(spectator);
			}
		}
		else if(spectator.compareTo(this) == -1) {
			
			if(leftChild == null) { 
				
				leftChild = spectator;
			}
			else {
				
				leftChild.addSpectator(spectator);
			}
		}
		else {
			
			throw new InvalidIdException(spectator.getId());
		}
	}
	
	/**
	 * <b>Description:</b> This method allows getting a spectator of the BST of spectators.<br> 
	 * @param spectator The spectator with the id - spectator != null.
	 * @return The spectator if it could be found, null if not.
	 */
	
	public Spectator getSpectatorById(Spectator spectator) {
		
		Spectator spectator1 = null;
		
		if(spectator.compareTo(this) == 0) {
			
			spectator1 = this;
		}
		else if(spectator.compareTo(this) == 1) {
			
			if(rightChild != null) {
				
				spectator1 = rightChild.getSpectatorById(spectator);
			}
		}
		else if(spectator.compareTo(this) == -1) {
			
			if(leftChild != null) {
				
				spectator1 = leftChild.getSpectatorById(spectator);
			}
		}
		
		return spectator1;
	}
	
	/**
	 * <b>Description:</b> This method allows getting the spectator's father.<br> 
	 * @param spectator The spectator that do you want to find the father - spectator != null.
	 * @param root The spectator's father, initially should be null.
	 * @return The spectator's father if it could be found, null if not.
	 */
	
	public Spectator getFather(Spectator spectator, Spectator root) {
		
		Spectator father = null;
		
		if(spectator.compareTo(this) == 0) {
			
			father = root;
		}
		else if(spectator.compareTo(this) == 1) {
			
			if(rightChild != null) {
				
				father = rightChild.getFather(spectator, this);
			}
		}
		else if(spectator.compareTo(this) == -1) {
			
			if(leftChild != null) {
				
				father = leftChild.getFather(spectator, this);
			}
		}
		
		return father;
	}
	
	/**
	 * <b>Description:</b> This method allows getting a random spectator of the BST of spectators.<br>
	 * @return a random spectator of the BST of spectators.
	 */
	
	public Spectator selectRandomSpectator() {
		
		Spectator spectator;
		
		if((int)(Math.random() * 100) <= 10) {
			
			spectator = this;
		}
		else if((int)(Math.random() * 100) >= 50) {
			
			if(leftChild != null) {
				
				spectator = leftChild.selectRandomSpectator();
			}
			else {
				
				spectator = this;
			}
		}
		else {
			
			if(rightChild != null) {
				
				spectator = rightChild.selectRandomSpectator();
			}
			else {
				
				spectator = this;
			}
		}
		
		return spectator;
	}
	
	/**
	 * <b>Description:</b> This method allows getting a the BST of spectators size.<br>
	 * @param root The root of the BST of spectators.
	 * @return the BST of spectators size.
	 */
	
	public int getSizeBST(Spectator root) {
		
		int size = 0;
		
		if(root == null) {
			
			size = 0;
		}
		else {
			
			size = 1 + getSizeBST(root.leftChild) + getSizeBST(root.rightChild);
		}
		
		return size;
	}
	
	/**
	 * <b>Description:</b> This method allows re-adding a spectator to the BST of spectators.<br>
	 * @return spectator If the spectator has two children return the right child, if has one child, return the child, if does not has a child return himself.
	 */
	
	public Spectator deleteSpectator() {
		
		Spectator spectator = this;
		
		if(hasChildren(this)) {
			
			if(rightChild != null & leftChild != null) {
				
				spectator = rightChild;
				
				rightChild.reAddSpectator(leftChild);
			}
			else if(rightChild != null) {
				
				spectator = rightChild;
			}
			else if(leftChild != null) {
				
				spectator = leftChild;
			}
		}
		return spectator;
	}
	
	/**
	 * <b>Description:</b> This method allows knowing if the spectator has children.<br>
	 * @param spectator The spectator that do you want to know if has children.
	 * @return True if the spectator has a child, false in otherwise.
	 */
	
	public boolean hasChildren(Spectator spectator) {
		
		boolean hasChildren = false;
		
		if(spectator.getLeftChild() != null || spectator.getRightChild() != null) {
			
			hasChildren = true;
		}
		
		return hasChildren;
	}
	
	/**
	 * <b>Description:</b> This method allows adding a spectator in the BST of spectators.<br>
	 * <b>Post:</b> The spectator was added on the BST of spectators.<br>
	 * @param spectator The spectator that will be added - spectator != null.
	 */
	
	public void reAddSpectator(Spectator spectator) {
		
		if(spectator.compareTo(this) == 1) {
			
			if(rightChild == null) {
				
				rightChild = spectator;
			}
			else {
				
				rightChild.reAddSpectator(spectator);
			}
		}
		else if(spectator.compareTo(this) == -1) {
			
			if(leftChild == null) { 
				
				leftChild = spectator;
			}
			else {
				
				leftChild.reAddSpectator(spectator);
			}
		}
	}
	
	/**
	 * <b>Description:</b> This method converting the spectator's attributes in a String.<br>
	 * @return A String with the spectator's attributes.
	 */
	
	public String showSpectator() {
		
		String msg = "| ID: " + getId() + 
				", FIRST NAME: " + getFirstName() + 
				", LAST NAME: " + getLastName() + 
				", EMAIL: " + getEmail() + 
				", GENDER: " + getGender() + 
				", COUNTRY: " + getNationality() + 
				", BIRTHDATE: " + getBirthdate() + " |        ";
		
		return msg;
	}
	
	/**
	 * <b>Description:</b> This method allows returning an ArrayList with all the spectator of the country.<br>
	 * @param country The spectator's country.
	 * @return An ArrayList with all the spectator of that country.
	 */
	
	public ArrayList<Spectator> createBSTByCountry(String country) {
		
		ArrayList<Spectator> spectators = new ArrayList<Spectator>();
		
		if(getNationality().equals(country)) {
			
			spectators.add(new Spectator(getId(), getFirstName(), getLastName(), getEmail(), getGender(), getNationality(), getBirthdate()));
			
		}
		if(leftChild != null) {
			
			spectators.addAll(leftChild.createBSTByCountry(country));
		}
		if(rightChild != null) {
			
			spectators.addAll(rightChild.createBSTByCountry(country));
		}
		
		return spectators;
	}
	
	/**
	 * <b>Description:</b> This method allows showing the spectators of the BST of spectators.<br>
	 * @param level The BST actual level.
	 * @return A message with all the spectators.
	 */
	
	public String showSpectators(int level) {
		
		String spectators = "";
		StringBuilder spectators2 = new StringBuilder();
		
		for(int i = 0; i < level; i++) {
			
			spectators2.append("        ");
		}
		
		spectators2.append(showSpectator() + "\n\n");
		
		if(leftChild != null) {
			
			spectators2.append(leftChild.showSpectators(level + 1));
		}
		if(rightChild != null) {
			
			spectators2.append(rightChild.showSpectators(level + 1));
		}
		
		spectators = spectators2.toString();
		
		return spectators;
	}
}
