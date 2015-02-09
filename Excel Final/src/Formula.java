public class Formula {
	public Cell goal;
	public String original;
	public String cmdsq;
	public String realvals;
	public double result;
	public boolean circle;

	// add formula inception class
	// run that in every formula if a cell within formula contains another
	// formula
	// if in dependency the input comes up again (circular reference) then stop
	// the loop
	// circle = true
	// print statement
	// rounding

	public Formula(Cellobj[][] Array, String input) {
		original = input;
		goal = new Cell(input.substring(0, input.indexOf(' ')));
		cmdsq = input.substring(input.indexOf('<'), input.length() - 1);
		circle = false;
		// count through string to identify cells
		while (cmdsq.contains("Average")) {
			cmdsq = Average(Array);
		}

		while (cmdsq.contains("Sum")) {
			cmdsq = Sum(Array);
		}

		realvals = cmdsq;
		for (int idx = 0; idx < cmdsq.length(); idx++) {
			if (realvals.charAt(idx) >= 65 && realvals.charAt(idx) <= 90) {
				if (realvals.charAt(idx + 2) == ' '
						|| realvals.charAt(idx + 2) == '>') {// i.e. A1
					Cell c1 = new Cell(realvals.substring(idx, idx + 2));
					if (c1.h == goal.h && c1.v == goal.v) {
						circle = true;
						return;
					} else {
						String value = Array[c1.h][c1.v].output;
						realvals = realvals.substring(0, idx) + value
								+ realvals.substring(idx + 2);
					}
				} else {// i.e. A11
					Cell c1 = new Cell(realvals.substring(idx, idx + 3));
					if (c1.h == goal.h && c1.v == goal.v) {
						circle = true;
						return;
					} else {
						String value = Array[c1.h][c1.v].output;
						realvals = realvals.substring(0, idx) + value
								+ realvals.substring(idx + 3);
					}
				}
			}
		}

		if (circle == false) {
			/*
			 * realvals = realvals.trim(); realvals = realvals.substring(1,
			 * realvals.length() - 1); int terms = 1; for (int idx = 0; idx <
			 * realvals.length(); idx++) { if (realvals.charAt(idx) == ' ') {
			 * terms++; } }
			 * 
			 * String[] eqarray = new String[terms]; int counter = 0;
			 * 
			 * String[] temp = null;
			 * 
			 * temp = realvals.split(" ");
			 */
			String[] temp = Parserealvals();
			result = Arraymath(temp);
		}
	}

	/*
	 * public String[] Parserealvals() { // count spaces within realvals and add
	 * 1 for number of terms (array // length) String temp =
	 * realvals.substring(1, realvals.length() - 1); int spcounter = 0; for (int
	 * x = 0; x < temp.length(); x++) { if (temp.charAt(x) == ' ') {
	 * spcounter++; } }
	 * 
	 * String[] arr = new String[spcounter + 1]; int idy = 0; int x = 0;
	 * 
	 * for (int y = 0; y < temp.length(); y++) { if (temp.charAt(y) == ' ') {
	 * arr[x] = temp.substring(idy, y); idy = y + 1; x++; } }
	 * 
	 * arr[x] = temp.substring(idy);
	 * 
	 * return arr; }
	 * 
	 * public String[] multiply(int start, int end, String[] arr) { String[]
	 * temp = new String[arr.length - 2]; double value =
	 * Double.parseDouble(arr[start]) / Double.parseDouble(arr[end]); arr[start]
	 * = "" + value;
	 * 
	 * temp = reallocate(temp, arr, start);
	 * 
	 * return temp; }
	 * 
	 * public String[] add(int start, int end, String[] arr) { String[] temp =
	 * new String[arr.length - 2]; double value = Double.parseDouble(arr[start])
	 * + Double.parseDouble(arr[end]); arr[start] = "" + value;
	 * 
	 * temp = reallocate(temp, arr, start);
	 * 
	 * return temp; }
	 * 
	 * public String[] subtract(int start, int end, String[] arr) { String[]
	 * temp = new String[arr.length - 2]; double value =
	 * Double.parseDouble(arr[start]) - Double.parseDouble(arr[end]); arr[start]
	 * = "" + value;
	 * 
	 * temp = reallocate(temp, arr, start);
	 * 
	 * return temp; }
	 * 
	 * public String[] divide(int start, int end, String[] arr) { String[] temp
	 * = new String[arr.length - 2]; double value =
	 * Double.parseDouble(arr[start]) / Double.parseDouble(arr[end]); arr[start]
	 * = "" + value;
	 * 
	 * temp = reallocate(temp, arr, start);
	 * 
	 * return temp; }
	 * 
	 * public String[] reallocate(String[] temp, String[] arr, int start) {
	 * boolean afterstart = false;
	 * 
	 * for (int x = 0; x < temp.length; x++) { if (start < x) { afterstart =
	 * true; } if (afterstart == false) { temp[x] = arr[x]; } else if
	 * (afterstart = true) { temp[x] = arr[x - 2]; } }
	 * 
	 * return temp; }
	 * 
	 * public double Arraymath(String[] arr) { // multiplication and division
	 * System.out.println("test"); for (int x = 0; x < arr.length; x++) { if
	 * (arr[x].equals("*")) { arr = multiply(x - 1, x + 1, arr);
	 * System.out.print(arr[x]); } else if (arr[x].equals("/")) { arr = divide(x
	 * - 1, x + 1, arr); System.out.print(arr[x]); } }
	 * 
	 * for (int x = 0; x < arr.length; x++) { if (arr[x].equals("+")) { arr =
	 * add(x - 1, x + 1, arr); } else if (arr[x].equals("-")) { arr = subtract(x
	 * - 1, x + 1, arr); } }
	 * 
	 * double Final = Double.parseDouble(arr[arr.length - 1]);
	 * 
	 * return Final; }
	 */

	public String[] Parserealvals() {
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

	public String[] multiply(int start, int end, String[] arr) {
		String[] temp = new String[arr.length - 2];
		double value = Double.parseDouble(arr[start])
				* Double.parseDouble(arr[end]);
		arr[start] = "" + value;

		temp = reallocate(temp, arr, start);

		return temp;
	}

	public String[] add(int start, int end, String[] arr) {
		String[] temp = new String[arr.length - 2];
		double value = Double.parseDouble(arr[start])
				+ Double.parseDouble(arr[end]);
		arr[start] = "" + value;

		temp = reallocate(temp, arr, start);

		return temp;
	}

	public String[] subtract(int start, int end, String[] arr) {
		String[] temp = new String[arr.length - 2];
		double value = Double.parseDouble(arr[start])
				- Double.parseDouble(arr[end]);
		arr[start] = "" + value;

		temp = reallocate(temp, arr, start);

		return temp;
	}

	public String[] divide(int start, int end, String[] arr) {
		String[] temp = new String[arr.length - 2];
		double value = Double.parseDouble(arr[start])
				/ Double.parseDouble(arr[end]);
		arr[start] = "" + value;

		temp = reallocate(temp, arr, start);

		return temp;
	}

	public String[] reallocate(String[] temp, String[] arr, int start) {
		boolean afterstart = false;

		for (int x = 0; x < temp.length; x++) {
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

		return temp;
	}

	public double Arraymath(String[] arr) {
		// multiplication and division
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

	public String Average(Cellobj[][] Array) {
		String finder = cmdsq.substring(cmdsq.indexOf('v') - 1);
		String rangefinder = finder.substring(finder.indexOf('<'),
				finder.indexOf('>') + 1);
		Cell[] storage = Main.cellrange(rangefinder);
		Cell c1 = storage[0];
		Cell c2 = storage[1];
		double avg = 0;

		int columns = (c2.h - c1.h) + 1;
		int rows = (c2.v - c1.v) + 1;

		for (int x = 0; x <= columns; x++) {
			for (int y = 0; y <= rows; y++) {
				avg += Array[x][y].internaldouble;
			}
		}

		avg = avg / (rows * columns);

		int start = cmdsq.indexOf('v') - 1;
		int end = finder.indexOf('>') + cmdsq.indexOf('v');
		return cmdsq.substring(0, start) + avg + cmdsq.substring(end);

	}

	public double averager(double[] arr) {
		double result = arr[0];
		for (int x = 1; x < arr.length; x++) {
			result += arr[x];
		}

		result = result / (arr.length);

		return result;
	}

	public String Sum(Cellobj[][] Array) {
		String finder = cmdsq.substring(cmdsq.indexOf('m') - 2);
		String rangefinder = finder.substring(finder.indexOf('<'),
				finder.indexOf('>') + 1);
		Cell[] storage = Main.cellrange(rangefinder);
		Cell c1 = storage[0];
		Cell c2 = storage[1];
		double sum = 0;

		int columns = (c2.h - c1.h) + 1;
		int rows = (c2.v - c1.v) + 1;

		for (int x = 0; x <= columns; x++) {
			for (int y = 0; y <= rows; y++) {
				sum += Array[x][y].internaldouble;
			}
		}

		int start = cmdsq.indexOf('m') - 2;
		int end = finder.indexOf('>') + cmdsq.indexOf('m');
		return cmdsq.substring(0, start) + sum + " " + cmdsq.substring(end);
	}

	public double summer(double[] arr) {
		double result = arr[0];
		for (int x = 1; x < arr.length; x++) {
			result += arr[x];
		}

		return result;
	}
}
