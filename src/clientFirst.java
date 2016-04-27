<<<<<<< HEAD

import java.net.*;
import java.io.*;
import java.lang.*;
public class clientFirst {
	
	public String hostname ;
	public int portnumber ;
	
	PrintWriter out ;
	Socket echoSocket;
	
	public  boolean connect(String ho,  int po )
	{
		hostname = ho ;
		portnumber = po ; 
		try {
			System.out.println(portnumber); 
			System.out.println(hostname);   
			Socket echoSocket = new Socket(hostname, portnumber);
			   System.out.println(hostname);	
			     out = new PrintWriter(echoSocket.getOutputStream(), true);
			     
			    System.out.println(hostname);
			    return true;
			    
		}
		catch(Exception e)
		{
				System.out.println(e.getMessage()) ;
				return false ;
		}
		
	}
	public void send()
	{
		out.println(Main.jsonstr) ;
		System.out.println(Main.jsonstr);
	}

	public void close()
	{
		try {
			echoSocket.close() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
=======

import java.net.*;
import java.io.*;
import java.lang.*;
public class clientFirst {
	
	public String hostname ;
	public int portnumber ;
	
	PrintWriter out ;
	Socket echoSocket;
	
	public  boolean connect(String ho,  int po )
	{
		hostname = ho ;
		portnumber = po ; 
		try {
			System.out.println(portnumber); 
			System.out.println(hostname);   
			Socket echoSocket = new Socket(hostname, portnumber);
			   System.out.println(hostname);	
			     out = new PrintWriter(echoSocket.getOutputStream(), true);
			     
			    System.out.println(hostname);
			    return true;
			    
		}
		catch(Exception e)
		{
				System.out.println(e.getMessage()) ;
				return false ;
		}
		
	}
	public void send()
	{
		out.println(Main.jsonstr) ;
		System.out.println(Main.jsonstr);
	}

	public void close()
	{
		try {
			echoSocket.close() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
>>>>>>> 7a6958b254b8c209578964171b40c92a9aec4cf8
