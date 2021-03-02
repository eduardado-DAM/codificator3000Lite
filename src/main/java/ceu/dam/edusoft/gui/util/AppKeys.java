package ceu.dam.edusoft.gui.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class AppKeys {

    public static final String KEY_PATH = "src/main/resources/ceu/dam/edusoft/rsaKey/";
    public static final String PUBLIC_KEY_FILE_NAME = "publicKey.key";
    public static final String PRIVATE_KEY_FILE_NAME = "privateKey.key";
    public static final String PUBLIC = "PUBLIC";
    public static final String PRIVATE = "PRIVATE";

    private File publicKeyFile;
    private File privateKeyFile;

    public AppKeys() {

    }

    public Boolean haveKeys() {
        return (havePublicKey() && havePrivateKey());
    }

    public boolean havePrivateKey() {
        return (privateKeyFile != null);
    }

    public Boolean havePublicKey() {
        return (publicKeyFile != null);
    }

    //todo testing borrar
    public static void main(String[] args) {
        AppKeys appKeys = new AppKeys();
        appKeys.loadKeys();

        System.out.println(appKeys.havePublicKey());
        System.out.println(appKeys.havePrivateKey());
        System.out.println(appKeys.haveKeys());

        String mensajeClaro = "blas";
        String mensajeCifrado = appKeys.cifra(mensajeClaro, appKeys.getPublicKeyFile());
        System.out.println("mensaje cifrado " + mensajeCifrado);
    }


    public File getPublicKeyFile() {
        return publicKeyFile;
    }

    public AppKeys setPublicKeyFile(File publicKeyFile) {
        this.publicKeyFile = publicKeyFile;
        return this;
    }

    public File getPrivateKeyFile() {
        return privateKeyFile;
    }

    public AppKeys setPrivateKeyFile(File privateKeyFile) {
        this.privateKeyFile = privateKeyFile;
        return this;
    }

    public void deleteGeneratedKeyes(String directoryPath, String publicKeyName, String privateKeyName) {

        File publicKey = new File(directoryPath + publicKeyName);
        File privateKey = new File(directoryPath + privateKeyName);
        if (publicKey.delete()) {
            System.out.println("Clave pública borrada");
        }
        if (privateKey.delete()) {
            System.out.println("Clave privada borrada");
        }
    }

    /**
     * Carga las claves en la clase
     */
    public void loadKeys() {
        System.out.println("AppKeys.loadKeys " + "cargando claves en la clase" );
        Map<String, File> keyMap = generateKeys(KEY_PATH, PUBLIC_KEY_FILE_NAME, PRIVATE_KEY_FILE_NAME);
        publicKeyFile = keyMap.get("PUBLIC");
        privateKeyFile = keyMap.get("PRIVATE");
    }

    public String descifra(String cipheredMsg, File privateKeyFile) {
        String clearMsg = null;

        try {
            // Recupera la clave privada
            byte[] fileContent = Files.readAllBytes(privateKeyFile.toPath());
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

    public String cifra(String clearMsg, File publicKeyFile) {
        String cipheredMsg = null;

        try {
            // Recupera la clave pública del fichero
            byte[] fileContent = Files.readAllBytes(publicKeyFile.toPath()); //lee el fichero de bytes y lo almacena en un array de bytes

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

    private Map<String, File> generateKeys(String directoryPath, String publicKeyName, String privateKeyName) {
        System.out.println("AppKeys.generateKeys " + "generando claves");
        Map<String, File> keyMap = null;

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            File filePublicKey = new File(directoryPath + publicKeyName);
            FileOutputStream fileOutputStream = new FileOutputStream(filePublicKey);
            fileOutputStream.write(publicKey.getEncoded());
            fileOutputStream.flush();
            fileOutputStream.close();

            File filePrivateKey = new File(directoryPath + privateKeyName);
            FileOutputStream fileOutputStream2 = new FileOutputStream(filePrivateKey);
            fileOutputStream2.write(privateKey.getEncoded());
            fileOutputStream2.flush();
            fileOutputStream2.close();



            //crea el mapa y lo devuelve
            keyMap = new HashMap<>();
            keyMap.put(PUBLIC, filePublicKey);
            keyMap.put(PRIVATE, filePrivateKey);

            //todo borrar
            System.out.println("AppKeys.generateKeys " + "claves generadas correctamente");
            System.out.println("Clave pública ->" + filePublicKey.getPath());
            System.out.println("Clave privada ->" + filePrivateKey.getPath());


        } catch (NoSuchAlgorithmException | IOException e) {
            System.err.println("Problema al generar claves");
            e.printStackTrace();
        }

        return keyMap;
    }
}
