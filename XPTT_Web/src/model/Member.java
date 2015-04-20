package model;


public class Member implements Comparable<Member> {

	private String name;
	private String lastName;
	private String role;

	public Member(String name, String lastName, String role) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return name + " " + lastName;
	}

	@Override
	public int compareTo(Member other) {
		return this.toString().compareTo(other.toString());
	}
	
	@Override
	public boolean equals(Object obj) {
		try {
			Member member = (Member) obj;
			return member.toString().equals(this.toString());
		} catch (ClassCastException e) {
			return false;
		}
	}

}
