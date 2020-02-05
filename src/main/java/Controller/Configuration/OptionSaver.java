package Controller.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OptionSaver {
        private static File f;
        private static ArrayList<Parameter> config = new ArrayList<Parameter>();

	public OptionSaver(String filename) {
            this.f = new File(filename);
            try {
                this.load();
            } catch (IOException ex) {
                Logger.getLogger(OptionSaver.class.getName()).log(Level.SEVERE, null, ex);
            } 
	}

    private void load() throws IOException  {
        try{
            FileInputStream fis = new FileInputStream(this.f);
            byte[] b = new byte[(int) this.f.length()];
            fis.read(b);
            String[] configs = new String(b).split("\n");
            System.out.println(new String(b)+"\n");
            for(String args: configs){
                if(args.split("=").length > 1){
                    this.config.add(new Parameter(args));
                }
            }
        } catch (FileNotFoundException fnf){
            this.f.createNewFile();
            
            //Define a default path for donloaded files
            String path = "downloadPath="+System.getProperty("user.home");
            
            FileOutputStream fos = new FileOutputStream(this.f);
            fos.write(path.getBytes());
        }
    }
    
    public void save(){
            try {
                FileOutputStream fos = new FileOutputStream("config_temp.conf");
                PrintWriter out = new PrintWriter(fos);
                for(Parameter p: this.config){
                    out.println(p);
                    System.out.println(p);
                }
                out.close();
                fos.close();
                
                File f = new File("config_temp.conf");
                f.renameTo(this.f);
                
                System.out.println(f.getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(OptionSaver.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        
    public Parameter get(String name){
        for(Parameter p: this.config){
            if(name.equals(p.getName())){
                return p;
            }
        }
        return null;
    }

    public boolean add(Parameter p){
        Parameter p2 = this.get(p.getName());
        if(p2 != null){
            p2.value = p.value;
        }else{
            OptionSaver.config.add(p);
        }
        return true;
    }
}

