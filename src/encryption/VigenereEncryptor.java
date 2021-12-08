package encryption;

import exceptions.WrongInputException;

public class VigenereEncryptor extends Encryptor implements EncryptorInterface {

	public VigenereEncryptor(String plaintext, String ciphertext, String key) {
		super(plaintext, ciphertext, key);
	}

	@Override
	public void encrypt() throws IllegalArgumentException {
		// use onlyLetters method inherited from superclass encryptor to check whether to key is valid
		if(!onlyLetters(key)) {
			throw new WrongInputException("Key [" + key + "] not allowed. Only letters pls");
		}
		
		// make sure key is at least as long as plaintext
		while(key.length() < plaintext.length()) {
			key += key;
		}
		
		ciphertext = "";
		// iterate through plaintext  
		for(int i = 0; i < plaintext.length(); i++) {
			CaesarEncryptor ce =  new CaesarEncryptor(Character.toString(plaintext.charAt(i)), "", Integer.toString(key.toUpperCase().charAt(i) - (int)'A')); // use i'th char and encrypt it with caesar encryption using key in place i as int
			ce.encrypt(); // encrypt it
			ciphertext += ce.getCiphertext(); // get encryptet char back and append it to ciphertext
		}
	}

	@Override
	public void decrypt() {
		if(!onlyLetters(key)) {
			throw new WrongInputException("Key [" + key + "] not allowed. Only letters pls");
		}
		plaintext = "";
		while(key.length() < ciphertext.length()) {
			key += key;
		}
		for(int i = 0; i < ciphertext.length(); i++) {
			CaesarEncryptor ce =  new CaesarEncryptor("", Character.toString(ciphertext.charAt(i)), Integer.toString(key.toUpperCase().charAt(i) - (int)'A'));
			ce.decrypt();
			plaintext += ce.getPlaintext();
		}		
	}
	
}
