package Encrypton_alopez;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author ANDRES
 */
public class Encrypton_ALG {
    
    /**
     * Genera un texto plano para encriptar
     * Obtenemos una clave secreta (impresa en forma hexadecimal).
     * En el uso real, esto debe cifrarse y mantenerse a salvo.
     * Se requiere la misma clave para el descifrado.
     */
    
    public static void main(String[] args) throws Exception {
        String plainText = "Hello World";
        SecretKey secKey = getSecretEncryptionKey();
        byte[] cipherText = encryptText(plainText, secKey);
        String decryptedText = decryptText(cipherText, secKey);
         
        System.out.println("Original Text:" + plainText);
        System.out.println("AES Key (Hex Form):"+bytesToHex(secKey.getEncoded()));
        System.out.println("Encrypted Text (Hex Form):"+bytesToHex(cipherText));
        System.out.println("Descrypted Text:"+decryptedText);
         
    }
     
    /**
     * Obtenemos la clave de cifrado AES. En programas reales, esto debe
     * almacenarse de forma segura.
     * @return
     * @throws Exception 
     */
    public static SecretKey getSecretEncryptionKey() throws Exception{
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128); // El tamaño de la clave AES en número de bits
        SecretKey secKey = generator.generateKey();
        return secKey;
    }
     
    /**
     * Cifrado del texto sin formato en AES usando la clave secreta
     * @param plainText
     * @param secKey
     * @return
     * @throws Exception 
     */
    public static byte[] encryptText(String plainText,SecretKey secKey) throws Exception{
    // AES por defecto es AES/ECB/PKCS5Padding en Java 7
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
        return byteCipherText;
    }
     
    /**
     * Descifra la matriz de bytes cifrados utilizando la clave utilizada para el cifrado.
     * @param byteCipherText
     * @param secKey
     * @return
     * @throws Exception 
     */
    public static String decryptText(byte[] byteCipherText, SecretKey secKey) throws Exception {
    // AES por defecto es AES/ECB/PKCS5Padding en Java 7
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secKey);
        byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
        return new String(bytePlainText);
    }
     
    /**
     * Convertimos una matriz de bytes binarios en forma hexadecimal legible
     * @param hash
     * @return 
     */
    private static String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
   }
   