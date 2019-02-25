import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.*;

public class StateParserTest {

    private void assertValid(String... lines) {
        try {
            StateParser.parseFrom(new StringReader(String.join("\n", lines)));
        } catch (Exception e) {
            fail();
        }
    }

    private void assertNotValid(String... lines) {
        try {
            StateParser.parseFrom(new StringReader(String.join("\n", lines)));
            fail();
        } catch (Exception e) {
            //ignore
        }
    }

    @Test
    public void parseValid(){
        assertValid("", "1 a 2");
        assertValid("1 2 3", "1 a 2");
        assertValid("1 2 3", "1 a 2", "5 z 9");
    }

    @Test
    public void parseNotValid(){
        assertNotValid("1 a 3", "1 a 2");
        assertNotValid("1 2 3", "1 2");
    }
}