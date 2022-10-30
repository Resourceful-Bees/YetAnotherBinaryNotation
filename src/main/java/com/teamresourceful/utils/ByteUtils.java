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

}
