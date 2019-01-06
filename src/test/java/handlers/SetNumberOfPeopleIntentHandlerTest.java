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
import com.amazon.ask.response.ResponseBuilder;

import verkocht.handlers.SetNumberOfPeopleIntentHandler;
import verkocht.model.Category;
import verkocht.model.PhrasesForAlexa;
import verkocht.model.Recipe;

public class SetNumberOfPeopleIntentHandlerTest {
	private SetNumberOfPeopleIntentHandler handler;
	private Recipe testRecipe = new Recipe("test", Category.MEAT, 2, 20);
	
	@Before
	public void setup() {
        handler = new SetNumberOfPeopleIntentHandler();
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
        final Response response = returnResponse.get();

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString()
                .contains(PhrasesForAlexa.MODIFY_UNIT_SELECT_RECIPE_FIRST));
    }
	
	 @Test
	    public void testHandleModificationNotPassed() {
	        Recipe.saveRecipe(testRecipe);
	        HandlerInput inputMock = Mockito.mock(HandlerInput.class);
	        when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

	        final Optional<Response> returnResponse = handler.handle(inputMock);        

	        assertTrue(returnResponse.isPresent());
	        final Response response = returnResponse.get();

	        assertFalse(response.getShouldEndSession());
	        assertNotNull(response.getOutputSpeech());
	        assertTrue(response.getOutputSpeech().toString()
	                .contains(PhrasesForAlexa.PEOPLE_UNKNOWN));
	    }
	 
	 @Test
	    public void testHandleModificationPassed() {
	        Recipe.saveRecipe(testRecipe);

	        final List<Slot> slotValues = new ArrayList<>();
	        Slot numberOfPeople = Slot.builder().withName("Number")
	                .withValue("3")
	                .build();
	        slotValues.add(numberOfPeople);
	        Intent.Builder intentBuilder = Intent.builder();
	        slotValues.forEach((slotItem) ->
	                intentBuilder.putSlotsItem(slotItem.getName(), slotItem)
	        );

	        final RequestEnvelope requestEnvelopeMock = RequestEnvelope.builder()
	                .withRequest(IntentRequest.builder()
	                        .withIntent(intentBuilder.build())
	                        .build())
	                .build();
	        
	        HandlerInput input = Mockito.mock(HandlerInput.class);
	        when(input.getResponseBuilder()).thenReturn(new ResponseBuilder());
	        when(input.getRequestEnvelope()).thenReturn(requestEnvelopeMock);

	        final Optional<Response> returnResponse = handler.handle(input);        

	        assertTrue(returnResponse.isPresent());
	        final Response response = returnResponse.get();

	        assertFalse(response.getShouldEndSession());
	        assertNotNull(response.getOutputSpeech());
	        assertTrue(response.getOutputSpeech().toString()
	                .contains(String.format(PhrasesForAlexa.PEOPLE_SET,"3")));
	    }
	 
	 @Test
	    public void testHandleModificationOutOfParameter() {
	        Recipe.saveRecipe(testRecipe);

	        final List<Slot> slotValues = new ArrayList<>();
	        Slot numberOfPeople = Slot.builder().withName("Number")
	                .withValue("13")
	                .build();
	        slotValues.add(numberOfPeople);
	        Intent.Builder intentBuilder = Intent.builder();
	        slotValues.forEach((slotItem) ->
	                intentBuilder.putSlotsItem(slotItem.getName(), slotItem)
	        );

	        final RequestEnvelope requestEnvelopeMock = RequestEnvelope.builder()
	                .withRequest(IntentRequest.builder()
	                        .withIntent(intentBuilder.build())
	                        .build())
	                .build();
	        
	        HandlerInput input = Mockito.mock(HandlerInput.class);
	        when(input.getResponseBuilder()).thenReturn(new ResponseBuilder());
	        when(input.getRequestEnvelope()).thenReturn(requestEnvelopeMock);

	        final Optional<Response> returnResponse = handler.handle(input);        

	        assertTrue(returnResponse.isPresent());
	        final Response response = returnResponse.get();

	        assertFalse(response.getShouldEndSession());
	        assertNotNull(response.getOutputSpeech());
	        assertTrue(response.getOutputSpeech().toString()
	                .contains(PhrasesForAlexa.PEOPLE_NUMBER_UNCLEAR));
	    }
	 
	 @Test
	    public void testHandleModificationParameterOne() {
	        Recipe.saveRecipe(testRecipe);

	        final List<Slot> slotValues = new ArrayList<>();
	        Slot numberOfPeople = Slot.builder().withName("Number")
	                .withValue("1")
	                .build();
	        slotValues.add(numberOfPeople);
	        Intent.Builder intentBuilder = Intent.builder();
	        slotValues.forEach((slotItem) ->
	                intentBuilder.putSlotsItem(slotItem.getName(), slotItem)
	        );

	        final RequestEnvelope requestEnvelopeMock = RequestEnvelope.builder()
	                .withRequest(IntentRequest.builder()
	                        .withIntent(intentBuilder.build())
	                        .build())
	                .build();
	        
	        HandlerInput input = Mockito.mock(HandlerInput.class);
	        when(input.getResponseBuilder()).thenReturn(new ResponseBuilder());
	        when(input.getRequestEnvelope()).thenReturn(requestEnvelopeMock);

	        final Optional<Response> returnResponse = handler.handle(input);        

	        assertTrue(returnResponse.isPresent());
	        final Response response = returnResponse.get();

	        assertFalse(response.getShouldEndSession());
	        assertNotNull(response.getOutputSpeech());
	        assertTrue(response.getOutputSpeech().toString()
	                .contains(PhrasesForAlexa.PEOPLE_ONE));
	    }


}
