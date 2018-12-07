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
        //create the welcome message for this intent
        String speechText;
        Request request = input.getRequestEnvelope().getRequest();
        //get request from the user
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        // get slots of the request
        Map<String, Slot> slots = intent.getSlots();

        // get the recipe slot from the list of slots.
        Slot chosenRecipeSlot = slots.get(RECIPE_SLOT);

        // store the user's recipe in the session and create response.
        String chosenRecipe = chosenRecipeSlot.getValue();
        input.getAttributesManager().setSessionAttributes(Collections.singletonMap(RECIPE_KEY, chosenRecipe));

        // get recipe name from the response
        String recipeOriginal = (String) input.getAttributesManager().getSessionAttributes().get(RECIPE_KEY);
        CookingBook cookingBook = new CookingBook();
        Recipe foundRecipe = cookingBook.findByName(recipeOriginal);//recipe returned 
        
        String recipe; //recipe name as String
        if (foundRecipe == null) {//recipe could not be found
            recipe = null; 
        }else {
            recipe = foundRecipe.getName(); //recipe could be found
        }
        if (recipe != null && !recipe.isEmpty()) {//string is not empty and not null
            TellRecipeStepsIntentHandler.resetCnt();
            TellRecipeStepsIntentHandler.setRecipeToRead(foundRecipe);
            speechText = String.format("Ich lese dir das Rezept %s vor. Sage \"WEITER\", wenn ich weiterlesen soll", recipe);
              
        } else {
           speechText = String.format ("Ich weiss nicht, welches Rezept ich vorlesen soll. Sag mir den Rezeptnamen. Sage zum Beispiel: ich möchte Schnitzel kochen.");
        }
        
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Rezeptauswahl", speechText)
                .withShouldEndSession(false).build();
    }
} 