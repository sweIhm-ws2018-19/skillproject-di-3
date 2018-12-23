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

import verkocht.handlers.SelectRecipeByNameInputRecipeIntentHandler;
import verkocht.model.CookingBook;
import verkocht.model.PhrasesForAlexa;

public class SelectRecipeByNameInputRecipeIntentHandlerTest {
    private SelectRecipeByNameInputRecipeIntentHandler handler;

    @Before
    public void setup() {
        handler = new SelectRecipeByNameInputRecipeIntentHandler();
        CookingBook.initiateCookingBook();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testHandleRecipeExsists() {
        Map<String, Slot> slots = new HashMap<String, Slot>();
        slots.put("Recipe", Slot.builder().withValue("schnitzel").build());
        RequestEnvelope requestEnvelope = RequestEnvelope.builder()
                .withRequest(IntentRequest.builder().withIntent(Intent.builder().withSlots(slots).build()).build())
                .withSession(Session.builder().withSessionId("1").build()).build();
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());
        when(inputMock.getRequestEnvelope()).thenReturn(requestEnvelope);
        when(inputMock.getAttributesManager())
                .thenReturn(AttributesManager.builder().withRequestEnvelope(requestEnvelope).build());

        Optional<Response> returnResponse = handler.handle(inputMock);

        assertTrue(returnResponse.isPresent());
        Response response = returnResponse.get();
        String recipe = "schnitzel";

        assertFalse(response.getShouldEndSession());
        assertTrue(response.getOutputSpeech().toString()
                .contains( String.format(PhrasesForAlexa.READ_RECIPE_STEPS, recipe)));
    }
    @Test
    public void testHandleRecipeDoesNotExsist() {
        Map<String, Slot> slots = new HashMap<String, Slot>();
        slots.put("Recipe", Slot.builder().withValue("Strudel").build());
        RequestEnvelope requestEnvelope = RequestEnvelope.builder()
                .withRequest(IntentRequest.builder().withIntent(Intent.builder().withSlots(slots).build()).build())
                .withSession(Session.builder().withSessionId("1").build()).build();
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());
        when(inputMock.getRequestEnvelope()).thenReturn(requestEnvelope);
        when(inputMock.getAttributesManager())
                .thenReturn(AttributesManager.builder().withRequestEnvelope(requestEnvelope).build());

        Optional<Response> returnResponse = handler.handle(inputMock);

        assertTrue(returnResponse.isPresent());
        Response response = returnResponse.get();

        assertFalse(response.getShouldEndSession());
        assertTrue(response.getOutputSpeech().toString()
                .contains(PhrasesForAlexa.REPEAT_RECIPE_INPUT));
    }
};