package com.teamresourceful.yabn.reader;

import com.teamresourceful.yabn.utils.ByteArrayList;
import com.teamresourceful.yabn.utils.ByteUtils;

import java.nio.charset.StandardCharsets;

public interface ByteReader {

    /**
     * @return returns the current byte in data without advancing the index.
     */
    byte peek();

    /**
     * Advances the index by 1.
     */
    void advance();

    /**
     * @return returns the current byte in data and advances the index.
     */
    byte readByte();

    default char readChar() {
        return (char) readByte();
    }

    default boolean readBoolean() {
        return readByte() == 0x01;
    }

    default short readShort() {
        return ByteUtils.fromBytes(readByte(), readByte());
    }

    default int readInt() {
        return ByteUtils.fromBytes(readByte(), readByte(), readByte(), readByte());
    }

    default long readLong() {
        return ByteUtils.fromBytes(readByte(), readByte(), readByte(), readByte(), readByte(), readByte(), readByte(), readByte());
    }

    default int readVInt() {
        byte curr = readByte();
        int num = curr & 127;
        while ((curr & 128) != 0) {
            curr = readByte();
            num = (num << 7) | (curr & 127);
        }
        return num;
    }

    default float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    default double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    default String readString() {
        ByteArrayList bytes = new ByteArrayList();
        while (peek() != 0) {
            bytes.add(readByte());
        }
        advance();
        return new String(bytes.toArray(), StandardCharsets.UTF_8);
    }

    default String readNullString() {
        int nulls = readVInt();
        ByteArrayList bytes = new ByteArrayList();
        while (nulls != 0 || peek() != 0) {
            byte c = readByte();
            bytes.add(c);
            if (c == 0) {
                nulls--;
            }
        }
        advance();
        return new String(bytes.toArray(), StandardCharsets.UTF_8);
    }
}
