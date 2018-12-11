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
    public static final String INGREDIENT_VALUE_SLOT = "IngredientValue";

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
                String getIngredient = "";
                String getIngredientSlotValue = "";
                int getIngredientValue = 0;
                boolean worked = false;
                if (state) {
                    try {
                        getIngredient = (String) sessionAttributes.get(INGREDIENT_SLOT);
                        getIngredientSlotValue = (String) sessionAttributes.get(INGREDIENT_VALUE_SLOT);
                        getIngredientValue = Integer.parseInt(getIngredientSlotValue);
                        worked = recipeToModify.modifyByUnit(getIngredient, getIngredientValue);
                        speechText = getIngredientSlotValue;
                        if (worked) {
                            speechText = PhrasesForAlexa.MODIFY_UNIT_DONE;
                            resetState();
                        } else {
                            speechText = PhrasesForAlexa.MODIFY_UNIT_NOT_DONE;
                        }
                    } catch (Exception e) {
                        speechText = PhrasesForAlexa.MODIFY_UNIT_NOT_DONE;
                    }
                } else {
                    speechText = PhrasesForAlexa.MODIFY_UNIT_WELCOME;
                    toggleState();
                }
            } else {
                speechText = PhrasesForAlexa.MODIFY_UNIT_SELECT_RECIPE_FIRST;
            }
        } catch (Exception e) {
            speechText = PhrasesForAlexa.MODIFY_UNIT_ERROR;
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