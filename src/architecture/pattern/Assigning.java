/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package architecture.pattern;

import static architecture.pattern.DecisionPattern.*;
import java.util.BitSet;
import org.moeaframework.core.variable.BinaryVariable;

/**
 * This pattern assumes there are two different sets of entities, and an
 * architecture fragment is defined by assigning each entity from one set to any
 * subset of entities from the other set.
 *
 * @author nozomihitomi
 */
public class Assigning extends BinaryVariable implements ArchitecturalDecision {

    private static final long serialVersionUID = -537105464191766990L;

    /**
     * The number of elements to assign (left hand side)
     */
    private final int mNodes;

    /**
     * The number of elements that get elements assigned (right hand side)
     */
    private final int nNodes;


    /**
     * This constuctor creates an assignment matrix with m items assigned to n
     * items
     *
     * @param mNodes the number of elements that are being assigned
     * @param nNodes the number of elements that will have things assigned to
     * it.
     */
    public Assigning(int mNodes, int nNodes) {
        super(mNodes * nNodes);
        this.mNodes = mNodes;
        this.nNodes = nNodes;
    }

    @Override
    public DecisionPattern getPattern() {
        return ASSINGING;
    }

   /**
     * Assigns element i from the left side to element j on the right side.
     * Elements numbered starting with 0.
     *
     * @param i element in the left hand side
     * @param j element in the right hand side
     * @return the value of the assignment matrix in cell i,j before the change.
     * True = assigned. False = not assigned.
     */
    public boolean connect(int i, int j) {
        boolean out = this.get(i * nNodes + j);
        this.set(i * nNodes + j, true);
        return out;
    }
    
    /**
     * Removes the connection between element i from the left side to element j on the right side.
     * Elements numbered starting with 0.
     *
     * @param i element in the left hand side
     * @param j element in the right hand side
     * @return the value of the assignment matrix in cell i,j before the change.
     * True = assigned. False = not assigned.
     */
    public boolean disconnect(int i, int j) {
        boolean out = this.get(i * nNodes + j);
        this.set(i * nNodes + j, false);
        return out;
    }

    /**
     * Checks if element i from the left hand side and element j from the right
     * hand side are assigned. If assigned, returns true. Else false.
     *
     * @param i element in the left hand side
     * @param j element in the right hand side
     * @return if element i from the left hand side and element j from the right
     * hand side are assigned.
     */
    public boolean isConnected(int i, int j) {
        return this.get(i * nNodes + j);
    }

    @Override
    public Assigning copy() {
        Assigning out =  new Assigning(mNodes, nNodes);
        BitSet bitset = this.getBitSet();
        for (int i = bitset.nextSetBit(0); i >= 0; i = bitset.nextSetBit(i + 1)) {
            out.set(i, true);
        }
        return out;
    }
    
    @Override
    public int getNumberOfVariables() {
        return 1;
    }
    
    /**
     * Returns the number of elements on the right hand side of the assigning pattern
     * @return elements on the left hand side of the assigning pattern
     */
    public int getNumberOfLHS(){
        return mNodes;
    }
    
    /**
     * Returns the number of elements on the right hand side of the assigning pattern
     * @return the number of elements on the right hand side of the assigning pattern
     */
    public int getNumberOfRHS(){
        return nNodes;
    }
    
    @Override
    public String toString(){
        String str = "";
        for(int i=0; i<super.getNumberOfBits(); i++){
            if(getBitSet().get(i)){
                str += 1;
            }else{
                str += 0;
            }
        }
        return str;
    }

}
