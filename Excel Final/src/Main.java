import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

	public static int width = 17;
	public static int height = 14;
	public static String user;
	public static String folder;

	public static void main(String[] args) {

		Cellobj[][] Array = new Cellobj[width][height];

		setoriginal(Array);
		print(Array);

		Scanner scan = new Scanner(System.in);

		System.out.println("User name?");
		user = scan.nextLine();

		System.out.println("Save location?");
		folder = scan.nextLine();

		System.out.println("Auto-execute from file? (Yes/No)");
		String answer = scan.nextLine();

		if (answer.equalsIgnoreCase("No")) {
			System.out.println("Enter a command: ");
			String input = scan.nextLine();

			while (!input.equalsIgnoreCase("quit")) {

				tasker(Array, input);

				Refresh(Array);

				print(Array);

				System.out.println("Enter a command: ");
				input = scan.nextLine();

			}

			System.out.println("Thank You for Using!");
			scan.close();

		} else if (answer.equalsIgnoreCase("Yes")) {
			System.out.println("Enter file location:");
			String location = scan.nextLine();

			File f = new File("c:\\Users\\" + user + "\\" + folder + "\\"
					+ location + ".txt");
			Scanner s;

			try {
				s = new Scanner(f);
				System.out.println(f);
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
				return;
			}

			for (int idx = 1; s.hasNext() == true; idx++) {
				String input = s.nextLine();
				System.out.println(input);

				tasker(Array, input);

				Refresh(Array);

				print(Array);

				System.out.print("\n\n\n");
			}

			System.out.println("Thank You for Using!");
			scan.close();
		}

	}

	public static void print(Cellobj[][] Array) {
		for (int vert = 0; vert < height; vert++) {
			for (int hor = 0; hor < width; hor++) {
				if (Array[hor][vert].output.length() == 0) {
					System.out.print("|          " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 1) {
					System.out.print("|         " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 2) {
					System.out.print("|        " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 3) {
					System.out.print("|       " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 4) {
					System.out.print("|      " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 5) {
					System.out.print("|     " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 6) {
					System.out.print("|    " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 7) {
					System.out.print("|   " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 8) {
					System.out.print("|  " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 9) {
					System.out.print("| " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 10) {
					System.out.print("|" + Array[hor][vert]);
				}

				else if (Array[hor][vert].output.length() > 10) {
					String printer = Array[hor][vert].output.substring(0, 9);
					System.out.print("|" + printer + ">");
				}
			}
			System.out.print("|");
			System.out.println("");
			for (int x = 1; x <= width; x++) {
				System.out.print("-----------");
			}
			System.out.println("");
		}
	}

	public static Cellobj[][] setoriginal(Cellobj[][] Array) {

		Cellobj blankcell = new Cellobj("");

		for (int x = 0; x < width; x++) {

			char name = (char) ((x - 1) + 'A');
			Cellobj c1 = new Cellobj(name);
			Array[x][0] = c1;

		}

		for (int y = 0; y < height; y++) {
			Cellobj c2 = new Cellobj(y);
			if (y < 10) {
				Array[0][y] = c2;
			} else if (y >= 10) {
				Array[0][y] = c2;
			}
		}

		for (int vert = 1; vert < height; vert++) {
			for (int hor = 1; hor < width; hor++) {
				Array[hor][vert] = blankcell;
			}
		}

		Array[0][0] = blankcell;
		return Array;
	}

	public static void tasker(Cellobj[][] Array, String input) {
		String task = "Unknown task, check syntax of input";

		if (input.contains("=") == true) {
			if (input.indexOf('<') != -1 && input.indexOf('=') != -1
					&& input.indexOf('(') != -1) {
				setformula(Array, input);
			} else if (input.indexOf('<') != -1 && input.indexOf('=') != -1) {
				setvalue(Array, input);
			} else if (input.indexOf('<') == -1 && input.indexOf('(') != -1) {
				Cellcontents(Array, input);
			}
		} else if (input.contains("clear") == true) {
			if (input.indexOf('<') != -1) {
				clear(Array, input);
			} else {
				clearall(Array);
			}
		} else if (input.contains("sorta") == true) {
			sorta(Array, input);
		} else if (input.contains("sortd") == true) {
			sortd(Array, input);
		} else if (input.contains("export") == true) {
			Exporter(Array, input);
		} else if (input.contains("import") == true) {
			Importer(Array, input);
		} else {
			System.out.println(task);
		}
	}

	public static void Cellcontents(Cellobj[][] Array, String input) {
		Cell toexamine = new Cell(input.substring(input.indexOf('(') + 1,
				input.indexOf(')')));
		boolean displayincell = false;
		Cell goal = null;

		try {
			goal = new Cell(input.substring(0, input.indexOf(' ')));
			displayincell = true;
		} catch (Exception err) {
			displayincell = false;
		}
		if (displayincell == false) {
			if (Array[toexamine.h][toexamine.v].DataType.equals("formula")) {
				System.out.println("Cell "
						+ input.substring(input.indexOf('(') + 1,
								input.indexOf(')')) + " contains: "
						+ Array[toexamine.h][toexamine.v].commandsequence);
			} else {
				System.out.println("Cell "
						+ input.substring(input.indexOf('(') + 1,
								input.indexOf(')')) + " contains: "
						+ Array[toexamine.h][toexamine.v].output);
			}
		} else if (displayincell = true) {
			if (Array[toexamine.h][toexamine.v].DataType.equals("formula")) {
				Array[goal.h][goal.v] = new Cellobj(
						Array[toexamine.h][toexamine.v].commandsequence);
			} else {
				Array[goal.h][goal.v] = new Cellobj(
						Array[toexamine.h][toexamine.v].output);
			}
		}
	}

	public static Cellobj[][] setvalue(Cellobj[][] Array, String input) {
		Cell c1 = new Cell(input.substring(0, input.indexOf(' ')));

		String newinput = input.substring(input.indexOf('<') + 1,
				input.indexOf('>'));

		input = newinput;

		Cellobj obj;

		if (dataTyper(Array, input) == "int") {
			int integer = Integer.parseInt(input);
			obj = new Cellobj(integer);
		} else if (dataTyper(Array, input) == "date") {
			Date d1 = new Date(input);
			obj = new Cellobj(d1);
		} else {
			obj = new Cellobj(input);
		}

		Array[c1.h][c1.v] = obj;
		return Array;
	}

	public static Cellobj[][] clear(Cellobj[][] Array, String input) {
		Cellobj blankcell = new Cellobj("");
		Cell c1 = new Cell(input.substring(input.indexOf('<') + 1,
				input.indexOf('>')));

		Array[c1.h][c1.v] = blankcell;
		return Array;
	}

	public static Cellobj[][] clearall(Cellobj[][] Array) {
		Cellobj blankcell = new Cellobj("");
		for (int vert = 1; vert < 14; vert++) {
			for (int hor = 1; hor < 17; hor++) {
				Array[hor][vert] = blankcell;
			}
		}

		return Array;
	}

	public static String dataTyper(Cellobj[][] Array, String input) {
		if (isint(input) == true) {
			return "int";
		} else if (isdouble(input) == true) {
			return "double";
		} else if (isdate(input) == true) {
			return "date";
		} else {
			return "string";
		}
	}

	public static boolean[] isformula(Cellobj[][] Array, String input) {
		boolean[] conditions = { true, false };
		// {isformula?, iscircle?}
		// try {
		Formula f1 = new Formula(Array, input);
		if (f1.circle == true) {
			conditions[1] = true;
			return conditions;
		} else {
			return conditions;
		}
		// } catch (Exception err) {
		// conditions[0] = false;
		// return conditions;
		// }
	}

	public static boolean isint(String input) {
		try {
			int x;
			x = Integer.parseInt(input);
			return true;
		} catch (Exception err) {
			return false;
		}
	}

	public static boolean isdouble(String input) {
		try {
			double x;
			x = Double.parseDouble(input);
			return true;
		} catch (Exception err) {
			return false;
		}
	}

	public static boolean isdate(String input) {
		try {
			Date d1 = new Date(input);
			return true;
		} catch (Exception err) {
			return false;
		}
	}

	public static Cellobj[][] sortd(Cellobj[][] Array, String input) {
		Cell[] storage = cellrange(input);
		Cell c1 = storage[0];
		Cell c2 = storage[1];

		int y = 0;

		int columns = (c2.h - c1.h) + 1;
		int rows = (c2.v - c1.v) + 1;

		if (columns < 0 || rows < 0) {
			System.out
					.println("This range is unsortable for the sortd command, enter range to be sorted using sorta instead.");
			return Array;
		}

		double[] total = new double[(columns) * rows];

		for (int idx = 0; idx <= columns - 1; idx++) {
			double[] arr = new double[rows];

			int x = 0;

			for (int i = c1.v; i <= c2.v; i++) {
				if (!Array[c1.h + idx][i].DataType.equals("double")
						&& !Array[c1.h + idx][i].DataType.equals("formula")) {
					System.out.println("Cannot Sort Selected Range");
					return Array;
				}
				arr[x] = Array[c1.h + idx][i].internaldouble;
				x++;
			}

			for (int idy = 0; idy < arr.length; idy++) {
				total[y] = arr[idy];
				y++;
			}
		}

		total = sort(total);

		int counter = 0;

		for (int idx = 0; idx <= columns - 1; idx++) {
			for (int i = c1.v; i <= c2.v; i++) {
				Array[c1.h + idx][i] = new Cellobj(total[counter]);
				counter++;
			}
		}

		return Array;

	}

	public static Cellobj[][] sorta(Cellobj[][] Array, String input) {
		Cell[] storage = cellrange(input);
		Cell c1 = storage[0];
		Cell c2 = storage[1];

		int y = 0;

		int columns = (c2.h - c1.h) + 1;
		int rows = (c2.v - c1.v) + 1;

		if (columns < 0 || rows < 0) {
			System.out
					.println("This range is unsortable for the sorta command, enter range to be sorted using sortd instead.");
			return Array;
		}

		double[] total = new double[(columns) * rows];

		for (int idx = 0; idx <= columns - 1; idx++) {

			double[] arr = new double[rows];

			int x = 0;

			for (int i = c1.v; i <= c2.v; i++) {
				if (!Array[c1.h + idx][i].DataType.equals("double")
						&& !Array[c1.h + idx][i].DataType.equals("formula")) {
					System.out.println("Cannot Sort Selected Range");
					return Array;
				}
				arr[x] = Array[c1.h + idx][i].internaldouble;
				x++;
			}

			for (int idy = 0; idy < arr.length; idy++) {
				total[y] = arr[idy];
				y++;
			}
		}

		total = sort(total);

		int counter = total.length - 1;
		int other = 0;

		for (int idx = 0; idx <= columns - 1; idx++) {
			for (int i = c1.v; i <= c2.v; i++) {
				Array[c1.h + idx][i] = new Cellobj(total[counter]);
				counter--;
			}
		}

		return Array;

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

	public static Cell[] cellrange(String input) {
		Cell[] storage = new Cell[2];
		String Cell1 = input.substring(input.indexOf('<') + 1,
				input.indexOf('-'));
		Cell c1 = new Cell(Cell1);

		storage[0] = c1;

		String Cell2 = input.substring(input.indexOf('-') + 1,
				input.indexOf('>'));
		Cell c2 = new Cell(Cell2);

		storage[1] = c2;

		return storage;
	}

	public static Cellobj[][] setformula(Cellobj[][] Array, String input) {
		Cell goal = new Cell(input.substring(0, input.indexOf(' ')));
		if (isformula(Array, input)[1] == true) {
			System.out
					.println("Operation could not be completed due to a circular refrence");
		} else if (isformula(Array, input)[0] == false) {
			System.out.println("Operation is not a functional formula");
		} else {
			Formula f1 = new Formula(Array, input);
			Cell c1 = new Cell(input.substring(0, input.indexOf(' ')));
			Array[c1.h][c1.v] = new Cellobj(f1);
		}
		return Array;
	}

	public static void Exporter(Cellobj[][] Array, String input) {// need to ask
																	// for user
																	// name

		String location = input.substring(input.indexOf('<') + 1,
				input.indexOf('>'));

		File f = new File("c:\\Users\\" + user + "\\" + folder + "\\"
				+ location + ".txt");
		PrintStream p;
		try {
			p = new PrintStream(f);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot Export");
			return;
		}

		int counter = 0;

		for (int vert = 0; vert < height; vert++) {
			for (int hor = 0; hor < width; hor++) {
				if (Array[hor][vert].output.length() == 0) {
					p.print("|          " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 1) {
					p.print("|         " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 2) {
					p.print("|        " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 3) {
					p.print("|       " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 4) {
					p.print("|      " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 5) {
					p.print("|     " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 6) {
					p.print("|    " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 7) {
					p.print("|   " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 8) {
					p.print("|  " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 9) {
					p.print("| " + Array[hor][vert]);
				} else if (Array[hor][vert].output.length() == 10) {
					p.print("|" + Array[hor][vert]);
				}

				else if (Array[hor][vert].output.length() > 10) {
					String printer = Array[hor][vert].output.substring(0, 9);
					p.print("|" + printer + ">");
				}
			}
			p.print("|");
			p.println("");
			for (int x = 1; x <= width; x++) {
				p.print("-----------");
			}
			p.println("");
		}

		p.println("Formulas:");

		for (int vert = 0; vert < height; vert++) {
			for (int hor = 0; hor < width; hor++) {
				if (Array[hor][vert].DataType.equals("formula")) {
					p.println(Array[hor][vert].internalformula.original);
				}
			}
		}
	}

	public static void Importer(Cellobj[][] Array, String input) {

		String location = input.substring(input.indexOf('<') + 1,
				input.indexOf('>'));
		File f = new File("c:\\Users\\" + user + "\\" + folder + "\\"
				+ location + ".txt");
		Scanner s;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot import");
			return;
		}

		for (double vert = 0; s.hasNext() == true; vert += .5) {
			int horz = 0;
			String x = s.nextLine();
			if (x.charAt(0) == '|' || x.charAt(0) == '-') {
				for (int y = 1; y < x.length(); y++) {
					if (x.charAt(y) == '|') {
						String contents = x.substring(y - 10, y);
						contents = contents.trim();
						String type = dataTyper(Array, contents);
						if (type.equals("int")) {
							Array[horz][(int) vert] = new Cellobj(
									Integer.parseInt(contents));
						} else if (type.equals("date")) {
							Array[horz][(int) vert] = new Cellobj(new Date(
									contents));
						} else {// string
							Array[horz][(int) vert] = new Cellobj(contents);
						}

						horz++;
					}

				}
			} else {
				if (x.charAt(0) == 'F') {
					// do nothing if line is "Formula"
				} else {
					setformula(Array, x);
				}
			}
		}

	}

	public static Cellobj[][] Refresh(Cellobj[][] Array) {
		for (int vert = 0; vert < height; vert++) {
			for (int hor = 0; hor < width; hor++) {
				if (Array[hor][vert].DataType.equals("formula")) {
					Formula f1 = new Formula(Array,
							Array[hor][vert].internalformula.original);
					Array[hor][vert] = new Cellobj(f1);
				} else if (Array[hor][vert].DataType.equals("int") && hor != 0) {
					double todouble = (double) Array[hor][vert].internalint;
					Array[hor][vert] = new Cellobj(todouble);
				}
			}
		}

		return Array;
	}
}
