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
    static int step;

    public static final String INGREDIENT_SLOT = "Ingredient";
    public static final String INGREDIENT_VALUE_SLOT = "IngredientValue";

    private String ingredientToModify;
    private String ingredientValue;

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("ModifyRecipeByUnitsIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = PhrasesForAlexa.MODIFY_UNIT_ERROR;
        Recipe recipeToModify = Recipe.getSavedRecipe();

        try {
            if (recipeToModify != null) {
                if (step == 3) {
                    recipeToModify.modifyByUnit(this.ingredientToModify, this.ingredientValue);
                    speechText = PhrasesForAlexa.MODIFY_UNIT_DONE;
                    step = 0;
                } else if (step == 2) {
                    Request request = input.getRequestEnvelope().getRequest();
                    IntentRequest intentRequest = (IntentRequest) request;
                    Intent intent = intentRequest.getIntent();
                    Map<String, Slot> slots = intent.getSlots();
                    String ingredientValue = slots.get(INGREDIENT_VALUE_SLOT).getValue();

                    if (ingredientValue.isEmpty()) {
                        speechText = PhrasesForAlexa.MODIFY_UNIT_VALUE;
                    } else {
                        speechText = "Ok!";
                        this.ingredientValue = ingredientValue;
                        step++;
                    }
                } else if (step == 1) {
                    Request request = input.getRequestEnvelope().getRequest();
                    IntentRequest intentRequest = (IntentRequest) request;
                    Intent intent = intentRequest.getIntent();
                    Map<String, Slot> slots = intent.getSlots();
                    String ingredientToModify = slots.get(INGREDIENT_SLOT).getValue();

                    if (ingredientToModify.isEmpty()) {
                        speechText = PhrasesForAlexa.MODIFY_UNIT_SELECT_INGREDIENT;
                    } else {
                        speechText = "Ok!";
                        this.ingredientToModify = ingredientToModify;
                        step++;
                    }
                } else if (step == 0) {
                    speechText = PhrasesForAlexa.MODIFY_UNIT_WELCOME;
                    step++;
                }
            } else {
                speechText = PhrasesForAlexa.MODIFY_UNIT_SELECT_FIRST;
                step = 0;
            }
        } catch (Exception e) {
            speechText = PhrasesForAlexa.MODIFY_UNIT_ERROR;
        }

        return input.getResponseBuilder().withSpeech(speechText).withSimpleCard("Rezeptschritte", speechText)
                .withReprompt("Wie kann ich dir helfen?").withShouldEndSession(false).build();
    }
}