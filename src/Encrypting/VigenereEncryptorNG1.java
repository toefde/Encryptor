package Encrypting;

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
			CaesarEncryptorNG1 ce =  new CaesarEncryptorNG1(Character.toString(plaintext.charAt(i)), "", Integer.toString(key.toUpperCase().charAt(i)));
			ce.encrypt();
			//System.out.println(ce.getPlaintext() + " " + (int)ce.getPlaintext().charAt(0) + "\t"
			//		+ "->\t" + ce.getCiphertext() + " " + + (int)ce.getCiphertext().charAt(0));
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
			CaesarEncryptorNG1 ce =  new CaesarEncryptorNG1("", Character.toString(ciphertext.charAt(i)), Integer.toString(key.toUpperCase().charAt(i)));
			ce.decrypt();
			//System.out.println(ce.getCiphertext() + " " + (int)ce.getCiphertext().charAt(0) + "\t"
			//		+ "->\t" + ce.getPlaintext() + " " + + (int)ce.getPlaintext().charAt(0));
			plaintext += ce.getPlaintext();
		}
	}
	
}
