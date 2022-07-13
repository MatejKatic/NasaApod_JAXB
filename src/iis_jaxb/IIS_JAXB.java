/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iis_jaxb;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import model.APODs;
import org.xml.sax.SAXException;

/**
 *
 * @author GraphX
 */
public class IIS_JAXB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JAXBException, SAXException {
        
        String xmlFile = "/validation/NasaApod.xml";
        String xsdFile = "/validation/NasaXSD.xsd";
        String dir = System.getProperty("user.dir");

        JAXBContext jaxbContext;

        try {
            jaxbContext = JAXBContext.newInstance(APODs.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema emplSchema = sf.newSchema(new File(dir + xsdFile));
            jaxbUnmarshaller.setSchema(emplSchema);

            APODs apods = (APODs) jaxbUnmarshaller.unmarshal(new File(dir + xmlFile));

            System.out.println(apods);
            System.out.println("Valid");
            

        } catch (JAXBException | SAXException e) {
            e.printStackTrace();
        }

    }

}
