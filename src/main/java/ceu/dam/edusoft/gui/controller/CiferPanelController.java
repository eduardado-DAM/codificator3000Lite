package ceu.dam.edusoft.gui.controller;

import ceu.dam.edusoft.gui.util.C3kUtil;
import ceu.dam.edusoft.service.RSAService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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



    @FXML
    void cifrar(ActionEvent event) {

        if (!taLienzo.getText().isEmpty()) {
            String mensajeClaro = taLienzo.getText();

            if(getAppKeys().getPublicKeyFile() != null){
                File publicKeyfile = getAppKeys().getPublicKeyFile();
                String mensajeCifrado = RSAService.cifra(mensajeClaro,publicKeyfile);
                taCifrado.setText(mensajeCifrado);

            }else{
                System.err.println("La app no tiene clave pública cargada");
            }

        }

    }


    @Override
    public void init()  {

        setCurrentPaneController(this); //establece el controlador de Panel en uso

        addButtonEvents();

        labelTransparent();


    }

    private void addButtonEvents() {
        btCifrar.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, this);
        btCifrar.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, this);

        btCifrar.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        btCifrar.addEventHandler(MouseEvent.MOUSE_RELEASED, this);

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

        C3kUtil.handleC3KMouseEvents(event);


    }
}
