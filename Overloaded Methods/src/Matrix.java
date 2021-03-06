public class Matrix {
	int[] matrixi;
	double[] matrixd;

	public Matrix(int[] ints) {
		matrixi = ints;
	}

	public Matrix(double[] doubs) {
		matrixd = doubs;
	}

	public static int[] Copy(int[] values) {
		if (values == null) {
			return null;
		}

		int[] newArray = new int[values.length];

		for (int position = 0; position < values.length; position++) {
			newArray[position] = values[position];
		}

		return newArray;
	}

	public static double[] Copy(double[] values) {
		if (values == null) {
			return null;
		}

		double[] newArray = new double[values.length];

		for (int position = 0; position < values.length; position++) {
			newArray[position] = values[position];
		}

		return newArray;
	}

	public static void Sort(int[] values) {
		if (values != null) {
			for (int position = 0; position < values.length; position++) {
				int positionOfLargest = position;
				int currentPositionValue = values[position];

				for (int positionToRight = position + 1; positionToRight < values.length; positionToRight++) {
					if (values[positionToRight] < currentPositionValue) {
						positionOfLargest = positionToRight;
					}
				}

				values[position] = values[positionOfLargest];
				values[positionOfLargest] = currentPositionValue;
			}
		}
	}

	public static void Sort(double[] values) {
		if (values != null) {
			for (int position = 0; position < values.length; position++) {
				int positionOfLargest = position;
				double currentPositionValue = values[position];

				for (int positionToRight = position + 1; positionToRight < values.length; positionToRight++) {
					if (values[positionToRight] < currentPositionValue) {
						positionOfLargest = positionToRight;
					}
				}

				values[position] = values[positionOfLargest];
				values[positionOfLargest] = currentPositionValue;
			}
		}
	}

	public static double Mean(int[] values) {
		// don't want to change original array, so make a copy before sorting
		int[] copy = Copy(values);
		Sort(copy);

		int sum = 0;
		for (int position = 0; position < copy.length; position++) {
			sum += copy[position];
		}

		double mean = (double) sum / (double) copy.length;

		return mean;
	}

	// TODO: overload mean so it works with an array of doubles

	public static double Mean(double[] values) {
		// don't want to change original array, so make a copy before sorting
		double[] copy = Copy(values);
		Sort(copy);

		double sum = 0;
		for (int position = 0; position < copy.length; position++) {
			sum += copy[position];
		}

		double mean = sum / (double) copy.length;

		return mean;
	}

	// TODO: overload median and mode so that it works with an array of doubles

	public static double Median(int[] values) {
		int median = 0;

		int[] copy = Copy(values);
		Sort(copy);
		int distance;

		if (values.length % 2 == 0) {
			distance = values.length / 2;
			median = (values[distance] + values[distance + 1]) / 2;
		} else {
			distance = values.length / 2;
			median = values[distance];
		}

		return median;
	}

	public static double Median(double[] values) {
		double median = 0;

		double[] copy = Copy(values);
		Sort(copy);
		int distance;

		if (values.length % 2 == 0) {
			distance = values.length / 2;
			median = (values[distance] + values[distance + 1]) / 2;
		} else {
			distance = values.length / 2;
			median = values[distance];
		}

		return median;
	}

	public int Mode() {
		int[] values = matrixi;
		int[] counterarr = new int[values.length];

		for (int x = 0; x < values.length; x++) {
			int test = values[x];
			int counter = 0;
			for (int y = x; y < values.length; y++) {
				if (values[y] == test) {
					counter++;
				}
			}
			counterarr[x] = counter;
		}

		int start = 0;
		for (int idx = 0; idx < counterarr.length; idx++) {
			if (counterarr[start] < counterarr[idx]) {
				start = idx;
			}
		}

		return values[start];
	}

	public double Mode() {
		double[] values = matrixd;
		int[] counterarr = new int[values.length];

		for (int x = 0; x < values.length; x++) {
			double test = values[x];
			int counter = 0;
			for (int y = x; y < values.length; y++) {
				if (values[y] == test) {
					counter++;
				}
			}
			counterarr[x] = counter;
		}

		int start = 0;
		for (int idx = 0; idx < counterarr.length; idx++) {
			if (counterarr[start] < counterarr[idx]) {
				start = idx;
			}
		}

		return values[start];
	}
}
