package com.teamresourceful.yabn;

import com.teamresourceful.yabn.elements.YabnArray;
import com.teamresourceful.yabn.elements.YabnElement;
import com.teamresourceful.yabn.elements.YabnObject;
import com.teamresourceful.yabn.elements.YabnPrimitive;
import com.teamresourceful.yabn.elements.primitives.DoubleContents;
import com.teamresourceful.yabn.elements.primitives.FloatContents;
import com.teamresourceful.yabn.elements.primitives.PrimitiveNumberContents;

public final class YabnCompressor {

    private YabnCompressor() {
        throw new IllegalStateException("Utility class");
    }

    public static YabnElement compress(YabnElement element) {
        if (element instanceof YabnObject object) {
            YabnObject newObject = new YabnObject();
            for (var entry : object.elements().entrySet()) {
                newObject.put(entry.getKey(), compress(entry.getValue()));
            }
            return object;
        } else if (element instanceof YabnArray array) {
            YabnArray newArray = new YabnArray();
            for (var item : array.elements()) {
                newArray.add(compress(item));
            }
            return newArray;
        } else if (element instanceof YabnPrimitive primitive) {
            if (primitive.contents() instanceof PrimitiveNumberContents numberContent) {
                if (numberContent instanceof DoubleContents doubles) {
                    return compressFloatingNumber(doubles.value());
                } else if (!(numberContent instanceof FloatContents)) {
                    return compressNonFloatingNumber(numberContent.getAsLong());
                }
            }
        }
        return element;
    }

    public static YabnElement compressNonFloatingNumber(long l) {
        if ((byte) l == l) return YabnPrimitive.ofByte((byte) l);
        else if ((short) l == l) return YabnPrimitive.ofShort((short) l);
        else if ((int) l == l) return YabnPrimitive.ofInt((int) l);
        return YabnPrimitive.ofLong(l);
    }

    public static YabnElement compressFloatingNumber(double d) {
        return (float) d == d ? YabnPrimitive.ofFloat((float) d) : YabnPrimitive.ofDouble(d);
    }

}
