package handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

import verkocht.handlers.TellRecipeStepsIntentHandler;
import verkocht.model.CookingBook;
import verkocht.model.Recipe;

public class TellRecipeStepsIntentHandlerTest {
    private TellRecipeStepsIntentHandler handler;

    @Before
    public void setup() {
        handler = new TellRecipeStepsIntentHandler();
        CookingBook.initiateCookingBook();
    }
    
    @Test
    public void setAndGetRecipeToRead() {
        Recipe recipeToRead = CookingBook.getAllRecipes().get(0);
        TellRecipeStepsIntentHandler.setRecipeToRead(recipeToRead);
        assertEquals(recipeToRead,TellRecipeStepsIntentHandler.getRecipeToRead());
    }

    @Test

    public  void testSetCnt() {
        TellRecipeStepsIntentHandler.setCounter(5);
        assertEquals (5, TellRecipeStepsIntentHandler.getCounter() );
    }
    @Test
    public void testIncrementCnt() {
        assertEquals (0, TellRecipeStepsIntentHandler.getCounter());
        TellRecipeStepsIntentHandler.incrementCnt();
        assertEquals (1, TellRecipeStepsIntentHandler.getCounter());
    }  
    @Test
    public void testResetCnt() {
        TellRecipeStepsIntentHandler.resetCnt();
        assertEquals (0, TellRecipeStepsIntentHandler.getCounter());
        TellRecipeStepsIntentHandler.incrementCnt();
        assertEquals (1, TellRecipeStepsIntentHandler.getCounter());
        TellRecipeStepsIntentHandler.resetCnt();
        assertEquals (0, TellRecipeStepsIntentHandler.getCounter());

    }
    
    @Test
    public void testCanHandleReadFirstStep() {
        Recipe.saveRecipe(CookingBook.getAllRecipes().get(0));
        
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

        Optional<Response> returnResponse = handler.handle(inputMock);

        assertTrue(returnResponse.isPresent());
        Response response = returnResponse.get();

        assertFalse(response.getShouldEndSession());
        assertTrue(response.getOutputSpeech().toString()
                .contains("Zuerst wird wird das Mehl auf einen Teller gestreut"));
        
        returnResponse = handler.handle(inputMock);

        assertTrue(returnResponse.isPresent());
        response = returnResponse.get();

        assertFalse(response.getShouldEndSession());
        assertTrue(response.getOutputSpeech().toString()
                .contains("Danach das Ei mit der Milch verruehren und in einen Teller giessen"));
    }
    

}