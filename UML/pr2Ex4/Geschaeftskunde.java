package uml.pr2Ex4;

public class Geschaeftskunde extends Kunde {

    private String firmenname;
    private Adresse domizilAdresse;

    public Geschaeftskunde(Konto[] konto, String firmenname, Adresse domizilAdresse) {
        super(konto);
        this.firmenname = firmenname;
        this.domizilAdresse = domizilAdresse;
    }

}
