package encryption;

import exceptions.WrongInputException;

public class CaesarEncryptor extends Encryptor implements EncryptorInterface {

	public CaesarEncryptor(String plaintext, String ciphertext, String key) {
		super(plaintext, ciphertext, key);
	}

	@Override
	public void encrypt() {
		// use onlyLetters method inherited from superclass encryptor to check whether to key is valid
		if(onlyLetters(key)) {
			throw new WrongInputException("Key [" + key + "] not allowed. Only numbers pls");
		}
		ciphertext = "";
		char[] plaintextArray = plaintext.toUpperCase().toCharArray(); // store string plaintext into char array
		for(int i = 0; i < plaintextArray.length; i++) { // iterate through char array
			for(int u = 0; u < Integer.parseInt(key) % 26; u++) { // increase current char plus 1 * key modulo count of valid characters (26)
				if(plaintextArray[i] == 'Z') { // if last char is reached, begin with the first again
					plaintextArray[i] = 'A';
				} else if (plaintextArray[i] == ' ') { // blank space remains blank
					// do nothing
				} else {
					plaintextArray[i]++;
				}
			}
			ciphertext += plaintextArray[i]; // store encrypted char in string ciphertext
		}
	}

	@Override
	public void decrypt() {
		plaintext = "";
		char[] ciphertextArray = ciphertext.toUpperCase().toCharArray();
		for(int i = 0; i < ciphertextArray.length; i++) {
			for(int u = 0; u < Integer.parseInt(key) % 26; u++) {
				if(ciphertextArray[i] == 'A') {
					ciphertextArray[i] = 'Z';
				} else if (ciphertextArray[i] == ' ') {
					//do nothing
				} else {
					ciphertextArray[i]--;
				}
			}
			plaintext += ciphertextArray[i];
		}
		
	}
}
