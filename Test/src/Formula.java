public class Formula {
	public static String realvals = "<12 / 3 + 30 / 3>";

	public static void main(String[] args) {
		String[] arr = Parserealvals();
		System.out.println(Arraymath(arr));
	}

	public static String[] Parserealvals() {
		// count spaces within realvals and add 1 for number of terms (array
		// length)
		String temp = realvals.substring(1, realvals.length() - 1);
		int spcounter = 0;
		for (int x = 0; x < temp.length(); x++) {
			if (temp.charAt(x) == ' ') {
				spcounter++;
			}
		}

		String[] arr = new String[spcounter + 1];
		int idy = 0;
		int x = 0;

		for (int y = 0; y < temp.length(); y++) {
			if (temp.charAt(y) == ' ') {
				arr[x] = temp.substring(idy, y);
				idy = y + 1;
				x++;
			}
		}

		arr[x] = temp.substring(idy);

		return arr;
	}

	public static String[] multiply(int start, int end, String[] arr) {
		String[] temp = new String[arr.length - 2];
		double value = Double.parseDouble(arr[start])
				* Double.parseDouble(arr[end]);
		arr[start] = "" + value;

		temp = reallocate(temp, arr, start);

		return temp;
	}

	public static String[] add(int start, int end, String[] arr) {
		String[] temp = new String[arr.length - 2];
		double value = Double.parseDouble(arr[start])
				+ Double.parseDouble(arr[end]);
		System.out.println(value);
		arr[start] = "" + value;

		temp = reallocate(temp, arr, start);

		return temp;
	}

	public static String[] subtract(int start, int end, String[] arr) {
		String[] temp = new String[arr.length - 2];
		double value = Double.parseDouble(arr[start])
				- Double.parseDouble(arr[end]);
		arr[start] = "" + value;

		temp = reallocate(temp, arr, start);

		return temp;
	}

	public static String[] divide(int start, int end, String[] arr) {
		String[] temp = new String[arr.length - 2];
		double value = Double.parseDouble(arr[start])
				/ Double.parseDouble(arr[end]);
		arr[start] = "" + value;

		temp = reallocate(temp, arr, start);

		return temp;
	}

	public static String[] reallocate(String[] temp, String[] arr, int start) {
		boolean afterstart = false;

		for (int x = 0; x < temp.length; x++) {
			System.out.println(arr[x] + "KK" + arr[x]);
			if (start < x) {
				afterstart = true;
			}
			if (afterstart == false) {
				temp[x] = arr[x];
			} else if (afterstart = true) {
				String test = arr[x + 2];
				temp[x] = arr[x + 2];
			}
		}

		for (int x = 0; x < temp.length; x++) {
			System.out.print(temp[x] + " ");
		}

		System.out.println("\n");

		return temp;
	}

	public static double Arraymath(String[] arr) {
		// multiplication and division
		System.out.println("test");
		for (int x = 0; x < arr.length; x++) {
			if (arr[x].equals("*")) {
				arr = multiply(x - 1, x + 1, arr);
				x = 0;
			} else if (arr[x].equals("/")) {
				arr = divide(x - 1, x + 1, arr);
				x = 0;
			}
		}

		for (int x = 0; x < arr.length; x++) {
			if (arr[x].equals("+")) {
				arr = add(x - 1, x + 1, arr);
				x = 0;
			} else if (arr[x].equals("-")) {
				arr = subtract(x - 1, x + 1, arr);
				x = 0;
			}
		}

		double Final = Double.parseDouble(arr[arr.length - 1]);

		return Final;
	}
}
