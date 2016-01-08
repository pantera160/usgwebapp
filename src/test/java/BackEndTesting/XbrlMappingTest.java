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
    public void WillCreateInputDataWhenNewObjectIsMade() throws ParserConfigurationException, SAXException, IOException{
//            File file = new File("https://www.dropbox.com/s/xuqz1ejb7v7i0of/test.xbrl");
//            XBRLMapping mapper = new XBRLMapping(file);
//            assertEquals(2012, mapper.getCurrentInputData().getYear());
//            assertEquals(12, mapper.getCurrentInputData().getNumberOfMonths());
//            assertEquals(2011, mapper.getPrecedingInputData().getYear());
//            assertEquals(12, mapper.getPrecedingInputData().getNumberOfMonths());
    }
    
}
