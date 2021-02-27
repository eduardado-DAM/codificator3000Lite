package ceu.dam.edusoft.gui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
    Diseño del controlador:
    - Existe un único Stage para toda la App
    - Las Scene pueden cambiar
        - Cada Scene tiene un BorderPane principal, el famoso borderPaneWindow. Es como la pantalla de una televisión, que irá mostrando distintos canales.
 */
public abstract class AppController {

    private static Stage stage; //El único escenario de la App
    protected BorderPane borderPaneWindow; //El panel que irá cambiando
    private Map<String, Object> parameters; //Datos no persistentes de la App
    private AppController currentController; //El controlador de la Scene que está en uso
    private AppController currentPaneController; //El controlador del Panel que está en uso




    public AppController() {
        parameters = new HashMap<>(); //instanciación del mapa que almacena información no persistente de la app
    }

    public void setBpWindow(BorderPane borderPane) {
        borderPaneWindow = borderPane;
    }

    /**
     * Establece los parámetros
     *
     * @param parameters
     */
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        AppController.stage = stage;
    }

    /**
     * Cambia escenas
     *
     * @param fxmlPath
     * @throws IOException
     */
    public void changeScene(String fxmlPath) throws IOException, InterruptedException {
        //Carga de escena
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent, 640, 480);
        getStage().setScene(scene);

        //Controller
        AppController appController = fxmlLoader.getController(); //obtenemos el controller de la escena que hayamos cargado
        appController.init(); //invocamos el método, que contiene aquellas acciones que queremos que ocurran siempre cuando se carga una escena
        appController.setParameters(parameters);
        appController.setBpWindow(appController.getBpWindow());


    }

    /**
     * Establece el controlador de la Scene en uso
     *
     * @param controller
     */
    protected void setCurrentController(AppController controller) {
        currentController = controller;
    }

    /**
     * Cambia el espacio central del BorderPane con un Anchor pane que le pasemos por parámetro
     *
     * @param fxmlPanel
     * @throws IOException
     */
    public void changePane(String fxmlPanel) throws IOException, InterruptedException {
        //cargamos el panel
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPanel));
        AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

        AppController appController = fxmlLoader.getController();
        appController.init();
        setCurrentPaneController(appController);

        //lo asignamos
        borderPaneWindow.setCenter(anchorPane);


    }



    /**
     * Carga inicial de datos y componentes de la escena
     */
    public abstract void init() throws InterruptedException;

    /**
     * Guarda los datos de entrada, selección de los usuarios
     */
    protected abstract void saveState();

    protected abstract void loadState();

    public abstract BorderPane getBpWindow();




    public void setCurrentPaneController(AppController currentPaneController) {
        this.currentPaneController = currentPaneController;
    }
}
