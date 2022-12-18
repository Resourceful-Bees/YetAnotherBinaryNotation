package com.teamresourceful.yabn.elements.primitives;


import com.teamresourceful.yabn.elements.YabnType;
import com.teamresourceful.yabn.utils.ByteUtils;

public record DoubleContents(double value) implements PrimitiveNumberContents {

    @Override
    public YabnType getId() {
        return YabnType.DOUBLE;
    }

    @Override
    public byte[] toData() {
        return ByteUtils.toBytes(value);
    }

    @Override
    public Number getValue() {
        return value;
    }
}
