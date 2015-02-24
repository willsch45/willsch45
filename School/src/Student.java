public class Student extends Person {
	public String gradyear;

	public void setGraduation(String s) {
		gradyear = s;
	}

	public String getGraduation() {
		return gradyear;
	}

	public String getFullName() {
		return firstname + " " + lastname + ", " + gradyear;
	}
}
