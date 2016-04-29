import java.net.*;
import java.io.*;

public class serverthread extends Thread {
    public Socket socket = null;
    public boolean stop;

    public serverthread(Socket socket) {
        super("serverthread");
        this.socket = socket;
        stop = false;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //out.println("true") ;

            int i = 0;
            boolean flag = true;
            while (true) {
                   /*if(flag)
                   {
	                	if(Main.no_rec<Main.no_players-1)
	                	{
	                		
	                	}
	                	else if(Main.no_rec==Main.no_players-1)
	                	{
	                		out.println("all_received") ;
	                		Main.no_rec++ ;
	                		
	                	}
	                	else if(Main.no_all_rec==Main.no_players-1)
                    	{
                    		flag = false ;

                    	}*/
                //}
                if (PongPanel.playing) {

                    if (i % 22 == 0) {
                        String own_gamestate = JsonUtils.jsonToString(GameState.gamestate);
                        out.println(own_gamestate);
                    }
                    i = i % 22;
                    i++;

                    //System.out.println("Sent:"+own_gamestate);
                }
                // break;

                if (stop)
                    break;
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
