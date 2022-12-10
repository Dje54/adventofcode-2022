import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day10 {
	public static String INPUT_FILE = "day10.txt";
	
	public static void main(String[] args) {
		Day10 game = new Day10();
		game.run();
	}
	
	public void run() {
		init();
		writeResult();
		init2();
	}
	
	long result;
	
	private void init() {
		try (Scanner scanner = new Scanner(new File("bin\\input\\" + INPUT_FILE))) {
			int nbCycle= 1;
			long x = 1;
			while(scanner.hasNext()) {
				if (nbCycle%40 == 20) {
					result += x*nbCycle;
				}
				if (scanner.next().equals("addx")) {
					nbCycle++;
					if (nbCycle%40 == 20) {
						result += x*nbCycle;
					}
					x += scanner.nextLong();
				}
				nbCycle++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void init2() {
		try (Scanner scanner = new Scanner(new File("bin\\input\\" + INPUT_FILE))) {
			int nbCycle= 1;
			long x = 1;
			String line="";
			while(scanner.hasNext()) {
				if (nbCycle%40 == 1) {
					System.out.println(line);
					line="";
				}
				if (scanner.next().equals("addx")) {
					line+= ((nbCycle%40) >= x && (nbCycle%40) < x+3 ? "#" : ".");
					nbCycle++;
					if (nbCycle%40 == 1) {
						System.out.println(line);
						line="";
					}
					line+= ((nbCycle%40) >= x && (nbCycle%40) < x+3 ? "#" : ".");
					x += scanner.nextLong();
				}
				else {
					line+= ((nbCycle%40) >= x && (nbCycle%40) < x+3 ? "#" : ".");
				}
				nbCycle++;
			}
			System.out.println(line);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void resolve1() {
		result = 0;
	}
	
	private void resolve2() {
		result = 0;
	}
	
	private void writeResult() {
		System.out.println(result);
	}
}