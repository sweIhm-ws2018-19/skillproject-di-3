package test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazonaws.services.dynamodbv2.document.Item;

import verkocht.VerkochtStreamHandler;
import verkocht.handlers.TellRecipeStepsIntentHandler;
import verkocht.model.CookingBook;
import verkocht.model.Recipe;

public class TellRecipeStepsIntentHandlerTest {
    
    
    @Mock
    HandlerInput input;
    
    
    @InjectMocks
    private TellRecipeStepsIntentHandler tellRecipeStepsHandler = new TellRecipeStepsIntentHandler(); 
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
   // @Test
//    public void testHandle() {
//        //VerkochtStreamHandler verkochtStreamHandler = new VerkochtStreamHandler();
//        CookingBook coockingBook = new CookingBook();
//        Recipe selectedRecipe = coockingBook.getAllRecipes().get(0); //schnitzel
//        tellRecipeStepsHandler.handle(input);
   // }

}
