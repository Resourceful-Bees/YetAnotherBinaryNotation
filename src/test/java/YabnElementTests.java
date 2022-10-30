import com.teamresourceful.elements.YabnArray;
import com.teamresourceful.elements.YabnObject;
import com.teamresourceful.elements.YabnPrimitive;
import com.teamresourceful.elements.YabnType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class YabnElementTests {

    @Test
    public void createNull() {
        YabnPrimitive element = YabnPrimitive.ofNull();
        Assertions.assertEquals(0, element.toData().length, "Expected null element to have no data");
        Assertions.assertEquals(YabnType.NULL, element.getType(), "Expected null element type");
    }

    @Test
    public void createBooleanTrue() {
        YabnPrimitive element = YabnPrimitive.ofBoolean(true);
        Assertions.assertEquals(0, element.toData().length, "Expected boolean true element to have no data");
        Assertions.assertEquals(YabnType.BOOLEAN_TRUE, element.getType(), "Expected boolean true element type");
    }

    @Test
    public void createBooleanFalse() {
        YabnPrimitive element = YabnPrimitive.ofBoolean(false);
        Assertions.assertEquals(0, element.toData().length, "Expected boolean false element to have no data");
        Assertions.assertEquals(YabnType.BOOLEAN_FALSE, element.getType(), "Expected boolean false element type");
    }

    @Test
    public void createByte() {
        YabnPrimitive element = YabnPrimitive.ofByte((byte) 0x01);
        Assertions.assertEquals(1, element.toData().length, "Expected byte element to have 1 byte of data");
        Assertions.assertArrayEquals(new byte[]{0x01}, element.toData(), "Expected byte element type");
        Assertions.assertEquals(YabnType.BYTE, element.getType(), "Expected byte element type");
    }

    @Test
    public void createShort() {
        YabnPrimitive element = YabnPrimitive.ofShort((short) 0x01);
        Assertions.assertEquals(2, element.toData().length, "Expected short element to have 2 bytes of data");
        Assertions.assertArrayEquals(new byte[]{0x00, 0x01}, element.toData(), "Expected short element type");
        Assertions.assertEquals(YabnType.SHORT, element.getType(), "Expected short element type");
    }

    @Test
    public void createInt() {
        YabnPrimitive element = YabnPrimitive.ofInt(0x01);
        Assertions.assertEquals(4, element.toData().length, "Expected int element to have 4 bytes of data");
        Assertions.assertArrayEquals(new byte[]{0x00, 0x00, 0x00, 0x01}, element.toData(), "Expected int element type");
        Assertions.assertEquals(YabnType.INT, element.getType(), "Expected int element type");
    }

    @Test
    public void createLong() {
        YabnPrimitive element = YabnPrimitive.ofLong(0x01);
        Assertions.assertEquals(8, element.toData().length, "Expected long element to have 8 bytes of data");
        Assertions.assertArrayEquals(new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01}, element.toData(), "Expected long element type");
        Assertions.assertEquals(YabnType.LONG, element.getType(), "Expected long element type");
    }

    @Test
    public void createFloat() {
        YabnPrimitive element = YabnPrimitive.ofFloat(0.1f);
        Assertions.assertEquals(4, element.toData().length, "Expected float element to have 4 bytes of data");
        Assertions.assertArrayEquals(new byte[]{0x3D, (byte) 0xCC, (byte) 0xCC, (byte) 0xCD}, element.toData(), "Expected float element type");
        Assertions.assertEquals(YabnType.FLOAT, element.getType(), "Expected float element type");
    }

    @Test
    public void createDouble() {
        YabnPrimitive element = YabnPrimitive.ofDouble(0.1);
        Assertions.assertEquals(8, element.toData().length, "Expected double element to have 8 bytes of data");
        Assertions.assertArrayEquals(new byte[]{0x3F, (byte) 0xB9, (byte) 0x99, (byte) 0x99, (byte) 0x99, (byte) 0x99, (byte) 0x99, (byte) 0x9A}, element.toData(), "Expected double element type");
        Assertions.assertEquals(YabnType.DOUBLE, element.getType(), "Expected double element type");
    }

    @Test
    public void createString() {
        YabnPrimitive element = YabnPrimitive.ofString("Hello World");
        Assertions.assertEquals(12, element.toData().length, "Expected string element to have 12 bytes of data");
        Assertions.assertArrayEquals(new byte[]{0x48, 0x65, 0x6C, 0x6C, 0x6F, 0x20, 0x57, 0x6F, 0x72, 0x6C, 0x64, 0x00}, element.toData(), "Expected string element type");
        Assertions.assertEquals(YabnType.STRING, element.getType(), "Expected string element type");
    }

    @Test
    public void createStringEmpty() {
        YabnPrimitive element = YabnPrimitive.ofString("");
        Assertions.assertEquals(0, element.toData().length, "Expected string element to have 4 bytes of data");
        Assertions.assertArrayEquals(new byte[0], element.toData(), "Expected string element type");
        Assertions.assertEquals(YabnType.EMPTY_STRING, element.getType(), "Expected string element type");
    }

    @Test
    public void createStringNull() {
        YabnPrimitive element = YabnPrimitive.ofString("Hello\u0000World");
        Assertions.assertEquals(13, element.toData().length, "Expected string element to have 13 bytes of data");
        Assertions.assertArrayEquals(new byte[]{0x01, 0x48, 0x65, 0x6C, 0x6C, 0x6F, 0x00, 0x57, 0x6F, 0x72, 0x6C, 0x64, 0x00}, element.toData(), "Expected string element type");
        Assertions.assertEquals(YabnType.NULL_STRING, element.getType(), "Expected string element type");
    }

    @Test
    public void createArray() {
        YabnArray element = new YabnArray();
        element.add(YabnPrimitive.ofBoolean(true));
        element.add(YabnPrimitive.ofBoolean(false));
        Assertions.assertEquals(3, element.toData().length, "Expected array element to have 3 byte of data");
        Assertions.assertArrayEquals(new byte[]{0x02, 0x03, 0x00}, element.toData(), "Expected array element type");
        Assertions.assertEquals(YabnType.ARRAY, element.getType(), "Expected array element type");
    }

    @Test
    public void createArrayEmpty() {
        YabnArray element = new YabnArray();
        Assertions.assertEquals(0, element.toData().length, "Expected array element to have 0 byte of data");
        Assertions.assertArrayEquals(new byte[0], element.toData(), "Expected array element type");
        Assertions.assertEquals(YabnType.EMPTY_ARRAY, element.getType(), "Expected array element type");
    }

    @Test
    public void createTypedArray() {
        YabnArray element = new YabnArray();
        element.add(YabnPrimitive.ofByte((byte) 1));
        element.add(YabnPrimitive.ofByte((byte) 1));
        Assertions.assertEquals(4, element.toData().length, "Expected array element to have 3 byte of data");
        Assertions.assertArrayEquals(new byte[]{0x04, 0x01, 0x01, 0x00}, element.toData(), "Expected array element type");
        Assertions.assertEquals(YabnType.TYPED_ARRAY, element.getType(), "Expected typed array element type");
    }

    @Test
    public void createObject() {
        YabnObject element = new YabnObject();
        element.put("key", YabnPrimitive.ofBoolean(true));
        element.put("key2", YabnPrimitive.ofBoolean(false));
        Assertions.assertEquals(12, element.toData().length, "Expected object element to have 12 byte of data");
        Assertions.assertArrayEquals(new byte[]{0x02, 0x6b, 0x65, 0x79, 0x00, 0x03, 0x6b, 0x65, 0x79, 0x32, 0x00, 0x00}, element.toData(), "Expected object element type");
        Assertions.assertEquals(YabnType.OBJECT, element.getType(), "Expected object element type");
    }

    @Test
    public void createObjectEmpty() {
        YabnObject element = new YabnObject();
        Assertions.assertEquals(0, element.toData().length, "Expected object element to have 0 byte of data");
        Assertions.assertArrayEquals(new byte[0], element.toData(), "Expected object element type");
        Assertions.assertEquals(YabnType.EMPTY_OBJECT, element.getType(), "Expected object element type");
    }
}
