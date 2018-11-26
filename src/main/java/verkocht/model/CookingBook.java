package verkocht.model;

import java.util.ArrayList;
import java.util.List;

/*
 * The cooking book that holds all recipes and categories.
 */
public class CookingBook {
    private List<Recipe> favorites = new ArrayList<Recipe>();
    private List<Recipe> allRecipes = new ArrayList<Recipe>();
    private List<Ingredient> allIngredients = new ArrayList<Ingredient>();;

    public CookingBook() {
        //add Ingredients
        allIngredients.add(new Ingredient ("Fleisch", Unit.GRAMM));
        allIngredients.add(new Ingredient ("Ei", Unit.STUECK));
        allIngredients.add(new Ingredient ("Mehl", Unit.GRAMM));
        allIngredients.add(new Ingredient ("Erdbeeren", Unit.GRAMM));
        allIngredients.add(new Ingredient ("Milch", Unit.MILLILITER));
        allIngredients.add(new Ingredient ("Zucker", Unit.GRAMM));
        allIngredients.add(new Ingredient ("Nudeln", Unit.GRAMM));
        allIngredients.add(new Ingredient ("Salz", Unit.BRISE));
        allIngredients.add(new Ingredient ("Nudeln", Unit.GRAMM));
        allIngredients.add(new Ingredient ("Tomatensauce", Unit.GRAMM));
        allIngredients.add(new Ingredient ("Paniermehl", Unit.GRAMM));
        allIngredients.add(new Ingredient ("Öl", Unit.MILLILITER));
        allIngredients.add(new Ingredient ("Eis", Unit.GRAMM));



        // Schnitzel
        Recipe schniztelRec = new Recipe("schnitzel", Category.MEAT);
        schniztelRec. getIngredients().put(getIngredientByName("Fleisch"),200);
        schniztelRec. getIngredients().put(getIngredientByName("Ei"),1);
        schniztelRec. getIngredients().put(getIngredientByName("Mehl"),100);
        schniztelRec. getIngredients().put(getIngredientByName("Mehl"),50);
        schniztelRec. getIngredients().put(getIngredientByName("Paniermehl"),50);
        schniztelRec. getIngredients().put(getIngredientByName("Öl"),40);
        schniztelRec.getSteps().add("Zuerst wird wird das Mehl auf einen Teller gestreut.");
        schniztelRec.getSteps().add("Danach das Ei mit der Milch verrühren und in einen Teller gießen");
        schniztelRec.getSteps().add("Danach wird das Paniermehl im dritten Teller mit einer Prise Salz vermischt.");
        schniztelRec.getSteps().add("Dann auf beide Seiten der Schnitzel etwas Salz und Pfeffer streuen und es von beiden Seiten mit dem Mehl bestreuen.");
        schniztelRec.getSteps().add("Danach in dem verquirlten Ei und zuletzt im Paniermehl wälzen");
        schniztelRec.getSteps().add("Öl in einer Pfanne schmelzen lassen und die Schnitzel goldbraun braten lassen");
        allRecipes.add(schniztelRec);

        

        // Erdbeermilchshake
        Recipe milchshakeRec = new Recipe("milchshake", Category.VEGETARIAN);
        milchshakeRec.getIngredients().put(getIngredientByName("Erdbeeren"),200);
        milchshakeRec.getIngredients().put(getIngredientByName("Milch"),400);
        milchshakeRec.getIngredients().put(getIngredientByName("Zucker"),20);
        milchshakeRec.getIngredients().put(getIngredientByName("Eis"),200);

        milchshakeRec.getSteps().add("Erdbeeren waschen, von den Blättern befreien und gut abtropfen lassen.");
        milchshakeRec.getSteps().add("Alle Zutaten mit einem Mixer vermischen.");
        milchshakeRec.getSteps().add("In Gläser füllen.");
        allRecipes.add(milchshakeRec);
        
        // Nudeln mit Tomatensauce
        Recipe nudelnMitTomatensoßeRec = new Recipe("nudelnMitTomatensoße", Category.VEGAN);
        nudelnMitTomatensoßeRec.getIngredients().put(getIngredientByName("Nudeln"),200);
        nudelnMitTomatensoßeRec.getIngredients().put(getIngredientByName("Salz"),1);
        nudelnMitTomatensoßeRec.getIngredients().put(getIngredientByName("Tomatensauce"),100);
        nudelnMitTomatensoßeRec.getSteps().add("Nudeln in kochendem Salzwasser bissfest garen.");
        nudelnMitTomatensoßeRec.getSteps().add("Tomatensoße aufwärmen.");
        nudelnMitTomatensoßeRec.getSteps().add("Die Nudeln abgießen und mit der Sauce heiß servieren.");    
        allRecipes.add(nudelnMitTomatensoßeRec);
    }

    /**
     * Returns recipe for the given name 
     * @param name name of the recipe to be found
     * @return recipe if the recipe is in the cooking book and null otherwise
     */
    public Recipe findByName(String name) {
        for (Recipe recipe : allRecipes) {
            if (recipe.getName().equals(name)) {
                return recipe;
            } 
        }
        return null;
    }

    /**
     * This method filters all recipes by their category
     * @param category
     * @return list of filtered recipes 
     */
    public List<Recipe> findByCategory(Category category) {
    	List<Recipe> a = new ArrayList<Recipe>();
			for (int i = 0; i < allRecipes.size(); i++) {
				if(allRecipes.get(i).getCategory() == category) {
				a.add(allRecipes.get(i));
				}
			}
			return a;
    }

    /**
     * 
     * @return
     */
    public Recipe findByFavorite(String favorite) {
        return null;
    }

    public List<Recipe> getAllRecipes() {
        return allRecipes;
    }

    public Ingredient getIngredientByName(String name) {
        for (Ingredient ingr : allIngredients) {
            if (ingr.getIngredient().equals(name)) {
                return ingr;
            }
        }
        return null;
    }

    public List<Ingredient> getAllIngredients() {
        return allIngredients;
    }
    
}