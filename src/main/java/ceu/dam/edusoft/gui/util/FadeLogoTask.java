package ceu.dam.edusoft.gui.util;

import ceu.dam.edusoft.gui.controller.MainMenuController;
import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Es necesario lanzar un hilo para desvacener el logo, de lo contrario el usuario tendría que esperar a que el efecto
 * termine antes de poder interactuar con la interfaz.
 */
public class FadeLogoTask extends Task<Void> {

    private MainMenuController mainMenuController;
    public FadeLogoTask(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }

    @Override
    protected Void call() throws Exception {
        fade(mainMenuController.getIvDarkLogo());
        return null;
    }

    @Override
    protected void succeeded() {

    }

    @Override
    protected void failed() {
    }

    public void fade(ImageView imageView) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(4000));
        fadeTransition.setNode(imageView);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }


}
