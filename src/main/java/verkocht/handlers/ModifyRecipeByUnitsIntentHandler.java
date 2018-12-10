package verkocht.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import verkocht.model.PhrasesForAlexa;
import verkocht.model.Recipe;

public class ModifyRecipeByUnitsIntentHandler implements RequestHandler {
    static boolean state = false;

    public static final String INGREDIENT_SLOT = "Ingredient";
    public static final String INGREDIENT_VALUE_SLOT = "Ingredient_Value";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("ModifyRecipeByUnitsIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        String speechText = PhrasesForAlexa.MODIFY_UNIT_ERROR;
        Recipe recipeToModify = Recipe.getSavedRecipe();

        try {
            if (recipeToModify != null) {
                if (state) {
                    String getIngredient = (String) sessionAttributes.get(INGREDIENT_SLOT);
                    int getIngredientValue = (int) sessionAttributes.get(INGREDIENT_VALUE_SLOT);
                    boolean worked = recipeToModify.modifyByUnit(getIngredient, getIngredientValue);
                    if (worked) {
                        speechText = PhrasesForAlexa.MODIFY_UNIT_DONE;
                        resetState();
                    } else {
                        speechText = PhrasesForAlexa.MODIFY_UNIT_NOT_DONE;
                    }
                } else {
                    speechText = PhrasesForAlexa.MODIFY_UNIT_WELCOME;
                    toggleState();
                }
            }
        } catch (Exception e) {
            speechText = PhrasesForAlexa.MODIFY_UNIT_ERROR + "Exception";
        }

        return input.getResponseBuilder().withSpeech(speechText).withSimpleCard("Rezeptschritte", speechText)
                .withReprompt("Wie kann ich dir helfen?").withShouldEndSession(false).build();
    }

    public static void resetState() {
        state = false;
    }

    public static void toggleState() {
        state = !state;
    }
}