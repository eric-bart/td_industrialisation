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
        } else{
            throw new IllegalArgumentException("Vous n'avez pas d'actions");
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

    /**
     * Retourne la quantité d'action simple ou composée dans le portefeuille pour une action donnée
     * @author Eric B
     * @param actionName le nom de l'action pour laquelle récupérer la quantité enregistrée
     * @return la quantité d'action disponible pour le nom d'action passé en paramètre
     */
    public int getQuantiteAction(String actionName) {
        Integer quantite = this.mapActions.get(new ActionSimple(actionName));
        if (quantite != null) {
            return quantite;
        }
        quantite = this.mapActions.get(new ActionComposee(actionName));
        return quantite != null ? quantite : 0;
    }
    
    /**
     * Retourne une chaîne de caractère correspondant à la quantité d'action détenue pour une action donnée.
     * @author Eric B
     * @param actionName le nom de l'action pour laquelle on souhaite recueillir les informations de quantité
     * @return la chaîne de caractère
     */
    public String getQuantiteActionMessage(String actionName) {
        if(getQuantiteAction(actionName) != 0) {
            return "Vous disposez de " + getQuantiteAction(actionName) + " actions pour " + actionName;
        }
        return "Vous ne disposez pas d'action pour " + actionName;
    }
    
    /**
     * Affichage du message de quantité d'action pour un nom d'action donné.
     * @author Eric B
     * @param actionName le nom de l'action pour laquelle on souhaite afficher le message 
     */
    public void displayQuantiteAction(String actionName) {
        System.out.println(getQuantiteActionMessage(actionName));
    }
    
    /**
     * Retourne une chaîne de caractère récapitulant la composition d'une action composée (Nom action simple + Pourcentage associé)
     * @param actionName nom de l'action dont il faut récupérer la composition
     * @return la chaîne de caractère qui récapitule la composition
     */
    public String displayCompositionActionComposee(String actionName) {
        for (Map.Entry<Action, Integer> entry : this.mapActions.entrySet()) {
            Action action = entry.getKey();
            if (action instanceof ActionComposee && action.getLibelle().equals(actionName)) {
                return ((ActionComposee) action).getCompositionActionComposeeWithPercentage();
            }
        }
        return "L'action composée " + actionName + " n'est pas présente dans le portefeuille.";
    }
}
