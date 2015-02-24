public class OverloadedMethods {

	public static void main(String[] args) {
		int[] integerValues = { 100, 34, 290, 4, 6, 6, 34, 100, 34 };
		double[] doubleValues = { 0.1, 3.4, 29.0, 0.4, 0.1, 6.6, 3.4, 0.1, 0.1 };

		System.out.println("Integer mean: " + Mean(integerValues));
		System.out.println("Double mean: " + Mean(doubleValues));
		System.out.println("Integer median: " + Median(integerValues));
		System.out.println("Double median: " + Median(doubleValues));
		System.out.println("Integer mode: " + Mode(integerValues));
		System.out.println("Double mode: " + Mode(doubleValues));
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

	public static double Mean(int[] values) {
		// don't want to change original array, so make a copy before sorting
		int[] copy = Copy(values);
		sort(copy);

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
		sort(copy);

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
		sort(copy);
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
		sort(copy);
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

	public static int Mode(int[] values) {
		int[] counterarr = new int[values.length];
		int[] copy = Copy(values);
		sort(copy);

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

	public static double Mode(double[] values) {
		int[] counterarr = new int[values.length];
		double[] copy = Copy(values);
		sort(copy);

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

	public static double[] sort(double[] array) {
		for (int strtidx = 0; strtidx < array.length; strtidx++) {
			int tryidx = strtidx;
			for (int idx = strtidx + 1; idx < array.length; idx++) {
				if (array[idx] > array[tryidx]) {
					tryidx = idx;
				}
			}

			double tmp = array[strtidx];
			array[strtidx] = array[tryidx];
			array[tryidx] = tmp;
		}

		return array;
	}

	public static int[] sort(int[] array) {
		for (int strtidx = 0; strtidx < array.length; strtidx++) {
			int tryidx = strtidx;
			for (int idx = strtidx + 1; idx < array.length; idx++) {
				if (array[idx] > array[tryidx]) {
					tryidx = idx;
				}
			}

			int tmp = array[strtidx];
			array[strtidx] = array[tryidx];
			array[tryidx] = tmp;
		}

		return array;
	}
}
