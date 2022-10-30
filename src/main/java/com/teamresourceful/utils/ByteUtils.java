package com.teamresourceful.utils;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class ByteUtils {

    private ByteUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static byte[] toBytes(short value) {
        return new byte[] {(byte) (value >> 8), (byte) value};
    }

    public static byte[] toBytes(int value) {
        return new byte[] {(byte) (value >> 24), (byte) (value >> 16), (byte) (value >> 8), (byte) value};
    }

    public static byte[] toBytes(long value) {
        byte[] result = new byte[8];
        for (int i = 7; i >= 0; i--) {
            result[i] = (byte) (value & 0xffL);
            value >>= 8;
        }
        return result;
    }

    public static byte[] toBytes(double value) {
        return toBytes(Double.doubleToLongBits(value));
    }

    public static byte[] toBytes(float value) {
        return toBytes(Float.floatToIntBits(value));
    }

    public static short fromBytes(byte byte1, byte byte2) {
        return (short) ((byte1 << 8) | (byte2 & 0xFF));
    }

    public static int fromBytes(byte byte1, byte byte2, byte byte3, byte byte4) {
        return (byte1 << 24) | ((byte2 & 0xFF) << 16) | ((byte3 & 0xFF) << 8) | (byte4 & 0xFF);
    }

    public static long fromBytes(byte byte1, byte byte2, byte byte3, byte byte4, byte byte5, byte byte6, byte byte7, byte byte8) {
        return (byte1 & 0xFFL) << 56 | (byte2 & 0xFFL) << 48 | (byte3 & 0xFFL) << 40 | (byte4 & 0xFFL) << 32 | (byte5 & 0xFFL) << 24 | (byte6 & 0xFFL) << 16 | (byte7 & 0xFFL) << 8 | (byte8 & 0xFFL);
    }

    public static byte[] vIntToBytes(int num) {
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

}
