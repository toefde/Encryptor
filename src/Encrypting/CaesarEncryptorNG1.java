package Encrypting;
import java.util.HashMap;

public class CaesarEncryptorNG1 extends Encryptor implements EncryptorInterface {

	HashMap<Character, Character> encryptAssignment = new HashMap<Character, Character>();
	HashMap<Character, Character> decryptAssignment = new HashMap<Character, Character>();
	
	public CaesarEncryptorNG1(String plaintext, String ciphertext, String key) {
		super(plaintext, ciphertext, key);
		for(int charact = 0; charact < 256; charact++) {
			encryptAssignment.put((char)charact, (char)(charact + (Integer.parseInt(key) % 26) % 256));
		}
		encryptAssignment.put(' ', ' ');
		for(int charact = 0; charact < 256; charact++) {
			decryptAssignment.put((char)(charact + (Integer.parseInt(key) % 26) % 256), (char)charact);
		}
		decryptAssignment.put(' ', ' ');
		for(int i = 0; i < encryptAssignment.size(); i++) {
			//System.out.println(encryptAssignment.toString());
		}
		System.out.println(encryptAssignment.size());
	}

	@Override
	public void encrypt() {
		ciphertext = "";
		char[] plaintextArray = plaintext.toCharArray();
		for(int i = 0; i < plaintextArray.length; i++) {
			ciphertext += encryptAssignment.get(plaintextArray[i]);
		}
	}

	@Override
	public void decrypt() {
		plaintext = "";
		char[] ciphertextArray = ciphertext.toUpperCase().toCharArray();
		for(int i = 0; i < ciphertextArray.length; i++) {
			plaintext += decryptAssignment.get(ciphertextArray[i]);
		}
	}
	
}
