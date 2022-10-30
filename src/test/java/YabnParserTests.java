import com.teamresourceful.elements.*;
import com.teamresourceful.elements.primitives.PrimitiveNumberContents;
import com.teamresourceful.elements.primitives.StringContents;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class YabnParserTests {

    @Test
    public void parseNull() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x01);
        Assertions.assertEquals(YabnType.NULL, element.getType(), "Expected null element type");
    }

    @Test
    public void parseBooleanTrue() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x02);
        Assertions.assertEquals(YabnType.BOOLEAN_TRUE, element.getType(), "Expected boolean true element type");
    }

    @Test
    public void parseBooleanFalse() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x03);
        Assertions.assertEquals(YabnType.BOOLEAN_FALSE, element.getType(), "Expected boolean false element type");
    }

    @Test
    public void parseByte() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x04, (byte) 0x01);
        Assertions.assertEquals(YabnType.BYTE, element.getType(), "Expected byte element type");
        PrimitiveNumberContents contents = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals((byte) 0x01, contents.getAsByte());
    }

    @Test
    public void parseShort() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x05, (byte) 0x00, (byte) 0x01);
        Assertions.assertEquals(YabnType.SHORT, element.getType(), "Expected short element type");
        PrimitiveNumberContents contents = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals((short) 0x01, contents.getAsShort());
    }

    @Test
    public void parseInt() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x06, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01);
        Assertions.assertEquals(YabnType.INT, element.getType(), "Expected int element type");
        PrimitiveNumberContents contents = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals(1, contents.getAsInt());
    }

    @Test
    public void parseLong() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01);
        Assertions.assertEquals(YabnType.LONG, element.getType(), "Expected long element type");
        PrimitiveNumberContents contents = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals(1L, contents.getAsLong());
    }

    @Test
    public void parseDouble() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x08, (byte) 0x3f, (byte) 0xf0, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00);
        Assertions.assertEquals(YabnType.DOUBLE, element.getType(), "Expected double element type");
        PrimitiveNumberContents contents = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals(1d, contents.getAsDouble());
    }

    @Test
    public void parseFloat() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x09, (byte) 0x3f, (byte) 0x80, (byte) 0x00, (byte) 0x00);
        Assertions.assertEquals(YabnType.FLOAT, element.getType(), "Expected float element type");
        PrimitiveNumberContents contents = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals(1F, contents.getAsFloat());
    }

    @Test
    public void parseString() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x0a, (byte) 0x48, (byte) 0x65, (byte) 0x6c, (byte) 0x6c, (byte) 0x6f, (byte) 0x20, (byte) 0x57, (byte) 0x6f, (byte) 0x72, (byte) 0x6c, (byte) 0x64, (byte) 0x00);
        Assertions.assertEquals(YabnType.STRING, element.getType(), "Expected string element type");
        YabnPrimitive primitive = YabnTestHelper.assertPrimitive(element);
        Assertions.assertTrue(primitive.contents() instanceof StringContents, "Expected primitive contents to be an instance of StringContents");
        Assertions.assertEquals("Hello World", ((StringContents) primitive.contents()).value());
    }

    @Test
    public void parseEmptyString() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x0b);
        Assertions.assertEquals(YabnType.EMPTY_STRING, element.getType(), "Expected empty string element type");
        YabnPrimitive primitive = YabnTestHelper.assertPrimitive(element);
        Assertions.assertTrue(primitive.contents() instanceof StringContents, "Expected primitive contents to be an instance of StringContents");
        Assertions.assertEquals("", ((StringContents) primitive.contents()).value());
    }

    @Test
    public void parseNullableString() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x10, (byte) 0x01, (byte) 0x48, (byte) 0x65, (byte) 0x6c, (byte) 0x6c, (byte) 0x6f, (byte) 0x00, (byte) 0x57, (byte) 0x6f, (byte) 0x72, (byte) 0x6c, (byte) 0x64, (byte) 0x00);
        Assertions.assertEquals(YabnType.NULL_STRING, element.getType(), "Expected null string element type");
        YabnPrimitive primitive = YabnTestHelper.assertPrimitive(element);
        Assertions.assertTrue(primitive.contents() instanceof StringContents, "Expected primitive contents to be an instance of StringContents");
        Assertions.assertEquals("Hello\u0000World", ((StringContents) primitive.contents()).value());
    }

    @Test
    public void parseArray() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x0C, (byte) 0x0b, (byte) 0x02, (byte) 0x00);
        Assertions.assertEquals(YabnType.ARRAY, element.getType(), "Expected array element type");
        YabnArray array = YabnTestHelper.assertArray(element);
        Assertions.assertEquals(2, array.elements().size(), "Expected array to have 2 elements");
        Assertions.assertEquals(YabnType.EMPTY_STRING, array.elements().get(0).getType(), "Expected first element to be an empty string");
        Assertions.assertEquals(YabnType.BOOLEAN_TRUE, array.elements().get(1).getType(), "Expected second element to be a boolean");
    }

    @Test
    public void parseEmptyArray() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x0E);
        Assertions.assertEquals(YabnType.EMPTY_ARRAY, element.getType(), "Expected empty array element type");
        YabnArray array = YabnTestHelper.assertArray(element);
        Assertions.assertEquals(0, array.elements().size(), "Expected array to have 0 elements");
    }

    @Test
    public void parseTypedArray() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x0C, (byte) 0x04, (byte) 0x01, (byte) 0x04, (byte) 0x01, (byte) 0x00);
        Assertions.assertEquals(YabnType.TYPED_ARRAY, element.getType(), "Expected typed array element type");
        YabnArray array = YabnTestHelper.assertArray(element);
        Assertions.assertEquals(2, array.elements().size(), "Expected array to have 2 elements");
        Assertions.assertEquals(YabnType.BYTE, array.elements().get(0).getType(), "Expected first element to be a byte");
        Assertions.assertEquals(YabnType.BYTE, array.elements().get(1).getType(), "Expected second element to be a byte");
    }

    @Test
    public void parseDatalessTypedArray() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x12, (byte) 0x02, (byte) 0x08);
        Assertions.assertEquals(YabnType.DATALESS_TYPED_ARRAY, element.getType(), "Expected dataless typed array element type");
        YabnArray array = YabnTestHelper.assertArray(element);
        Assertions.assertEquals(8, array.elements().size(), "Expected array to have 8 elements");
        for (YabnElement arrayElement : array.elements()) {
            Assertions.assertEquals(YabnType.BOOLEAN_TRUE, arrayElement.getType(), "Expected array element to be boolean(true)");
        }
    }

    @Test
    public void parseObject() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x0D, (byte) 0x0b, (byte) 0x41, (byte) 0x00, (byte) 0x0b, (byte) 0x42, (byte) 0x00, (byte) 0x00);
        Assertions.assertEquals(YabnType.OBJECT, element.getType(), "Expected object element type");
        YabnObject object = YabnTestHelper.assertObject(element);
        Map<String, YabnElement> elements = object.elements();
        Assertions.assertEquals(2, elements.size(), "Expected object to have 2 elements");
        Assertions.assertTrue(elements.containsKey("A"), "Expected object to contain key A");
        Assertions.assertTrue(elements.containsKey("B"), "Expected object to contain key B");
        Assertions.assertEquals(YabnType.EMPTY_STRING, elements.get("A").getType(), "Expected element A to be an empty string");
        Assertions.assertEquals(YabnType.EMPTY_STRING, elements.get("B").getType(), "Expected element B to be an empty string");
    }

    @Test
    public void parseEmptyObject() {
        YabnElement element = YabnTestHelper.parseElement((byte) 0x0F);
        Assertions.assertEquals(YabnType.EMPTY_OBJECT, element.getType(), "Expected empty object element type");
        YabnObject object = YabnTestHelper.assertObject(element);
        Assertions.assertEquals(0, object.elements().size(), "Expected object to have 0 elements");
    }
}
