package ceu.dam.edusoft.gui.util;

import ceu.dam.edusoft.service.RSAService;

import java.io.File;

public class AppKeys {

    private File publicKeyFile;
    private File privateKeyFile;

    /**
     * El constructor asigna automáicamente las claves generadas por la clase RSAService
     */
    public AppKeys() {
        RSAService.generateKeys();
        publicKeyFile = new File(RSAService.KEY_PATH + RSAService.PUBLIC_AUTO);
        privateKeyFile = new File(RSAService.KEY_PATH + RSAService.PRIVATE_AUTO);

        System.out.println(privateKeyFile.toString());
        System.out.println(publicKeyFile.toString());
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
}
