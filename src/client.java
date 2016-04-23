
import java.net.*;
import java.io.*;
import java.lang.*;
public class client {
	
	public String hostname ;
	public int portnumber ;
	public client(String name, int number)
	{
		hostname = name ;
		portnumber = number ;
	}
	
	public  boolean connect()
	{
		try {
			    Socket echoSocket = new Socket(hostname, portnumber);
			    	
			    new clientthread(echoSocket).start();
			     
			    return true;
			    
		}catch(Exception e)
		{
				System.out.println(e.getMessage()) ;
				return false ;
		}
		
	}
	
	
	public static void main(String[] args)
	{
		
	}
	
	
}
