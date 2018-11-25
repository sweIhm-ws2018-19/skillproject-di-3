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
   
        // Schnitzel
        Recipe schniztelRec = new Recipe("schnitzel");
        schniztelRec. getIngredients().put(getIngredientByName("Fleisch"),200);
        schniztelRec. getIngredients().put(getIngredientByName("Ei"),1);
        schniztelRec. getIngredients().put(getIngredientByName("Mehl"),100);
        allRecipes.add(schniztelRec);

        // Erdbeermilchshake
        Recipe milchshakeRec = new Recipe("milchshake");
        milchshakeRec.getIngredients().put(getIngredientByName("Erdbeeren"),100);
        milchshakeRec.getIngredients().put(getIngredientByName("Milch"),500);
        milchshakeRec.getIngredients().put(getIngredientByName("Zucker"),50);
        allRecipes.add(milchshakeRec);
        
        // Nudeln mit Tomatensauce
        Recipe nudelnMitTomatensoßeRec = new Recipe("nudelnMitTomatensoße");
        milchshakeRec.getIngredients().put(getIngredientByName("Nudeln"),200);
        milchshakeRec.getIngredients().put(getIngredientByName("Salz"),1);
        milchshakeRec.getIngredients().put(getIngredientByName("Tomatensauce"),100);
        allRecipes.add(nudelnMitTomatensoßeRec);
    }

    /**
     * 
     * @param name
     * @return
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
     * 
     * @param category
     * @return
     */
    public Recipe findByCategory(Category category) {
        return null;
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
