package ceu.dam.edusoft.service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class RSAService {

    public static final String KEY_PATH = "src/main/resources/ceu/dam/edusoft/rsaKey/";
    public static final String PUBLIC = "publicKey.key";
    public static final String PRIVATE = "privateKey.key";

    public static void main(String[] args) {
        generateKeys();
        String mensajeClaro = "blas";
        String mensajeCifrado = cifra(mensajeClaro);
        System.out.println("mensaje cifrado" + mensajeCifrado);
        String mensajeDescifrado = descifra(mensajeCifrado);
        System.out.println("mensaje descifrado " + mensajeDescifrado);
    }

    public static void generateKeys() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            File filePublicKey = new File(KEY_PATH + PUBLIC);
            FileOutputStream fileOutputStream = new FileOutputStream(filePublicKey);
            fileOutputStream.write(publicKey.getEncoded()); //?
            fileOutputStream.flush();
            fileOutputStream.close();

            File filePrivateKey = new File(KEY_PATH + PRIVATE);
            FileOutputStream fileOutputStream2 = new FileOutputStream(filePrivateKey);
            fileOutputStream2.write(privateKey.getEncoded());
            fileOutputStream2.flush();
            fileOutputStream2.close();

            System.out.println("Claves generadas correctamente");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String cifra(String clearMsg) {
        String cipheredMsg = null;

        try {
            // Recupera la clave pública del fichero
            File filePublicKey = new File(KEY_PATH + PUBLIC); // obtiene referencia al fichero de clave pública
            byte[] fileContent = Files.readAllBytes(filePublicKey.toPath()); //lee el fichero de bytes y lo almacena en un array de bytes

            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(fileContent);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

            // Obtiene el cifrador
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            // Cifra el mensaje de entrada
            byte[] result = cipher.doFinal(clearMsg.getBytes());

            // Convierte el resultado a base64 (mejora legibilidad)
            cipheredMsg = Base64.getEncoder().encodeToString(result);
            System.out.println(cipheredMsg);
        } catch (IOException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return cipheredMsg;

    }

    public static String descifra(String cipheredMsg) {
        String clearMsg = null;


        try {
            // Recupera la clave privada
            File filePrivateKey = new File(KEY_PATH + PRIVATE);
            byte[] fileContent = Files.readAllBytes(filePrivateKey.toPath());
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(fileContent);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

            // Obtiene desfricrador
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            // Obtiene mensaje cifrado original decodificando el base64
            byte[] msg = Base64.getDecoder().decode(cipheredMsg.getBytes());

            // Descifra usando clave privada
            byte[] result = cipher.doFinal(msg);

            // Convierte bytes a cadena
            clearMsg = new String(result);
        } catch (IOException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return clearMsg;

    }

}
