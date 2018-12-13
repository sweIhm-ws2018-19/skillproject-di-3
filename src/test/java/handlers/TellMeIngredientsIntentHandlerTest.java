package handlers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

import verkocht.handlers.TellMeIngredientsIntentHandler;
import verkocht.model.Category;
import verkocht.model.Ingredient;
import verkocht.model.PhrasesForAlexa;
import verkocht.model.Recipe;
import verkocht.model.Unit;

public class TellMeIngredientsIntentHandlerTest {
	private TellMeIngredientsIntentHandler handler;

    @Before
    public void setup() {
        handler = new TellMeIngredientsIntentHandler();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testHandleWithoutSavedRecipe() {
    	Recipe.saveRecipe(null);
    	final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
    	when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

    	final Optional<Response> returnResponse = handler.handle(inputMock);        

        assertTrue(returnResponse.isPresent());
        final Response response = returnResponse.get();;

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString()
                .contains(PhrasesForAlexa.TELL_INGREDIENTS_SELECT_FIRST));
    }
    
    @Test
    public void testHandleWithSavedRecipe() {
    	Recipe recipe = new Recipe("Nudeln", Category.VEGAN, 2, 10);
    	recipe.addIngredient(new Ingredient("Nudeln", Unit.GRAMM), 400);
    	Recipe.saveRecipe(recipe);
    	
    	final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
    	when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

    	final Optional<Response> returnResponse = handler.handle(inputMock);        

        assertTrue(returnResponse.isPresent());
        final Response response = returnResponse.get();;

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        System.out.println(response.getOutputSpeech().toString());
        assertTrue(response.getOutputSpeech().toString()
                .contains(PhrasesForAlexa.TELL_INGREDIENTS_OK) 
                && response.getOutputSpeech().toString().contains("- 400 GRAMM Nudeln"));
    }
}
