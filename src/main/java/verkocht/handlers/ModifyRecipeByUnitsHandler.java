package verkocht.handlers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

import verkocht.model.PhrasesForAlexa;

public class ModifyRecipeByUnitsHandler implements SpeechletV2 {
    private static final Logger log = LoggerFactory.getLogger(ModifyRecipeByUnitsHandler.class);

    private static final String RECIPE_KEY = "RECIPE";
    private static final String RECIPE_SLOT = "Recipe";
    private String ingredient;

    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
        log.info("onSessionStarted requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
                requestEnvelope.getSession().getSessionId());
    }

    @Override
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
        log.info("onLaunch requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
                requestEnvelope.getSession().getSessionId());
        return getWelcomeResponse();
    }

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        IntentRequest request = requestEnvelope.getRequest();
        Session session = requestEnvelope.getSession();
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session);

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        String savedRecipe = (String) session.getAttribute(RECIPE_KEY);
        
        if (savedRecipe.isEmpty()) {
            return getSpeechletResponse(PhrasesForAlexa.MODIFY_UNIT_SELECT_FIRST, PhrasesForAlexa.MODIFY_UNIT_SELECT_FIRST, false);
        } else if ("ModifyRecipeIntent".equals(intentName)) {
            return getRecipeFromSession(intent, session);
        } else {
            String errorSpeech = PhrasesForAlexa.MODIFY_UNIT_ERROR;
            return getSpeechletResponse(errorSpeech, errorSpeech, true);
        }
    }

    @Override
    public void onSessionEnded(SpeechletRequestEnvelope<com.amazon.speech.speechlet.SessionEndedRequest> requestEnvelope) {
        log.info("onSessionEnded requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
                requestEnvelope.getSession().getSessionId());
    }

    private SpeechletResponse getWelcomeResponse() {
        String speechText = PhrasesForAlexa.MODIFY_UNIT_WELCOME;
        String repromptText = PhrasesForAlexa.MODIFY_UNIT_REPROMT;

        return getSpeechletResponse(speechText, repromptText, true);
    }

    private SpeechletResponse getRecipeFromSession(final Intent intent, final Session session) {
        String speechText;
        boolean isAskResponse = false;

        String savedRecipe = (String) session.getAttribute(RECIPE_KEY);
        
        if (!savedRecipe.isEmpty()) {
            speechText = String.format(PhrasesForAlexa.MODIFY_UNIT_DONE, savedRecipe);
        } else {
            speechText = PhrasesForAlexa.MODIFY_UNIT_SELECT_INGREDIENT;
            isAskResponse = true;
        }

        return getSpeechletResponse(speechText, speechText, isAskResponse);
    }

    private SpeechletResponse setIngredientToChange(final Intent intent, final Session session) {
        // NOT IMPLEMENTED YET

        return getSpeechletResponse(PhrasesForAlexa.MODIFY_UNIT_ERROR, PhrasesForAlexa.MODIFY_UNIT_ERROR, true);
    }
    
    private SpeechletResponse getSpeechletResponse(String speechText, String repromptText, boolean isAskResponse) {
        SimpleCard card = new SimpleCard();
        card.setTitle("Rezept modifizieren");
        card.setContent(speechText);

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        if (isAskResponse) {
            PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
            repromptSpeech.setText(repromptText);
            Reprompt reprompt = new Reprompt();
            reprompt.setOutputSpeech(repromptSpeech);

            return SpeechletResponse.newAskResponse(speech, reprompt, card);
        } else {
            return SpeechletResponse.newTellResponse(speech, card);
        }
    }

}
