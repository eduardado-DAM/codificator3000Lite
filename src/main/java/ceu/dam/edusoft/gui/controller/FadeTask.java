package ceu.dam.edusoft.gui.controller;

import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class FadeTask extends Task<Void> {

    private MainMenuController mainMenuController;
    public FadeTask(MainMenuController mainMenuController) {
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
