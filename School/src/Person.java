public class Person {
	public String firstname;
	public String lastname;

	public void setFirstName(String s) {
		firstname = s;
	}

	public void setLastname(String s) {
		lastname = s;
	}

	public String getFullName() {
		return firstname + " " + lastname;
	}
}
