/*
 * Copyright 2024 clement.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tp04.metier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

/**
 *
 * @author clement
 */
public class ActionComposeeTest {
    private static final int DEFAULT_DAY = 1;
    private static final int DEFAULT_YEAR = 1901;
    private static final float DEFAULT_ACTIONSIMPLE1_VALUE = 100;
    private static final float DEFAULT_ACTIONSIMPLE2_VALUE = 50;
    private static final float DEFAULT_COMPOSITION = 0.5f;
    private static final Jour DEFAULT_JOUR = new Jour(DEFAULT_YEAR, DEFAULT_DAY);
    
    @Test
    void testValueExistForDate() {
        //Arrange
        final ActionSimple Default_AS1 = new ActionSimple("ActionSimpleTest1");
        final ActionSimple Default_AS2 = new ActionSimple("ActionSimpleTest2");
        Default_AS1.enrgCours(DEFAULT_JOUR, DEFAULT_ACTIONSIMPLE1_VALUE);
        Default_AS2.enrgCours(DEFAULT_JOUR, DEFAULT_ACTIONSIMPLE2_VALUE);
        final ActionComposee Default_AC = new ActionComposee("ActionComposeeTest");
        Default_AC.enrgComposition(Default_AS1, DEFAULT_COMPOSITION);
        Default_AC.enrgComposition(Default_AS2, DEFAULT_COMPOSITION);

        //Action
        final String expectedValue = "Cours pour le jour : 75.0";
        final String currentValue = Default_AC.getCoursForDateToString(DEFAULT_JOUR);

        //Assert
        Assertions.assertEquals(expectedValue, currentValue, "Returned values are wrong");
    }
    
    @Test
    void testValueNotExistForDate() {
        //Arrange
        final ActionSimple Default_AS1 = new ActionSimple("ActionSimpleTest1");
        final ActionSimple Default_AS2 = new ActionSimple("ActionSimpleTest2");
        Default_AS1.enrgCours(DEFAULT_JOUR, DEFAULT_ACTIONSIMPLE1_VALUE);
        final ActionComposee Default_AC = new ActionComposee("ActionComposeeTest");
        Default_AC.enrgComposition(Default_AS1, DEFAULT_COMPOSITION);
        Default_AC.enrgComposition(Default_AS2, DEFAULT_COMPOSITION);

        //Action
        final String expectedValue = "Il n'existe pas de cours pour cette valeur";
        final String currentValue = Default_AC.getCoursForDateToString(DEFAULT_JOUR);

        //Assert
        Assertions.assertEquals(expectedValue, currentValue, "Returned values are wrong");
    }
         
    @Test
    void testAcheterActionComposee() {
        //Arrange
        ActionSimple action_simple = new ActionSimple("Action Test");
        ActionSimple action_simple_2 = new ActionSimple("Action Test_2");
        ActionComposee action_composee = new ActionComposee("Action composee Test");
        //Action
        Portefeuille portefeuille = new Portefeuille();
        action_composee.enrgComposition(action_simple, 20.0f);
        action_composee.enrgComposition(action_simple_2, 80.0f);
        Assertions.assertThrows(IllegalArgumentException.class,() -> action_composee.enrgComposition(action_simple_2, 40.0f));

        
        portefeuille.acheter(action_composee, 5);
            
        //Assert
        Assertions.assertEquals(portefeuille.mapActions.get(action_composee), 5);
        portefeuille.acheter(action_composee, 2);
        Assertions.assertEquals(portefeuille.mapActions.get(action_composee), 7);
    
        
    }
    
    @Test
    void testVendreActionComposee(){
        ActionComposee action_composee = new ActionComposee("Action composee Test");
        //Action
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action_composee, 2);
        portefeuille.vendre(action_composee, 1);
        
    }
    
    /**
     * Tests ActionComposee enregistrerCours() avec une action ne faisant pas partie de l'action composée
     * 
     * @author samuelGardies
     */
    @Test
    void testEnregistrerCoursWrongAction() {
        //Arrange
        ActionComposee action_composee_test = new ActionComposee("Action composee Test");
        final ActionSimple auchan = new ActionSimple("Auchan");
        final ActionSimple micromania = new ActionSimple("Micromania");
        final ActionSimple disney = new ActionSimple("Disney");
        final ActionSimple mauvaiseAction = new ActionSimple("mauvaiseAction");
        //Action
        HashMap<ActionSimple,Float> valeursActionsSimple = new HashMap<ActionSimple,Float>();
        valeursActionsSimple.put(auchan, 5.0f);
        valeursActionsSimple.put(micromania, 10.0f);
        valeursActionsSimple.put(mauvaiseAction, 15.0f);
        action_composee_test.enrgComposition(auchan, 40);
        action_composee_test.enrgComposition(micromania, 50);
        action_composee_test.enrgComposition(disney, 10);
        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> action_composee_test.enregistrerCours(DEFAULT_JOUR, valeursActionsSimple));    
    }
    
    /**
     * Tests ActionComposee enregistrerCours() valide
     * 
     * @author samuelGardies
     */
    @Test
    void testEnregistrerCoursValid() {
        //Arrange
        ActionComposee action_composee_test = new ActionComposee("Action composee Test");
        final ActionSimple auchan = new ActionSimple("Auchan");
        final ActionSimple micromania = new ActionSimple("Micromania");
        final ActionSimple disney = new ActionSimple("Disney");
        //Action
        HashMap<ActionSimple,Float> valeursActionsSimple = new HashMap<ActionSimple,Float>();
        valeursActionsSimple.put(auchan, 5.0f);
        valeursActionsSimple.put(micromania, 10.0f);
        valeursActionsSimple.put(disney, 15.0f);
        action_composee_test.enrgComposition(auchan, 40);
        action_composee_test.enrgComposition(micromania, 50);
        action_composee_test.enrgComposition(disney, 10);
        //Assert
        //to do quand l'user story 9 sera faite
    }

}
