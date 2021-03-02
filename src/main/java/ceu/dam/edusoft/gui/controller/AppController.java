package ceu.dam.edusoft.gui.controller;

import ceu.dam.edusoft.gui.util.AppKeys;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
    Diseño del controlador:
    - Existe un único Stage para toda la App pero está diseñado para que pueda cambiar (mejoras futuras)
    - Las Scene pueden cambiar
    - Cada Scene tiene un BorderPane principal, el famoso borderPaneWindow. Es como la pantalla de una televisión,
        que irá mostrando distintos canales.
 */
public abstract class AppController {

    private AppKeys appKeys; // Las claves Pública y Privada de la aplicación

    private static Stage stage; //El único escenario de la App
    protected BorderPane borderPaneWindow; // El panel que irá cambiando
    private Map<String, Object> parameters; // Datos no persistentes de la App
    private AppController currentController; // El controlador de la Scene que está en uso
    private AppController currentPaneController; //El controlador del Panel que está en uso


    public AppController() {
        System.out.println("AppController.AppController");
        parameters = new HashMap<>();
        appKeys = new AppKeys();
    }

    //Getters & Setters----------------------------------------------------------------

    public AppKeys getAppKeys() {
        return appKeys;
    }

    public void setBpWindow(BorderPane borderPane) {
        borderPaneWindow = borderPane;
    }

    public AppController getCurrentPaneController() {
        return currentPaneController;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        AppController.stage = stage;
    }

    protected void setCurrentController(AppController controller) {
        currentController = controller;
    }

    public void setCurrentPaneController(AppController currentPaneController) {
        this.currentPaneController = currentPaneController;
    }

    // Métodos implementados en el controlador padre--------------------------------------------

    /**
     * Cambia el espacio central del BorderPane con un Anchor pane que le pasemos por parámetro
     *
     * @param fxmlPanel
     * @throws IOException
     */
    public void changePane(String fxmlPanel) throws IOException, InterruptedException {

        //guarda el estado del panel
        savePanelState();

        //cargamos el siguiente panel
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPanel));
        AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

        //se obtiene el controlador del panel
        AppController appController = fxmlLoader.getController();
        appController.init(); // se arranca el controlador
        setCurrentPaneController(appController);

        //lo asignamos al famoso borderPaneWindow
        borderPaneWindow.setCenter(anchorPane);


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

    // Métodos abstractos------------------------------------ (algunos no se usan pero están así por si la app crece)

    /**
     * Carga inicial de datos y componentes de la escena
     */
    public abstract void init() throws InterruptedException;

    /**
     * Obtiene el famoso BorderPane
     * @return
     */
    public abstract BorderPane getBpWindow();

    protected abstract void savePanelState();

    /**
     * Guarda los datos de entrada, selección de los usuarios
     */
    protected abstract void saveSceneState();

    /**
     * Carga el último estado de la escena y/o del panel
     */
    protected abstract void loadSceneState();

    protected abstract void loadPanelState();


}
