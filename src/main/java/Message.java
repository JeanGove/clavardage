import java.io.Serializable;
import java.util.Date;


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
	
	/**
	 * Create a message Object
	 * @param date Date of the message
	 * @param sourceId Emitter of the message
	 * @param destId Receiver of the message
	 * @param content Message itself
	 */
	public Message(Date date, int sourceId, int destId,String content) {
            this.init(date, sourceId, destId, content);
	}
        
        public Message(String type,Date date, int sourceId, int destId,String content){
            this.init(date, sourceId, destId, content);
            this.type = type;
        }
        
        private void init(Date date, int sourceId, int destId,String content){
            this.content = content;
            this.date = date;
            this.sourceId = sourceId;
            this.destId = destId;
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
		return this.content;
	}

	/**
	 * Get the Date of the message
	 * @return Returns the date of the message
	 */
	public Date getDate(){
		return this.date;
	}
	
        public String toString(){
            return this.content;
        }
	
}
