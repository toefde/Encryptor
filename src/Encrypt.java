import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import Encrypting.CaesarEncryptor;
import Encrypting.CaesarEncryptorNG1;
import Encrypting.Encryptor;
import Encrypting.VigenereEncryptor;
import Encrypting.VigenereEncryptorNG1;
import Exceptions.AlgorithmNotFoundException;

public class Encrypt {

	private static String inputfile, outputfile, key, algorithm;
	
	private static boolean encrypt;
	
	public static void main(String[] args) {
		if(args[0] == "help" || args.length != 5) {
			System.out.println("Encrypt <inputfile> <outputfile> <key> <algorithm> <encrypt|decrypt>");
			System.exit(0);
		}
		
		long startTime = System.currentTimeMillis();
		
		inputfile = args[0];
		outputfile = args[1];
		key = args[2];
		algorithm = args[3];
		
		if(args[4].compareToIgnoreCase("decrypt") == 0) {
			encrypt = false;
		} else {
			encrypt = true;
		}
		
		System.out.println("INPUT: " + readFile(inputfile));
		System.out.println("KEY: " + key);
		System.out.println("MODE: " + algorithm);
		System.out.println("ENCRYPT: " + encrypt);
		
		Encryptor encryptor = null;
		switch (algorithm.toLowerCase()) {
			case "caesar":
				if(encrypt) {
					encryptor = new CaesarEncryptor(readFile(inputfile).toUpperCase(), "", key);
				} else {
					encryptor = new CaesarEncryptor("", readFile(inputfile).toUpperCase(), key);
				}
				break;
			case "caesarng1":
				if(encrypt) {
					encryptor = new CaesarEncryptorNG1(readFile(inputfile), "", key);
				} else {
					encryptor = new CaesarEncryptorNG1("", readFile(inputfile), key);
				}
				break;
			case "vigenere":
				if(encrypt) {
					encryptor = new VigenereEncryptor(readFile(inputfile).toUpperCase(), "", key);					
				} else {
					encryptor = new VigenereEncryptor("", readFile(inputfile).toUpperCase(), key);
				}
				break;
			case "vigenereng1":
				if(encrypt) {
					encryptor = new VigenereEncryptorNG1(readFile(inputfile), "", key);					
				} else {
					encryptor = new VigenereEncryptorNG1("", readFile(inputfile), key);
				}
				break;
			default:
				throw new AlgorithmNotFoundException(algorithm);
		}
		
		if(encrypt) {
			encryptor.encrypt();			
			writeFile(outputfile, encryptor.getCiphertext());
			System.out.println("OUTPUT: " + encryptor.getCiphertext());
		} else {
			encryptor.decrypt();
			writeFile(outputfile, encryptor.getPlaintext());
			System.out.println("OUTPUT: " + encryptor.getPlaintext());
		}
		
		System.out.println("Total time: " + (System.currentTimeMillis() - startTime) + "ms");
	}
	
	private static String readFile(String filename) {
		String back = "";
		File file = new File(filename);
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(in.hasNextLine()) {
			back += in.nextLine();
		}
		in.close();
		return back;
	}
	
	private static void writeFile(String filename, String text) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		out.print(text);
		out.close();
		
	}
	
}
