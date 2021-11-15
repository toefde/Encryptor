package Encrypting;

public class CaesarEncryptor extends Encryptor implements EncryptorInterface {

	public CaesarEncryptor(String plaintext, String ciphertext, String key) {
		super(plaintext, ciphertext, key);
	}

	@Override
	public void encrypt() {
		if(onlyLetters(key)) {
			System.out.println(key);
			throw new IllegalStateException("Key must be only numbers");
		}
		ciphertext = "";
		char[] plaintextArray = plaintext.toUpperCase().toCharArray();
		for(int i = 0; i < plaintextArray.length; i++) {
			for(int u = 0; u < Integer.parseInt(key) % 26; u++) {
				if(plaintextArray[i] == 'Z') {
					plaintextArray[i] = 'A';
				} else if (plaintextArray[i] == ' ') {
					//do nothing
				} else {
					plaintextArray[i]++;
				}
			}
			ciphertext += plaintextArray[i];
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
