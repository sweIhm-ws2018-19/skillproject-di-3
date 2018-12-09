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
import verkocht.model.Recipe;
import verkocht.model.PhrasesForAlexa;

public class SelectRecipeByCategoryIntentHandler implements RequestHandler {
    public static final String CATEGORY_KEY = "CATEGORY";
    public static final String CATEGORY_SLOT = "Category";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SelectRecipeByCategoryIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        
        Slot chosenCategorieSlot = slots.get(CATEGORY_SLOT);
        
        String chosenCategorie = chosenCategorieSlot.getValue();
        input.getAttributesManager().setSessionAttributes(Collections.singletonMap(CATEGORY_KEY, chosenCategorie));
        
        
        String actualCategorie = (String) input.getAttributesManager().getSessionAttributes().get(CATEGORY_KEY);
        CookingBook cookingBook = new CookingBook();
        Category[] categories = Category.values();
        List<Recipe> a = new ArrayList<>();
        
        if (actualCategorie != null || !actualCategorie.isEmpty()) {
        
        for (int i = 0; i <= Category.values().length; i++) {
        	if (categories[i].getName() == actualCategorie) {
                a.add((Recipe) cookingBook.findByCategory(categories[i]));
        	} 
        }
        
        String b = null;
        for (int i = 0; i <= a.size(); i++){
        	b += a.get(0).getName();
        }
        
        speechText = String.format(PhrasesForAlexa.TELL_CATEGORIES, b);
        
        }
        
        speechText = PhrasesForAlexa.CATEGORIES_UNKOWN;
        
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Rezeptauswahl", speechText)
                .withReprompt("Wie kann ich dir helfen?")
                .withShouldEndSession(false)
                .build();
    }
}