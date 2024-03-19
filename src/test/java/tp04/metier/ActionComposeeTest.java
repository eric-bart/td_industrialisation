/*
 * Copyright 2024 Lothaire Bailly.
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
 *
 * @author Lothaire Bailly
 */
public class ActionComposeeTest {
     
    @Test
    public void testAcheterActionComposee() {
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
    public void testVendreActionComposee(){
        ActionComposee action_composee = new ActionComposee("Action composee Test");
        //Action
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action_composee, 2);
        portefeuille.vendre(action_composee, 1);
        
    }
        @Test
        void testActionListUpdatedShouldSucceed() {
        final ActionComposee auchan = new ActionComposee("Auchan");
        final ActionComposee micromania = new ActionComposee("Micromania");
        final ActionComposee disney = new ActionComposee("Disney");
        
        //Arrange
        Bourse bourse = Bourse.getBourse();
        bourse.listeActionsComposees.add(auchan);
        bourse.listeActionsComposees.add(micromania);
        bourse.listeActionsComposees.add(disney);

        //Assert
        Assertions.assertTrue(bourse.listeActionsComposees.equals(listActions), "Liste des actions consultables différentes de celles insérées.");
    }
}
