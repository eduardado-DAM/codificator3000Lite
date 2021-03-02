package ceu.dam.edusoft.gui.controller;

import ceu.dam.edusoft.gui.util.AppKeys;
import ceu.dam.edusoft.gui.util.C3kUtil;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.File;

public class DeciferPanelController extends AppController implements EventHandler {

    @FXML
    private Button btDescifrar;

    @FXML
    private Label lbDescifrar;

    @FXML
    private TextArea taDescifrado;

    @FXML
    private TextArea taLienzo;


    @Override
    public void init() throws InterruptedException {

        setCurrentPaneController(this); // establece el controlador de panel en uso
        addButtonEvents();
        labelTransparent();
    }

    private void addButtonEvents() {

        //gráficos
        btDescifrar.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, this);
        btDescifrar.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, this);

        btDescifrar.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        btDescifrar.addEventHandler(MouseEvent.MOUSE_RELEASED, this);

        //navegación y acciones
        btDescifrar.addEventHandler(ActionEvent.ACTION, this);
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

        EventType eventType = event.getEventType();
        // los eventos para hacer brillar los botones se repiten mucho
        if (eventType.equals(MouseEvent.MOUSE_ENTERED) || eventType.equals(MouseEvent.MOUSE_EXITED)) {

            C3kUtil.handleC3KMouseEvents(event);
        } else if (event.getEventType().equals(ActionEvent.ACTION)) {

            Button button = (Button) event.getSource();
            String buttonId = button.getId();

            if (buttonId.equals(btDescifrar.getId())) {

                if (!taLienzo.getText().isEmpty()) {
                    String mensajeCifrado = taLienzo.getText();

                    /*if (getAppKeys().getPrivateKeyFile() != null) {
                        File privateFileKey = getAppKeys().getPrivateKeyFile();
                        mensajeCifrado = AppKeys.descifra(mensajeCifrado, privateFileKey);
                        taDescifrado.setText(mensajeCifrado);

                    } else {
                        System.err.println("La app no tiene clave privada cargada");
                    }*/

                }

            }


        }


    }


}
