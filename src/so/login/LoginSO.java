package so.login;

import domain.AbstractDomainObject;
import domain.Korisnik;
import repository.db.DBBroker;
import so.AbstractSO;

import java.util.ArrayList;
import java.util.List;

public class LoginSO extends AbstractSO {
    Korisnik ulogovaniKorisnik;

    @Override
    protected void precondition(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Korisnik)) {
            throw new Exception("Parametar nije klase korisnik!");
        }
    }

    @Override
    protected void executeOperation(AbstractDomainObject ado) throws Exception {
        Korisnik k = (Korisnik) ado;
        List<Korisnik> korisnici = (ArrayList<Korisnik>) (ArrayList<?>) DBBroker.getInstance().selectWithCriteria(ado);

        for (Korisnik korisnik : korisnici) {
            if (korisnik.getUsername().equals(k.getUsername()) && korisnik.getSifra().equals(k.getSifra())) {
                ulogovaniKorisnik = korisnik;
                return;
            }
        }

        throw new Exception("Ne postoji korisnik sa tim kredencijalima.");
    }

    public Korisnik getUlogovani() {
        return ulogovaniKorisnik;
    }
}
