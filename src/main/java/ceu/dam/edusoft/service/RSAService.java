package ceu.dam.edusoft.service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
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

        try {
            // RECUPERACIÓN DE LA CLAVE PÚBLICA

            File filePubK = new File(KEY_PATH + PUBLIC );
            Scanner scanner = new Scanner(filePubK);
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

            String b64Result = Base64.getEncoder().encodeToString(result);
            System.out.println("Mensaje Cifrado:" + b64Result);

            return b64Result;


        } catch (NoSuchAlgorithmException | InvalidKeySpecException | FileNotFoundException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }


        return null;
    }
}
