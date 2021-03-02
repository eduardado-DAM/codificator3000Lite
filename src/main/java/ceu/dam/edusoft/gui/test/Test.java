package ceu.dam.edusoft.gui.test;

import ceu.dam.edusoft.gui.util.AppKeys;

import static ceu.dam.edusoft.gui.util.AppKeys.*;


public class Test {

    public static void main(String[] args) {
        AppKeys appKeys = new AppKeys();
        /*appKeys.generateKeys(KEY_PATH, PUBLIC_KEY_FILE_NAME, PRIVATE_KEY_FILE_NAME);*/
        appKeys.deleteGeneratedKeyes(KEY_PATH, PUBLIC_KEY_FILE_NAME, PRIVATE_KEY_FILE_NAME);
    }


}
