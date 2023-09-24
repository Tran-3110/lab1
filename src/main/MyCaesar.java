package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

public class MyCaesar {
	public static final char[] ALPHABET = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private int n;

	public MyCaesar(int shiftSteps) {
		this.n = shiftSteps;
	}

	// Encrypt a character according to the given shif steps.
	// Encrypt: En(x) = (x + n) mod 26. x represents the index of c in the ALPHABET
	// array
	public char encryptChar(char c) {
		for (int i = 0; i < ALPHABET.length; i++) {
			if (ALPHABET[i] == c) {
				return ALPHABET[(i + n) % 26];
			}
		}
		return c;
	}

	// Encrypt a text using the above function for encrypting a charater.
	public String encrypt(String input) {
		String result = "";
		for (int i = 0; i < input.length(); i++) {
			result += encryptChar(input.charAt(i));
		}
		return result;
	}

	// Decrypt a character according to the given shif steps.
	// Decrypt: Dn(x) = (x – n) mod 26. x represents the index of c in the ALPHABET
	// array
	public char decryptChar(char c) {
		for (int i = 0; i < ALPHABET.length; i++) {
			if (ALPHABET[i] == c) {
				if (((i - n) % 26) >= 0) {
					return ALPHABET[(i - n) % 26];
				} else {
					return ALPHABET[26 - Math.abs(((i - n) % 26))];
				}
			}
		}
		return c;
	}

	// Decrypt a encrypted text using the above function for decrypting a charater.
	public String decrypt(String input) {
		String result = "";
		for (int i = 0; i < input.length(); i++) {
			result += decryptChar(input.charAt(i));
		}
		return result;
	}

	// expand task 3
	public int encryptNum(int number) {
		return (number + n) % 10;
	}

	public int decryptNum(int number) {
		int result = (10 - (Math.abs(number - n) % 10));
		if(result == 10) {
			return 0;
		}
		return result;
	}

	public String encryptText(String text) {
		String result = "";
		for (int i = 0; i < text.length(); i++) {
			if (Character.isDigit(text.charAt(i))) {
				result += encryptNum(Integer.parseInt(text.charAt(i) + ""));
			} else {
				result += encryptChar(text.charAt(i));
			}
		}
		return result;
	}

	public String decryptText(String text) {
		String result = "";
		for (int i = 0; i < text.length(); i++) {
			if (Character.isDigit(text.charAt(i))) {
				result += decryptNum(Integer.parseInt(text.charAt(i)+""));
			} else {
				result += decryptChar(text.charAt(i));
			}
		}
		return result;
	}

	// expand task 4
	public void encryptByConsole() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap text can ecrypt: ");
		String text = sc.nextLine();
		System.out.println("Da ecrypt: " + encryptText(text));
	}

	public void decryptByConsole() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap text can decrypt: ");
		String text = sc.nextLine();
		System.out.println("Da decrypt: " + decryptText(text));
	}

	// expand task 5

	// Encrypt a encrypted the text content in the srcfile and save it into
	// desFile.
	public void encrypt(String srcFile, String desFile) throws IOException {
		try {
			FileReader file = new FileReader(srcFile);
			BufferedReader bf = new BufferedReader(file);
			FileWriter fileResult = new FileWriter(desFile);
			PrintWriter pt = new PrintWriter(fileResult);
			String line;
			while((line=bf.readLine()) != null) {
				System.out.println(line);
				pt.write(encryptText(line) + "\n");
			}
			bf.close();
			pt.close();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void decrypt(String srcFile, String desFile) throws IOException {
		try {
			FileReader file = new FileReader(srcFile);
			BufferedReader bf = new BufferedReader(file);
			FileWriter fileResult = new FileWriter(desFile);
			PrintWriter pt = new PrintWriter(fileResult);
			String line;
			while((line=bf.readLine()) != null) {
				pt.write(decryptText(line) + "\n");
			}
			bf.close();
			pt.close();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MyCaesar myCeasar = new MyCaesar(12);
        try {
			myCeasar.decrypt("./data/decrypt.txt", "./data/encrypt.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}