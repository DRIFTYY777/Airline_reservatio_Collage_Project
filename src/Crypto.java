
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class Crypto {

    /**
     * The instance of the crypto class
     */
    public static Crypto crypto = null;
    Common common = new Common();

    /**
     * The constructor of the crypto class
     * 
     * @throws NoSuchAlgorithmException
     */
    public Crypto() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
    }

    /**
     * This method is used to encrypt the data using the public key of the user
     * 
     * @param plainText - The data to be encrypted in the form of string
     * @return - The encrypted data
     */
    public String encrypt(String plainText) {
        try {
            return Crypto.toHexString(Crypto.getSHA(plainText));
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch blocks
            common.show_message(null, "Error", "Error in encrypting the data", "Okay");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used to get the SHA-256 hash of the data
     * 
     * @param input - The data to be hashed
     * @return - The hash of the data in the form of byte array
     * @throws NoSuchAlgorithmException
     */
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * This method is used to convert the byte array into hex string
     * 
     * @param hash - The byte array
     * @return - The hex string
     */
    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}