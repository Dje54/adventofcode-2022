import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day3 {
	public static String INPUT_FILE = "day3.txt";
	
	public static void main(String[] args) {
		Day3 game = new Day3();
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
	List<String> lSac = new ArrayList<>();
	
	private void init() {
		try (Scanner scanner = new Scanner(new File("bin\\input\\" + INPUT_FILE))) {
			while(scanner.hasNext()) {
				lSac.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void resolve1() {
		result = 0;
		for (String sac : lSac) {
			boolean find = false;
			for (int i = 0 ; i < sac.length()/2 ; i++) {
				for (int j = sac.length()/2 ; j < sac.length() ; j++) {
					if (sac.charAt(i) == sac.charAt(j)) {
						if ((int)sac.charAt(i) > 96) {
							result += (int)sac.charAt(i)-96;
						}
						else {
							result += (int)sac.charAt(i)-38;
						}
						find = true;
						break;
					}
				}
				if (find) {
					break;
				}
			}
		}
	}
	
	private void resolve2() {
		result = 0;
		for (int groupe=0 ; groupe<lSac.size() ; groupe=groupe+3) {
			for (int i = 0 ; i < lSac.get(groupe).length() ; i++) {
				if (lSac.get(groupe+1).contains(String.valueOf(lSac.get(groupe).charAt(i)))
						&& lSac.get(groupe+2).contains(String.valueOf(lSac.get(groupe).charAt(i)))) {
					if ((int)lSac.get(groupe).charAt(i) > 96) {
						result += (int)lSac.get(groupe).charAt(i)-96;
					}
					else {
						result += (int)lSac.get(groupe).charAt(i)-38;
					}
					break;
				}
			}
		}
	}
	
	private void writeResult() {
		System.out.println(result);
	}
	
}