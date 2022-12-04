import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day4 {
	public static String INPUT_FILE = "day4.txt";
	
	public static void main(String[] args) {
		Day4 game = new Day4();
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
	List<int[][]> lInput = new ArrayList<>();
	
	private void init() {
		try (Scanner scanner = new Scanner(new File("bin\\input\\" + INPUT_FILE))) {
			String s;
			int[][] line;
			while(scanner.hasNext()) {
				s = scanner.nextLine();
				line = new int[2][2];
				String[] paire = s.split(",");
				line[0][0] = Integer.parseInt(paire[0].split("-")[0]);
				line[0][1] = Integer.parseInt(paire[0].split("-")[1]);
				line[1][0] = Integer.parseInt(paire[1].split("-")[0]);
				line[1][1] = Integer.parseInt(paire[1].split("-")[1]);
				lInput.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void resolve1() {
		result = 0;
		for (int[][] paire : lInput) {
			if ((paire[0][0] <= paire[1][0] && paire[0][1] >= paire[1][1])
					|| (paire[1][0] <= paire[0][0] && paire[1][1] >= paire[0][1])) {
				result++;
			}
		}
	}
	
	private void resolve2() {
		result = 0;
		for (int[][] paire : lInput) {
			if ((paire[0][0] <= paire[1][0] && paire[0][1] >= paire[1][0])
					|| (paire[1][0] <= paire[0][0] && paire[1][1] >= paire[0][0])) {
				result++;
			}
		}
	}
	
	private void writeResult() {
		System.out.println(result);
	}
	
}