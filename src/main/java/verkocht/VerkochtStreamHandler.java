/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package verkocht;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import verkocht.handlers.CancelandStopIntentHandler;
import verkocht.handlers.FallbackIntentHandler;
import verkocht.handlers.HauptmenueIntentHandler;
import verkocht.handlers.HelpIntentHandler;
import verkocht.handlers.LaunchRequestHandler;
import verkocht.handlers.ModifyRecipeByUnitsIntentHandler;
import verkocht.handlers.ModifyRecipeIntentHandler;
import verkocht.handlers.SaveRecipeToFavoriteHandler;
import verkocht.handlers.SelectRecipeByCategoryIntentHandler;
import verkocht.handlers.SelectRecipeByFavoritsSelectorIntentHandler;
import verkocht.handlers.SelectRecipeByFavoritsStartIntentHandler;
import verkocht.handlers.SelectRecipeByNameInputRecipeIntentHandler;
import verkocht.handlers.SelectRecipeByNameStartIntentHandler;
import verkocht.handlers.SelectRecipeIntentHandler;
import verkocht.handlers.SessionEndedRequestHandler;
import verkocht.handlers.TellMeCategoriesIntentHandler;
import verkocht.handlers.TellMeIngredientsIntentHandler;
import verkocht.handlers.TellRecipeStepsIntentHandler;

public class VerkochtStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelandStopIntentHandler(),
                        new HauptmenueIntentHandler(),
                        new FallbackIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new ModifyRecipeIntentHandler(),
                        new ModifyRecipeByUnitsIntentHandler(),
                        new SaveRecipeToFavoriteHandler(),
                        new SelectRecipeByCategoryIntentHandler(), 
                        new SelectRecipeByFavoritsSelectorIntentHandler(),
                        new SelectRecipeByFavoritsStartIntentHandler(),
                        new SelectRecipeByNameInputRecipeIntentHandler(),
                        new SelectRecipeByNameStartIntentHandler(),
                        new SelectRecipeIntentHandler(), 
                        new SessionEndedRequestHandler(), 
                        new TellRecipeStepsIntentHandler(),
                        new TellMeIngredientsIntentHandler(),
                        new TellMeCategoriesIntentHandler())
//                .withSkillId("amzn1.ask.skill.c5bc074f-b96e-4343-ba1e-393f8084fe94")
                .build();
    }

    public VerkochtStreamHandler() {
        super(getSkill());
    }

}