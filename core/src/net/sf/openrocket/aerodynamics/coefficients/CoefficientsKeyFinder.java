package net.sf.openrocket.aerodynamics.coefficients;

import java.util.Set;

/**
 * Interface to key finder.
 */
public interface CoefficientsKeyFinder {

    /**
     * Pre-condition: The keys must be sorted from lowest to highest by machNumber and angleOfAttack.
     * @param keys The coefficient keys to search through. These should be in ascending order.
     * @param key A key that does not exactly match existing keys.
     * @return The found coefficient key.
     */
    CoefficientsKey find(Set<CoefficientsKey> keys, CoefficientsKey key);
}
