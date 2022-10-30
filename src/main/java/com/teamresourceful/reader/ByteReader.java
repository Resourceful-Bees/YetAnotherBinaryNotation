package com.teamresourceful.reader;

import com.teamresourceful.utils.ByteUtils;

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

//    default int readVInt() {
//        int num = 0;
//        int pos = 0;
//
//        byte curr;
//        do {
//            curr = readByte();
//            num |= (curr & 127) << (pos++ * 7);
//            if (pos > 5) {
//                throw new RuntimeException("VInt data exceeds maximum length of 5 bytes.");
//            }
//        } while((curr & 128) == 128);
//
//        return num;
//    }

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
        StringBuilder builder = new StringBuilder();
        while (peek() != 0) {
            builder.append(readChar());
        }
        advance();
        return builder.toString();
    }

    default String readNullString() {
        int nulls = readVInt();
        String output = "";
        while (nulls != 0 || peek() != 0) {
            char c = readChar();
            //This is like this because StringBuilder checks if it can encode and then decides to remove nulls.
            //noinspection StringConcatenationInLoop
            output += c;
            if (c == 0) {
                nulls--;
            }
        }
        advance();
        return output;
    }
}
