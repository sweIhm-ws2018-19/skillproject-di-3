package verkocht.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Collections;
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
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot chosenFavoriteSlot = slots.get(PhrasesForAlexa.SELECTED_FAVORITE);
        
        String chosenRecipe = chosenFavoriteSlot.getValue();
        input.getAttributesManager().setSessionAttributes(Collections.singletonMap(PhrasesForAlexa.SELECTED_FAVORITE, chosenRecipe));
        
        
        
        
        String speechText = "Sage den Rezeptnamen aus der vorgelesen Liste.";

        return input.getResponseBuilder().withSpeech(speechText).withSimpleCard("Rezeptauswahl", speechText)
                .withShouldEndSession(false).build();
    }

}
