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

import verkocht.handlers.SelectRecipeIntentHandler;

public class SelectRecipeIntentHandlerTest {
	private SelectRecipeIntentHandler handler;

	@Before
    public void setup() {
        handler = new SelectRecipeIntentHandler();
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
                .contains("Hier kannst du ein Rezept auswaehlen.")
                && response.getOutputSpeech().toString()
                .contains("Du hast folgende Optionen:")
                && response.getOutputSpeech().toString()
                .contains("Sage \"zur Namenauswahl\", wenn du ein Rezept direkt waehlen moechtest.")
                && response.getOutputSpeech().toString()
                .contains("Sage \"zur Kategorieauswahl\", wenn du ein Rezept aus einer Kategorie suchst.")
                && response.getOutputSpeech().toString()
                .contains("Sage \"zur Favoritenauswahl\", wenn du ein Rezept aus deinen Favoriten kochen willst."));
    }
}