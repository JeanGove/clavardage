/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author corentin
 */
public class TestJfileChooser {

    public static JFileChooser JFileChooser = new JFileChooser();
    public static JFrame Jf = new JFrame("Test");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Jf.add(JFileChooser);
        JFileChooser.setVisible(true);
        Jf.setVisible(true);
    }
    
}
