import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

//import java.util.Base64;
import javax.xml.bind.DatatypeConverter;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class AES {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    // Hace un set del secret key, actualiza el secret key cada vez que se llama para hacer la prueba
    private static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    // Funcion que desencripta el mensaje oculto, necesita una llame secreta para intentarlo
    public static String decrypt(String strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
<<<<<<< HEAD:AES.java
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
=======
            //return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
            return new String(cipher.doFinal(DatatypeConverter.parseBase64Binary(strToDecrypt)));
>>>>>>> Steven:src/AES.java
        } catch (Exception e) {
            return "";
        }        
    }
    

}

