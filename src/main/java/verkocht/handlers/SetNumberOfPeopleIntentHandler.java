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

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import verkocht.model.Recipe;
import verkocht.model.PhrasesForAlexa;

public class SetNumberOfPeopleIntentHandler implements RequestHandler {
    public static final String NUMBER_KEY = "NUMBER";
    public static final String NUMBER_SLOT = "Number";


    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SetNumberOfPeopleIntent"));
    }
    
    @Override
    public Optional<Response> handle(HandlerInput input) {
    	String speechText = "";
    	Recipe recipe = Recipe.getSavedRecipe();
    	
    	if (recipe == null) {
    	 speechText = PhrasesForAlexa.MODIFY_UNIT_SELECT_RECIPE_FIRST;
    	} else {
    		try {
    			Request request = input.getRequestEnvelope().getRequest();
    			IntentRequest intentRequest = (IntentRequest) request;
    			Intent intent = intentRequest.getIntent();
    			Map<String, Slot> slots = intent.getSlots();
    	
    			Slot chosenNumberSlot = slots.get(NUMBER_SLOT);
    			String chosenNumber = chosenNumberSlot.getValue();
    			int getNumberOfPeople = Integer.parseInt(chosenNumber);
    	 	
    			if (getNumberOfPeople <= 0 || getNumberOfPeople > 12) {
    				speechText = PhrasesForAlexa.PEOPLE_NUMBER_UNCLEAR;
    			} else {
    				if (getNumberOfPeople == 1) {
    					recipe.changeIngredientAmounts(1);
    					speechText = PhrasesForAlexa.PEOPLE_ONE;
    				} else {
    					recipe.changeIngredientAmounts(getNumberOfPeople);
    					speechText = String.format(PhrasesForAlexa.PEOPLE_SET, getNumberOfPeople);
    				}
    			}    			
    		}   			catch (Exception e) {
    						speechText = PhrasesForAlexa.PEOPLE_UNKNOWN;
    		}
    	} 
    	
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Rezeptauswahl", speechText)
                .withReprompt("Wie kann ich dir helfen?")
                .withShouldEndSession(false)
                .build();
    }
}