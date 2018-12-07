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
public class SelectRecipeByNameStartIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SelectRecipeByNameStartIntent"));
    }

  
    @Override
    // says the welcoming message and returns the user input
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Nenne das Rezept, das vorgelesen werden soll. Sage zum Beispiel: ich möchte Schnitzel kochen.";

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Rezeptauswahl", speechText)
                .withShouldEndSession(false)
                .build();
    }

}
