import com.teamresourceful.yabn.YabnParser;
import com.teamresourceful.yabn.elements.YabnArray;
import com.teamresourceful.yabn.elements.YabnElement;
import com.teamresourceful.yabn.elements.YabnPrimitive;
import com.teamresourceful.yabn.reader.ArrayByteReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class YabnArrayTests {

    @Test
    public void zeroNumberInTypedArray() {
        YabnArray array = new YabnArray();
        array.add(YabnPrimitive.ofByte((byte)1));
        array.add(YabnPrimitive.ofByte((byte)0));
        byte[] data = array.toFullData();
        YabnElement parse = YabnParser.parse(new ArrayByteReader(data));
        Assertions.assertTrue(parse instanceof YabnArray, "Expected array but got " + parse.getClass().getSimpleName());
        YabnArray parsedArray = (YabnArray) parse;
        Assertions.assertEquals(2, parsedArray.elements().size(), "Expected array size");
        Assertions.assertTrue(parsedArray.elements().get(0) instanceof YabnPrimitive, "Expected primitive");
        Assertions.assertTrue(parsedArray.elements().get(1) instanceof YabnPrimitive, "Expected primitive");
    }
}
