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
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import verkocht.model.CookingBook;
import verkocht.model.PhrasesForAlexa;
import verkocht.model.Recipe;
/**
 * Handler to get the requested recipe from the cooking book
 * 
 *
 */
public class SelectRecipeByNameInputRecipeIntentHandler implements RequestHandler {
    public static final String RECIPE_KEY = "RECIPE";
    public static final String RECIPE_SLOT = "Recipe";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SelectRecipeByNameInputRecipeIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot chosenRecipeSlot = slots.get(RECIPE_SLOT);
        String chosenRecipe = chosenRecipeSlot.getValue();
        input.getAttributesManager().setSessionAttributes(Collections.singletonMap(RECIPE_KEY, chosenRecipe));

        String recipeOriginal = (String) input.getAttributesManager().getSessionAttributes().get(RECIPE_KEY);
        CookingBook cookingBook = new CookingBook();
        Recipe foundRecipe = cookingBook.findByName(recipeOriginal);//recipe returned 
        
        String recipe; 
        if (foundRecipe == null) {
            recipe = null; 
        }else {
            recipe = foundRecipe.getName(); 
        }
        if (recipe != null && !recipe.isEmpty()) {
            TellRecipeStepsIntentHandler.resetCnt();
            TellRecipeStepsIntentHandler.setRecipeToRead(foundRecipe);
            speechText = String.format(PhrasesForAlexa.READ_RECIPE_STEPS, recipe);
              
        } else {
           speechText = String.format (PhrasesForAlexa.REPEAT_RECIPE_INPUT);
        }
        
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Rezeptauswahl", speechText)
                .withShouldEndSession(false).build();
    }
} 