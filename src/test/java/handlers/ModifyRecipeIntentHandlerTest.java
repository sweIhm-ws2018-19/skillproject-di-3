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

import verkocht.handlers.ModifyRecipeIntentHandler;

public class ModifyRecipeIntentHandlerTest {
	private ModifyRecipeIntentHandler handler;

	@Before
    public void setup() {
        handler = new ModifyRecipeIntentHandler();
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
        final Response response = returnResponse.get();;

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString()
                .contains("Hier kannst du ein Rezept modifizieren.")
                && response.getOutputSpeech().toString()
                .contains("Du kannst folgende Optionen:")
                && response.getOutputSpeech().toString()
                .contains("Sage \"Rezept nach Zutaten anpassen\", wenn du ein Rezept nach einer bestimmten Zutatenmenge anpassen willst.")
                && response.getOutputSpeech().toString()
                .contains("Sage \"Personenzahl aendern\", wenn du ein Rezept aufgrund einer geaenderten Personenzahl anpassen moechtest."));
    }
}
