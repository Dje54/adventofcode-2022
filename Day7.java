import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day7 {
	public static String INPUT_FILE = "day7.txt";
	
	public static void main(String[] args) {
		Day7 game = new Day7();
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
	Dir start = new Dir("/");
	List<Dir> lAllDir = new ArrayList<>();
	
	private void init() {
		try (Scanner scanner = new Scanner(new File("bin\\input\\" + INPUT_FILE))) {
			String[] line;
			scanner.nextLine();
			List<Dir> lCurrent = new ArrayList<>();
			lCurrent.add(start);
			while(scanner.hasNext()) {
				line = scanner.nextLine().split(" ");
				if (line[0].equals("$")) {
					if (line[1].equals("cd")) {
						if (line[2].equals("..")) {
							// On remonte d'un répertoire
							Dir dTmp = lCurrent.remove(0);
							dTmp.taille = dTmp.lDir.stream().mapToLong(a -> a.taille).sum()+dTmp.lFile.stream().mapToLong(a -> a.taille).sum();
						}
						else {
							// On ajoute le prochain répertoire à parcourir
							lCurrent.add(0, lCurrent.get(0).getDir(line[2]));
						}
					} else {
						// commande ls, on n'a rien à faire
					}
				}
				else if (line[0].equals("dir")) {
					Dir dTmp = new Dir(line[1]);
					lAllDir.add(dTmp);
					lCurrent.get(0).lDir.add(dTmp);
				} else {
					lCurrent.get(0).lFile.add(new Fichier(line[1], Long.parseLong(line[0])));
				}
			}
			Dir dTmp = lCurrent.remove(0);
			dTmp.taille = dTmp.lDir.stream().mapToLong(a -> a.taille).sum()+dTmp.lFile.stream().mapToLong(a -> a.taille).sum();
			dTmp = lCurrent.remove(0);
			dTmp.taille = dTmp.lDir.stream().mapToLong(a -> a.taille).sum()+dTmp.lFile.stream().mapToLong(a -> a.taille).sum();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void resolve1() {
		result = sumDirMax100000(start);
	}
	
	private long sumDirMax100000(Dir d) {
		long total = 0;
		if (d.taille < 100000) {
			total = d.taille;
		}
		for (Dir dir : d.lDir) {
			total += sumDirMax100000(dir);			
		}
		return total;
	}
	
	private void resolve2() {
		result = 0;
		long spaceToFree = 30000000-(70000000-start.taille);
		result = lAllDir.stream().filter(a -> a.taille >= spaceToFree).sorted((a, b) -> Long.compare(a.taille, b.taille)).findFirst().get().taille;
	}
	
	private void writeResult() {
		System.out.println(result);
	}
	
}

class Dir {
	String name;
	long taille;
	List<Dir> lDir = new ArrayList<>();
	List<Fichier> lFile = new ArrayList<>();
	
	public Dir(String name) {
		this.name = name;
	}
	
	public Dir getDir(String search) {
		return lDir.stream().filter(a -> a.name.equals(search)).findFirst().get();
	}
}

class Fichier {
	String name;
	long taille;
	
	public Fichier(String name, long taille) {
		this.name = name;
		this.taille = taille;
	}
}