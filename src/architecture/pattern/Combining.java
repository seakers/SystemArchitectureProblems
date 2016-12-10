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
    private int[] values;

    public Combining(int[] numAlternatives) {
        this.numAlternatives = numAlternatives;
    }

    public Combining(int[] numAlternatives, int[] value) {
        this(numAlternatives);
        this.values = value;
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
        if (value >= values[index]) {
            throw new IllegalArgumentException(String.format("Cannot access value greater than %d. Tried accessesing %d.", values[index], value));
        }
        this.values[index] = value;

    }

    /**
     * Gets the value stored in the given index
     *
     * @param index
     * @return
     */
    public int getValue(int index) {
        return values[index];
    }

    /**
     * Returns the number of alternatives available for the specified decision
     *
     * @param index of the decision
     * @return
     */
    public int getNumberOfAlternatives(int index) {
        return numAlternatives[index];
    }

    @Override
    public void randomize() {
        for(int i=0; i<values.length; i++){
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
     * @return
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
