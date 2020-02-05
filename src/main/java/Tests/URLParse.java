/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author corentin
 */
public class URLParse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            URL url = new URL("file:///home/corentin/Téléchargements/sexeBienGorgéDeRat.png");
            System.out.println(url.getFile());
            File f = new File("/home/corentin/Téléchargements/sexeBienGorgéDeRat.png");
            URL str = f.toURI().toURL();
            //System.out.println(str.ge);
        } catch (MalformedURLException ex) {
            Logger.getLogger(URLParse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
