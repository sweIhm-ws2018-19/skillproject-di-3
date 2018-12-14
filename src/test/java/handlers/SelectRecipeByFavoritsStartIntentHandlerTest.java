package handlers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.slu.entityresolution.Resolution;
import com.amazon.ask.model.slu.entityresolution.Resolutions;
import com.amazon.ask.model.slu.entityresolution.Value;
import com.amazon.ask.model.slu.entityresolution.ValueWrapper;
import com.amazon.ask.response.ResponseBuilder;

import verkocht.handlers.SelectRecipeByFavoritsStartIntentHandler;
import verkocht.model.Category;
import verkocht.model.CookingBook;
import verkocht.model.Ingredient;
import verkocht.model.PhrasesForAlexa;
import verkocht.model.Recipe;
import verkocht.model.Unit;

public class SelectRecipeByFavoritsStartIntentHandlerTest {
	private SelectRecipeByFavoritsStartIntentHandler handler;
	private Recipe testRecipe = new Recipe("test", Category.MEAT, 2, 20);

    @Before
    public void setup() {
        handler = new SelectRecipeByFavoritsStartIntentHandler();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testHandleWithoutFavorites() {
        Recipe.saveRecipe(null);
    	final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
    	when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

    	final Optional<Response> returnResponse = handler.handle(inputMock);        

        assertTrue(returnResponse.isPresent());
        final Response response = returnResponse.get();;

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString()
                .contains(PhrasesForAlexa.NO_FAVORITE_AVAILABLE));
    }
    
    @Test
    public void testHandleWithAllFavoritesSaved() {
        CookingBook.initiateCookingBook();
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

        final Optional<Response> returnResponse = handler.handle(inputMock);        

        assertTrue(returnResponse.isPresent());
        final Response response = returnResponse.get();;

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString()
                .contains(String.format(PhrasesForAlexa.FAVORITE_AVAILABLE, CookingBook.getAllFavorites())));
    }
}