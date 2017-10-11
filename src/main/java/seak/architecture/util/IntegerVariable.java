/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seak.architecture.util;

import java.text.MessageFormat;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.moeaframework.core.PRNG;
import org.moeaframework.core.Variable;

/**
 * An integer variable is a discrete-valued variable with an upper and lower
 * bound.
 * 
 * Implementation mirrors that of RealVariable from MOEAFramework
 *
 * @author nozomihitomi
 */
public class IntegerVariable implements Variable {

    private static final long serialVersionUID = -105255218829744090L;

    private static final String VALUE_OUT_OF_BOUNDS
            = "value out of bounds (value: {0}, min: {1}, max: {2})";
    /**
     * The current value of this decision variable.
     */
    private int value;

    /**
     * The lower bound of this decision variable.
     */
    private final int lowerBound;

    /**
     * The upper bound of this decision variable.
     */
    private final int upperBound;
    
    /**
     * Constructs a integer variable in the range {@code lowerBound <= x <=
     * upperBound} with the specified initial value.
     *
     * @param value the initial value of this decision variable
     * @param lowerBound the lower bound of this decision variable, inclusive
     * @param upperBound the upper bound of this decision variable, inclusive
     * @throws IllegalArgumentException if the value is out of bounds
     * {@code (value < lowerBound) || (value > upperBound)}
     */
    public IntegerVariable(int value, int lowerBound, int upperBound) {
        super();
        this.value = value;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;

        if ((value < lowerBound) || (value > upperBound)) {
            throw new IllegalArgumentException(MessageFormat.format(
                    VALUE_OUT_OF_BOUNDS, value, lowerBound, upperBound));
        }
    }

    /**
     * Returns the current value of this decision variable.
     *
     * @return the current value of this decision variable
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of this decision variable.
     *
     * @param value the new value for this decision variable
     * @throws IllegalArgumentException if the value is out of bounds
     * {@code (value < getLowerBound()) || (value > getUpperBound())}
     */
    public void setValue(int value) {
        if ((value < lowerBound) || (value > upperBound)) {
            throw new IllegalArgumentException(MessageFormat.format(
                    VALUE_OUT_OF_BOUNDS, value, lowerBound, upperBound));
        }

        this.value = value;
    }

    /**
     * Returns the lower bound of this decision variable.
     *
     * @return the lower bound of this decision variable, inclusive
     */
    public int getLowerBound() {
        return lowerBound;
    }

    /**
     * Returns the upper bound of this decision variable.
     *
     * @return the upper bound of this decision variable, inclusive
     */
    public int getUpperBound() {
        return upperBound;
    }

    @Override
    public IntegerVariable copy() {
        return new IntegerVariable(value, lowerBound, upperBound);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(lowerBound)
                .append(upperBound)
                .append(value)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if ((obj == null) || (obj.getClass() != getClass())) {
            return false;
        } else {
            IntegerVariable rhs = (IntegerVariable) obj;

            return new EqualsBuilder()
                    .append(lowerBound, rhs.lowerBound)
                    .append(upperBound, rhs.upperBound)
                    .append(value, rhs.value)
                    .isEquals();
        }
    }

    @Override
    public void randomize() {
        setValue(PRNG.nextInt(lowerBound, upperBound));
    }
}
