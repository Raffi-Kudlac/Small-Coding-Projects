import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  

/**
 * 
 * @author Raffi Kudlac
 * @purpose An email system that I will use to send emails to myself when my 
 * 	program crashes. Only works if sending from GMail.
 * @Date_Made  April 2nd 2015
 *
 */
public class Email  
{  
	
	String sendTo = "";//who the email will go to  
    String sendFrom = "";//who the email is from 
    String msgBody = ""; // the message in the email
    String subject = ""; // subject of the email
    String password = ""; // password of the email address that the email is from
    String host = "localhost";//localhost or IP address  
    String oppeningMessage = ""; //this text will be displayed in the message before the
    							// msgBody. Like a greating
    
	/*
     * Sets up to,from and the password
     */
    public Email(String to, String from, String psw)
    {
    	sendTo = to;
    	sendFrom = from;
    	password = psw;
    }
    public Email(){}
    
    
    /**
     * Responsible for sending the email
     * @throws Can throw multiple exceptions. Up to the user to catch and check
     * correct usage.
     */
    public void send() throws Exception
	{
	     //Get the session object  
	     Properties properties = System.getProperties();  
	     properties.setProperty("mail.smtp.host", host);  
	     Session session = Session.getInstance(properties);
  
	     //compose the message   
	     MimeMessage message = new MimeMessage(session);  
	     message.setFrom(new InternetAddress(sendFrom));  
	     message.addRecipient(Message.RecipientType.TO,new InternetAddress(sendTo));  
	     message.setSubject(subject); 
	     message.setText(oppeningMessage + msgBody);  
	  
	         // Send message  
	     Transport transport = session.getTransport("smtps");
	     transport.connect ("smtp.gmail.com",465, sendFrom, password);
	     transport.sendMessage(message, message.getAllRecipients());
	     transport.close(); 
	     
	     setMsgBody("",false,0);
	}
    
    public String getOppeningMessage() {
		return oppeningMessage;
	}
	public void setOppeningMessage(String oppeningMessage) {
		this.oppeningMessage = oppeningMessage + "\n\n";
	}
    
	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getSendFrom() {
		return sendFrom;
	}

	public void setSendFrom(String sendFrom) {
		this.sendFrom = sendFrom;
	}

	public String getMsgBody() {
		return msgBody;
	}
	
	/**
	 * 
	 * @param msgBody 	message to be in the email
	 * 
	 * @param concat	if true message passed in will be concatinated with old
	 * 					message variable
	 * 
	 * @param newLine	holds the number of vertical spaces that will seperate
	 * 					the old message from the new. Only applies if concat is true 
	 */
	public void setMsgBody(String msgBody, boolean concat, int newLine) {
		
		String lines = "";
		
		for(int x = 1; x <= newLine; x++)
			lines +="\n";
		
		if(concat)
			this.msgBody += lines + msgBody;
		else
			this.msgBody = msgBody;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
}  