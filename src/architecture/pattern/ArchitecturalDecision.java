/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package architecture.pattern;

import java.io.Serializable;
import org.moeaframework.core.Variable;

/**
 * Architectural decisions must be be one of the 6 architectural decision
 * patterns. It is a single discrete variable or a set of discrete valued
 * variables.
 *
 * @author Nozomi
 */
public interface ArchitecturalDecision extends Variable, Serializable{
    
    /**
     * Gets the architectural decision pattern for this decision
     * @return 
     */
    public DecisionPattern getPattern();
    
    /**
     * Returns the number of variables that define the decision.
     * @return 
     */
    public int getNumberOfVariables();
}
