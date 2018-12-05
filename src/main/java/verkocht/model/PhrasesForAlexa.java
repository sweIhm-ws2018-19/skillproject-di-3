package verkocht.model;

public final class PhrasesForAlexa {
    
    private PhrasesForAlexa() {
        throw new IllegalStateException("Utility class");
    }
    
    // LaunchIntent
    public static final String WELCOME = "Hallo. Ich bin dein interaktives Kochbuch \"Verkocht\"! Was willst du tun?";
    public static final String[] REPROMTS = {"Sage zum Beispiel: Welche Kategorien gibt es?",
			"Sage zum Beispiel: Zur Rezeptauswahl",
			"Sage zum Beispiel: Ich habe einen Favoriten",
			"Ich kann dir helfen, sage zum Beispiel: Wie waehle ich ein Rezept aus?"};
    
    // Select Recipe
    public static final String SELECT_RECIPE = "Nenne das Rezept, das vorgelesen werden soll.";
    public static final String TELL_RECIPE = "Sage den Rezptnamen aus der vorgelesen Liste.";
    
    // TellSteps
    public static final String NEW_STEP = "Neuer Schritt %s %d toSet %d";
    public static final String TELL_STEP = "Ich lese dir das Rezept %s vor.";
    public static final String TELL_STEP_REPROMT = "Ich weiss nicht welches Rezept ich vorlesen soll." +
    			"Sag mir den Rezeptnamen. Sage zum Beispiel: ich moechte Schnitzel kochen.";
    
    // Categories
    public static final String TELL_CATEGORIES = "Folgende Kategorien stehen zur Auswahl: %s.";
    
    // Favorites
    public static final String TELL_FAVORITE = "Das sind alle deine Favoriten: %s. "
    		+ "Waehle eine deiner Favoriten fuer den naechsten Schritt aus.";
    public static final String FAVORITE_REPROMT = "Bis jetzt hast du noch keine Favoriten. "
    		+ "Markiere zuerst Favoriten, damit ich sie dir vorlesen kann.";
    
    // Cancel-/Stop-/Help-/Fallback-Intent
    public static final String SORRY = "Tut mir leid, das weiss ich nicht. Sage einfach Hilfe.";
    public static final String HELP = "Wie kann ich dir helfen?";
    public static final String GOODBYE = "Auf Wiedersehen";
    
    // Temporary
    public static final String FAVORITES = "Hier kannst du spaeter ein Rezept favorisieren.";
    public static final String RECIPE = "Hier kannst du spaeter ein Rezept auswaehlen.";
    public static final String CATEGORIES = "Hier kannst du spaeter ein Rezept nach Kategorie auswaehlen.";
    public static final String MODIFY = "Hier kannst du spaeter ein Rezept modifizieren.";
}
