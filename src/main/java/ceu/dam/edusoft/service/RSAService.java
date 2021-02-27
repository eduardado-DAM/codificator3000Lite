package ceu.dam.edusoft.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RSAService {

    public static final String KEY_PATH = "src/main/resources/ceu/dam/edusoft/rsaKey/";


    /**
     * Produce
     */
    public static void generateKeyPair() {

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();


            // Obtiene la clave pública y la codifica en base64 antes de guardarla en un fichero
            File filePublicKey = new File(KEY_PATH + "publicKey.key");
            FileOutputStream fileOutputStream = new FileOutputStream(filePublicKey);
            byte[] encodedPublicKey = publicKey.getEncoded();
            String b64PublicKey = Base64.getEncoder().encodeToString(encodedPublicKey);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(b64PublicKey);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            fileOutputStream.close();


            // Obtiene la clave privada y la codifica en base64 antes de guardarla en un fichero
            File filePrivateKey = new File(KEY_PATH + "privateKey.key");
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
}
