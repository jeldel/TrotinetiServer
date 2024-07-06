package server;

import communication.*;
import controller.Controller;
import domain.Korisnik;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void startServer() throws Exception {
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("Cekam klijenta");
        Socket socket = serverSocket.accept();
        System.out.println("Klijent je povezan sa serverom");

        handleClient(socket);
    }

    private void handleClient(Socket socket) throws Exception {
        while (true) {
            try {
                //ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                //Request request = (Request) in.readObject();

                Request request = (Request) new Receiver(socket).receive();

                Response response = null;

                int operation = request.getOperation();
                switch (operation) {
                    case Operations.LOGIN:
                        response = login(request);
                        break;
                    default:
                        break;
                }

                new Sender(socket).send(response);

                //ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                //out.writeObject(response);
                //out.flush();


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private Response login(Request request) {
        Response response = new Response();
        Korisnik requestKorisnik = (Korisnik) request.getArgument();
        try {
            Korisnik korisnik = Controller.getInstance().login(requestKorisnik.getUsername(), requestKorisnik.getSifra());
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(korisnik);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }
}
