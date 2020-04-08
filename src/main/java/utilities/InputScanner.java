package utilities;

import java.util.Scanner;

public class InputScanner {
	
	private InputScanner() {
		
	}
	
	public static String getInput() {
		Scanner inputScanner = new Scanner(System.in);
		return inputScanner.nextLine();
	}

}
