package Encrypting;

public class Encryptor {
	
	protected String plaintext;
	protected String ciphertext;
	protected String key;
	
	public Encryptor(String plaintext, String ciphertext, String key) {
		this.plaintext = plaintext;
		this.ciphertext = ciphertext;
		this.key = key;
	}
	
	public String getPlaintext() {
		return plaintext;
	}
	public void setPlaintext(String plaintext) {
		this.plaintext = plaintext;
	}
	public String getCiphertext() {
		return ciphertext;
	}
	public void setCiphertext(String ciphertext) {
		this.ciphertext = ciphertext;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public void encrypt() {
		System.out.println("Nix wird verschlüsselt");
	}
	
	public void decrypt() {
		System.out.println("Nix wird entschlüsselt");
	}

	
	protected boolean onlyLetters(String text) {
		char[] textArray = text.toCharArray();
		for(char item : textArray) {
			if(!Character.isLetter(item)) {
				return false;
			}
		}
		return true;
	}

	
}
