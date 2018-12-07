package verkocht.model;

public class PhrasesForAlexa {
    
    private PhrasesForAlexa() {
        throw new IllegalStateException("Utility class");
    }
    
    public static final String WELCOME = "Hallo. Ich bin dein interaktives Kochbuch \"Verkocht\"! Was willst du tun?";
    public static final String END_READ_RECIPE_STEPS = "Das Rezept ist zu Ende. Sage \"stop\", um ins Hauptmenü zurückzukommen.";
    public static final String READ_RECIPE_STEPS = "Ich lese dir das Rezept %s vor. Sage \"WEITER\", wenn ich weiterlesen soll";
    public static final String REPEAT_RECIPE_INPUT ="Ich weiss nicht, welches Rezept ich vorlesen soll. Sag mir den Rezeptnamen. Sage zum Beispiel: ich möchte Schnitzel kochen.";
    public static final String START_RECIPE_INPUT = "Nenne das Rezept, das vorgelesen werden soll. Sage zum Beispiel: ich möchte Schnitzel kochen.";

}
