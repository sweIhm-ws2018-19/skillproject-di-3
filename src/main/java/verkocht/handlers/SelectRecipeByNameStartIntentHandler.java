package verkocht.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class SelectRecipeByNameStartIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SelectRecipeByNameStartIntent"));
    }

//    @Override
//    public Optional<Response> handle(HandlerInput input) {
//        String speechText = String.format("Nenne das Rezept, das vorgelesen werden soll.");
//        //// repromptText =
//        //// "Frage nach meiner Lieblingsfarbe.";
//        //
//        // } else {
//        // // Render an error since we don't know what the users favorite color is.
//        // speechText = "Ich kenne Deine Lieblingsfarbe nicht. Bitte versuche es noch
//        //// einmal.";
//        // repromptText =
//        // "Ich weiss nicht welches Deine Lieblingsfarbe ist. Sag mir Deine
//        //// Lieblingsfarbe. Sage zum Beispiel: ich mag blau.";
//        // isAskResponse = true;
//        // }
//
//        ResponseBuilder responseBuilder = input.getResponseBuilder();
//
//        responseBuilder.withSimpleCard("Rezeptauswahl", speechText)
//        .withSpeech(speechText).withShouldEndSession(false);
//
//        // if (isAskResponse) {
//        // responseBuilder.withShouldEndSession(false)
//        // .withReprompt(repromptText);
//        // }
//
//        return responseBuilder.build();
//
//    }
//    
    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Nenne das Rezept, das vorgelesen werden soll.";

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Rezeptauswahl", speechText)
                .withShouldEndSession(false)
                .build();
    }

}
