/*
 * Copyright 2024 Moi.
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
 * Test class for ActionSimple's enrgCours method.
 * 
 * @author noeCollongues
 */
public class ActionSimpleTest {
    
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
}
