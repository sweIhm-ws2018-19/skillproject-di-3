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

import verkocht.handlers.SelectRecipeByFavoritsSelectorIntentHandler;
import verkocht.model.Category;
import verkocht.model.CookingBook;
import verkocht.model.PhrasesForAlexa;
import verkocht.model.Recipe;

public class SelectRecipeByFavoritsSelectorIntentHandlerTest {
	private SelectRecipeByFavoritsSelectorIntentHandler handler;
	private Recipe testRecipe = new Recipe("test", Category.MEAT, 2, 20);

    @Before
    public void setup() {
        handler = new SelectRecipeByFavoritsSelectorIntentHandler();
        CookingBook.initiateCookingBook();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    
    public void testHandleWithoutFavorites() {
        Recipe.saveRecipe(null);
    	final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
    	when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

    	final Optional<Response> returnResponse = handler.handle(inputMock);        

        assertTrue(returnResponse.isPresent());
        final Response response = returnResponse.get();;

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        String test = response.getOutputSpeech().toString();
        assertTrue(test.contains(PhrasesForAlexa.REPEAT_RECIPE_INPUT));
    }
    
    
    public void testHandleWithAllFavoritesSaved() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

        final Optional<Response> returnResponse = handler.handle(inputMock);        

        assertTrue(returnResponse.isPresent());
        final Response response = returnResponse.get();;

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString()
                .contains(String.format(PhrasesForAlexa.REPEAT_RECIPE_INPUT, CookingBook.getAllFavorites())));
    }
}