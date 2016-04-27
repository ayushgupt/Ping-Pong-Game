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
			 Main.no_rec++ ;
			 System.out.println("1 received");
		 }
		  confirmation =in.readLine() ;
		 if(confirmation.equals("all_received"))
		 {
			 Main.no_all_rec++ ;
			 System.out.println(Main.no_all_rec);
		 }
		 
		 while(true)
			{  String s = in.readLine() ;
				System.out.println("received"+id) ;
				
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
