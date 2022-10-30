package com.teamresourceful.elements.primitives;

import com.teamresourceful.elements.YabnType;
import com.teamresourceful.utils.ByteUtils;

public record ShortContents(short value) implements PrimitiveNumberContents {

    @Override
    public YabnType getId() {
        return YabnType.SHORT;
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
