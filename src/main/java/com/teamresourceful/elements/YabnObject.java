package com.teamresourceful.elements;

import com.teamresourceful.utils.ByteArrayUtils;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public record YabnObject(Map<String, YabnElement> elements) implements YabnElement {

    public YabnObject() {
        this(new LinkedHashMap<>());
    }

    public YabnObject put(String key, @Nullable YabnElement element) {
        elements.put(key, element == null ? YabnPrimitive.ofNull() : element);
        return this;
    }

    public YabnElement get(String key) {
        return elements.get(key);
    }

    @Override
    public byte[] toData() {
        byte[] data = new byte[0];
        if (elements.isEmpty()) return data;
        for (var entry : this.elements.entrySet()) {
            YabnElement element = entry.getValue();
            byte[] dataEntry = ByteArrayUtils.add(new byte[]{element.getType().id}, entry.getKey().getBytes(StandardCharsets.UTF_8));
            dataEntry = ByteArrayUtils.add(dataEntry, YabnElement.EOD);
            dataEntry = ByteArrayUtils.add(dataEntry, element.toData());
            data = ByteArrayUtils.add(data, dataEntry);
        }
        return ByteArrayUtils.add(data, YabnElement.EOD);
    }

    @Override
    public YabnType getType() {
        return elements.isEmpty() ? YabnType.EMPTY_OBJECT : YabnType.OBJECT;
    }
}
