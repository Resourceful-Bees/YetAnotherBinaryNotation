package com.teamresourceful.yabn.elements.primitives;

public interface PrimitiveNumberContents extends PrimitiveContents {

    Number getValue();

    default byte getAsByte() {
        return getValue().byteValue();
    }

    default short getAsShort() {
        return getValue().shortValue();
    }

    default int getAsInt() {
        return getValue().intValue();
    }

    default long getAsLong() {
        return getValue().longValue();
    }

    default float getAsFloat() {
        return getValue().floatValue();
    }

    default double getAsDouble() {
        return getValue().doubleValue();
    }

}
