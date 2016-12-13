/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package architecture.pattern;

import org.moeaframework.core.Variable;
import static architecture.pattern.DecisionPattern.COMBINING;
import java.util.Arrays;
import org.moeaframework.core.PRNG;

/**
 *
 * @author nozomihitomi
 */
public class Combining implements ArchitecturalDecision {

    private static final long serialVersionUID = 4142639957025157845L;

    /**
     * The list of the number of possible alternatives for each decision
     */
    private final int[] numAlternatives;

    /**
     * The current values of the decision
     */
    private final int[] values;

    /**
     * The number of alternatives available for each decision. Default solution
     * has 0 selected for all decisions
     *
     * @param numAlternatives number of alternatives available for each decision
     */
    public Combining(int[] numAlternatives) {
        this.numAlternatives = numAlternatives;
        //check that all values are positive
        for(int alt : numAlternatives){
            if(alt<=0){
                throw new IllegalArgumentException("The number of alternatives for any decision must be positive");
            }
        }
        this.values = new int[numAlternatives.length];
    }

    public Combining(int[] numAlternatives, int[] value) {
        this(numAlternatives);
        if(numAlternatives.length != value.length){
            throw new IllegalArgumentException("Expected a one-to-one mapping between groups of alternatives to decision values");
        }
        for(int i=0; i<value.length; i++){
            this.values[i] = value[i];
        }
    }

    /**
     * Sets the value of the specified decision. The specified value of the
     * decision must not exceed the number of available values. The alternative
     * values are zero-indexed
     *
     * @param index the index of the decision
     * @param value the value to set the decision at the given index
     */
    public void setValue(int index, int value) {
        if (value >= numAlternatives[index]) {
            throw new IllegalArgumentException(String.format("Cannot access value greater than %d. Tried accessesing %d.", numAlternatives[index], value));
        }
        this.values[index] = value;

    }

    /**
     * Gets the value stored in the given index
     *
     * @param index the index of the decision of interest
     * @return the value stored in the given index
     */
    public int getValue(int index) {
        return values[index];
    }

    /**
     * Returns the number of alternatives available for the specified decision
     *
     * @param index of the decision
     * @return the number of possible alternatives available for the specified
     * decision
     */
    public int getNumberOfAlternatives(int index) {
        return numAlternatives[index];
    }

    @Override
    public void randomize() {
        for (int i = 0; i < values.length; i++) {
            setValue(i, PRNG.nextInt(numAlternatives.length));
        }
    }

    @Override
    public DecisionPattern getPattern() {
        return COMBINING;
    }

    @Override
    public int getNumberOfVariables() {
        return 1;
    }

    @Override
    public Variable copy() {
        return new Combining(numAlternatives, values);
    }

    /**
     * Returns a string containing the value of the decision
     *
     * @return the string containing the value of the decision
     */
    @Override
    public String toString() {
        return Arrays.toString(values);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Arrays.hashCode(this.numAlternatives);
        hash = 89 * hash + Arrays.hashCode(this.values);
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
        final Combining other = (Combining) obj;
        if (this.values != other.values) {
            return false;
        }
        return true;
    }

}
