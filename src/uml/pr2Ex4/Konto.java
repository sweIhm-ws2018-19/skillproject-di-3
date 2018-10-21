package uml.pr2Ex4;

public class Konto {

    private String bezeichnung;

    public Geldbetrag saldo();

    public Geldbetrag (Geldbetrag betrag);

    private Kunde[] zeichnungsberechtigter;

    public Konto(Kunde[] zeichnungsberechtigter) {

        if (zeichnungsberechtigter.length <= 0) {
            System.out.println("Es muss mindestens ein Zeichungsberechtigter existieren.");
            System.exit(0);
        }
        this.zeichnungsberechtigter = zeichnungsberechtigter;
    }
}
