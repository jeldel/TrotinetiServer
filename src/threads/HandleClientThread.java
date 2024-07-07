package threads;

import communication.*;
import controller.Controller;
import domain.Korisnik;

import java.net.Socket;

public class HandleClientThread extends Thread {
    private Socket socket;

    public HandleClientThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            Request request;
            try {
                request = (Request) new Receiver(socket).receive();
                Response response = handleRequest(request);
                new Sender(socket).send(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Response handleRequest(Request request){
        switch (request.getOperation()) {
            case Operations.LOGIN:
                return login(request);
            default:
                return null;
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
