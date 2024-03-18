/*
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

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author somebody
 */
public class Portefeuille {
    Map<Action,Integer> mapActions;

    public Portefeuille() {
        this.mapActions = new HashMap();
    }

    public void acheter(Action action, int qte) {
        if (this.mapActions.containsKey(action) == false) {
            this.mapActions.put(action, qte);
        } else {
            this.mapActions.put(action,this.mapActions.get(action) + qte);
        }
    }

    public void vendre(Action action, int qte) throws IllegalArgumentException{
        if (this.mapActions.containsKey(action) == true) {
            if (this.mapActions.get(action) > qte) {
                this.mapActions.put(action,this.mapActions.get(action) - qte);
            } else if (this.mapActions.get(action) == qte) {
                this.mapActions.remove(action);
            } else{
                throw new IllegalArgumentException("Vous n'avez pas assez d'actions en stock pour vendre la quantité saisie");
            }
        }
    }

    public String toString() {
        return this.mapActions.toString();
    }

    public float valeur(Jour j) {
        float total = 0;
        for (var entry : mapActions.entrySet()) {
                total = total +  ( entry.getKey().valeur(j) * entry.getValue()) ;
            }
        return total;
    }
}
