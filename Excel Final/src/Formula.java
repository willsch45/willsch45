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
		cmdsq = input.substring(input.indexOf('<'), input.indexOf('>') + 1);
		realvals = cmdsq;
		circle = false;
		// count through string to identify cells
		for (int idx = 0; idx < cmdsq.length(); idx++) {
			if (realvals.charAt(idx) >= 65 && realvals.charAt(idx) <= 90) {
				if (realvals.charAt(idx + 2) == ' '
						|| realvals.charAt(idx + 2) == '>') {// i.e. A1
					Cell c1 = new Cell(realvals.substring(idx, idx + 2));
					if (c1.h == goal.h && c1.v == goal.v) {
						System.out
								.println("Circular Refrence detected within formula");
						circle = true;
					} else {
						String value = Array[c1.h][c1.v].output;
						realvals = realvals.substring(0, idx) + value
								+ realvals.substring(idx + 2);
					}
				} else {// i.e. A11
					Cell c1 = new Cell(realvals.substring(idx, idx + 3));
					if (c1.h == goal.h && c1.v == goal.v) {
						System.out
								.println("Circular Refrence detected within formula");
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

	public static double Arraymath(String[] arr) {
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
}
