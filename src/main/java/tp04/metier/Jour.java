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

/**
 *
 * @author noeCollongues
 */
public class Jour implements Comparable<Jour>{

    private static final int CORRECT_MIN_YEAR = 1901;
    private static final int CORRECT_MIN_DAY = 0;
    private static final int CORRECT_MAX_DAY = 366;
    private static final int MODULO_LEAP_YEAR = 4;
    
    private int annee;
    private int noJour;

    /**
     * Get the value of annee
     *
     * @return the value of annee
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Get the value of noJour
     *
     * @return the value of noJour
     */
    public int getNoJour() {
        return noJour;
    }

    public Jour(int annee, int noJour) {
        checkArguments(annee, noJour);
        this.annee = annee;
        this.noJour = noJour;
    }
    
    private static void checkArguments(int annee, int noJour) {
        if (annee < CORRECT_MIN_YEAR) {
            throw new IllegalArgumentException("annee must be greater than 1901");
        }
        // Test 1 = noJour not negative
        // Test 2 = noJour not greater than 366
        // Test 3 = Cannot put 366 in a year that is not a leap year
        if (noJour <= CORRECT_MIN_DAY || noJour > CORRECT_MAX_DAY ||
            (noJour == CORRECT_MAX_DAY && annee % MODULO_LEAP_YEAR != 0)) {
            throw new IllegalArgumentException("noJour must be greater than 0 and lower or equal to 366");
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.annee;
        hash = 61 * hash + this.noJour;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jour other = (Jour) obj;
        if (this.annee != other.annee) {
            return false;
        }
        if (this.noJour != other.noJour) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Jour{" + "annee=" + annee + ", noJour=" + noJour + '}';
    }
    
    
    /**
    * Compare method, returns positive int if j1>j2, negative if j2<j1 and 0 if equals.
    * @author noeCollongues
    */
    @Override
    public int compareTo(Jour j2){
        if(this.equals(j2)) return 0;
        else if (this.annee > j2.annee) return 1;
        else if (this.annee < j2.annee) return -1;
        else if (this.noJour > j2.noJour) return 1;
        else return -1;
    }

}
