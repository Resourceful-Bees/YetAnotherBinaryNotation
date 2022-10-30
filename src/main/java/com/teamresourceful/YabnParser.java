package com.teamresourceful;


import com.teamresourceful.elements.*;
import com.teamresourceful.reader.ByteReader;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class YabnParser {

    private YabnParser() {
        throw new IllegalStateException("Utility class");
    }

    public static YabnElement parseCompress(ByteReader data) throws YabnException {
        return YabnCompressor.compress(parse(data));
    }

    public static YabnElement parse(ByteReader data) throws YabnException {
        try {
            YabnType type = YabnType.fromId(data.readByte());
            return getElement(type, data);
        }catch (Exception exception) {
            if (exception instanceof YabnException) {
                throw exception;
            } else if (exception instanceof ArrayIndexOutOfBoundsException) {
                throw new YabnException("Array index out of bounds, make sure there is an EOD byte at the end of the data that requires it.");
            } else {
                throw new YabnException(exception.getMessage());
            }
        }
    }

    private static YabnElement readyObject(ByteReader data) {
        Map<String, YabnElement> obj = new LinkedHashMap<>();
        while (data.peek() != YabnElement.EOD) {
            YabnType type = YabnType.fromId(data.readByte());
            String key = data.readString();
            obj.put(key, getElement(type, data));
        }
        data.advance(); // skip 0x00 because it needs to go to the next elements.
        return new YabnObject(obj);
    }

    private static YabnArray readArray(ByteReader data, YabnType arrayType) {
        if (arrayType != null && !arrayType.hasData) throw new YabnException("Type for typed array is not permitted in typed arrays.");
        List<YabnElement> elements = new ArrayList<>();
        while (data.peek() != YabnElement.EOD) {
            YabnType type = arrayType == null ? YabnType.fromId(data.readByte()) : arrayType;
            elements.add(getElement(type, data));
        }
        data.advance(); // skip 0x00 because it needs to go to the next elements.
        return new YabnArray(elements);
    }

    private static YabnArray readDatalessTypedArray(ByteReader data) {
        YabnType type = YabnType.fromId(data.readByte());
        if (type.hasData) throw new YabnException("Type for dataless typed array is not permitted in dataless typed arrays.");
        int size = data.readVInt();
        List<YabnElement> elements = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            elements.add(getElement(type, data));
        }
        return new YabnArray(elements);
    }

    private static YabnElement getElement(YabnType type, ByteReader data) {
        return switch (type) {
            case NULL -> YabnPrimitive.ofNull();
            case BOOLEAN_TRUE -> YabnPrimitive.ofBoolean(true);
            case BOOLEAN_FALSE -> YabnPrimitive.ofBoolean(false);
            case BYTE -> YabnPrimitive.ofByte(data.readByte());
            case SHORT -> YabnPrimitive.ofShort(data.readShort());
            case INT -> YabnPrimitive.ofInt(data.readInt());
            case LONG -> YabnPrimitive.ofLong(data.readLong());
            case FLOAT -> YabnPrimitive.ofFloat(data.readFloat());
            case DOUBLE -> YabnPrimitive.ofDouble(data.readDouble());
            case STRING -> YabnPrimitive.ofString(data.readString());
            case NULL_STRING -> YabnPrimitive.ofString(data.readNullString());
            case EMPTY_STRING -> YabnPrimitive.ofString("");

            case ARRAY -> readArray(data, null);
            case TYPED_ARRAY -> readArray(data, YabnType.fromId(data.readByte()));
            case DATALESS_TYPED_ARRAY -> readDatalessTypedArray(data);
            case OBJECT -> readyObject(data);
            case EMPTY_ARRAY -> new YabnArray();
            case EMPTY_OBJECT -> new YabnObject();
        };
    }
}
