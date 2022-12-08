import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day8 {
	public static String INPUT_FILE = "day8.txt";
	
	public static void main(String[] args) {
		Day8 game = new Day8();
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
	//int[][] input = new int[5][5];
	int[][] input = new int[99][99];
	
	private void init() {
		try (Scanner scanner = new Scanner(new File("bin\\input\\" + INPUT_FILE))) {
			int nbLine = 0;
			String line;
			while(scanner.hasNext()) {
				line = scanner.nextLine();
				for (int i=0 ; i<line.length() ; i++) {
					input[nbLine][i] = Integer.valueOf(String.valueOf(line.charAt(i)));
				}
				nbLine++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void resolve1() {
		result = input.length*2 + (input[0].length-2)*2;
		for (int i=1 ; i<input.length-1 ; i++) {
			for (int j=1 ; j<input[i].length-1 ; j++) {
				int cur = input[i][j];
				boolean visible = true;
				for (int x=i-1 ; x >= 0  ; x-- ) {
					if (input[x][j] >= cur) {
						visible = false;
					}
				}
				if (visible) {
					result++;
					continue;
				}
				visible = true;
				for (int x=i+1 ; x < input.length  ; x++ ) {
					if (input[x][j] >= cur) {
						visible = false;
					}
				}
				if (visible) {
					result++;
					continue;
				}
				visible = true;
				for (int x=j-1 ; x >= 0  ; x-- ) {
					if (input[i][x] >= cur) {
						visible = false;
					}
				}
				if (visible) {
					result++;
					continue;
				}
				visible = true;
				for (int x=j+1 ; x < input[i].length  ; x++ ) {
					if (input[i][x] >= cur) {
						visible = false;
					}
				}
				if (visible) {
					result++;
					continue;
				}
			}
		}
	}
	
	private void resolve2() {
		result = 0;
		for (int i=1 ; i<input.length-1 ; i++) {
			for (int j=1 ; j<input[i].length-1 ; j++) {
				long score = 1;
				int cur = input[i][j];
				boolean fin = true;
				for (int x=i-1 ; x >= 0  ; x-- ) {
					if (input[x][j] >= cur) {
						score = score * (i-x);
						fin = false;
						break;
					}
				}
				if (fin) {
					score = score * (i);
				}
				fin = true;
				for (int x=i+1 ; x < input.length  ; x++ ) {
					if (input[x][j] >= cur) {
						score = score * (x-i);
						fin = false;
						break;
					}
				}
				if (fin) {
					score = score * (input.length-1-i);
				}
				fin = true;
				for (int x=j-1 ; x >= 0  ; x-- ) {
					if (input[i][x] >= cur) {
						score = score * (j-x);
						fin = false;
						break;
					}
				}
				if (fin) {
					score = score * (j);
				}
				fin = true;
				for (int x=j+1 ; x < input[i].length  ; x++ ) {
					if (input[i][x] >= cur) {
						score = score * (x-j);
						fin = false;
						break;
					}
				}
				if (fin) {
					score = score * (input[i].length-1-j);
				}
				if (score > result) {
					result = score;
				}
			}
		}
	}
	
	private void writeResult() {
		System.out.println(result);
	}
	
}