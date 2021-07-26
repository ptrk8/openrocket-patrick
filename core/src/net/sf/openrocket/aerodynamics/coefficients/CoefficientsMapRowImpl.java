package net.sf.openrocket.aerodynamics.coefficients;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CoefficientsMapRowImpl implements CoefficientsMapRow {

    private final List<String> headers = new ArrayList<>();
    private final List<String> values = new ArrayList<>();

    public CoefficientsMapRowImpl(CoefficientsKey key, BigDecimal coefficient) {
        headers.addAll(key.getHeaders());
        headers.add("value");

        values.addAll(key.getValues());
        values.add(coefficient.toPlainString());
    }

    @Override
    public List<String> getHeaders() {
        return headers;
    }

    @Override
    public List<String> getValues() {
        return values;
    }
}
