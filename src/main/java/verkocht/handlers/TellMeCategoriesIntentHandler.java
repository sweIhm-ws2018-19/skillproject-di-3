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

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import verkocht.model.Category;

/**
 * Intent handler that tells the user the current available categories out
 * of a array of value from the Enum "Category" of the cooking book.
 */
public class TellMeCategoriesIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("TellMeCategoriesIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	String speechText;
        String response = Category.getCategories();

        if (response != null && !response.isEmpty()) {
            speechText = String.format("Folgende Kategorien stehen zur Auswahl: %s.", response);
        } else {
            speechText = "Ich kann dir leider im Moment nicht helfen. Tut mir Leid.";
        }

        return input
        		.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Deine Kategorien", speechText)
                .withReprompt("Wie kann ich dir helfen?")
                .withShouldEndSession(false)
                .build();
    }
}
