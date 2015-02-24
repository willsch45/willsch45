public class Main {
	public static void main(String[] args) {
		Student s = new Student();

		s.setFirstName("Sally");
		s.setLastname("Johnson");
		s.setGraduation("2017");

		System.out.println(s.getFullName());
	}
}
