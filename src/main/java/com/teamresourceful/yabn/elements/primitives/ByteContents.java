package com.teamresourceful.yabn.elements.primitives;

import com.teamresourceful.yabn.elements.YabnType;

public record ByteContents(byte value) implements PrimitiveNumberContents {

    @Override
    public YabnType getId() {
        return YabnType.BYTE;
    }

    @Override
    public byte[] toData() {
        return new byte[]{value};
    }

    @Override
    public Number getValue() {
        return value;
    }
}
