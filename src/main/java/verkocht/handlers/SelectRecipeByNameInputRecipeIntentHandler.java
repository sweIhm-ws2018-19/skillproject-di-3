/*
Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
except in compliance with the License. A copy of the License is located at

    http://aws.amazon.com/apache2.0/

or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
the specific language governing permissions and limitations under the License.
*/

package verkocht.handlers;

import static com.amazon.ask.request.Predicates.intentName;
import static verkocht.handlers.SelectRecipeByNameInputRecipeIntentHandler.RECIPE_KEY;
import static verkocht.handlers.SelectRecipeByNameInputRecipeIntentHandler.RECIPE_SLOT;
import static verkocht.handlers.WhatsMyColorIntentHandler.COLOR_KEY;
import static verkocht.handlers.WhatsMyColorIntentHandler.COLOR_SLOT;

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

public class SelectRecipeByNameInputRecipeIntentHandler implements RequestHandler {
    public static final String RECIPE_KEY = "RECIPE";
    public static final String RECIPE_SLOT = "Recipe";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SelectRecipeByNameInputRecipeIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        
        String speechText = "Bitte nenne das Rezept, das vorgelesen werden soll.";
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        // Get the recipe slot from the list of slots.
        Slot chosenRecipeSlot = slots.get(RECIPE_SLOT);

        // boolean isAskResponse = false;

        // if (chosenRecipeSlot != null) {
        // Store the user's recipe in the Session and create response.
        String chosenRecipe = chosenRecipeSlot.getValue();
        input.getAttributesManager().setSessionAttributes(Collections.singletonMap(RECIPE_KEY, chosenRecipe));

        String recipe = (String) input.getAttributesManager().getSessionAttributes().get(RECIPE_KEY);

        if (recipe != null && !recipe.isEmpty()) {
            speechText = String.format("Ich lese dir das Rezept %s vor.", recipe);
        } else {
            // Since the user's favorite color is not set render an error message.
           speechText = "Ich weiss nicht welches Rezept ich vorlesen soll. Sag mir den Rezeptnamen. Sage zum Beispiel: ich möchte Schnitzel kochen.";
        }
        
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Rezeptauswahl", speechText)
                .withShouldEndSession(false).build();
    }
}