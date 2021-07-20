package net.sf.openrocket.aerodynamics.coefficients;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Finds the lower neighbouring key.
 */
public class CoefficientsKeyFinderLowerNeighbour implements CoefficientsKeyFinder {

    @Override
    public CoefficientsKey find(
        Set<CoefficientsKey> keys,
        CoefficientsKey key
    ) {
        List<CoefficientsKey> keyList = new ArrayList<>(keys);
        for (int i = keys.size() - 1; i >= 0; i--) {
            CoefficientsKey currentKey = keyList.get(i);
            if (currentKey.isLessThanOrEqualTo(key)) {
                return currentKey;
            }
        }
        throw new IllegalArgumentException("Mach number or angle of attack has no lower bound.");
    }
}
