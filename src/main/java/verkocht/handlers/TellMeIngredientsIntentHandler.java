package verkocht.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import verkocht.model.PhrasesForAlexa;
import verkocht.model.Recipe;

public class TellMeIngredientsIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("TellMeIngredientsIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText;
		Recipe recipeToRead = Recipe.getSavedRecipe();
		StringBuilder stringBuilder = new StringBuilder();

		if (recipeToRead != null && !recipeToRead.getIngredients().isEmpty()) {
			stringBuilder.append(PhrasesForAlexa.TELL_INGREDIENTS_OK);

			recipeToRead.getIngredients()
				.forEach((i, value) -> stringBuilder.append("* " + value + " "
						+ i.getUnit() + " " + i.getIngredient() + "\n"));
			
			speechText = stringBuilder.toString();
		} else {
			speechText = PhrasesForAlexa.TELL_INGREDIENTS_SELECT_FIRST;
		}

		return input.getResponseBuilder().withSpeech(speechText).withSimpleCard("Deine Zutaten", speechText)
				.withReprompt("Wie kann ich dir helfen?").withShouldEndSession(false).build();
	}
}
