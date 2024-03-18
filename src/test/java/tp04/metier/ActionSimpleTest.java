/*
 * Copyright 2024 Mortadha Boubaker.
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

/**
 *
 * @author Mortadha Boubaker
 */
public class ActionSimpleTest {
    
    private static final int DEFAULT_DAY = 1;
    private static final int DEFAULT_YEAR = 1901;
    private static final float DEFAULT_ACTIONSIMPLE_VALUE = 100.01f;
    
    @Test
    public void testAcheterActionSimple() {
        //Arrange
        ActionSimple action_simple = new ActionSimple("Action Test");
        //Action
        Portefeuille portefeuille = new Portefeuille();
        
        portefeuille.acheter(action_simple, 5);
             
        //Assert
        Assertions.assertEquals(portefeuille.mapActions.get(action_simple), 5);
        portefeuille.acheter(action_simple, 2);
        Assertions.assertEquals(portefeuille.mapActions.get(action_simple), 7);
    
        
    }
    
    @Test
    public void testVendreActionSimple() {
        //Arrange
        ActionSimple action_simple = new ActionSimple("Action Test");
        //Action
        Portefeuille portefeuille = new Portefeuille();
        
        portefeuille.acheter(action_simple, 5);
             

        portefeuille.vendre(action_simple, 2);
        Assertions.assertEquals(portefeuille.mapActions.get(action_simple), 3);
        portefeuille.vendre(action_simple, 2);
        Assertions.assertThrows(IllegalArgumentException.class,() -> portefeuille.vendre(action_simple, 100));
        
        portefeuille.acheter(action_simple, 5);
        Assertions.assertThrows(IllegalArgumentException.class,() -> portefeuille.vendre(action_simple, 100));
    
        
    }
    
    /**
     * Tests proper Cours value assignment to a day, assignment should succeed.
     * 
     * @author noeCollongues
     */
    @Test
    public void testValidAssignmentValueShouldSucceed() {
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
    public void testValueAlreadyExistsForDayShouldFail() {
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
    public void testNegativeValueShouldFail() {
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
    public void testValueExistForDate() {
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
    public void testValueNotExistForDate() {
        //Arrange
        final Jour jour = new Jour(DEFAULT_YEAR, DEFAULT_DAY);
        final ActionSimple actionSimple = new ActionSimple("TestActionSimple");

        //Action
        final String expectedToString = "Il n'existe pas de cours pour cette valeur";
        final String currentToString = actionSimple.getCoursForDateToString(jour);

        //Assert
        Assertions.assertEquals(expectedToString, currentToString, "Basic construction");
    }

}
