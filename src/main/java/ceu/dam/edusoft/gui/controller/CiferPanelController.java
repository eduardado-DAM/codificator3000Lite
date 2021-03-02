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

public class CiferPanelController extends AppController implements EventHandler {

    @FXML
    private Button btCifrar;

    @FXML
    private Label lbEnviar;

    @FXML
    private TextArea taCifrado;

    @FXML
    private TextArea taLienzo;


    @Override
    public void init() {
        System.out.println("CiferPanelController.init");

        setCurrentPaneController(this); //establece el controlador de Panel en uso

        addButtonEvents();

        labelTransparent();


    }


    private void addButtonEvents() {
        System.out.println("CiferPanelController.addButtonEvents");

        btCifrar.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, this);
        btCifrar.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, this);
        btCifrar.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        btCifrar.addEventHandler(MouseEvent.MOUSE_RELEASED, this);

        btCifrar.addEventHandler(ActionEvent.ACTION, this);


    }

    private void labelTransparent() {
        lbEnviar.setMouseTransparent(true);

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

            if (buttonId.equals(btCifrar.getId())) {

                if (!taLienzo.getText().isEmpty()) {
                    String mensajeClaro = taLienzo.getText();

                    /*if(getAppKeys().haveKeys()){ //todo porqué no?
                        taCifrado.setText(getAppKeys().cifra(mensajeClaro,getAppKeys().getPublicKeyFile()));
                    }else {
                        System.err.println("La app no tiene keys todavía");
                    }*/
                    taCifrado.setText(getAppKeys().cifra(mensajeClaro,getAppKeys().getPublicKeyFile()));

                }

            }


        }


    }
}
