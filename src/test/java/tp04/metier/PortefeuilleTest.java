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

/**
 *
 * @author Eric B
 */
public class PortefeuilleTest {
    
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
    public void testRecuperationNombreActionComposeePresente() {
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(new ActionComposee("Carrefour"), 3);
        Assertions.assertEquals(3, portefeuille.getQuantiteAction("Carrefour"));
        Assertions.assertEquals("Vous disposez de 3 actions pour Carrefour", portefeuille.getQuantiteActionMessage("Carrefour"));
    
    }
}
