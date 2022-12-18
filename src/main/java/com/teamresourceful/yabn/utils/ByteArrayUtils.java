package com.teamresourceful.yabn.utils;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class ByteArrayUtils {

    private ByteArrayUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Creates a new array with the first elements being the array and the last being the bytes provided.
     *
     * @param array The array to be at the start of the new array.
     * @param bytes The bytes to be at the end of the new array.
     * @return the new combined array.
     */
    public static byte[] add(byte[] array, byte... bytes) {
        byte[] newArray = new byte[array.length + bytes.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        System.arraycopy(bytes, 0, newArray, array.length, bytes.length);
        return newArray;
    }

    public static byte[] createIfNull(byte[] array, int size) {
        return array == null ? new byte[size] : array;
    }
}
