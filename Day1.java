import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day1 {
	public static String INPUT_FILE = "day1.txt";
	
	public static void main(String[] args) {
		Day1 game = new Day1();
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
		int nbMax = -1;
		int nbTmp = 0;
		for (String s : lChaine) {
			if (s.equals("")) {
				if (nbMax < nbTmp) {
					nbMax = nbTmp;
				}
				nbTmp = 0;
				continue;
			}
			nbTmp += Integer.valueOf(s);
		}
		if (nbMax < nbTmp) {
			nbMax = nbTmp;
		}
		result = nbMax;
	}
	
	
	private void resolve2() {
		result = 0;
		List<Integer> l = new ArrayList<>();
		int nbTmp = 0;
		for (String s : lChaine) {
			if (s.equals("")) {
				l.add(nbTmp);
				nbTmp = 0;
				continue;
			}
			nbTmp += Integer.valueOf(s);
		}
		l.add(nbTmp);
		Collections.sort(l);
		result = 0;
		for (int i = 1 ; i<4 ; i++) {
			result += l.get(l.size()-i);
		}
	}

	private void writeResult() {
		System.out.println(result);
	}
	
}