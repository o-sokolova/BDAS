import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ObfuscatorUtils {
    private static String source = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 `~!@#$%^*()_-+={[}]:;\"',.?\\|<>/";
    private static String target = "0{7(3M5_H2+OYRrwZ]%9iWd4X^J=)6DK|zkoVychpbEnBUNA<GI`~LTuvs/l,>ft.#;}[\"gj!S@'1CePQa- $\\?q:*x8mF";

    enum Options {obfuscate, unobfuscate}

    static String obfuscate(String s) {
        char[] result = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = source.indexOf(c);
            result[i] = target.charAt(index);
        }
        return new String(result);
    }

    static String unobfuscate(String s) {
        char[] result = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = target.indexOf(c);
            result[i] = source.charAt(index);
        }
        return new String(result);
    }

    public static void obfuscateWithParameter(Options option, String pathToXML) throws IOException, TransformerException, ParserConfigurationException, SAXException {
        XMLReader xr = new XMLFilterImpl(SAXParserFactory.newInstance().newSAXParser().getXMLReader()) {
            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                String information = new String(ch, start, length).trim();
                if (information.length() > 0) {
                    String ob = (option == Options.obfuscate) ? obfuscate(information) : unobfuscate(information);
                    ch = ob.toCharArray();
                    start = 0;
                    length = ch.length;
                }
                super.characters(ch, start, length);
            }
        };

        String pathToOutputXML = (option == Options.obfuscate) ? "./obfuscated_result.xml" : "./unobfuscated_result.xml";
        Source src = new SAXSource(xr, new InputSource(new FileReader(pathToXML)));
        Result res = new StreamResult(new FileWriter(pathToOutputXML));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.transform(src, res);
    }
}
