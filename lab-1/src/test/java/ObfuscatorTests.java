import org.junit.Assert;
import org.junit.Test;
import junitx.framework.FileAssert;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ObfuscatorTests {
    @Test
    public void testObfuscateXMLPositive() throws ParserConfigurationException, TransformerException, SAXException, IOException {
        ObfuscatorUtils.obfuscateWithParameter(ObfuscatorUtils.Options.obfuscate, "./src/test/resources/obfuscate_test.xml");
        FileAssert.assertEquals(new File("./src/test/resources/true_obfuscated_result.xml"),
                new File("./obfuscated_result.xml"));
    }

    @Test
    public void testUnobfuscateXMLPositive() throws ParserConfigurationException, TransformerException, SAXException, IOException {
        ObfuscatorUtils.obfuscateWithParameter(ObfuscatorUtils.Options.unobfuscate, "./src/test/resources/unobfuscate_test.xml");
        FileAssert.assertEquals(new File("./src/test/resources/true_unobfuscated_result.xml"),
                new File("./unobfuscated_result.xml"));
    }

    @Test(expected = FileNotFoundException.class)
    public void testUnobfuscateXMLNegative() throws ParserConfigurationException, TransformerException, SAXException, IOException {
        ObfuscatorUtils.obfuscateWithParameter(ObfuscatorUtils.Options.unobfuscate, "./unobfuscate_test.xml");
    }

    @Test
    public void testObfuscateString() {
        Assert.assertEquals("0{7", ObfuscatorUtils.obfuscate("abc"));
    }

    @Test
    public void testUnobfuscateString() {
        Assert.assertEquals("abc", ObfuscatorUtils.unobfuscate("0{7"));
    }
}
