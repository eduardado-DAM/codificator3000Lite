package ceu.dam.edusoft.server;

import javafx.concurrent.Task;

import java.net.ServerSocket;
import java.net.Socket;

public class EsperaPeticionesTask extends Task<Void> {

    private ServerSocket serverSocket;

    public EsperaPeticionesTask(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    protected Void call() throws Exception {
        Socket clientSocket = serverSocket.accept();
        System.out.println("Esperando peticiones"); //en qué consola se vería esto???
        System.out.println("ESTAMOS AQUIIIIIIIIIIII");

        return null;
    }

    @Override
    protected void succeeded() {
        System.out.println("Esperando peticiones"); //en qué consola se vería esto???
        System.out.println("ESTAMOS AQUIIIIIIIIIIII");
        super.succeeded();
    }

    @Override
    protected void failed() {
        super.failed();
    }
}
