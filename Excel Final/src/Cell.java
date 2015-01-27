public class Cell {
	public int v;
	public int h;

	public Cell(String s) {

		if (s.charAt(0) == 'A') {
			h = 1;
		} else if (s.charAt(0) == 'B') {
			h = 2;
		} else if (s.charAt(0) == 'C') {
			h = 3;
		} else if (s.charAt(0) == 'D') {
			h = 4;
		} else if (s.charAt(0) == 'E') {
			h = 5;
		} else if (s.charAt(0) == 'F') {
			h = 6;
		} else if (s.charAt(0) == 'G') {
			h = 7;
		} else if (s.charAt(0) == 'H') {
			h = 8;
		} else if (s.charAt(0) == 'I') {
			h = 9;
		} else if (s.charAt(0) == 'J') {
			h = 10;
		} else if (s.charAt(0) == 'K') {
			h = 11;
		} else if (s.charAt(0) == 'L') {
			h = 12;
		} else if (s.charAt(0) == 'M') {
			h = 13;
		} else if (s.charAt(0) == 'N') {
			h = 14;
		} else if (s.charAt(0) == 'O') {
			h = 15;
		} else if (s.charAt(0) == 'P') {
			h = 16;
		}

		v = Integer.parseInt(s.substring(1));
	}
}
