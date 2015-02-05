public class Formula {
	public Cell goal;
	public String original;
	public String cmdsq;
	public String realvals;
	public double result;
	public boolean circle;

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
			realvals = realvals.trim();
			realvals = realvals.substring(1, realvals.length() - 1);
			int terms = 1;
			for (int idx = 0; idx < realvals.length(); idx++) {
				if (realvals.charAt(idx) == ' ') {
					terms++;
				}
			}

			String[] eqarray = new String[terms];
			int counter = 0;

			String[] temp = null;

			temp = realvals.split(" ");

			result = Arraymath(temp);
		}
	}

	public double Arraymath(String[] arr) {
		for (int x = 0; x < arr.length; x++) {
			if (arr[x].equals("*")) {
				double newval = (Double.parseDouble(arr[x - 1]))
						* (Double.parseDouble(arr[x + 1]));
				arr[x + 1] = "" + newval;

				arr[x] = "`";
				arr[x - 1] = "`";
			} else if (arr[x].equals("+")) {
				double newval = (Double.parseDouble(arr[x - 1]))
						+ (Double.parseDouble(arr[x + 1]));
				arr[x + 1] = "" + newval;

				arr[x] = "`";
				arr[x - 1] = "`";
			} else if (arr[x].equals("-")) {
				double newval = (Double.parseDouble(arr[x - 1]))
						- (Double.parseDouble(arr[x + 1]));
				arr[x + 1] = "" + newval;

				arr[x] = "`";
				arr[x - 1] = "`";
			} else if (arr[x].equals("/")) {
				double newval = (Double.parseDouble(arr[x - 1]))
						/ (Double.parseDouble(arr[x + 1]));
				arr[x + 1] = "" + newval;

				arr[x] = "`";
				arr[x - 1] = "`";
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

		double[] total = new double[(columns) * rows];

		for (int idx = 0; idx <= columns - 1; idx++) {// keep eye on this,
														// might
														// need to start at
														// 1
			double[] arr = new double[rows];

			int x = 0;

			for (int i = c1.v; i <= c2.v; i++) {
				arr[x] = Array[c1.h + idx][i].internaldouble;
				x++;
			}

			int y = 0;

			for (int idy = 0; idy < arr.length; idy++) {
				total[y] = arr[idy];
				y++;
			}
		}

		avg = averager(total);

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

		double[] total = new double[(columns) * rows];

		for (int idx = 0; idx <= columns - 1; idx++) {// keep eye on this,
														// might
														// need to start at
														// 1
			double[] arr = new double[rows];

			int x = 0;

			for (int i = c1.v; i <= c2.v; i++) {
				arr[x] = Array[c1.h + idx][i].internaldouble;
				x++;
			}

			int y = 0;

			for (int idy = 0; idy < arr.length; idy++) {
				total[y] = arr[idy];
				y++;
			}
		}

		sum = summer(total);

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
