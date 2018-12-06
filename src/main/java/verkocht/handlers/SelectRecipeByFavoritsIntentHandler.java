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

public class SelectRecipeByFavoritsIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SelectRecipeByFavoritsIntent"));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        //get request from the user
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        // get slots of the request
        Map<String, Slot> slots = intent.getSlots();

        // get the recipe slot from the list of slots.
        Slot chosenFavoriteSlot = slots.get(PhrasesForAlexa.SELECTED_FAVORITE);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        AttributesManager attributesManager = input.getAttributesManager();
        List<String> listOfFavorites = (List<String>) attributesManager.getPersistentAttributes().get(PhrasesForAlexa.FAVORTIE_RECEPIE_LIST) ;
        StringBuilder allFavorites = new StringBuilder("");
        if (listOfFavorites != null) {
            for (String string : listOfFavorites) {
                allFavorites.append(string);
            }
        }
        String favoritesString = allFavorites.toString();
        String speechText = String.format("Das sind alle deine Favoriten: %s. Waehle eine deiner Favoriten fuer den naechsten Schritt aus.",favoritesString);
        if (favoritesString.isEmpty()) {
            speechText = "Bis jetzt hast du noch keine Favoriten. Markiere zuerst Favoriten, damit ich sie dir vorlesen kann.";
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Rezeptauswahl", speechText)
                .withReprompt("Wie kann ich dir helfen?")
                .withShouldEndSession(false)
                .build();
    }

}