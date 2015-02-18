public class Cellobj {
	public int internalint;
	public Date internaldate;
	public String internalstr;
	public char internalchar;
	public Formula internalformula;
	public static String commandsequence;
	public double internaldouble = 0.0;
	public String output;
	public String DataType;

	public Cellobj(Date d1) {
		internaldate = d1;
		DataType = "Date";
		output = internaldate.toString();
	}

	public Cellobj(int input) {
		internalint = input;
		DataType = "int";
		output = "" + input;
	}

	public Cellobj(String input) {
		internalstr = input;
		DataType = "string";
		output = input;
	}

	public Cellobj(char input) {
		internalchar = input;
		DataType = "char";
		output = "" + input;
	}

	public Cellobj(Formula input) {
		internaldouble = input.result;
		internalformula = input;
		commandsequence = input.cmdsq;
		DataType = "formula";
		output = "" + input.result;
	}

	public Cellobj(double input) {
		internaldouble = input;
		DataType = "double";
		output = "" + input;
	}

	public String toString() {
		return output;
	}
}
