package uml.pr2Ex4;

public class Geschaeftskunde extends Kunde {

    private String firmenname;
    private Adresse domizilAdresse;

    public Geschaeftskunde(Konto[] konto, Adresse domizilAdresse) {
        super(konto);
        this.domizilAdresse = domizilAdresse;
    }

}
