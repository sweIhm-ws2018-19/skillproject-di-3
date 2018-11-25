package verkocht.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

/**
 * Handler to start SelectRecipeByName interaction
 * 
 *
 */
public class SelectRecipeByFavoritsIntentHandlerSelector implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SelectRecipeByFavoritsIntentHandlerSelector"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Sage den Rezptnamen aus der vorgelesen Liste.";

        return input.getResponseBuilder().withSpeech(speechText).withSimpleCard("Rezeptauswahl", speechText)
                .withShouldEndSession(false).build();
    }

}
