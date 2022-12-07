import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day6 {
	public static String INPUT_FILE = "day6.txt";
	
	public static void main(String[] args) {
		Day6 game = new Day6();
		game.run();
	}
	
	public void run() {
		init();
		resolve1();
		writeResult();
		resolve2();
		writeResult();
	}
	
	int result;
	String input;
	
	private void init() {
		try (Scanner scanner = new Scanner(new File("bin\\input\\" + INPUT_FILE))) {
			input = scanner.nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void resolve1() {
		result = 0;
		for (int i=0 ; i<input.length() ; i++) {
			boolean trouve = true;
			for (int j=1 ; j<4 ; j++ ) {
				if (input.charAt(i) == input.charAt(i+j)) {
					trouve = false;
					break;
				}
			}
			if (!trouve) {
				continue;
			}
			for (int j=2 ; j<4 ; j++ ) {
				if (input.charAt(i+1) == input.charAt(i+j)) {
					trouve = false;
					break;
				}
			}
			if (!trouve) {
				continue;
			}
			if (input.charAt(i+2) != input.charAt(i+3)) {
				result = i+4;
				break;
			}
		}
	}
	
	private void resolve2() {
		result = 0;
		for (int i=0 ; i<input.length() ; i++) {
			boolean trouve = true;
			String tmp =input.substring(i, i+14);
			for (int j=0 ; j<14 ; j++ ) {
				if (countMatches(tmp, String.valueOf(tmp.charAt(j))) > 1) {
					trouve = false;
					break;
				}
			}
			if (trouve) {
				result = i+14;
				break;
			}
		}
	}

	public static int countMatches(String str, String sub) {
		int count = 0;
		int idx = 0;
		while ((idx = str.indexOf(sub, idx)) != -1) {
			count++;
			idx += sub.length();
		}
		return count;
	}

	
	private void writeResult() {
		System.out.println(result);
	}
	
}