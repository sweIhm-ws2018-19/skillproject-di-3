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

import verkocht.handlers.SaveRecipeToFavoriteHandler;
import verkocht.model.CookingBook;
import verkocht.model.PhrasesForAlexa;

public class SelectRecipeByFavoritsSelectorIntentHandlerTest {
    private SaveRecipeToFavoriteHandler handler;

    @Before
    public void setup() {
        handler = new SaveRecipeToFavoriteHandler();
        CookingBook.initiateCookingBook();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testHandleWithoutFavorites() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    public void testHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

        final Optional<Response> returnResponse = handler.handle(inputMock);

        assertTrue(returnResponse.isPresent());
        final Response response = returnResponse.get();

        assertFalse(response.getShouldEndSession());
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesForAlexa.FAVORITE_REPROMT));
    }

    @Test
    public void testHandleWithUnknowRecipe() {
        Map<String, Slot> slots = new HashMap<String, Slot>();
        slots.put("FavoritRecipe", Slot.builder().withValue("KennIchNicht").build());
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
        String recipe = "KennIchNicht";

        assertFalse(response.getShouldEndSession());
        assertTrue(response.getOutputSpeech().toString().contains("Das Rezept " + recipe + " ist mir nicht bekannt. Sage zum Beispiel, füge schnitzel zu meinen Favoriten hinzu."));
    }

    @Test
    public void testHandleWithKnownRecipe() {
        Map<String, Slot> slots = new HashMap<String, Slot>();
        slots.put("FavoritRecipe", Slot.builder().withValue("schnitzel").build());
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
        assertTrue(response.getOutputSpeech().toString().contains("Das Rezept " + recipe + " wurde zu den Favoriten hinzugefuegt. Du kannst es dir ueber die Auswahl vorlesen lassen."));
    }
}