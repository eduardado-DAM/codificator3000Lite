package ceu.dam.edusoft.service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
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


    /**
     * Produce una clave pública y una clave privada y las almacena en base64 dentro del directorio
     * especificado en KEY_PATH
     */
    public static void generateKeyPair() {

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();


            // Obtiene la clave pública y la codifica en base64 antes de guardarla en un fichero
            File filePublicKey = new File(KEY_PATH + PUBLIC);
            FileOutputStream fileOutputStream = new FileOutputStream(filePublicKey);
            byte[] encodedPublicKey = publicKey.getEncoded();
            String b64PublicKey = Base64.getEncoder().encodeToString(encodedPublicKey);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(b64PublicKey);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            fileOutputStream.close();


            // Obtiene la clave privada y la codifica en base64 antes de guardarla en un fichero
            File filePrivateKey = new File(KEY_PATH + PRIVATE);
            FileOutputStream fileOutputStream2 = new FileOutputStream(filePrivateKey);
            byte[] encodedPrivateKey = privateKey.getEncoded();
            String b64PrivateKey = Base64.getEncoder().encodeToString(encodedPrivateKey);
            OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(fileOutputStream2);
            outputStreamWriter2.write(b64PrivateKey);
            outputStreamWriter2.flush();
            outputStreamWriter2.close();
            fileOutputStream2.close();

            /*fileOutputStream2.write(privateKey.getEncoded());
            fileOutputStream2.flush();
            fileOutputStream2.close();*/

            System.out.println("Claves generadas correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String cifra(String clearMsg)  {

        Scanner scanner = null;
        String b64Result = null;

        try {
            // RECUPERACIÓN DE LA CLAVE PÚBLICA

            File filePubK = new File(KEY_PATH + PUBLIC );
            scanner = new Scanner(filePubK);
            String b64clavePublica = "";
            while (scanner.hasNextLine()){
                b64clavePublica += scanner.nextLine();
            }

            //ahora tenemos que decodificar la clave pública antes de poder usarla
            byte[] clavePublica = Base64.getDecoder().decode(b64clavePublica);

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(clavePublica);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            //Obtención del cifrador
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            // cifra el mensaje
            byte[] result = cipher.doFinal(clearMsg.getBytes());

            b64Result = Base64.getEncoder().encodeToString(result);
            System.out.println("Mensaje Cifrado:" + b64Result);



        } catch (NoSuchAlgorithmException | InvalidKeySpecException | FileNotFoundException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }


        return b64Result;
    }

    public static String descifra(String codedMsg){
        // RECUPERACIÓN DE LA CLAVE PRIVADA
        String decipheredMsg = null;
        Scanner scanner = null;



        try {
            //instanciamos un file
            File filePrivKey = new File(KEY_PATH + PRIVATE );
            //leemos la clave privada que está en base64 (caracteres)
            scanner = new Scanner(filePrivKey);
            String b64clavePrivada = "";
            while (scanner.hasNextLine()){
                b64clavePrivada += scanner.nextLine();
            }

            //ahora tenemos que decodificar la clave privada antes de poder usarla
            byte[] clavePrivada = Base64.getDecoder().decode(b64clavePrivada);

            //terminamos de obtener la clave privada
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clavePrivada);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            //Obtener descifrador
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            //descifra usando clave privada
            byte[] result = cipher.doFinal(codedMsg.getBytes());

            //transformamos los bytes a cadena
            decipheredMsg = new String(result);
            System.out.println("Mensaje descifrado: " + decipheredMsg);


        } catch (FileNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }finally {
            scanner.close();
        }

        return decipheredMsg;


    }
}
