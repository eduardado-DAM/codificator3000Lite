package ceu.dam.edusoft.gui.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class DeciferPanelController extends AppController implements EventHandler {

    @FXML
    private Button btDescifrar;

    @FXML
    private Label lbDescifrar;

    @FXML
    private TextArea taDescifrado;

    @FXML
    private TextArea taLienzo;



    @FXML
    void cifrar(ActionEvent event) {

        if (!taLienzo.getText().isEmpty()) {

        }

        //usa la clave pública para cifrar el mensaje

        //cambia a la pantalla de descifrar

        //carga el mensaje cifrado en el textarea de la pantalla de cifrado

    }


    @Override
    public void init() throws InterruptedException {

        setCurrentPaneController(this); // establece el controlador de panel en uso
        addButtonEvents();
        labelTransparent();
    }

    private void addButtonEvents() {

        btDescifrar.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, this);
        btDescifrar.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, this);

        btDescifrar.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        btDescifrar.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
    }

    private void labelTransparent() {

        lbDescifrar.setMouseTransparent(true);

    }


    @Override
    protected void saveSceneState() {

    }

    @Override
    protected void savePanelState() {

    }


    @Override
    protected void loadSceneState() {

    }

    @Override
    protected void loadPanelState() {

    }

    @Override
    public BorderPane getBpWindow() {
        return null;
    }


    @Override
    public void handle(Event event) {

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
}
