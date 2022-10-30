package com.teamresourceful.elements;

import org.jetbrains.annotations.Nullable;

public sealed interface YabnElement permits YabnObject, YabnPrimitive, YabnArray {

    byte EOD = 0x00; // end of data

    byte[] toData();

    YabnType getType();

    default boolean isNull() {
        return YabnType.NULL.equals(getType());
    }

    @Nullable
    default YabnElement getOrNull() {
        return isNull() ? null : this;
    }
}
