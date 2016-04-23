import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;


public class clientthread extends Thread{
	 public boolean stop; 
	 public Socket socket = null;
	 public clientthread(Socket rec)
	 {
		 socket = rec ;
		 stop = false ;
	 }
	 
	 public void run()
	 { try {
		 BufferedReader in =new BufferedReader( new InputStreamReader(socket.getInputStream()));
			while(true)
			{  String s = in.readLine() ;
				if(!(s.equals("")))
			    { System.out.println("Data Received") ;
					System.out.println(s) ;	
			     
			    }
				
			    if(stop)
            	 break ;
			}
			socket.close(); 
		} 
	    catch(Exception e)
	 		{
	    		e.printStackTrace() ;
	 		}
	 }
}
