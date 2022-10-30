package com.teamresourceful.elements.primitives;

import com.teamresourceful.elements.YabnType;
import com.teamresourceful.utils.ByteUtils;

public record IntContents(int value) implements PrimitiveNumberContents {

    @Override
    public YabnType getId() {
        return YabnType.INT;
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
