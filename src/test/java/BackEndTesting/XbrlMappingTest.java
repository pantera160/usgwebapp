/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BackEndTesting;
import be.usgictprofessionals.usgfinancewebapp.utilisties.XBRLMapping;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Pantera
 */
public class XbrlMappingTest {

    public XbrlMappingTest() {
    }
    
    @Test
    public void WillCreateInputDataWhenNewObjectIsMade(){
        try {
            File file = new File("D:\\Pantera\\Downloads\\2015-23700037.xbrl");
            XBRLMapping mapper = new XBRLMapping(file);
            System.out.println(mapper.getCurrentInputData().getAp());
            System.out.println(mapper.getCurrentInputData().getAr());
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XbrlMappingTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
