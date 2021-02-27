package ceu.dam.edusoft;

import ceu.dam.edusoft.controller.AppController;
import ceu.dam.edusoft.controller.FXMLPATH;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {

        // Primera carga del Scene
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(FXMLPATH.Scene.MAIN_MENU));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent, 800, 500);

        //scene.addEventFilter(MouseEvent.ANY, e -> System.out.println( e)); //todo para saber qué evento de ratón capturar luego

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // CONTROLLER DEVICE
        /**
         * Este trozo de código sólo se ejecuta una vez, al cargar la App
         */
        AppController.setStage(stage); //le pasa el Stage al controlador principal (atributo estático por lo que no es necesario cargar la instancia de la clase)
        AppController mainMenuController = fxmlLoader.getController(); //obtengo el controlador del menú principal
        mainMenuController.setBpWindow(mainMenuController.getBpWindow()); //asigna, en el controlador padre, quíen es el famoso BorderPaneWindow
        mainMenuController.init(); //arranca los funciones iniciales del controlador de la escena

    }

    public static void main(String[] args) {
        launch();
    }

}