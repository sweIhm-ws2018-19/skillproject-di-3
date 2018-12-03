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

import verkocht.model.Recipe;

public class TellRecipeStepsIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("TellRecipeStepsIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Recipe recipeToRead = Recipe.getRecipeToRead();
        String recipeStep = recipeToRead.getSteps().get(Recipe.getStepsCounter());
        String speechText = String.format("Neuer Schritt %s %d toSet %d", recipeStep, Recipe.getStepsCounter(),
                Recipe.getStepsCounter());
        Recipe.incStepsCounter();

        return input.getResponseBuilder().withSpeech(speechText).withSimpleCard("Rezeptschritte", speechText)
                .withReprompt("Wie kann ich dir helfen?").withShouldEndSession(false).build();
    }

    public static Recipe getRecipeToRead() {
        return Recipe.getRecipeToRead();
    }

    public static void setRecipeToRead(Recipe recipeToRead) {
        Recipe.setRecipeToRead(recipeToRead);
    }

}