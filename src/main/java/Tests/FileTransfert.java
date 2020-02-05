/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author corentin
 */
public class FileTransfert {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File[] fileIn = { new File("input.txt")
        , new File("input.png")};
        
        File[] fileOut = { new File("output.txt")
        , new File("output.png")};
        
        for(File f: fileOut){
            f.delete();
        }
        
        for(int i=0;i<fileOut.length;i++){
            try {
                FileInputStream fis = new FileInputStream(fileIn[i]);
                byte[] b = new byte[(int) fileIn[i].length()];
                fis.read(b);
                //String str = new String(b);
                byte[] b2 = b;
                String currentUsersHomeDir = System.getProperty("user.home");
                System.out.println(currentUsersHomeDir);
                FileOutputStream fos = new FileOutputStream(fileOut[i]);
                fos.write(b2);
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileTransfert.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileTransfert.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*
        if(fileOut1.exists())
            fileOut1.delete();
        if(fileOut2.exists())
            fileOut2.delete();*/
        
        
        

    }
    
}
