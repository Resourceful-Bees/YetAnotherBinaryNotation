package com.teamresourceful.yabn.elements.primitives;

import com.teamresourceful.yabn.elements.YabnType;
import com.teamresourceful.yabn.utils.ByteUtils;

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
