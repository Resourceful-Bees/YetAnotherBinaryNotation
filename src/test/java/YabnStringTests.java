import com.teamresourceful.yabn.elements.YabnPrimitive;
import com.teamresourceful.yabn.elements.primitives.PrimitiveContents;
import com.teamresourceful.yabn.elements.primitives.StringContents;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class YabnStringTests {

    @Test
    public void oneToOneTest() {
        YabnPrimitive element = new YabnPrimitive(new StringContents("⛏"));
        YabnPrimitive parsed = (YabnPrimitive) YabnTestHelper.parseElement(element.toFullData());
        Assertions.assertEquals(element, parsed);
        Assertions.assertEquals(element.getType(), parsed.getType());
        PrimitiveContents contents = element.contents();
        PrimitiveContents parsedContents = parsed.contents();
        Assertions.assertEquals(contents, parsedContents);
        Assertions.assertTrue(contents instanceof StringContents);
        Assertions.assertTrue(parsedContents instanceof StringContents);
        Assertions.assertEquals(((StringContents) contents).value(), ((StringContents) parsedContents).value());
        Assertions.assertEquals(((StringContents) parsedContents).value(), "⛏");
    }
}
