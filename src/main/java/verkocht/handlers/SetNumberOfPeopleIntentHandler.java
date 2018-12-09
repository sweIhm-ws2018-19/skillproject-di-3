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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import verkocht.model.Category;
import verkocht.model.CookingBook;
import verkocht.model.Ingredient;
import verkocht.model.Recipe;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class SetNumberOfPeopleIntentHandler implements RequestHandler {
    public static final String NUMBER_KEY = "NUMBER";
    public static final String NUMBER_SLOT = "Number";


    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SetNumberOfPeopleIntent"));
    }
    
    // Zu umsetzende Idee: Eine Kategorie wird ï¿½bergeben und es werden alle Rezepte aus dieser Kategorie zurï¿½ckgegeben. 

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	String speechText = "";
    	Request request = input.getRequestEnvelope().getRequest();
    	IntentRequest intentRequest = (IntentRequest) request;
    	Intent intent = intentRequest.getIntent();
    	Map<String, Slot> slots = intent.getSlots();
    	
    	Slot chosenNumberSlot = slots.get(NUMBER_SLOT);
    	
    	String chosenNumber = chosenNumberSlot.getValue();
    	input.getAttributesManager().setSessionAttributes(Collections.singletonMap(NUMBER_KEY, chosenNumber));
    	
    	String actualNumber = (String) input.getAttributesManager().getSessionAttributes().get(NUMBER_KEY);
    	
    	CookingBook cookingBook = new CookingBook();
    	if (actualNumber == null || actualNumber.isEmpty()) {
    		speechText = "Ich habe die Anzahl der Personen leider nicht genau verstanden, sage zum Beispiel: Ich moechte fuer zwei Personen kochen";
    	} else {
    	switch (actualNumber) {
    		case "eine":
    		case "mich":
    			for (int i = 0; cookingBook.getAllRecipes().size() <= i; i++) {
    				cookingBook.getAllRecipes().get(i).setNumberOfPeople(1);
    				cookingBook.getAllRecipes().get(i).changeIngredientAmounts();
    			}
    			break;
    		case "zwei":
    			for (int i = 0; cookingBook.getAllRecipes().size() <= i; i++) {
    				cookingBook.getAllRecipes().get(i).setNumberOfPeople(2);
    				cookingBook.getAllRecipes().get(i).changeIngredientAmounts();
    			}
    			break;
    	    }
    	speechText = "Dein Rezept ist nun fuer" + actualNumber + "ausgerichtet"; 
    	}
    	
    	
    	
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Rezeptauswahl", speechText)
                .withReprompt("Wie kann ich dir helfen?")
                .withShouldEndSession(false)
                .build();
    }
}