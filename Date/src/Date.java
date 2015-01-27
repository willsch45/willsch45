public class Date {
	public int month;
	public int year;
	public int day;

	public Date(int days) {
		year = 2014;
		int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		int x = 0;
		while ((days - months[x]) > 0) {
			days -= months[x];
			x++;
		}

		month = x + 1;

	}

	public Date(int days, int months, int years) {
		day = days;
		month = months;
		year = years;

	}

}
