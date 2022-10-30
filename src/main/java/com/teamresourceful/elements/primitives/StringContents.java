package com.teamresourceful.elements.primitives;

import com.teamresourceful.elements.YabnElement;
import com.teamresourceful.elements.YabnType;
import com.teamresourceful.utils.ByteArrayUtils;

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
        if (nulls != 0) return ByteArrayUtils.add(writeVInt(nulls), ByteArrayUtils.add(value.getBytes(StandardCharsets.UTF_8), YabnElement.EOD));
        return ByteArrayUtils.add(value.getBytes(StandardCharsets.UTF_8), YabnElement.EOD);
    }

    private static byte[] writeVInt(int num) {
        int pos = 0;
        byte[] bytes = null;
        if (num > 268435455 || num < 0) {
            bytes = new byte[5];
            bytes[pos++] = (byte) (128 | (num >>> 28));
        }
        if (num > 2097151 || num < 0) {
            if (bytes == null) bytes = new byte[4];
            bytes[pos++] = (byte) (128 | ((num >>> 21) & 127));
        }
        if (num > 16383 || num < 0) {
            if (bytes == null) bytes = new byte[3];
            bytes[pos++] = (byte) (128 | ((num >>> 14) & 127));
        }
        if (num > 127 || num < 0) {
            if (bytes == null) bytes = new byte[2];
            bytes[pos++] = (byte) (128 | ((num >>> 7) & 127));
        }
        if (bytes == null) bytes = new byte[1];
        bytes[pos] = (byte) (num & 127);
        return bytes;
    }

    private static int getNulls(String value) {
        int nulls = 0;
        for (char c : value.toCharArray()) {
            if (c == '\u0000') nulls++;
        }
        return nulls;
    }
}
