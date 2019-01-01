package handlers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Session;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

import verkocht.handlers.SelectRecipeByCategoryIntentHandler;
import verkocht.model.CookingBook;
import verkocht.model.PhrasesForAlexa;
import verkocht.model.Recipe;

public class SelectRecipeByCategoryIntentHandlerTest {
	private SelectRecipeByCategoryIntentHandler handler;
	
	@Before
	public void setup() {
        handler = new SelectRecipeByCategoryIntentHandler();
		CookingBook.initiateCookingBook();
    }
	
	@Test
	   public void testCanHandle() {
	       final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
	       when(inputMock.matches(any())).thenReturn(true);
	       assertTrue(handler.canHandle(inputMock));
	   }
	
	@Test
    public void testHandleWithoutSavedRecipe() {

        Map<String, Slot> slots = new HashMap<String, Slot>();
        slots.put("Category", Slot.builder().withValue("Mediterran").build());
        final RequestEnvelope requestEnvelope = RequestEnvelope.builder()
                .withRequest(IntentRequest.builder().withIntent(Intent.builder().withSlots(slots).build()).build())
                .withSession(Session.builder().withSessionId("1").build()).build();
        
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());
        when(inputMock.getRequestEnvelope()).thenReturn(requestEnvelope);
        when(inputMock.getAttributesManager())
                .thenReturn(AttributesManager.builder().withRequestEnvelope(requestEnvelope).build());

        final Optional<Response> returnResponse = handler.handle(inputMock);

        assertTrue(returnResponse.isPresent());
        Response response = returnResponse.get();

        assertFalse(response.getShouldEndSession());
        assertTrue(response.getOutputSpeech().toString()
                .contains(PhrasesForAlexa.NO_RECIPE_IN_THIS_CATEGORY));
    }
	
	@Test
    public void testHandleWith() {
		Recipe aRecipe = CookingBook.getAllRecipes().get(0);
		String b = aRecipe.getName();

        Map<String, Slot> slots = new HashMap<String, Slot>();
        slots.put("Category", Slot.builder().withValue("Fleisch").build());
        final RequestEnvelope requestEnvelope = RequestEnvelope.builder()
                .withRequest(IntentRequest.builder().withIntent(Intent.builder().withSlots(slots).build()).build())
                .withSession(Session.builder().withSessionId("1").build()).build();
        
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());
        when(inputMock.getRequestEnvelope()).thenReturn(requestEnvelope);
        when(inputMock.getAttributesManager())
                .thenReturn(AttributesManager.builder().withRequestEnvelope(requestEnvelope).build());

        final Optional<Response> returnResponse = handler.handle(inputMock);

        assertTrue(returnResponse.isPresent());
        Response response = returnResponse.get();

        assertFalse(response.getShouldEndSession());
        assertTrue(response.getOutputSpeech().toString()
                .contains(String.format(PhrasesForAlexa.ONLY_ONE_RECIPE,b)));
    }
}
