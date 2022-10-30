package com.teamresourceful.elements;

import com.teamresourceful.elements.primitives.*;

public record YabnPrimitive(PrimitiveContents contents) implements YabnElement {

    private static final YabnPrimitive NULL = new YabnPrimitive(PrimitiveContents.ofStatic(YabnType.NULL, new byte[0]));

    public static YabnPrimitive ofNull() {
        return NULL;
    }

    public static YabnPrimitive ofString(String string) {
        return new YabnPrimitive(new StringContents(string));
    }

    public static YabnPrimitive ofBoolean(boolean bool) {
        return new YabnPrimitive(new BooleanContents(bool));
    }

    public static YabnPrimitive ofFloat(float f) {
        return new YabnPrimitive(new FloatContents(f));
    }

    public static YabnPrimitive ofDouble(double d) {
        return new YabnPrimitive(new DoubleContents(d));
    }

    public static YabnPrimitive ofByte(byte b) {
        return new YabnPrimitive(new ByteContents(b));
    }

    public static YabnPrimitive ofShort(short s) {
        return new YabnPrimitive(new ShortContents(s));
    }

    public static YabnPrimitive ofInt(int i) {
        return new YabnPrimitive(new IntContents(i));
    }

    public static YabnPrimitive ofLong(long l) {
        return new YabnPrimitive(new LongContents(l));
    }

    @Override
    public byte[] toData() {
        return contents.toData();
    }

    @Override
    public YabnType getType() {
        return this.contents.getId();
    }
}
