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
public class CoursTest {
    private static final Jour DEFAULT_JOUR = new Jour(1901, 1);
    private static final float DEFAULT_CORRECT_VALUE = 100.01f;
    private static final float DEFAULT_INCORRECT_VALUE = -1;
    
    @Test
    void testConstructorParametersAreCorrectSuccess() {
        //Arrange
        final Cours cours = new Cours(DEFAULT_JOUR, DEFAULT_CORRECT_VALUE);

        //Action
        final String expectedToString = "Cours{" + "jour=" + DEFAULT_JOUR + ", valeur=" + DEFAULT_CORRECT_VALUE + '}';
        final String currentToString = cours.toString();

        //Assert
        Assertions.assertEquals(expectedToString, currentToString, "Basic construction");
    }
    
    @Test
    void testConstructorParametersIncorrectShouldFail() {
        //Arrange
        final String expectedMessage = "aValeur must be greater or equal to 0";
        //Action and asserts
        IllegalArgumentException assertThrowsExactly = Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
            new Cours(DEFAULT_JOUR, DEFAULT_INCORRECT_VALUE);
        }, "aValeur must be greater or equal to 0");
        String currentMessage = assertThrowsExactly.getMessage();
        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");
    }

}
