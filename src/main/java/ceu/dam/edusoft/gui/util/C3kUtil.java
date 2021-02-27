package ceu.dam.edusoft.gui.util;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;

public class C3kUtil {

    public static void informUser(String msg){
        //todo implemetnar una alerta o algo así fino

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

    public class ErrorString {
        public static final String ES_PANEL_CHANGE_ERROR = "Error al cambiar de paneles";
    }
}
