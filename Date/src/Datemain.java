public class Datemain {
	public static void main(String[] args) {
		Date d1, d2;

		d1 = new Date(12, 7, 1996);
		d2 = new Date(10, 7, 2000);

		printDate(d1);
		printDate(d2);
		System.out.println("Date 1: " + DayOfYear(d1));
		System.out.println("Date 2: " + DayOfYear(d2));
		compareDates(d1, d2);

	}

	public static void printDate(Date d) {
		System.out.println("Date: " + d.month + "/" + d.day + "/" + d.year);
	}

	public static void compareDates(Date d1, Date d2) {
		boolean yearsEarlier = false;

		if (d1.year > d2.year) {
			System.out.println("Date 2 was earlier than Date 1");
		} else if (d1.year < d2.year) {
			System.out.println("Date 1 was earlier than Date 2");
		} else if (d1.year == d2.year) {
			if (DayOfYear(d1) > DayOfYear(d2)) {
				System.out.println("Date 2 was earlier than Date 1");
			} else if (DayOfYear(d1) < DayOfYear(d2)) {
				System.out.println("Date 1 was earlier than Date 2");
			} else if (DayOfYear(d1) == DayOfYear(d2)) {
				System.out.println("The dates are the same");
			}
		}
	}

	public static int DayOfYear(Date d) {
		int m = d.month;// -1 for index of array
		int day = d.day;

		int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		int days = day - 31;

		int idx = 0;

		while (idx <= m - 1) {
			days += months[idx];
			idx++;
		}

		return days;

	}

}
