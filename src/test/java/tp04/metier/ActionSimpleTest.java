/*
 * Copyright 2024 clement, Noé.
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

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for ActionSimple's enrgCours method.
 * 
 * @author noeCollongues
 */
public class ActionSimpleTest {
    private static final int DEFAULT_DAY = 1;
    private static final int DEFAULT_YEAR = 1901;
    private static final float DEFAULT_ACTIONSIMPLE_VALUE = 100.01f;
    
    /**
     * Tests proper Cours value assignment to a day, assignment should succeed.
     * 
     * @author noeCollongues
     */
    @Test
    void testValidAssignmentValueShouldSucceed() {
        //Creating test variables
        final float testValue = (float) 2.00;
        final ActionSimple action = new ActionSimple("actionTest");
        final Jour jour = new Jour(2024, 2);

        //Action
        action.enrgCours(jour, testValue);
        
        //Assert
        Assertions.assertTrue(action.valeur(jour)==testValue, "Aucun cours inséré avec la bonne valeur.");
    }
    
    /**
    * Tests Cours value assignment to a day that already has value, assignment should fail, error should be thrown.
    * @author noeCollongues
    */
    @Test
    void testValueAlreadyExistsForDayShouldFail() {
        //Creating test variables
        final float testValue1 = (float) 2.00;
        final float testValue2 = (float) 3.00;
        final ActionSimple action = new ActionSimple("actionTest");
        final Jour jour = new Jour(2021, 12);

        //Action
        action.enrgCours(jour, testValue1);
        
        //Assert
        Assertions.assertThrows(
            IllegalArgumentException.class, ()->{
                action.enrgCours(jour, testValue2);
            }
        );
    }
    
        /**
    * Tests Cours negative value assignment, assignment should fail, error should be thrown.
    * @author noeCollongues
    */
    @Test
    void testNegativeValueShouldFail() {
        //Creating test variables
        final float testValue = (float) -2.00;
        final ActionSimple action = new ActionSimple("actionTest");
        final Jour jour = new Jour(2023, 4);
        
        //Assert
        Assertions.assertThrows(
            IllegalArgumentException.class, ()->{
                action.enrgCours(jour, testValue);
            }
        );
    }
    
    /**
    * Tests proper Cours value assignment to a day, value should be returned.
    * @author clement
    */
    @Test
    void testValueExistForDate() {
        //Arrange
        final Jour jour = new Jour(DEFAULT_YEAR, DEFAULT_DAY);
        final ActionSimple actionSimple = new ActionSimple("TestActionSimple");
        actionSimple.enrgCours(jour, DEFAULT_ACTIONSIMPLE_VALUE);

        //Action
        final String expectedToString = "Cours pour le jour : " + DEFAULT_ACTIONSIMPLE_VALUE;
        final String currentToString = actionSimple.getCoursForDateToString(jour);

        //Assert
        Assertions.assertEquals(expectedToString, currentToString, "Basic construction");
    }
    
    /**
    * Tests Cours value is not assigned to a day, no value should be returned.
    * @author clement
    */
    @Test
    void testValueNotExistForDate() {
        //Arrange
        final Jour jour = new Jour(DEFAULT_YEAR, DEFAULT_DAY);
        final ActionSimple actionSimple = new ActionSimple("TestActionSimple");

        //Action
        final String expectedToString = "Il n'existe pas de cours pour cette valeur";
        final String currentToString = actionSimple.getCoursForDateToString(jour);

        //Assert
        Assertions.assertEquals(expectedToString, currentToString, "Basic construction");
    }
    
    /**
    * Tests Creating multiple Actions and checking that the static method returns all of them
    * @author noeCollongues
    */
    @Test
    void testActionListUpdatedShouldSucceed() {
        final ActionSimple auchan = new ActionSimple("Auchan");
        final ActionSimple micromania = new ActionSimple("Micromania");
        final ActionSimple disney = new ActionSimple("Disney");
        
        //Arrange
       ArrayList<ActionSimple> listActions = new ArrayList<ActionSimple>();
        listActions.add(auchan);
        listActions.add(micromania);
        listActions.add(disney);


        //Assert
        Assertions.assertTrue(ActionSimple.consulterActionsSimpleAchetables().equals(listActions), "Liste des actions consultables différentes de celles insérées.");
    }
}
