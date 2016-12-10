/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package architecture;

import architecture.pattern.ArchitecturalDecision;
import java.util.Collection;
import java.util.Objects;
import org.moeaframework.core.Solution;

/**
 * The architecture is defined by the types of decisions and the values of those
 * decisions
 *
 * @author nozomihitomi
 */
public class Architecture extends Solution {

    private static final long serialVersionUID = -2195550924166538032L;
    
    public Architecture(Solution solution) {
        super(solution);
        if (solution instanceof Architecture) {
            Architecture arch = (Architecture) solution;
        }else{
            throw new IllegalArgumentException("Expected argument to be of type Architecture");
        }
    }

    public Architecture(Collection<ArchitecturalDecision> decisions, int numberOfObjectives) {
        super(decisions.size(), numberOfObjectives);
        int ind = 0;
        for (ArchitecturalDecision dec : decisions) {
            setVariable(ind, dec);
            ind++;
        }
    }

    /**
     * Checks all the constraint values of the architecture.
     * @return 
     */
    public boolean isFeasible() {
        for (int i = 0; i < getNumberOfConstraints(); i++) {
            if (getConstraint(i) > 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Solution copy() {
        return new Architecture(this);
    }

    /**
     * Returns the values of each decision
     *
     * @return
     */
    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < getNumberOfObjectives(); i++) {
            out += getVariable(i).toString() + "::";
        }
        return out;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        for (int i = 0; i < getNumberOfVariables(); i++) {
            hash = 67 * hash + Objects.hashCode(this.getVariable(i));
        }
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
        final Architecture other = (Architecture) obj;
        for (int i = 0; i < getNumberOfVariables(); i++) {
            if (!this.getVariable(i).equals(other.getVariable(i))) {
                return false;
            }
        }
        return true;
    }

}
