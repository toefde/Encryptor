
public class VigenereEncryptor extends Encryptor implements EncryptorInterface {

	public VigenereEncryptor(String plaintext, String key) {
		super(plaintext, key);
	}

	@Override
	public void encrypt() throws IllegalArgumentException {
		if(!onlyLetters(key)) {
			throw new IllegalStateException("Key must be only letters");
		}
		ciphertext = "";
		while(key.length() < plaintext.length()) {
			key += key;
		}
		for(int i = 0; i < plaintext.length(); i++) {
			CaesarEncryptor ce =  new CaesarEncryptor(Character.toString(plaintext.charAt(i)), Integer.toString(key.toUpperCase().charAt(i) - (int)'A'));
			ce.encrypt();
			ciphertext += ce.getCiphertext();
		}
	}
	
}
