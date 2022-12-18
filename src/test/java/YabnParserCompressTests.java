import com.teamresourceful.yabn.elements.YabnElement;
import com.teamresourceful.yabn.elements.YabnType;
import com.teamresourceful.yabn.elements.primitives.PrimitiveNumberContents;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class YabnParserCompressTests {

    @Test
    public void parseDoubleToFloat() {
        YabnElement element = YabnTestHelper.compressPraseElement((byte) 0x08, (byte) 0x3f, (byte) 0xf0, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00);
        PrimitiveNumberContents number = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals(YabnType.FLOAT, element.getType(), "Expected float element type");
        Assertions.assertEquals(1f, number.getAsFloat(), "Expected 1");
    }

    @Test
    public void parseDoubleToDouble() {
        YabnElement element = YabnTestHelper.compressPraseElement((byte) 0x08, (byte) 0x48, (byte) 0x21, (byte) 0xa1, (byte) 0xe5, (byte) 0xf7, (byte) 0x75, (byte) 0x37, (byte) 0x96);
        PrimitiveNumberContents number = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals(YabnType.DOUBLE, element.getType(), "Expected double element type");
        Assertions.assertEquals(3E+39D, number.getAsDouble(), "Expected 3E+39D");
    }

    @Test
    public void parseLongToByte() {
        YabnElement element = YabnTestHelper.compressPraseElement((byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01);
        PrimitiveNumberContents number = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals(YabnType.BYTE, element.getType(), "Expected byte element type");
        Assertions.assertEquals((byte)1, number.getAsByte(), "Expected 1");
    }

    @Test
    public void parseLongToShort() {
        YabnElement element = YabnTestHelper.compressPraseElement((byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0F, (byte) 0xFF);
        PrimitiveNumberContents number = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals(YabnType.SHORT, element.getType(), "Expected short element type");
        Assertions.assertEquals(4095, number.getAsShort(), "Expected 4095");
    }

    @Test
    public void parseLongToInt() {
        YabnElement element = YabnTestHelper.compressPraseElement((byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF);
        PrimitiveNumberContents number = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals(YabnType.INT, element.getType(), "Expected int element type");
        Assertions.assertEquals(16777215, number.getAsInt(), "Expected 16777215");
    }

    @Test
    public void parseLongToLong() {
        YabnElement element = YabnTestHelper.compressPraseElement((byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF);
        PrimitiveNumberContents number = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals(YabnType.LONG, element.getType(), "Expected long element type");
        Assertions.assertEquals(1099511627775L, number.getAsLong(), "Expected 1099511627775");
    }

    @Test
    public void parseIntToByte() {
        YabnElement element = YabnTestHelper.compressPraseElement((byte) 0x06, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01);
        PrimitiveNumberContents number = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals(YabnType.BYTE, element.getType(), "Expected byte element type");
        Assertions.assertEquals((byte)1, number.getAsByte(), "Expected 1");
    }

    @Test
    public void parseIntToShort() {
        YabnElement element = YabnTestHelper.compressPraseElement((byte) 0x06, (byte) 0x00, (byte) 0x00, (byte) 0x0F, (byte) 0xFF);
        PrimitiveNumberContents number = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals(YabnType.SHORT, element.getType(), "Expected short element type");
        Assertions.assertEquals(4095, number.getAsShort(), "Expected 4095");
    }

    @Test
    public void parseIntToInt() {
        YabnElement element = YabnTestHelper.compressPraseElement((byte) 0x06, (byte) 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF);
        PrimitiveNumberContents number = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals(YabnType.INT, element.getType(), "Expected int element type");
        Assertions.assertEquals(16777215, number.getAsInt(), "Expected 16777215");
    }

    @Test
    public void parseShortToByte() {
        YabnElement element = YabnTestHelper.compressPraseElement((byte) 0x05, (byte) 0x00, (byte) 0x01);
        PrimitiveNumberContents number = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals(YabnType.BYTE, element.getType(), "Expected byte element type");
        Assertions.assertEquals((byte)1, number.getAsByte(), "Expected 1");
    }

    @Test
    public void parseShortToShort() {
        YabnElement element = YabnTestHelper.compressPraseElement((byte) 0x05, (byte) 0x0F, (byte) 0xFF);
        PrimitiveNumberContents number = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals(YabnType.SHORT, element.getType(), "Expected short element type");
        Assertions.assertEquals(4095, number.getAsShort(), "Expected 4095");
    }

    @Test
    public void parseByteToByte() {
        YabnElement element = YabnTestHelper.compressPraseElement((byte) 0x04, (byte) 0x01);
        PrimitiveNumberContents number = YabnTestHelper.assertNumberPrimitive(YabnTestHelper.assertPrimitive(element));
        Assertions.assertEquals(YabnType.BYTE, element.getType(), "Expected byte element type");
        Assertions.assertEquals((byte)1, number.getAsByte(), "Expected 1");
    }
}
