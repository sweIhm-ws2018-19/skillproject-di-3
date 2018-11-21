/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package main.java.verkocht;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import main.java.verkocht.handlers.CancelandStopIntentHandler;
import main.java.verkocht.handlers.FallbackIntentHandler;
import main.java.verkocht.handlers.HelpIntentHandler;
import main.java.verkocht.handlers.LaunchRequestHandler;
import main.java.verkocht.handlers.ModifyRecipeIntentHandler;
import main.java.verkocht.handlers.MyColorIsIntentHandler;
import main.java.verkocht.handlers.SelectRecipeByCategorieIntentHandler;
import main.java.verkocht.handlers.SelectRecipeByFavoritsIntentHandler;
import main.java.verkocht.handlers.SelectRecipeByNameIntentHandler;
import main.java.verkocht.handlers.SelectRecipeIntentHandler;
import main.java.verkocht.handlers.SessionEndedRequestHandler;
import main.java.verkocht.handlers.SetFavoriteIntentHandler;
import main.java.verkocht.handlers.TellMeCategoriesIntentHandler;
import main.java.verkocht.handlers.TellRecipeStepsIntentHandler;
import main.java.verkocht.handlers.WhatsMyColorIntentHandler;

public class VerkochtStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
//                        new WhatsMyColorIntentHandler(),
//                        new MyColorIsIntentHandler(),
                        new TellMeCategoriesIntentHandler(),
                        new ModifyRecipeIntentHandler(),
                        new SelectRecipeByCategorieIntentHandler(),
                        new SelectRecipeByFavoritsIntentHandler(),
                        new SelectRecipeByNameIntentHandler(),
                        new SelectRecipeIntentHandler(),
                        new SetFavoriteIntentHandler(),
                        new TellRecipeStepsIntentHandler(),
                        new LaunchRequestHandler(),
                        new CancelandStopIntentHandler(),
                        new SessionEndedRequestHandler(),
                        new HelpIntentHandler(),
                        new FallbackIntentHandler())
                // Add your skill id below
                //.withSkillId("")
                .build();
    }

    public VerkochtStreamHandler() {
        super(getSkill());
    }

}
