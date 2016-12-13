/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package architecture.pattern;

import java.util.Objects;
import org.moeaframework.core.Variable;
import org.moeaframework.core.variable.Permutation;

/**
 * This pattern is typically used for scheduling items in order from first to
 * last. The length of the decision vector is the number of items N to order and
 * the value for each decision ranges from [0,N-1] with no repeated numbers
 * allowed
 *
 * @author nozomihitomi
 */
public class Permuting implements ArchitecturalDecision {

    private static final long serialVersionUID = -3659524698177678542L;

    /**
     * The permutation problem built into MOEAframework
     */
    private final Permutation permProb;

    /**
     * This constructor creates an ordered permutation from 0 to n
     *
     * @param numElements the number of elements to include in the permutation
     */
    public Permuting(int numElements) {
        this.permProb = new Permutation(numElements);
    }

    /**
     * This constructor allows user to specify any permutation. Invalid
     * permutations will cause an illegal argument exception
     *
     * @param permutation the ordered permutation to set
     */
    public Permuting(int[] permutation) throws IllegalArgumentException {
        try {
            this.permProb = new Permutation(permutation);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    /**
     * Swaps the {@code i}th and {@code j}th elements in this permutation.
     *
     * @param i the first index
     * @param j the second index
     * @throws ArrayIndexOutOfBoundsException if {@code i} or {@code j} is out
     * or range @{code [0, size()-1]}
     */
    public void swap(int i, int j) {
        permProb.swap(i, j);
    }
    
    /**
     * gets the number of elements in the permutation
     * @return the number of elements in the permutation
     */
    public int getLength(){
        return permProb.size();
    }

    @Override
    public DecisionPattern getPattern() {
        return DecisionPattern.PERMUTING;
    }

    @Override
    public int getNumberOfVariables() {
        return 1;
    }

    @Override
    public Variable copy() {
        return permProb.copy();
    }

    @Override
    public void randomize() {
        permProb.randomize();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.permProb);
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
        final Permuting other = (Permuting) obj;
        if (!Objects.equals(this.permProb, other.permProb)) {
            return false;
        }
        return true;
    }
    
    

}
