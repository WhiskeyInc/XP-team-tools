package model;

/**
 * TeamComponent class represents a generic person who takes part of a team.
 * 
 * @author simone
 *
 */
public class TeamComponent implements Comparable<TeamComponent> {

	private String firstName;
	private String lastName;
	private String role;

	/**
	 * Creates a new instance of this class
	 * 
	 * @param name
	 *            : the first name of this member
	 * @param lastName
	 *            : the last name of this member
	 * @param role
	 *            : the role that he fulfills in the team
	 */
	public TeamComponent(String name, String lastName, String role) {
		super();
		this.firstName = name;
		this.lastName = lastName;
		this.role = role;
	}

	/**
	 * Returns the role of this member
	 * 
	 * @return: a string representing his role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Provides a way to change the role of this member
	 * 
	 * @param role
	 *            : the new role that this member will fulfill
	 */
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return firstName + " " + lastName;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(TeamComponent other) {
		return this.toString().compareTo(other.toString());
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		try {
			TeamComponent member = (TeamComponent) obj;
			return member.toString().equals(this.toString());
		} catch (ClassCastException e) {
			return false;
		}
	}

}
