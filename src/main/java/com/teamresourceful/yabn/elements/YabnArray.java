package com.teamresourceful.yabn.elements;

import com.teamresourceful.yabn.utils.ByteArrayUtils;
import com.teamresourceful.yabn.utils.ByteUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public record YabnArray(@NotNull List<@NotNull YabnElement> elements) implements YabnElement {

    public YabnArray() {
        this(new ArrayList<>());
    }

    public YabnArray add(@Nullable YabnElement element) {
        elements.add(element == null ? YabnPrimitive.ofNull() : element);
        return this;
    }

    @Override
    public byte[] toData() {
        YabnType arrayType = getArrayType();
        byte[] data = arrayType != null ? new byte[]{arrayType.id} : new byte[0];
        if (elements.isEmpty()) return data;
        if (arrayType != null && !arrayType.hasData) {
            data = ByteArrayUtils.add(data, ByteUtils.vIntToBytes(elements.size()));
        } else {
            for (YabnElement element : elements) {
                if (arrayType == null) {
                    data = ByteArrayUtils.add(data, element.getType().id);
                }
                data = ByteArrayUtils.add(data, element.toData());
            }
            data = ByteArrayUtils.add(data, YabnElement.EOD);
        }
        return data;
    }

    @Override
    public YabnType getType() {
        if (elements.isEmpty()) return YabnType.EMPTY_ARRAY;
        YabnType arrayType = getArrayType();
        if (arrayType == null) return YabnType.ARRAY;
        return arrayType.hasData ? YabnType.TYPED_ARRAY : YabnType.DATALESS_TYPED_ARRAY;
    }

    @Nullable
    private YabnType getArrayType() {
        if (elements.isEmpty()) return null;
        YabnType type = elements.get(0).getType();
        for (YabnElement element : elements) {
            if (element.getType() != type) return null;
        }
        return type;
    }
}
