package ceu.dam.edusoft.gui.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainMenuController extends AppController implements EventHandler {


    @FXML
    private BorderPane bpWindow;

    @FXML
    private ImageView ivDarkLogo;

    @FXML
    private Button btLoadPrivateKey;

    @FXML
    private Button btLoadPublicKey;

    @FXML
    private Button btDecifer;

    @FXML
    private Button btCifer;

    @FXML
    private Label lbCifrar;

    @FXML
    private Label lbDescifrar;

    @FXML
    private Label lbLoadPubK;

    @FXML
    private Label lbLoadPriKey;

    @FXML
    void cifrar(ActionEvent event) throws IOException, InterruptedException {
        changePane(FXMLPATH.Panel.CIFRAR_PANEL);

    }

    @FXML
    void descifrar(ActionEvent event) throws IOException, InterruptedException {
        changePane(FXMLPATH.Panel.DECIFER_PANEL);
    }

    @FXML
    void loadPrivateKey(ActionEvent event) {

    }

    @FXML
    void loadPublicKey(ActionEvent event) {

    }


    @Override
    public void init() {

        setCurrentController(this); //se establece como controlador en uso en el controlador padre

        fadeLogo();

        addButtonEvents();

        labelTransparent();


    }


    /**
     * Evita que el texto de los botones sea sensible al ratón y estropee los efectos
     */
    private void labelTransparent() {
        lbCifrar.setMouseTransparent(true);
        lbDescifrar.setMouseTransparent(true);
        lbLoadPriKey.setMouseTransparent(true);
        lbLoadPubK.setMouseTransparent(true);
    }

    /**
     * Añade eventos de entrada y salida de ratón en botones
     */
    private void addButtonEvents() {
        btCifer.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, this);
        btCifer.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, this);
        btCifer.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        btCifer.addEventHandler(MouseEvent.MOUSE_RELEASED, this);

        btDecifer.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, this);
        btDecifer.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, this);
        btDecifer.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        btDecifer.addEventHandler(MouseEvent.MOUSE_RELEASED, this);

        btLoadPublicKey.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, this);
        btLoadPublicKey.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, this);
        btLoadPublicKey.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        btLoadPublicKey.addEventHandler(MouseEvent.MOUSE_RELEASED, this);

        btLoadPrivateKey.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, this);
        btLoadPrivateKey.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, this);
        btLoadPrivateKey.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        btLoadPrivateKey.addEventHandler(MouseEvent.MOUSE_RELEASED, this);








    }

    /**
     * Lanza un hilo que hace desaparecer al logo
     */
    private void fadeLogo() {
        FadeTask fadeTask = new FadeTask(this);
        Thread thread = new Thread(fadeTask);
        thread.start();
    }


    @Override
    protected void saveState() {

    }

    @Override
    protected void loadState() {

    }

    public BorderPane getBpWindow() {
        return bpWindow;
    }

    public ImageView getIvDarkLogo() {
        return ivDarkLogo;
    }

    public void setIvDarkLogo(ImageView ivDarkLogo) {
        this.ivDarkLogo = ivDarkLogo;
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
