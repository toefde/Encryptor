import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import encryption.CaesarEncryptor;
import encryption.CaesarEncryptorNG1;
import encryption.Encryptor;
import encryption.VigenereEncryptor;
import encryption.VigenereEncryptorNG1;
import exceptions.AlgorithmNotFoundException;
import exceptions.WrongInputException;

public class Encrypt {

	// declaring variables for command line parameters
	private static String inputfile, outputfile, key, algorithm;
	private static boolean encrypt;
	
	// declare superclass encryptor
	private static Encryptor encryptor = null;
	
	public static void main(String[] args) {
		// check for valid parameters
		if(args[0] == "help" || args.length != 5) {
			throw new WrongInputException("Encrypt <inputfile> <outputfile> <key> <algorithm> <encrypt|decrypt> <gui|nogui>");
		}
		
		// initialize variables
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
		
		// store starttime for duration
		long startTime = System.currentTimeMillis();
		
		// select matching encryptor according to algorithm
		switch (algorithm.toLowerCase()) {
			case "caesar":
				// if algorithm is caesar
				if(encrypt) {
					// initialize encryptor as subclass caesarencryptor
					// contructor -> CaesarEncryptor(String plaintext, String ciphertext, String key)
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
				// if no valid encryptor class found throw error
				throw new AlgorithmNotFoundException(algorithm);
		}
		
		// execute encryption / decryption method
		if(encrypt) {
			encryptor.encrypt();			
			writeFile(outputfile, encryptor.getCiphertext());
			System.out.println("OUTPUT: " + encryptor.getCiphertext());
		} else {
			encryptor.decrypt();
			writeFile(outputfile, encryptor.getPlaintext());
			System.out.println("OUTPUT: " + encryptor.getPlaintext());
		}
		
		// print time duration
		System.out.println("Total time: " + (System.currentTimeMillis() - startTime) + "ms");

	}
	
	public static String readFile(String filename) {
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
	
	public static void writeFile(String filename, String text) {
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