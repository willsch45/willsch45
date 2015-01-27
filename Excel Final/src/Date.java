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

	public Date(String date) {// format mm/dd/yyyy
		month = Integer.parseInt(date.substring(0, date.indexOf('/')));
		String date2 = date.substring(date.indexOf('/') + 1);
		day = Integer.parseInt(date2.substring(0, date2.indexOf('/')));
		String date3 = date2.substring(date2.indexOf('/') + 1);
		year = Integer.parseInt(date3);
	}

	public Date(int days, int months, int years) {
		day = days;
		month = months;
		year = years;

	}

	public String toString() {
		return month + "/" + day + "/" + year;
	}

}