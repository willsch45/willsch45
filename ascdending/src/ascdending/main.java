package ascdending;

import java.util.Arrays;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Series?");
		String input = scan.nextLine();

		int[] arr = stringtointarray(input);

		Arrays.sort(arr);

		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

	}

	public static int[] stringtointarray(String input) {
		int idx = 1;
		int counter = 0;
		String newin;
		String input2 = input;
		String newin2;
		int x;

		while (input.indexOf(' ') != -1) {// find what the length of the array
			// should be (will be one less than
			// actually necessary)
			idx++;
			newin = input.substring(input.indexOf(' ') + 1);
			input = newin;
		}

		int[] values = new int[idx];

		int length = idx;

		while (input2.indexOf(' ') != -1) {// assign values to the array to
			// store (excludes last number)
			x = Integer.parseInt(input2.substring(0, input2.indexOf(' ')));
			values[counter] = x;
			counter++;
			newin2 = input2.substring(input2.indexOf(' ') + 1);
			input2 = newin2;
		}

		values[counter] = Integer.parseInt(input2);// add last number into array

		idx = 0;

		return values;
	}
}
