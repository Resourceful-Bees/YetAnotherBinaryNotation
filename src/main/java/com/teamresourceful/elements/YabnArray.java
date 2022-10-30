package com.teamresourceful.elements;

import com.teamresourceful.utils.ByteArrayUtils;
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
        for (YabnElement element : elements) {
            if (arrayType == null) {
                data = ByteArrayUtils.add(data, element.getType().id);
            }
            data = ByteArrayUtils.add(data, element.toData());
        }
        return ByteArrayUtils.add(data, YabnElement.EOD);
    }

    @Override
    public YabnType getType() {
        if (elements.isEmpty()) return YabnType.EMPTY_ARRAY;
        return getArrayType() != null ? YabnType.TYPED_ARRAY : YabnType.ARRAY;
    }

    @Nullable
    private YabnType getArrayType() {
        if (elements.isEmpty()) return null;
        YabnType type = elements.get(0).getType();
        for (YabnElement element : elements) {
            if (element.getType() != type) return null;
        }
        return type.hasData ? type : null;
    }
}
