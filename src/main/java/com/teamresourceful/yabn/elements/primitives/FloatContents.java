package com.teamresourceful.yabn.elements.primitives;


import com.teamresourceful.yabn.elements.YabnType;
import com.teamresourceful.yabn.utils.ByteUtils;

public record FloatContents(float value) implements PrimitiveNumberContents {

    @Override
    public YabnType getId() {
        return YabnType.FLOAT;
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
