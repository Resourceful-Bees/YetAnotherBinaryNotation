package com.teamresourceful.elements;

import java.util.HashMap;
import java.util.Map;

public enum YabnType {
    NULL(0x01),
    BOOLEAN_TRUE(0x02),
    BOOLEAN_FALSE(0x03),
    BYTE(0x04, true),
    SHORT(0x05, true),
    INT(0x06, true),
    LONG(0x07, true),
    DOUBLE(0x08, true),
    FLOAT(0x09, true),
    STRING(0x0A, true),
    EMPTY_STRING(0x0B),
    ARRAY(0x0C, true),
    OBJECT(0x0D, true),
    EMPTY_ARRAY(0x0E),
    EMPTY_OBJECT(0x0F),
    NULL_STRING(0x10, true),
    TYPED_ARRAY(0x11, true),
    DATALESS_TYPED_ARRAY(0x12, true);

    private static final Map<Byte, YabnType> LOOKUP = new HashMap<>() {{
        for (YabnType type : YabnType.values()) {
            put(type.id, type);
        }
    }};

    public final byte id;
    public final boolean hasData;

    YabnType(int id) {
        this(id, false);
    }

    YabnType(int id, boolean hasData) {
        this.id = (byte) id;
        this.hasData = hasData;
    }

    public static YabnType fromId(byte id) {
        YabnType type = LOOKUP.get(id);
        if (type == null) {
            throw new YabnException("Invalid YABN type id: " + id);
        }
        return type;
    }
}
