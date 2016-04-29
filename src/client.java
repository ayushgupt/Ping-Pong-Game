
import java.net.*;
import java.io.*;
import java.lang.*;

public class client {

    public String hostname;
    public int portnumber;
    public String data;
    public boolean connected;

    Socket echoSocket;

    public client() {

    }

    public boolean connect(String ho, int po, int id) {
        hostname = ho;
        portnumber = po;
        try {
            echoSocket = new Socket(hostname, portnumber);

            new clientthread(echoSocket, id).start();

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }


}
