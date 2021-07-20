package net.sf.openrocket.aerodynamics.coefficients;

import java.util.Set;

/**
 * Finds the upper neighbouring key.
 */
public class CoefficientsKeyFinderUpperNeighbour implements CoefficientsKeyFinder {

    @Override
    public CoefficientsKey find(
        Set<CoefficientsKey> keys,
        CoefficientsKey key
    ) {
        for (CoefficientsKey currentKey : keys) {
            if (currentKey.isGreaterThanOrEqualTo(key)) {
                return currentKey;
            }
        }
        throw new IllegalArgumentException("Mach number or angle of attack has no upper bound.");
    }
}
