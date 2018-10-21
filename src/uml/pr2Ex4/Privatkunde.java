package uml.pr2Ex4;

public class Privatkunde extends Kunde {

    private String vorname;
    private String nachname;
    private Adresse postAdresse;

    public Privatkunde(Konto[] konto, Adresse postAdresse) {
        super(konto);
        this.postAdresse = postAdresse;
    }
}
