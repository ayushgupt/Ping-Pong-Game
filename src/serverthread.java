import java.net.*;
import java.io.*;
public class serverthread extends Thread{
	public Socket socket = null;
    public boolean stop; 
    public serverthread(Socket socket) {
        super("serverthread");
        this.socket = socket;
        stop= false ;
    }
    public void run()
    {
    	try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
                out.println("true");

                while (true) {
                    out.println("some global variable");
                 if(stop)
                	 break ;
                }
                
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
