package ceu.dam.edusoft.gui.controller;

import ceu.dam.edusoft.gui.util.C3kUtil;
import ceu.dam.edusoft.service.RSAService;
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
        if(eventType.equals(MouseEvent.MOUSE_ENTERED) || eventType.equals(MouseEvent.MOUSE_EXITED) ){

            C3kUtil.handleC3KMouseEvents(event);
        }else if(event.getEventType().equals(ActionEvent.ACTION)){

            Button button = (Button) event.getSource();
            String buttonId = button.getId();

            if(buttonId.equals(btDescifrar.getId())){
                String clearString = deciferMsg(taLienzo.getText()); //descifra el mensaje
                taDescifrado.setText(clearString); //lo muestra al usuario

            }


        }


    }

    private String deciferMsg(String codedMsg) {
        return RSAService.descifra(codedMsg);
    }

    private void handleGraphicEvents(Event event) {

    }
}
