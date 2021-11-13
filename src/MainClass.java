import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainClass {

	private static String inputfile, outputfile, key, encryptionmode;
	
	public static void main(String[] args) {
		if(args[0] == "help" || args.length != 4) {
			System.out.println("Encryptor <inputfile> <outputfile> <key> <encryptionmode>");
			System.exit(0);
		}
		
		long startTime = System.currentTimeMillis();
		
		inputfile = args[0];
		outputfile = args[1];
		key = args[2];
		encryptionmode = args[3];
		
		System.out.println("PLAINTEXT: " + readFile(inputfile));
		System.out.println("KEY: " + key);
		System.out.println("ENCRYPTIONMODE: " + encryptionmode);
		
		Encryptor encryptor = null;
		switch (encryptionmode.toLowerCase()) {
			case "caesar":
				encryptor = new CaesarEncryptor(readFile(inputfile), key);
				break;
			case "vigenere":
				encryptor = new VigenereEncryptor(readFile(inputfile), key);
				break;
		}
		
		encryptor.encrypt();
		
		writeFile(outputfile, encryptor.getCiphertext());
		
		System.out.println("CIPHERTEXT: " + encryptor.getCiphertext());
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
		return back.toUpperCase();
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
