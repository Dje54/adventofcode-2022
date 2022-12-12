import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day11 {
	public static String INPUT_FILE = "day11.txt";
	
	public static void main(String[] args) {
		Day11 game = new Day11();
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
	
	long result;
	List<Monkey> lMonkey;
	
	private void init() {
		lMonkey =  new ArrayList<>();
		try (Scanner scanner = new Scanner(new File("bin\\input\\" + INPUT_FILE))) {
			String[] line;
			while(scanner.hasNext()) {
				//Monkey 0:
				scanner.nextLine();
				Monkey monkey = new Monkey();
				//Starting items: 99, 67, 92, 61, 83, 64, 98
				scanner.next();
				scanner.next();
				line = scanner.nextLine().split(",");
				for (int i=0 ; i<line.length ; i++) {
					monkey.lItem.add(new BigInteger(line[i].trim()));
				}
				//Operation: new = old * 17
				line = scanner.nextLine().split(" ");
				monkey.operationAdd = line[6].equals("+");
				if (line[7].equals("old"))
					monkey.operationOld = true;
				else
					monkey.operationNb = new BigInteger(line[7]);
				//Test: divisible by 3
				line = scanner.nextLine().split(" ");
				monkey.divisibleBy = new BigInteger(line[5]);
				//If true: throw to monkey 4
				line = scanner.nextLine().split(" ");
				monkey.noMonkeyTrue = Integer.parseInt(line[9]);
				//If false: throw to monkey 2
				line = scanner.nextLine().split(" ");
				monkey.noMonkeyFalse = Integer.parseInt(line[9]);
				lMonkey.add(monkey);
				if (scanner.hasNext()) {
					scanner.nextLine();
				}
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
		/*for (int i=0 ; i<20 ; i++) {
			for (Monkey monkey : lMonkey) {
				for (long item : monkey.lItem) {
					long newItem = monkey.inspect(item) / 3;
					if (newItem%monkey.divisibleBy == 0) {
						lMonkey.get(monkey.noMonkeyTrue).lItem.add(newItem);
					}
					else {
						lMonkey.get(monkey.noMonkeyFalse).lItem.add(newItem);
					}
				}
				monkey.lItem.clear();
			}
		}*/
		BigInteger zero = new BigInteger("0");
		BigInteger trois = new BigInteger("3");
		for (int i=0 ; i<20 ; i++) {
			for (Monkey monkey : lMonkey) {
				for (BigInteger item : monkey.lItem) {
					BigInteger newItem = monkey.inspect(item).divide(trois);
					if (newItem.mod(monkey.divisibleBy).equals(zero)) {
						lMonkey.get(monkey.noMonkeyTrue).lItem.add(newItem);
					}
					else {
						lMonkey.get(monkey.noMonkeyFalse).lItem.add(newItem);
					}
				}
				monkey.lItem.clear();
			}
		}
		List<Monkey> lSort = lMonkey.stream().sorted((a, b) -> Long.compare(b.nbInspection, a.nbInspection)).collect(Collectors.toList());
		result = lSort.get(0).nbInspection * lSort.get(1).nbInspection;
	}
	
	private void resolve2() {
		result = 0;
		BigInteger zero = new BigInteger("0");
		BigInteger newItem;
		for (int i=0 ; i<10000 ; i++) {
			System.out.println( "tour " + i);
			for (Monkey monkey : lMonkey) {
				if (i == 1 || i== 20 || i%1000 ==0) {
					System.out.println( "tour " + i);
					System.out.println(monkey.nbInspection);
				}
				for (BigInteger item : monkey.lItem) {
					newItem = monkey.inspect(item);
					if (newItem.mod(monkey.divisibleBy).equals(zero)) {
						lMonkey.get(monkey.noMonkeyTrue).lItem.add(newItem);
					}
					else {
						lMonkey.get(monkey.noMonkeyFalse).lItem.add(newItem);
					}
				}
				monkey.lItem.clear();
			}
		}
		List<Monkey> lSort = lMonkey.stream().sorted((a, b) -> Long.compare(b.nbInspection, a.nbInspection)).collect(Collectors.toList());
		result = lSort.get(0).nbInspection * lSort.get(1).nbInspection;
	}
	
	private void writeResult() {
		System.out.println(result);
	}
}
class Monkey {
	List<BigInteger> lItem = new ArrayList<>();
	boolean operationAdd;
	boolean operationOld;
	BigInteger operationNb;
	BigInteger divisibleBy;
	int noMonkeyTrue;
	int noMonkeyFalse;

	long nbInspection = 0;
	
	public BigInteger inspect(BigInteger item) {
		nbInspection++;
		if (operationAdd) {
			if (operationOld) {
				return item.add(item);
			}
			else {
				return item.add(operationNb);
			}
		}
		else {
			if (operationOld) {
				return item.multiply(item);
			}
			else {
				return item.multiply(operationNb);
			}
		}
	}
	
}