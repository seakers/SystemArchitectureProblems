/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package architecture.pattern;

import org.moeaframework.core.PRNG;
import org.moeaframework.core.variable.BinaryVariable;

/**
 * There is a set of entities that can be seen as the nodes in a graph and an
 * architecture fragment is defined by the set of edges in the graph, i.e. the
 * way of connecting those nodes.
 *
 * @author nozomihitomi
 */
public class Connecting extends BinaryVariable implements ArchitecturalDecision {

    private static final long serialVersionUID = -193099801348840331L;

    /**
     * flag for if the graph is directed
     */
    private final boolean isDirected;
    
    /**
     * flag for if loops are allowed (self-connecting edges)
     */
    private final boolean loop;

    /**
     * The number of nodes in the graph
     */
    private final int nNodes;

    /**
     * This constructor creates a graph with no connected edges.
     *
     * @param nNodes
     * @param isDirected flag to determine if graph is directed
     * @param loop flag to determine if loops are allowed
     */
    public Connecting(int nNodes, boolean isDirected, boolean loop) {
        super(nNodes * nNodes);
        this.isDirected = isDirected;
        this.nNodes = nNodes;
        this.loop = loop;
    }

    /**
     * Connects nodes i and j. If directed, this will only establish a directed
     * edge from i to j. If undirected an undirected edge will be created
     * between i and j. Node count starts with 0.
     *
     * @param i
     * @param j
     * @return the value of the adjacency matrix in cell i,j before the change.
     * True = connected. False = not connected.
     */
    public boolean connect(int i, int j) {
        if(!loop && i==j){
            throw new IllegalArgumentException("Loops are not allowed.");
        }
        
        boolean out = this.get(i * nNodes + j);
        if (isDirected) {
            this.set(i * nNodes + j, true);
        } else {
            this.set(i * nNodes + j, true);
            this.set(j * nNodes + i, true);
        }

        return out;
    }

    /**
     * Removes the connection between element i from the left side to element j
     * on the right side. Elements numbered starting with 0. If directed, this
     * will only disconnect a directed edge from i to j. If undirected all edges
     * between i and j will be disconnected .Node count starts with 0.
     *
     * @param i
     * @param j
     * @return the value of the adjacency matrix in cell i,j before the change.
     * True = connected. False = not connected.
     */
    public boolean disconnect(int i, int j) {
        boolean out = this.get(i * nNodes + j);
        if (isDirected) {
            this.set(i * nNodes + j, false);
        } else {
            this.set(i * nNodes + j, false);
            this.set(j * nNodes + i, false);
        }

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

    /**
     * Gets the number of nodes in the graph
     *
     * @return the number of nodes in the graph
     */
    public int getNumberOfNodes() {
        return nNodes;
    }

    @Override
    public DecisionPattern getPattern() {
        return DecisionPattern.CONNECTING;
    }

    @Override
    public int getNumberOfVariables() {
        return 1;
    }

    @Override
    public void randomize() {
        if (isDirected) {
            for(int i=0; i<nNodes; i++){
                for(int j=i; j<nNodes; j++){
                    if(PRNG.nextBoolean())
                        connect(i, j);
                }
            }
        } else {
            super.randomize();
        }
        if(!loop){
            for(int i=0; i<nNodes; i++){
                disconnect(i, i);
            }
        }
    }

}
