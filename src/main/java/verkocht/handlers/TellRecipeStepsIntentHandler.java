package verkocht.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import verkocht.model.PhrasesForAlexa;
import verkocht.model.Recipe;

public class TellRecipeStepsIntentHandler implements RequestHandler {
    static int counter;

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("TellRecipeStepsIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;
        Recipe recipeToRead = Recipe.getSavedRecipe();

        if (recipeToRead != null) {
            if (counter >= Recipe.getSavedRecipe().getSteps().size()) {
                speechText = PhrasesForAlexa.END_READ_RECIPE_STEPS;
                resetCnt();
            } else {
                String recipeStep = recipeToRead.getSteps().get(counter);
                speechText = String.format("(%d), %s ", counter + 1, recipeStep);
                incrementCnt();
            }
        } else {
            speechText = PhrasesForAlexa.MODIFY_UNIT_SELECT_RECIPE_FIRST;
        }
        return input.getResponseBuilder().withSpeech(speechText).withSimpleCard("Rezeptschritte", speechText)
                .withReprompt("Wie kann ich dir helfen?").withShouldEndSession(false).build();
    }

    public static Recipe getRecipeToRead() {
        return Recipe.getSavedRecipe();
    }

    public static void setRecipeToRead(Recipe recipeToRead) {
        Recipe.saveRecipe(recipeToRead);
    }

    public static void resetCnt() {
        counter = 0;
    }

    public static void incrementCnt() {
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        TellRecipeStepsIntentHandler.counter = counter;
    }
    
    

}