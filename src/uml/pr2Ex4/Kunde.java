package uml.pr2Ex4;

public abstract class Kunde {

    private Konto[] konto;

    public Kunde(Konto[] konto) {
        if (konto.length <= 0) {
            System.out.println("Es muss mindestens ein Konto existieren.");
            System.exit(0);
        }
        this.konto = konto;
    }
}
