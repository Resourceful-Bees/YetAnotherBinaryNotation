package com.teamresourceful.yabn.elements.primitives;

import com.teamresourceful.yabn.elements.YabnType;

public interface PrimitiveContents {

    YabnType getId();

    byte[] toData();

    static PrimitiveContents ofStatic(YabnType type, byte[] data) {
        return new PrimitiveContents() {
            @Override
            public YabnType getId() {
                return type;
            }

            @Override
            public byte[] toData() {
                return data;
            }
        };
    }
}
