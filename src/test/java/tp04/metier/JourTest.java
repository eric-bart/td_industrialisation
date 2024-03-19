/*
 * test push
 * Copyright 2024 David Navarre &lt;David.Navarre at irit.fr&gt;.
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
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
public class JourTest {

    private static final int DEFAULT_DAY = 1;
    private static final int DEFAULT_YEAR = 1901;
    private static final int INCORRECT_MIN_DAY = 0;
    private static final int INCORRECT_MAX_DAY = 367;
    private static final int INCORRECT_YEAR = 0;

    public JourTest() {
        
    }

    @Test
    public void testConstructorParametersAreCorrectSuccess() {
        //Arrange
        final Jour jour = new Jour(DEFAULT_YEAR, DEFAULT_DAY);
        //Action
        final String expectedToString = "Jour{" + "annee=" + DEFAULT_YEAR + ", noJour=" + DEFAULT_DAY + '}';
        final String currentToString = jour.toString();
        //Assert
        Assertions.assertEquals(expectedToString, currentToString, "Basic construction");
    }

    @Test
    public void testConstructorDayIncorrectShouldFail() {
        //Arrange
        final String expectedMessage = "noJour must be greater than 0 and lower or equal to 366";
        //Action and asserts
        IllegalArgumentException assertThrowsExactly = Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
            new Jour(DEFAULT_YEAR, INCORRECT_MIN_DAY);
        }, "noJour must be greater than 0 and lower or equal to 366");
        String currentMessage = assertThrowsExactly.getMessage();
        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");
        
        assertThrowsExactly = Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
            new Jour(DEFAULT_YEAR, INCORRECT_MAX_DAY);
        }, "noJour must be greater than 0 and lower or equal to 366");
        currentMessage = assertThrowsExactly.getMessage();
        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");

    }
    
    @Test
    public void testConstructorYearIncorrectShouldFail() {
        //Arrange
        final String expectedMessage = "annee must be greater than 1901";
        //Action and asserts
        IllegalArgumentException assertThrowsExactly = Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
            new Jour(INCORRECT_YEAR, DEFAULT_DAY);
        }, "annee must be greater than 1901");
        final String currentMessage = assertThrowsExactly.getMessage();
        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");
    }
    
    @Test
    public void testGetShouldReturnValues() {
        //Arrange
        final Jour jour = new Jour(DEFAULT_YEAR, DEFAULT_DAY);
        
        //Asserts
        Assertions.assertEquals(DEFAULT_YEAR, jour.getAnnee(), "Annee should be 1901");
        Assertions.assertEquals(DEFAULT_DAY, jour.getNoJour(), "noJour should be 1");
    }
    
    @Test
    public void testComparisonDaySuperiorSameYear() {
        //Arrange
        final Jour j1 = new Jour(2000, 12);
        final Jour j2 = new Jour(2000, 11);
        
        //Asserts
        Assertions.assertTrue(j1.compareTo(j2)>0, "j1 devrait être supérieur à j2");
    }
    
    @Test
    public void testComparisonDaySuperiorDifferentYear() {
        //Arrange
        final Jour j1 = new Jour(2001, 12);
        final Jour j2 = new Jour(2000, 11);
        
        //Asserts
        Assertions.assertTrue(j1.compareTo(j2)>0, "j1 devrait être supérieur à j2");
    }
    
    @Test
    public void testComparisonDayInferiorSameYear() {
        //Arrange
        final Jour j1 = new Jour(2000, 10);
        final Jour j2 = new Jour(2000, 11);
        
        //Asserts
        Assertions.assertTrue(j1.compareTo(j2)<0, "j1 devrait être inférieur à j2");
    }
    
    @Test
    public void testComparisonDayInferiorDifferentYear() {
        //Arrange
        final Jour j1 = new Jour(1999, 10);
        final Jour j2 = new Jour(2000, 11);
        
        //Asserts
        Assertions.assertTrue(j1.compareTo(j2)<0, "j1 devrait être inférieur à j2");
    }
    
    @Test
    public void testComparisonDayEquals() {
        //Arrange
        final Jour j1 = new Jour(1999, 10);
        final Jour j2 = new Jour(1999, 10);
        
        //Asserts
        Assertions.assertTrue(j1.compareTo(j2)==0, "j1 devrait être égal à j2");
    }
}
