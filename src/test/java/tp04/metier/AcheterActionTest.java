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
public class AcheterActionTest {
    
    @Test
    public void testAcheterActionSimple() {
        //Arrange
        ActionSimple action_simple = new ActionSimple("Action Test");
        //Action
        Portefeuille portefeuille = new Portefeuille();
        
        portefeuille.acheter(action_simple, 5);
             
        //Assert
        Assertions.assertEquals(portefeuille.mapLignes.get(action_simple), 5);
        portefeuille.acheter(action_simple, 2);
        Assertions.assertEquals(portefeuille.mapLignes.get(action_simple), 7);
    
        
    }
    
    @Test
    public void testVendreActionSimple() {
        //Arrange
        ActionSimple action_simple = new ActionSimple("Action Test");
        //Action
        Portefeuille portefeuille = new Portefeuille();
        
        portefeuille.acheter(action_simple, 5);
             

        portefeuille.vendre(action_simple, 2);
        Assertions.assertEquals(portefeuille.mapLignes.get(action_simple), 3);
        portefeuille.vendre(action_simple, 3);
        Assertions.assertThrows(IllegalArgumentException.class,() -> portefeuille.vendre(action_simple, 100));
        
        portefeuille.acheter(action_simple, 5);
        Assertions.assertThrows(IllegalArgumentException.class,() -> portefeuille.vendre(action_simple, 100));
    
        
    }
    
    

}
