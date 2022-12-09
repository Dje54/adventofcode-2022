import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day9 {
	public static String INPUT_FILE = "day9.txt";
	
	public static void main(String[] args) {
		Day9 game = new Day9();
		game.run();
	}
	
	public void run() {
		init();
		resolve1();
		writeResult();
		init2();
		resolve1();
		writeResult();
	}
	
	long result;
	boolean[][] tab = new boolean[1000][1000];
	
	private void init() {
		try (Scanner scanner = new Scanner(new File("bin\\input\\" + INPUT_FILE))) {
			int headX = 500;
			int headY = 500;
			int tailX = 500;
			int tailY = 500;
			tab[tailY][tailX] = true;
			String direction;
			int nbDepl;
			while(scanner.hasNext()) {
				direction = scanner.next();
				nbDepl = scanner.nextInt();
				switch (direction) {
					case "U":
						for (int i = 0 ; i<nbDepl ; i++) {
							headY--;
							if ((tailY - headY) <= 1) {
								continue;
							}
							else {
								tailX = headX;
								tailY = headY+1;
								tab[tailY][tailX] = true;
							}
						}
						break;
					case "R":
						for (int i = 0 ; i<nbDepl ; i++) {
							headX++;
							if ((headX - tailX) <= 1) {
								continue;
							}
							else {
								tailY = headY;
								tailX = headX-1;
								tab[tailY][tailX] = true;
							}
						}
						break;
					case "D":
						for (int i = 0 ; i<nbDepl ; i++) {
							headY++;
							if ((headY - tailY) <= 1) {
								continue;
							}
							else {
								tailX = headX;
								tailY = headY-1;
								tab[tailY][tailX] = true;
							}
						}
						break;
					case "L":
						for (int i = 0 ; i<nbDepl ; i++) {
							headX--;
							if ((tailX - headX) <= 1) {
								continue;
							}
							else {
								tailY = headY;
								tailX = headX+1;
								tab[tailY][tailX] = true;
							}
						}
						break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void init2() {
		try (Scanner scanner = new Scanner(new File("bin\\input\\" + INPUT_FILE))) {
			tab = new boolean[1000][1000];
			List<Pos> rope = new ArrayList<>();
			for (int i=0 ; i<10 ; i++) {
				rope.add(new Pos(500, 500));
			}
			Pos tail = rope.get(rope.size()-1);
			tab[tail.y][tail.x] = true;
			String direction;
			int nbDepl;
			while(scanner.hasNext()) {
				direction = scanner.next();
				nbDepl = scanner.nextInt();
				for (int nb = 0 ; nb<nbDepl ; nb++) {
					switch (direction) {
						case "U":
							rope.get(0).y--;
							break;
						case "R":
							rope.get(0).x++;
							break;
						case "D":
							rope.get(0).y++;
							break;
						case "L":
							rope.get(0).x--;
							break;
					}
					for (int i=1 ; i<rope.size() ; i++) {
						Pos p1 = rope.get(i-1);
						Pos p2 = rope.get(i);
						if (p1.x - p2.x == 2 && Math.abs(p1.y-p2.y) <= 1) {
							p2.x = p1.x - 1;
							p2.y = p1.y;
						}
						else if (p2.x - p1.x == 2 && Math.abs(p1.y-p2.y) <= 1) {
							p2.x = p1.x + 1;
							p2.y = p1.y;
						}
						else if (p1.y - p2.y == 2 && Math.abs(p1.x-p2.x) <= 1) {
							p2.x = p1.x;
							p2.y = p1.y - 1;
						}
						else if (p2.y - p1.y == 2 && Math.abs(p1.x-p2.x) <= 1) {
							p2.x = p1.x;
							p2.y = p1.y + 1;
						}
						else if (p2.y - p1.y >= 2 && p2.x - p1.x >= 2) {
							p2.x = p1.x + 1;
							p2.y = p1.y + 1;
						}
						else if (p2.y - p1.y >= 2 && p1.x - p2.x >= 2) {
							p2.x = p1.x - 1;
							p2.y = p1.y + 1;
						}
						else if (p1.y - p2.y >= 2 && p2.x - p1.x >= 2) {
							p2.x = p1.x + 1;
							p2.y = p1.y - 1;
						}
						else if (p1.y - p2.y >= 2 && p1.x - p2.x >= 2) {
							p2.x = p1.x - 1;
							p2.y = p1.y - 1;
						}
					}
					tab[tail.y][tail.x]= true; 
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void resolve1() {
		result = 0;
		for (int i=0 ; i<tab.length ; i++) {
			String s= "";
			for (int j=0 ; j<tab[i].length ; j++) {
				s+=(tab[i][j]?"#":"0");
				if (tab[i][j]) {
					result++;
				}
			}
			System.out.println(s);
		}
	}
	
	private void resolve2() {
		result = 0;
	}
	
	private void writeResult() {
		System.out.println(result);
	}
	
}
class Pos {
	int x;
	int y;
	
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}