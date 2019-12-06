//package clavardage;


import java.util.Date;


public class Message {
	
	private Date date;
	private int sourceId;
	private int destId;
	private String content;
	
	public Message(Date date, int sourceId, int destId,String content) {
		this.date= date;
		this.sourceId= sourceId;
		this.destId= destId;
		this.content= content;
	}
	
	public int getSourceId() {
		return sourceId;
	}
	
	public int getDestId() {
		return destId;
	}
	
	public String getContent() {
		return content;
	}
	
	
}
