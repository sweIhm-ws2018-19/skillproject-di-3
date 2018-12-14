package verkocht.model;

import java.util.ArrayList;
import java.util.List;

/*
 * The cooking book that holds all recipes and categories.
 */
public abstract class CookingBook {
    private static List<Recipe> favorites = new ArrayList<>();
    private static List<Recipe> allRecipes = new ArrayList<>();
    private static List<Ingredient> allIngredients = new ArrayList<>();

    public static void initiateCookingBook() {
        // add ingredients to the list of ingredients
        allIngredients.add(new Ingredient("Fleisch", Unit.GRAMM));
        allIngredients.add(new Ingredient("Ei", Unit.STUECK));
        allIngredients.add(new Ingredient("Mehl", Unit.GRAMM));
        allIngredients.add(new Ingredient("Erdbeeren", Unit.GRAMM));
        allIngredients.add(new Ingredient("Milch", Unit.MILLILITER));
        allIngredients.add(new Ingredient("Zucker", Unit.GRAMM));
        allIngredients.add(new Ingredient("Salz", Unit.BRISE));
        allIngredients.add(new Ingredient("Nudeln", Unit.GRAMM));
        allIngredients.add(new Ingredient("Tomatensauce", Unit.GRAMM));
        allIngredients.add(new Ingredient("Paniermehl", Unit.GRAMM));
        allIngredients.add(new Ingredient("Oel", Unit.MILLILITER));
        allIngredients.add(new Ingredient("Eis", Unit.GRAMM));

        // Schnitzel
        Recipe schniztelRec = new Recipe("schnitzel", Category.MEAT, 4, 40);
        schniztelRec.getIngredients().put(getIngredientByName("Fleisch"), 200);
        schniztelRec.getIngredients().put(getIngredientByName("Ei"), 1);
        schniztelRec.getIngredients().put(getIngredientByName("Mehl"), 100);
        schniztelRec.getIngredients().put(getIngredientByName("Mehl"), 50);
        schniztelRec.getIngredients().put(getIngredientByName("Paniermehl"), 50);
        schniztelRec.getIngredients().put(getIngredientByName("Oel"), 40);
        schniztelRec.getSteps().add("Zuerst wird wird das Mehl auf einen Teller gestreut.");
        schniztelRec.getSteps().add("Danach das Ei mit der Milch verruehren und in einen Teller giessen");
        schniztelRec.getSteps().add("Danach wird das Paniermehl im dritten Teller mit einer Prise Salz vermischt.");
        schniztelRec.getSteps().add(
                "Dann auf beide Seiten der Schnitzel etwas Salz und Pfeffer streuen und es von beiden Seiten mit dem Mehl bestreuen.");
        schniztelRec.getSteps().add("Danach in dem verquirlten Ei und zuletzt im Paniermehl waelzen");
        schniztelRec.getSteps().add("Oel in einer Pfanne schmelzen lassen und die Schnitzel goldbraun braten lassen");
        allRecipes.add(schniztelRec);

        // Erdbeermilchshake
        Recipe milchshakeRec = new Recipe("milchshake", Category.VEGETARIAN, 2, 15);
        milchshakeRec.getIngredients().put(getIngredientByName("Erdbeeren"), 200);
        milchshakeRec.getIngredients().put(getIngredientByName("Milch"), 400);
        milchshakeRec.getIngredients().put(getIngredientByName("Zucker"), 20);
        milchshakeRec.getIngredients().put(getIngredientByName("Eis"), 200);

        milchshakeRec.getSteps().add("Erdbeeren waschen, von den Blaettern befreien und gut abtropfen lassen.");
        milchshakeRec.getSteps().add("Alle Zutaten mit einem Mixer vermischen.");
        milchshakeRec.getSteps().add("In Glaeser fuellen.");
        allRecipes.add(milchshakeRec);

        // Nudeln mit Tomatensauce
        Recipe nudelnMitTomatensauce = new Recipe("nudeln", Category.VEGAN, 4, 20);
        nudelnMitTomatensauce.getIngredients().put(getIngredientByName("Nudeln"), 200);
        nudelnMitTomatensauce.getIngredients().put(getIngredientByName("Salz"), 1);
        nudelnMitTomatensauce.getIngredients().put(getIngredientByName("Tomatensauce"), 100);
        nudelnMitTomatensauce.getSteps().add("Nudeln in kochendem Salzwasser bissfest garen.");
        nudelnMitTomatensauce.getSteps().add("Tomatensauce aufwaermen.");
        nudelnMitTomatensauce.getSteps().add("Die Nudeln abgiessen und mit der Sauce heiss servieren.");
        allRecipes.add(nudelnMitTomatensauce);

        // add recipes to the list of favorites
        saveFavorite(milchshakeRec.getName());
        saveFavorite(nudelnMitTomatensauce.getName());
    }

    /**
     * Returns recipe for the given name
     * 
     * @param name
     *            name of the recipe to be found
     * @return recipe if the recipe is in the cooking book and null otherwise
     */
    public static Recipe findByName(String name) {
        for (Recipe recipe : allRecipes) {
            if (recipe.getName().equals(name)) {
                return recipe;
            }
        }
        return null;
    }

    /**
     * Returns recipe for the given name from favorite list.
     * 
     * @param name
     *            name of the recipe to be found
     * @return recipe if the recipe is in the cooking book and null otherwise
     */
    public static Recipe findFavoriteByName(String name) {
        for (Recipe recipe : allRecipes) {
            if (recipe.getName().equals(name)) {
                return recipe;
            }
        }
        return null;
    }

    public static boolean saveFavorite(String name) {
        boolean toReturn = false;
        Recipe recipeToAdd = findByName(name);
        if (recipeToAdd != null && !favorites.contains(recipeToAdd)) {
            favorites.add(recipeToAdd);
            toReturn = true;
        }
        return toReturn;
    }

    /**
     * Returns all Favorits in a String.
     * 
     * @return all Favorits in a String.
     */
    public static String getAllFavorites() {
        StringBuilder categoryString = new StringBuilder();
        int length = favorites.size();

        for (int i = 0; i < length; i++) {
            categoryString.append(favorites.get(i).getName());

            if (i == length - 2) {
                categoryString.append(" und ");
            } else if (i != length - 1) {
                categoryString.append(", ");
            }
        }

        return categoryString.toString();
    }

    /**
     * This method filters all recipes by their category
     * 
     * @param category
     * @return list of filtered recipes
     */
    public static List<Recipe> findByCategory(Category category) {
        List<Recipe> recipe = new ArrayList<>();
        for (int i = 0; i < allRecipes.size(); i++) {
            if (allRecipes.get(i).getCategory() == category) {
                recipe.add(allRecipes.get(i));
            }
        }
        return recipe;
    }

    public static List<Recipe> getAllRecipes() {
        return allRecipes;
    }

    public static Ingredient getIngredientByName(String name) {
        for (Ingredient ingr : allIngredients) {
            if (ingr.getIngredient().equals(name)) {
                return ingr;
            }
        }
        return null;
    }

    public static List<Ingredient> getAllIngredients() {
        return allIngredients;
    }

    public static List<Recipe> getFavorites() {
        return favorites;
    }

}