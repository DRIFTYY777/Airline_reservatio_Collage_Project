import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class Crypto {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    public static Crypto crypto = null;

    public Crypto() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        privateKey = pair.getPrivate();
        publicKey = pair.getPublic();
    }

    public String encrypt(String plainText) {
        // Cipher encryptCipher;
        // try {
        // encryptCipher = Cipher.getInstance("RSA");
        // encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        //
        // byte[] encryptedBytes = encryptCipher.doFinal(
        // plainText.getBytes(StandardCharsets.UTF_8));
        //
        // return Base64.getEncoder().encodeToString(encryptedBytes);
        // } catch (NoSuchAlgorithmException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (NoSuchPaddingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (InvalidKeyException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (IllegalBlockSizeException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (BadPaddingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        try {
            return Crypto.toHexString(Crypto.getSHA(plainText));
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

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

    public String decrypt(String cipherText) {
        Cipher decryptCipher;

        try {

            decryptCipher = Cipher.getInstance("RSA");
            decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] encryptedBytes = decryptCipher.doFinal(
                    Base64.getDecoder().decode(cipherText.getBytes(StandardCharsets.UTF_8)));
            return new String(encryptedBytes, StandardCharsets.UTF_8);

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}