package ceu.dam.edusoft.gui.util;

import ceu.dam.edusoft.gui.controller.AppController;
import ceu.dam.edusoft.service.RSAService;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class C3kUtil {

    public static void informUser(String msg){

        System.out.println(">>Error" + msg);
    }

    /**
     * Implementa efecto brillo cuando se pasa el ratón por encima de un botón
     * @param event
     */
    public static void handleC3KMouseEvents(Event event) {
        Glow glow = new Glow();
        glow.setLevel(10);
        if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
            ((Button) event.getSource()).setEffect(glow);
        }
        if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
            ((Button) event.getSource()).setEffect(null);
        }
        if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(5);
            ((Button) event.getSource()).setEffect(colorAdjust);
        }
        if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
            ((Button) event.getSource()).setEffect(null);
        }
    }

    /**
     *
     * @param title
     */

    /**
     * Muestra al usuario un selecto de archivos
     * @param title
     * @return un File con el archivo seleccionado por el usuario
     */
    public static File selectFile(String title) {
        File filechosen;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);

        fileChooser.setInitialDirectory(new File(RSAService.KEY_PATH));
        filechosen = fileChooser.showOpenDialog(AppController.getStage());

        return filechosen;
    }

    public class ErrorString {
        public static final String ES_PANEL_CHANGE_ERROR = "Error al cambiar de paneles";
    }
}
