import com.teamresourceful.yabn.YabnParser;
import com.teamresourceful.yabn.elements.YabnArray;
import com.teamresourceful.yabn.elements.YabnElement;
import com.teamresourceful.yabn.elements.YabnObject;
import com.teamresourceful.yabn.elements.YabnPrimitive;
import com.teamresourceful.yabn.elements.primitives.PrimitiveNumberContents;
import com.teamresourceful.yabn.reader.ArrayByteReader;
import org.junit.jupiter.api.Assertions;

public class YabnTestHelper {

    public static YabnElement compressPraseElement(byte... data) {
        return YabnParser.parseCompress(new ArrayByteReader(data));
    }

    public static YabnElement parseElement(byte... data) {
        return YabnParser.parse(new ArrayByteReader(data));
    }

    public static YabnObject assertObject(YabnElement element) {
        Assertions.assertTrue(element instanceof YabnObject, "Expected element to be an instance of YabnObject");
        return (YabnObject) element;
    }

    public static YabnArray assertArray(YabnElement element) {
        Assertions.assertTrue(element instanceof YabnArray, "Expected element to be an instance of YabnArray");
        return (YabnArray) element;
    }

    public static YabnPrimitive assertPrimitive(YabnElement element) {
        Assertions.assertTrue(element instanceof YabnPrimitive, "Expected element to be an instance of YabnPrimitive");
        return (YabnPrimitive) element;
    }

    public static PrimitiveNumberContents assertNumberPrimitive(YabnPrimitive primitive) {
        Assertions.assertTrue(primitive.contents() instanceof PrimitiveNumberContents, "Expected primitive contents to be an instance of NumberPrimitiveContents");
        return (PrimitiveNumberContents) primitive.contents();
    }
}
