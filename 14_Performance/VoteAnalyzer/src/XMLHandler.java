import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;

public class XMLHandler extends DefaultHandler {
    boolean isClose = true;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("voter") && isClose) {
            String birthDay = attributes.getValue("birthDay");
            String name = attributes.getValue("name");
            try {
                DBConnection.countVoter(name, birthDay);
                isClose = false;
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {
            isClose = true;
        }
    }
}
