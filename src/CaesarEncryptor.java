
public class CaesarEncryptor extends Encryptor implements EncryptorInterface {

	public CaesarEncryptor(String plaintext, String key) {
		super(plaintext, key);
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
			for(int u = 0; u < Integer.parseInt(key); u++) {
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
}
