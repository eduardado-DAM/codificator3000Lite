package ceu.dam.edusoft.server;

import ceu.dam.edusoft.controller.AppController;
import ceu.dam.edusoft.controller.MainMenuController;
import ceu.dam.edusoft.controller.OnOffStyle;
import javafx.scene.effect.Lighting;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private AppController appController;

    public void init(Integer port, AppController controller){

        appController = controller;

        //me veo obligado a lanzar un hilo porque la interfaz se bloquea mientras el servidor está esperando no entiendo muy bien porqué
        ServerSocket serverSocket = null;
        Socket clientSocket;
        try {
            serverSocket = new ServerSocket(port);

            //todo aquí hay que lanzar hilo o la interfaz se congela (no entiendo porqué)????
            EsperaPeticionesTask esperaPeticionesTask = new EsperaPeticionesTask(serverSocket);
            Thread thread = new Thread(esperaPeticionesTask);
            thread.start();

            //se informa al usuario (estamos fuera en el hilo de ejecución principal)

            /*String msgEsperando = "esperando peticiones";
            System.out.println(msgEsperando);
            ((MainMenuController)controller).getTaInfo().setVisible(true);
            ((MainMenuController)controller).getTaInfo().setText(msgEsperando);
            ((MainMenuController)controller).getBtStartServer().setDisable(true); //no se le deja al usuario que arranque otra vez o habrá fallo
            ((MainMenuController)controller).getConnectionLed().setStyle(OnOffStyle.ON);*/



        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
