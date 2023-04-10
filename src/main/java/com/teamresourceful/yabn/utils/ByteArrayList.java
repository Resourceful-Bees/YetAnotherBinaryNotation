package com.teamresourceful.yabn.utils;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class ByteArrayList {

    private byte[] data;
    private int size;

    public ByteArrayList() {
        this(10);
    }

    public ByteArrayList(int size) {
        this.data = new byte[size];
    }

    public void add(byte b) {
        if (this.size == this.data.length) {
            byte[] newData = new byte[data.length * 2];
            System.arraycopy(this.data, 0, newData, 0, this.data.length);
            this.data = newData;
        }
        this.data[this.size++] = b;
    }

    public byte[] toArray() {
        byte[] array = new byte[this.size];
        System.arraycopy(this.data, 0, array, 0, this.size);
        return array;
    }
}
