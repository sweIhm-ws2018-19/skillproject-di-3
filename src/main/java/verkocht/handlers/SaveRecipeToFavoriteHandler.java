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
import verkocht.model.PhrasesForAlexa;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

public class SaveRecipeToFavoriteHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SaveRecipeToFavoriteIntent"));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<Response> handle(HandlerInput input) {
       
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot chosenRecipeSlot = slots.get(PhrasesForAlexa.SELECTED_FAVORITE);
        String chosenRecipe = chosenRecipeSlot.getValue();
        AttributesManager attributeManager = input.getAttributesManager(); 
        List<String> listOfFavorites = (List<String>) attributeManager.getPersistentAttributes().get(PhrasesForAlexa.FAVORTIE_RECIPE_LIST) ;
        // add current recipe to the list
        listOfFavorites.add(chosenRecipe);
        // Ist die Frage Alexa dort etwas hineinschreiben wird. Sollte aber zu Beginn keine Probleme hervorrufen.
        attributeManager.setPersistentAttributes(Collections.singletonMap(PhrasesForAlexa.FAVORTIE_RECIPE_LIST, listOfFavorites));
        String speechText = "Das Rezept " + chosenRecipe + " wurde zu den Favoriten hinzugef�gt. Du kannst es dir ueber die Auswahl vorlesen lassen.";
        
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Rezeptauswahl", speechText)
                .withReprompt("Wie kann ich dir helfen?")
                .withShouldEndSession(false)
                .build();
    }

}