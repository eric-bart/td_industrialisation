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

/**
 *
 * @author clement
 */
class ActionSimpleTest {
    private static final int DEFAULT_DAY = 1;
    private static final int DEFAULT_YEAR = 1901;
    private static final float DEFAULT_ACTIONSIMPLE_VALUE = 100.01f;
    
    public ActionSimpleTest() {
    }

    
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
}
