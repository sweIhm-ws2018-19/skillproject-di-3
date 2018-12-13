package verkocht.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

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
        Recipe recipeToModify = Recipe.getSavedRecipe();
        String speechText;

		if (recipeToModify != null) {
			boolean worked = false;
			if (state) {
				try {
					Request request = input.getRequestEnvelope().getRequest();
			        IntentRequest intentRequest = (IntentRequest) request;
			        Intent intent = intentRequest.getIntent();
			        Map<String, Slot> slots = intent.getSlots();

			        String slotValue = slots.get(INGREDIENT_VALUE_SLOT).getValue();
			        int getIngredientValue = Integer.parseInt(slotValue);

			        String getIngredient = slots.get(INGREDIENT_SLOT)
			                .getResolutions()
			                .getResolutionsPerAuthority()
			                .get(0).getValues()
			                .get(0).getValue()
			                .getName();

					worked = recipeToModify.modifyByUnit(getIngredient, getIngredientValue);
					if (worked) {
						speechText = PhrasesForAlexa.MODIFY_UNIT_DONE;
					} else {
					    speechText = PhrasesForAlexa.MODIFY_UNIT_NOT_DONE;
					}
				} catch (Exception e) {
					speechText = PhrasesForAlexa.MODIFY_UNIT_NOT_DONE;
				}
				
				resetState();
			} else {
				speechText = PhrasesForAlexa.MODIFY_UNIT_WELCOME;
				toggleState();
			}
		} else {
			speechText = PhrasesForAlexa.MODIFY_UNIT_SELECT_RECIPE_FIRST;
			resetState();
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