import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;


public class clientthread extends Thread{
	 public boolean stop; 
	 public Socket socket = null;
	 public int id ;
	 public clientthread(Socket rec, int temp_id)
	 {
		 socket = rec ;
		 stop = false ;
		 id=temp_id;
	 }
	 
	 public void run()
	 { try {
		 BufferedReader in =new BufferedReader( new InputStreamReader(socket.getInputStream()));
		 String confirmation =in.readLine() ;
		 if(confirmation.equals("true"))
		 {	
			 Main.connected[id] =true ;
		 }
		 System.out.println(confirmation) ;
		 System.out.println( Main.connected[id]);   
		 while(true)
			{  String s = in.readLine() ;
				if(!(s.equals("")))
			    { System.out.println("Data Received") ;
				  System.out.println(s) ;	
			      Main.data[id]= s ;
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
