package threads;

import communication.*;
import controller.Controller;
import domain.*;

import java.net.Socket;
import java.util.List;

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

    private Response handleRequest(Request request) {
        switch (request.getOperation()) {
            case Operations.LOGIN:
                return login(request);
            case Operations.ADD_VOZNJA:
                return addVoznja(request);
            case Operations.ADD_TROTINET:
                return addTrotinet(request);
            case Operations.ADD_OSOBA:
                return addOsoba(request);
            case Operations.ADD_KORISNIK:
                return addKorisnik(request);
            case Operations.GET_ALL_VOZNJE:
                return getAllVoznje(request);
            case Operations.GET_ALL_TROTINET:
                return getAllTrotinet(request);
            case Operations.GET_ALL_KORISNIK:
                return getAllKorisnik(request);
            case Operations.GET_ALL_OSOBE:
                return getAllOsobe(request);
            case Operations.DELETE_TROTINET:
                return deleteTrotinet(request);
            case Operations.DELETE_KORISNIK:
                return deleteKorisnik(request);
            case Operations.GET_TROTINET_BY_ID:
                return getTrotinetById(request);
            case Operations.GET_TROTINET_BY_VRSTA:
                return getAllByVrsta(request);
            case Operations.GET_ALL_VOZNJE_BY_CRITERIA:
                return getAllVoznjeByCriteria(request);
            case Operations.GET_ALL_KORISNIK_BY_USERNAME:
                return getAllKorisnikByUsername(request);
            case Operations.GET_ALL_OSOBE_BY_BROJ_LK:
                return getOsobaByBrojLK(request);
            case Operations.UPDATE_KORISNIK:
                return updateKorisnik(request);
            case Operations.UPDATE_TROTINET:
                return updateTrotinet(request);
            default:
                return null;
        }

    }

    private Response updateTrotinet(Request request) {
        Response response = new Response();
        try {
            Trotinet trotinet = (Trotinet) request.getArgument();
            Controller.getInstance().updateTrotinet(trotinet);
            response.setResponseType(ResponseType.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response updateKorisnik(Request request) {
        Response response = new Response();
        try {
            Korisnik korisnik = (Korisnik) request.getArgument();
            Controller.getInstance().updateKorisnik(korisnik);
            response.setResponseType(ResponseType.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response addKorisnik(Request request) {
        Response response = new Response();
        Korisnik korisnik = (Korisnik) request.getArgument();
        try {
            Controller.getInstance().addKorisnik(korisnik);
            response.setResponseType(ResponseType.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response addOsoba(Request request) {
        Response response = new Response();
        Osoba osoba = (Osoba) request.getArgument();
        try {
            Controller.getInstance().addOsoba(osoba);
            response.setResponseType(ResponseType.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response addTrotinet(Request request) {
        Response response = new Response();
        Trotinet trotinet = (Trotinet) request.getArgument();
        try {
            Controller.getInstance().addTrotinet(trotinet);
            response.setResponseType(ResponseType.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
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

    private Response addVoznja(Request request) {
        Response response = new Response();
        IznajmljivanjeTrotineta voznja = (IznajmljivanjeTrotineta) request.getArgument();
        try {
            Controller.getInstance().addVoznja(voznja);
            response.setResponseType(ResponseType.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getAllTrotinet(Request request) {
        Response response = new Response();
        try {
            List<Trotinet> trotineti = Controller.getInstance().getAllTrotinet();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(trotineti);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getAllKorisnik(Request request) {
        Response response = new Response();
        try {
            List<Korisnik> korisnici = Controller.getInstance().getAllKorisnik();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(korisnici);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getAllByVrsta(Request request) {
        Response response = new Response();
        try {
            List<Trotinet> trotineti = Controller.getInstance().getAllByVrsta((VrstaTrotinetaEnum) request.getArgument());
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(trotineti);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getTrotinetById(Request request) {
        Response response = new Response();
        try {
            Trotinet trotinet = Controller.getInstance().getTrotinetById((Long) request.getArgument());
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(trotinet);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getOsobaByBrojLK(Request request) {
        Response response = new Response();
        try {
            List<Osoba> osobe = Controller.getInstance().getByBrojLK((Long) request.getArgument());
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(osobe);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getAllVoznje(Request request) {
        Response response = new Response();
        try {
            List<IznajmljivanjeTrotineta> voznje = Controller.getInstance().getAllVoznje();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(voznje);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getAllVoznjeByCriteria(Request request) {
        Response response = new Response();
        try {
            List<IznajmljivanjeTrotineta> voznje = Controller.getInstance().getAllByCriteria(String.valueOf(request.getArgument()));
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(voznje);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getAllKorisnikByUsername(Request request) {
        Response response = new Response();
        try {
            List<Korisnik> korisnici = Controller.getInstance().getAllByUsername(String.valueOf(request.getArgument()));
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(korisnici);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response addAllVoznje(Request request) {
        Response response = new Response();
        try {
            Controller.getInstance().addAllVoznje((List<IznajmljivanjeTrotineta>) request.getArgument());
            response.setResponseType(ResponseType.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response deleteTrotinet(Request request) {
        Response response = new Response();
        try {
            Controller.getInstance().deleteTrotinet((long) request.getArgument());
            response.setResponseType(ResponseType.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response deleteKorisnik(Request request) {
        Response response = new Response();
        try {
            Controller.getInstance().deleteKorisnik(String.valueOf(request.getArgument()));
            response.setResponseType(ResponseType.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getAllOsobe(Request request) {
        Response response = new Response();
        try {
            List<Osoba> osobe = Controller.getInstance().getAllOsoba();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(osobe);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }
}
