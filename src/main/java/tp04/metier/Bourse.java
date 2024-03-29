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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mortadha Boubaker
 */
public class Bourse {
    List<ActionSimple> listeActionsSimples = new ArrayList<>();
    List<ActionComposee> listeActionsComposees = new ArrayList<>();
    
    
    private static Bourse instance = null;
    private Bourse(){
      
    }
    
   
    public static Bourse getBourse(){
           if ( instance == null ) {
            instance = new Bourse();
        }
        return instance;
    }
    
    public void resetListeActionsSimples(){
        this.listeActionsSimples.clear();
    }
    public void resetListeActionsComposees(){
        this.listeActionsComposees.clear();
    }

}
