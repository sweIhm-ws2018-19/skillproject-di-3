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

import verkocht.handlers.HauptmenueIntentHandler;
import verkocht.model.CookingBook;

public class HauptmenueIntentHandlerTest {

    private HauptmenueIntentHandler handler;

    @Before
    public void setup() {
        handler = new HauptmenueIntentHandler();
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
        assertTrue(response.getOutputSpeech().toString().contains("Was willst du tun?") && 
                response.getOutputSpeech().toString().contains ("Um ein Rezept auszuwaehlen, sage \"Rezept auswaehlen.\"")&&
                response.getOutputSpeech().toString().contains("Du kannst die Rezeptkategorien oder die Favoriten vorlesen lassen.")
               && response.getOutputSpeech().toString().contains("Sage dafuer: \"Kategorien,\" oder: \"Favoriten\"")
                && response.getOutputSpeech().toString().contains("Moechtest du ein neues Rezept zu den Favoriten hinzufuegen, sage: \"Rezept favorisieren\"")
                && response.getOutputSpeech().toString().contains("Um das Rezept zu aendern, sage: \"Rezept modifizieren.\"")
                && response.getOutputSpeech().toString().contains("Um die Rezeptschritte für ein ausgewaehltes Rezept gleich anzuhoeren, sage:\"Rezeptschritte vorlesen\".")
                && response.getOutputSpeech().toString().contains ("Wenn du die Wahloptionen noch einmal anhoeren willst, sage: \"Zum Hauptmenue\"."));
    }
}
