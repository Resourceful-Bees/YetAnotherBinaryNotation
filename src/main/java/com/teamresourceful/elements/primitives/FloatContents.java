package com.teamresourceful.elements.primitives;


import com.teamresourceful.elements.YabnType;
import com.teamresourceful.utils.ByteUtils;

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
