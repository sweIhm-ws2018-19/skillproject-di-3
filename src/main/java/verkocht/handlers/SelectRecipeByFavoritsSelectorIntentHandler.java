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
import verkocht.model.PhrasesForAlexa;
import verkocht.model.Recipe;


public class SelectRecipeByFavoritsSelectorIntentHandler implements RequestHandler {
    
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SelectRecipeByFavoritsSelectorIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Map<String, Slot> slots = intentRequest.getIntent().getSlots();
        Slot chosenRecipeSlot = slots.get(PhrasesForAlexa.RECIPE_SLOT);
        String chosenRecipe = chosenRecipeSlot.getValue();
        input.getAttributesManager().setSessionAttributes(Collections.singletonMap(PhrasesForAlexa.RECIPE_KEY, chosenRecipe));
        String recipeOriginal = (String) input.getAttributesManager().getSessionAttributes().get(PhrasesForAlexa.RECIPE_KEY);
        
        Recipe foundRecipe = CookingBook.findByName(recipeOriginal);
        
        String recipe = foundRecipe == null ? null : foundRecipe.getName();
        if (recipe != null && !recipe.isEmpty()) {
            TellRecipeStepsIntentHandler.resetCnt();
            Recipe.saveRecipe(foundRecipe);
            speechText = String.format("Du hast %s ausgewählt. Sage \"WEITER\", wenn ich weiterlesen soll", recipe);
              
        } else {
           speechText = String.format (PhrasesForAlexa.REPEAT_RECIPE_INPUT);
        }
        
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Favoriten auswählen", speechText)
                .withShouldEndSession(false).build();
    }

}