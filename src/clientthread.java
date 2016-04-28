import javax.swing.*;
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
	 {
		 try {
		 	BufferedReader in =new BufferedReader( new InputStreamReader(socket.getInputStream()));

		 while(true)
			{
				String s = in.readLine() ;
				if(s==null){ continue; }

				PongPanel.received_gamestate[id] = JsonUtils.stringToJson(s);
				System.out.println("Received from "+id+":"+PongPanel.received_gamestate[id]);

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
