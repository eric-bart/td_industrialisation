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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author somebody
 */
public class ActionComposee extends Action {

    // attribut lien
    Map<ActionSimple, Float> mapPanier;

    public ActionComposee(String libelle) {
        super(libelle);
        this.mapPanier = new HashMap();
        Bourse.getBourse().listeActionsComposees.add(this);
    }

    public void enrgComposition(ActionSimple as, float pourcentage) throws IllegalArgumentException{
        float pourcentage_total = 0.0f;
        for (Map.Entry<ActionSimple, Float> entry : mapPanier.entrySet()) {
            ActionSimple key = entry.getKey();
            float val = entry.getValue();
            pourcentage_total += mapPanier.get(key);
        }
        if (pourcentage_total + pourcentage <= 100.0){
            this.mapPanier.put(as, pourcentage);
        } else {
            throw new IllegalArgumentException("Pourcentage trop élevé");
        }
    }
/**
 * 
 * @param j
 * @return 
 */
    @Override
    public float valeur(Jour j) {
        try {
            float valeur; 
            valeur = 0;
            for (ActionSimple as : this.mapPanier.keySet()) {
                valeur = valeur + (as.valeur(j) * this.mapPanier.get(as));
            }
            return valeur;
        } catch (Exception e) {
            throw new IllegalArgumentException("No value for this day");
        }
    }
    
    /**
     * Return the cours date in String format
     * @param j
     * @return String
     */
    public String getCoursForDateToString(Jour j) {
        try {
            return "Cours pour le jour : " + this.valeur(j);
        } catch(Exception e) {
            return "Il n'existe pas de cours pour cette valeur";
        }
    }
    
    /**
     * Retourne une liste d'actions simples, contenues dans l'action composée.
     * @author Eric B
     * @return la liste d'actions simples
     */
    public List<ActionSimple> getCompositionActionComposee() {
        return new ArrayList<>(this.mapPanier.keySet());
    }
    
    /**
     * Construit et retourne une chaîne de caractère correspondant à l'ensemble des actions simples associées à leur pourcentage
     * @author Eric B
     * @return la chaîne de caractère
     */
    public String getCompositionActionComposeeWithPercentage() {
        StringBuilder strBuilder = new StringBuilder();
        mapPanier.forEach((actionSimple, pourcentage) -> {
            String newLine = "| " + actionSimple.getLibelle() + " ; "+ pourcentage +"%";
            strBuilder.append(newLine);
        });
        return strBuilder.toString();
    }

    /**
     * Enregistre le cours des actions simples composant l'action pour un jour donné
     * @author samuelGardies
     * @param j jour
     * @param valeursActionsSimples : contient la valeur de chaque actions simple
     */
    public void enregistrerCours(Jour j, HashMap<ActionSimple,Float> valeursActionsSimples) {
        try {
            for (ActionSimple action : valeursActionsSimples.keySet()) {
                //enregistre le cours seulement si l'action est bien présente dans l'action composée
                if (mapPanier.keySet().contains(action))
                    action.enrgCours(j, valeursActionsSimples.get(action));
                else 
                    throw new IllegalArgumentException("L'action " + action.getLibelle() + "n'est pas présente dans cette action composée");
                    
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Erreur dans l'enregistrement du cours");
        }
    }

}
