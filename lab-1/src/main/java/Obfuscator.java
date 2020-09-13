import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class Obfuscator {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        if (args.length != 2) {
            System.err.println("Wrong number of arguments!");
            System.exit(1);
        }
        File inputXMLFile = new File(args[1]);
        if (!inputXMLFile.exists()) {
            System.err.println("File " + inputXMLFile.getAbsolutePath() + " not found!");
            System.exit(1);
        }
        if (args[0].equals("obfuscate")) {
            ObfuscatorUtils.obfuscateWithParameter(ObfuscatorUtils.Options.obfuscate, args[1]);
        } else if (args[0].equals("unobfuscate")) {
            ObfuscatorUtils.obfuscateWithParameter(ObfuscatorUtils.Options.unobfuscate, args[1]);
        } else {
            System.err.println("First argument must be obfuscate or unobfuscate!");
            System.exit(1);
        }
    }
}