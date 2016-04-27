import java.io.BufferedReader;
import java.io.*;

public class connection_main {
	public static String data ;
	
	
	public static void main(String arg[]) throws IOException
	{   data= "not yet initialised" ;
		int a ;
		InputStreamReader read = new InputStreamReader(System.in) ;
		BufferedReader in = new BufferedReader(read);
		System.out.println("1 for adding new client, 2 for connecting to new server") ;
		
		server ownServer = new server(9000) ;
		 client ownClient ;
		
		while(true)
		{
			a= Integer.parseInt(in.readLine()); 
			switch(a)
			{
			case 1: if(ownServer.connect()) 
						{
						  System.out.println("New Client Added") ;	
						}
					else
						{
						  System.out.println("failed to add new client. Please try again ") ;	
						}
				break ;
			case 2: System.out.println("enter address and port") ;
				    String add= in.readLine();
				    int p = Integer.parseInt(in.readLine()) ;
				  /* ownClient = new client(add, p) ;
				   if(!ownClient.connect())
				   {
					   System.out.println("failed to add new client");
				   }*/
				    
				break ;
			case 3: System.out.println("Enter data to send");
					data = in.readLine() ; 
				break ;
			default:  data = "" ;
				break ;
			}
		}
	}
}
