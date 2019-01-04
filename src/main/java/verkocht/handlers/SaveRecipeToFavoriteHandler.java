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

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import verkocht.model.CookingBook;

public class SaveRecipeToFavoriteHandler implements RequestHandler {
    public static final String RECIPE_KEY = "RECIPE";
    public static final String RECIPE_SLOT = "FavoritRecipe";
    
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SaveRecipeToFavoriteIntent"));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Map<String, Slot> slots = intentRequest.getIntent().getSlots();
        Slot chosenRecipeSlot = slots.get(RECIPE_SLOT);
        String chosenRecipe = chosenRecipeSlot.getValue();
        input.getAttributesManager().setSessionAttributes(Collections.singletonMap(RECIPE_KEY, chosenRecipe));
        String recipeOriginal = (String) input.getAttributesManager().getSessionAttributes().get(RECIPE_KEY);
        
        if(CookingBook.saveFavorite(recipeOriginal)) {
        
        speechText = "Das Rezept " + chosenRecipe + " wurde zu den Favoriten hinzugefuegt. Du kannst es dir ueber die Auswahl vorlesen lassen.";
        } else {
            speechText = "Das Rezept " + chosenRecipe + " ist mir nicht bekannt. Sage zum Beispiel, füge schnitzel zu meinen Favoriten hinzu."; 
        }
        
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Favoriten speichern", speechText)
                .withReprompt("Wie kann ich dir helfen?")
                .withShouldEndSession(false)
                .build();
    }

}