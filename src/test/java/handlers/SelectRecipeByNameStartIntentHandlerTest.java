package handlers;

import static org.junit.Assert.assertFalse;
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

import verkocht.handlers.SelectRecipeByNameStartIntentHandler;
import verkocht.model.CookingBook;
import verkocht.model.PhrasesForAlexa;

public class SelectRecipeByNameStartIntentHandlerTest {
    private SelectRecipeByNameStartIntentHandler handler;

    @Before
    public void setup() {
        handler = new SelectRecipeByNameStartIntentHandler();
        CookingBook.initiateCookingBook();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

        final Optional<Response> returnResponse = handler.handle(inputMock);

        assertTrue(returnResponse.isPresent());
        final Response response = returnResponse.get();

        assertFalse(response.getShouldEndSession());
        assertTrue(response.getOutputSpeech().toString()
                .contains(PhrasesForAlexa.START_RECIPE_INPUT));
    }
}
