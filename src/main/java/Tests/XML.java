
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class XML {

	public static void main(String[] args) {
            try {
                System.out.println("ok");
                
                /*	String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><a><b></b><c></c></a>";
                
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder;
                try {
                builder = factory.newDocumentBuilder();
                Document document = builder.parse(new InputSource(new StringReader(xmlString)));
                } catch (Exception e) {
                e.printStackTrace();
                } */
                File f = new File("XML.xml");
                
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(f);
        
                Element createElement = doc.createElement("option1");
                createElement.setAttribute("id", "prems");
                createElement.setTextContent("bonjor");
                Node body = doc.getElementsByTagName("body").item(0);
                body.appendChild(createElement);
                //Node appendChild = .appendChild(createElement);
               // doc.
                //doc.getElementsByTagName("meta").item(0).setTextContent("meta");
                Node e = doc.getElementsByTagName("title").item(0);
                
                String text = e.getTextContent();
                
                System.out.println(text);
              /*  e.setTextContent("bonsoir");
                text = e.getTextContent();*/
                
                System.out.println(body.getChildNodes().item(0).getTextContent());
                
                TransformerFactory tf = TransformerFactory.newInstance();
Transformer transformer;
                try {
                    transformer = tf.newTransformer();
                
                    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                    StringWriter writer = new StringWriter();
                    transformer.transform(new DOMSource(doc), new StreamResult(writer));
                    String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
                    
                    System.out.println(output);
                    
                    f.delete();
                    f.createNewFile();
                    FileOutputStream out = new FileOutputStream(f);
                            //= new FileOutputStream("the-file-name");
                    out.write(output.getBytes());
                    out.close();
                } catch (TransformerConfigurationException ex) {
                    Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TransformerException ex) {
                    Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (ParserConfigurationException ex) {
                Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
            }
		
	}

}
