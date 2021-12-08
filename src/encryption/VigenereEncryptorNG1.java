package encryption;

public class VigenereEncryptorNG1 extends Encryptor implements EncryptorInterface {

	public VigenereEncryptorNG1(String plaintext, String ciphertext, String key) {
		super(plaintext, ciphertext, key);
	}

	@Override
	public void encrypt() {
		ciphertext = "";
		while(key.length() < plaintext.length()) {
			key += key;
		}
		for(int i = 0; i < plaintext.length(); i++) {
			CaesarEncryptorNG1 ce =  new CaesarEncryptorNG1(Character.toString(plaintext.charAt(i)), "", Integer.toString(key.toUpperCase().charAt(i) - (int)'A'));
			ce.encrypt();
			ciphertext += ce.getCiphertext();
		}
	}

	@Override
	public void decrypt() {
		plaintext = "";
		while(key.length() < ciphertext.length()) {
			key += key;
		}
		for(int i = 0; i < ciphertext.length(); i++) {
			CaesarEncryptorNG1 ce =  new CaesarEncryptorNG1("", Character.toString(ciphertext.charAt(i)), Integer.toString(key.toUpperCase().charAt(i) - (int)'A'));
			ce.decrypt();
			plaintext += ce.getPlaintext();
		}
	}
	
}
