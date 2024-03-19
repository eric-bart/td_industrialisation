/*
 * Copyright 2024 Eric B.
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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Eric B
 */
public class PortefeuilleTest {
    private static final ActionSimple DEFAULT_AS1 = new ActionSimple("ActionSimpleTest1");
    private static final ActionSimple DEFAULT_AS2 = new ActionSimple("ActionSimpleTest2");
    private static final ActionComposee DEFAULT_AC = new ActionComposee("ActionComposeeTest");
    private static final Portefeuille DEFAULT_PORTEFEUILLE = new Portefeuille();

    
    @Test
    public void testRecuperationNombreActionNonPresente() {
        Portefeuille portefeuille = new Portefeuille();
        Assertions.assertEquals(0, portefeuille.getQuantiteAction("Carrefour"));
        Assertions.assertEquals(0, portefeuille.getQuantiteAction("Auchan"));
        Assertions.assertEquals("Vous ne disposez pas d'action pour Carrefour", portefeuille.getQuantiteActionMessage("Carrefour"));
        Assertions.assertEquals("Vous ne disposez pas d'action pour Auchan", portefeuille.getQuantiteActionMessage("Auchan"));
    }
    
    @Test
    public void testRecuperationNombreActionSimplePresente() {
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(new ActionSimple("Carrefour"), 3);
        Assertions.assertEquals(3, portefeuille.getQuantiteAction("Carrefour"));
        Assertions.assertEquals("Vous disposez de 3 actions pour Carrefour", portefeuille.getQuantiteActionMessage("Carrefour"));
    }
    
    @Test
    public void testAffichageCompositionPortefeuille() {
        // Arrange
        DEFAULT_PORTEFEUILLE.acheter(DEFAULT_AS1, 1);
        DEFAULT_PORTEFEUILLE.acheter(DEFAULT_AS2, 2);
        DEFAULT_PORTEFEUILLE.acheter(DEFAULT_AC, 3);
        
        // Action
        DEFAULT_PORTEFEUILLE.printAllAction();
        String expectedResult = "ActionComposeeTest, quantitée : 3\nActionSimpleTest1, quantitée : 1\nActionSimpleTest2, quantitée : 2\n";
        String currentResult = DEFAULT_PORTEFEUILLE.getAllActionMessage();
        
        // Test
        Assertions.assertEquals(expectedResult, currentResult, "Returned message is wrong");
    }

    @Test
    public void testRecuperationNombreActionComposeePresente() {
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(new ActionComposee("Carrefour"), 3);
        Assertions.assertEquals(3, portefeuille.getQuantiteAction("Carrefour"));
        Assertions.assertEquals("Vous disposez de 3 actions pour Carrefour", portefeuille.getQuantiteActionMessage("Carrefour"));
    }
    
     @Test
    public void testGetCompositionActionComposeePresent() {
        // Création du portefeuille
        Portefeuille portefeuille = new Portefeuille();

        // Création d'une action composée
        ActionComposee actionComposee = new ActionComposee("France Télévision");
        
        // Création des actions simples pour la composition
        ActionSimple actionSimple1 = new ActionSimple("France 2");
        ActionSimple actionSimple2 = new ActionSimple("France 3");
        
        // Ajout des actions simples à la composition
        actionComposee.enrgComposition(actionSimple1, 35);
        actionComposee.enrgComposition(actionSimple2, 65);
        
        // Ajout de l'action composée au portefeuille
        portefeuille.acheter(actionComposee, 10);
        
        // Appel de la méthode à tester
        String result = portefeuille.displayCompositionActionComposee("France Télévision");
        
        // Vérification du résultat
        assertEquals("| France 3 ; 65.0%| France 2 ; 35.0%", result);
    }

    @Test
    public void testGetCompositionActionComposeeAbsent() {
        // Création du portefeuille
        Portefeuille portefeuille = new Portefeuille();
        
        // Appel de la méthode à tester avec une action composée absente
        String result = portefeuille.displayCompositionActionComposee("France Télévision");
        
        // Vérification du résultat
        assertEquals("L'action composée France Télévision n'est pas présente dans le portefeuille.", result);
    }
}
