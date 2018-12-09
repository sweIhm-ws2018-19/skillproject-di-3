package verkocht.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import verkocht.model.PhrasesForAlexa;
/**
 * Handler to start SelectRecipeByName interaction
 * 
 *
 */
public class SelectRecipeByNameStartIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SelectRecipeByNameStartIntent"));
    }

  
    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = PhrasesForAlexa.START_RECIPE_INPUT;
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Rezeptauswahl", speechText)
                .withShouldEndSession(false)
                .build();
    }

}
