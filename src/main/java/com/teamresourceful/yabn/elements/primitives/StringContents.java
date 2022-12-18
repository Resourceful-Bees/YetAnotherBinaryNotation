package com.teamresourceful.yabn.elements.primitives;

import com.teamresourceful.yabn.elements.YabnElement;
import com.teamresourceful.yabn.elements.YabnType;
import com.teamresourceful.yabn.utils.ByteArrayUtils;
import com.teamresourceful.yabn.utils.ByteUtils;

import java.nio.charset.StandardCharsets;

public record StringContents(String value, int nulls) implements PrimitiveContents {

    public StringContents(String value) {
        this(value, getNulls(value));
    }

    @Override
    public YabnType getId() {
        return value.isEmpty() ? YabnType.EMPTY_STRING : nulls != 0 ? YabnType.NULL_STRING : YabnType.STRING;
    }

    @Override
    public byte[] toData() {
        if (value.isEmpty()) return new byte[0];
        if (nulls != 0) return ByteArrayUtils.add(ByteUtils.vIntToBytes(nulls), ByteArrayUtils.add(value.getBytes(StandardCharsets.UTF_8), YabnElement.EOD));
        return ByteArrayUtils.add(value.getBytes(StandardCharsets.UTF_8), YabnElement.EOD);
    }

    private static int getNulls(String value) {
        int nulls = 0;
        for (char c : value.toCharArray()) {
            if (c == '\u0000') nulls++;
        }
        return nulls;
    }
}
