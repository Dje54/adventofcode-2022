import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day2 {
	public static String INPUT_FILE = "day2.txt";
	
	public static void main(String[] args) {
		Day2 game = new Day2();
		game.run();
	}
	
	public void run() {
		init();
		resolve1();
		writeResult();
		resolve2();
		writeResult();
	}
	
	long result;
	List<String> lChaine = new ArrayList<>();
	
	private void init() {
		try (Scanner scanner = new Scanner(new File("bin\\input\\" + INPUT_FILE))) {
			while(scanner.hasNext()) {
				lChaine.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void resolve1() {
		result = 0;
		for (String s : lChaine) {
			String[] partie = s.split(" ");
			int other;
			if (partie[0].equals("A")) {
				other = 1;
			} else if (partie[0].equals("B")) {
				other = 2;
			} else {
				other = 3;
			}
			int me;
			if (partie[1].equals("X")) {
				me = 1;
			} else if (partie[1].equals("Y")) {
				me = 2;
			} else {
				me = 3;
			}
			int score = me;
			if (other == me) {
				score += 3;
			}
			else if ((me == 1 && other == 3)
					|| (me == 2 && other == 1)
					|| (me == 3 && other == 2)) {
				score += 6;
			}
			result += score;
		}
	}

	private void resolve2() {
		result = 0;
		for (String s : lChaine) {
			String[] partie = s.split(" ");
			int other;
			if (partie[0].equals("A")) {
				other = 1;
			} else if (partie[0].equals("B")) {
				other = 2;
			} else {
				other = 3;
			}
			int me;
			if (partie[1].equals("X")) {
				switch (other) {
				case 1:
					me = 3;
					break;
				case 2:
					me = 1;
					break;
				default:
					me = 2;
					break;
				}
			} else if (partie[1].equals("Y")) {
				me = other;
			} else {
				switch (other) {
				case 1:
					me = 2;
					break;
				case 2:
					me = 3;
					break;
				default:
					me = 1;
					break;
				}
			}
			int score = me;
			if (other == me) {
				score += 3;
			}
			else if ((me == 1 && other == 3)
					|| (me == 2 && other == 1)
					|| (me == 3 && other == 2)) {
				score += 6;
			}
			result += score;
		}
	}
	
	private void writeResult() {
		System.out.println(result);
	}
	
}