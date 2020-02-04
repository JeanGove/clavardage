package Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Message implements Serializable{
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
	private int sourceId;
	private int destId;
	private String content;
        private String type = "texte";
        private String name;
	
	/**
	 * Create a message Object
	 * @param date Date of the message
	 * @param sourceId Emitter of the message
	 * @param destId Receiver of the message
	 * @param content Message itself
	 */
	public Message(Date date, int sourceId, int destId,String content, String name) {
            this.init(date, sourceId, destId, content,name);
	}
        
        public Message(Date date, int sourceId, int destId,String content, String type,String name){
            this.init(date, sourceId, destId, content,name);
            this.type = type;
        }
        
        private void init(Date date, int sourceId, int destId,String content, String name){
            this.content = content;
            this.date = date;
            this.sourceId = sourceId;
            this.destId = destId;
            this.name = name;
        }
	
	/**
	 * Get the ID of the message emitter
	 * @return Returns an ID
	 */
	public int getSourceId() {
		return this.sourceId;
	}
		
	/**
	 * Get the ID of the message receiver
	 * @return Returns an ID
	 */
	public int getDestId() {
		return this.destId;
	}
		
	/**
	 * Get the content of the message
	 * @return Returns the message as a String
	 */
	public String getContent() {
                System.out.println(this.type);
		if("file".equals(this.type)){
                    this.DownloadFile();
                    return "Fichier: <b>"+this.name+"</d> enregistré dans Téléchargements";
                }else if("image".equals(this.type)){
                    return "<img></img>";
                }
                return this.content;
	}
        
        public void DownloadFile(){
            FileOutputStream fos = null;
            try {
                String path = "/home/corentin/";
                //
                File file = new File(path+this.name);
                
                //Dont overwrite an existing file
                int num = 1;
                while(file.exists()){
                    file = new File(path+this.name+" ("+num+")");
                    num++;
                }
                
                fos = new FileOutputStream(file);
                fos.write(this.content.getBytes());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }

	/**
	 * Get the Date of the message
	 * @return Returns the date of the message
	 */
	public Date getDate(){
		return this.date;
	}
	
        public String toString(){
            return this.getContent();
        }
	
}
