package verkocht.model;

/**
 * Class thats holds all phrases that can be returned by alexa.
 * 
 * @author carnesirone
 */
public final class PhrasesForAlexa {

    /**
     * A constructor to warn a programmer, that it is not possible to initialize
     * this class.
     */
    private PhrasesForAlexa() {
        throw new IllegalStateException("Utility class");
    }

    // LaunchIntent
    public static final String WELCOME = "Hallo. Ich bin dein interaktives Kochbuch \"Verkocht\"! Was willst du tun?";
    public static final String FAVORTIE_RECIPE_LIST = "LIST_OF_FAVORITES";
    public static final String SELECTED_FAVORITE = "FavoritRecipe";

    public static final String END_READ_RECIPE_STEPS = "Das Rezept ist zu Ende. Sage \"stop\", um ins Hauptmenue zuruezukommen.";
    public static final String READ_RECIPE_STEPS = "Ich lese dir das Rezept %s vor. Sage \"WEITER\", wenn ich weiterlesen soll";
    public static final String REPEAT_RECIPE_INPUT = "Ich weiss nicht, welches Rezept ich vorlesen soll. Sag mir den Rezeptnamen. Sage zum Beispiel: ich moechte Schnitzel kochen.";
    public static final String START_RECIPE_INPUT = "Nenne das Rezept, das vorgelesen werden soll. Sage zum Beispiel: ich moechte Schnitzel kochen.";

    protected static final String[] REPROMTS = { "Sage zum Beispiel: Welche Kategorien gibt es?",
            "Sage zum Beispiel: Zur Rezeptauswahl", "Sage zum Beispiel: Ich habe einen Favoriten",
            "Ich kann dir helfen, sage zum Beispiel: Wie waehle ich ein Rezept aus?" };

    // Exception
    public static final String EXCEPTION = "Entschuldige. Ich habe dich nicht verstanden.";

    // Hauptmenue
    public static final String HAUPTMENUE = "Was willst du tun?\n"
            + "Um ein Rezept auszuwaehlen, sage \"Rezept auswaehlen.\"\n"
            + "Du kannst die Rezeptkategorien oder die Favoriten vorlesen lassen.\n"
            + "Sage dafuer: \"Kategorien,\" oder: \"Favoriten\".\n"
            + "Moechtest du ein neues Rezept zu den Favoriten hinzufuegen, sage: \"Rezept favorisieren\".\n"
            + "Um das Rezept zu aendern, sage: \"Rezept modifizieren.\"\n"
            + "Um die Rezeptschritte für ein ausgewaehltes Rezept gleich anzuhoeren, sage:\"Rezeptschritte vorlesen\"."
            + "Wenn du die Wahloptionen noch einmal anhoeren willst, sage: \"Zum Hauptmenue\".";

    // Select Recipe
    public static final String SELECT_RECIPE_INSTRUCTION = "Hier kannst du ein Rezept auswaehlen.\n"
            + "Du hast folgende Optionen:\n"
            + "Sage \"zur Namenauswahl\", wenn du ein Rezept direkt waehlen moechtest.\n"
            + "Sage \"zur Kategorieauswahl\", wenn du ein Rezept aus einer Kategorie suchst.\n"
            + "Sage \"zur Favoritenauswahl\", wenn du ein Rezept aus deinen Favoriten kochen willst.\n";
    public static final String SELECT_RECIPE = "Nenne das Rezept, das vorgelesen werden soll.";
    public static final String TELL_RECIPE = "Sage den Rezptnamen aus der vorgelesen Liste.";

    // TellSteps
    public static final String NEW_STEP = "Neuer Schritt %s %d toSet %d";
    public static final String TELL_STEP = "Ich lese dir das Rezept %s vor.";
    public static final String TELL_STEP_REPROMT = "Ich weiss nicht welches Rezept ich vorlesen soll."
            + "Sag mir den Rezeptnamen. Sage zum Beispiel: ich moechte Schnitzel kochen.";

    // Categories
    public static final String TELL_CATEGORIES = "Folgende Kategorien stehen zur Auswahl: %s.";

    // Favorites
    public static final String TELL_FAVORITE = "Das sind alle deine Favoriten: %s. "
            + "Waehle eine deiner Favoriten fuer den naechsten Schritt aus.";
    public static final String NO_FAVORITE_AVAILABLE = "Bis jetzt hast du noch keine Favoriten. Markiere zuerst Favoriten, damit ich sie dir vorlesen kann.";
    public static final String FAVORITE_AVAILABLE = "Das sind alle deine Favoriten: %s. Waeshle das Rezept, das vorgelesen werden soll.";
    public static final String FAVORITE_REPROMT = "Bis jetzt hast du noch keine Favoriten. "
            + "Markiere zuerst Favoriten, damit ich sie dir vorlesen kann.";

    // Modify
    public static final String MODIFY_RECIPE_INSTRUCTION = "Hier kannst du ein Rezept modifizieren.\n"
            + "Du kannst folgende Optionen:\n"
            + "Sage \"Rezept nach Zutaten anpassen\", wenn du ein Rezept nach einer bestimmten Zutatenmenge anpassen willst.\n"
            + "Sage \"Personenzahl aendern\", wenn du ein Rezept aufgrund einer geaenderten Personenzahl anpassen moechtest.\n";

    // Modify Unit
    public static final String MODIFY_UNIT_WELCOME = "Hier kannst du ein Rezept entsprechend einer Zutat modifizieren. Sage: Ei auf drei Stueck anpassen.";
    public static final String MODIFY_UNIT_REPROMT = "Bitte nenne eine Zutat des Rezept.";
    public static final String MODIFY_UNIT_ERROR = "Dies ist nicht moeglich.";
    public static final String MODIFY_UNIT_SELECT_RECIPE_FIRST = "Waehle bitte zuerst ein Rezept aus.";
    public static final String MODIFY_UNIT_NOT_DONE = "Das Rezept konnte nicht angepasst werden.";
    public static final String MODIFY_UNIT_DONE = "Das Rezept wurde angepasst.";

    // Tell ingredient list
    public static final String TELL_INGREDIENTS_OK = "Hier deine Zutatenliste:\n";
    public static final String TELL_INGREDIENTS_SELECT_FIRST = "Waehle bitte zuerst ein Rezept aus.";

    // Cancel-/Stop-/Help-/Fallback-Intent
    public static final String SORRY = "Tut mir leid, ich habe dich nicht verstanden.";
    public static final String HELP = "Wie kann ich dir helfen?";
    public static final String GOODBYE = "Auf Wiedersehen";

    // Temporary
    public static final String FAVORITES = "Hier kannst du spaeter ein Rezept favorisieren.";
    public static final String RECIPE = "Hier kannst du spaeter ein Rezept auswaehlen.";
    public static final String CATEGORIES = "Hier kannst du spaeter ein Rezept nach Kategorie auswaehlen.";
    public static final String MODIFY = "Hier kannst du spaeter ein Rezept modifizieren.";

    public static final String RECIPE_KEY = "RECIPE";
    public static final String RECIPE_SLOT = "FavoritRecipe";
}
