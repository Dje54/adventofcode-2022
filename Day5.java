import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day5 {
	public static String INPUT_FILE = "day5.txt";
	
	public static void main(String[] args) {
		Day5 game = new Day5();
		game.run();
	}
	
	public void run() {
		init();
		resolve1();
		writeResult();
		init();
		resolve2();
		writeResult();
	}
	
	String result;
	List<String>[] lInput;
	List<int[]> lMove; 
	
	private void init() {
		lInput = new ArrayList[9];
		 lMove = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File("bin\\input\\" + INPUT_FILE))) {
			String s;
			for (int i = 0 ; i< 9 ; i++) {
				lInput[i] = new ArrayList<>();
			}
			for (int j = 0 ; j< 8 ; j++) {
				s = scanner.nextLine();
				for (int i = 0 ; i< 9 ; i++) {
					String letter = s.substring((i*4)+1, (i*4)+2);
					if (!letter.equals(" ")) {
						lInput[i].add(letter);
					}
				}
			}
			scanner.nextLine();
			scanner.nextLine();
			int[] move;
			while(scanner.hasNext()) {
				move = new int[3];
				scanner.next();
				move[0] = scanner.nextInt();
				scanner.next();
				move[1] = scanner.nextInt()-1;
				scanner.next();
				move[2] = scanner.nextInt()-1;
				lMove.add(move);
			}
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void resolve1() {
		result = "";
		for (int[] move : lMove) {
			for (int i=0 ; i<move[0] ; i++ ) {
				lInput[move[2]].add(0, lInput[move[1]].remove(0));
			}
		}
		for (List<String> pile : lInput) {
			result += pile.get(0);
		}
	}
	
	private void resolve2() {
		result = "";
		for (int[] move : lMove) {
			for (int i=0 ; i<move[0] ; i++ ) {
				lInput[move[2]].add(0, lInput[move[1]].remove(move[0]-1-i));
			}
		}
		for (List<String> pile : lInput) {
			result += pile.get(0);
		}
	}
	
	private void writeResult() {
		System.out.println(result);
	}
	
}