package uml.pr2Ex4;

public class Privatkunde extends Kunde {

    private String vorname;
    private String nachname;
    private Adresse postAdresse;

    public Privatkunde(Konto[] konto, String vorname, String nachname, Adresse postAdresse) {
        super(konto);
        this.vorname = vorname;
        this.nachname = nachname;
        this.postAdresse = postAdresse;
    }
}
