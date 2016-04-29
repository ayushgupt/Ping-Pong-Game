import java.net.*;
import java.io.*;
import java.lang.*;

public class server {
    ServerSocket serverSocket;
    int portNumber;

    public server(int n) {
        try {
            serverSocket = new ServerSocket(n);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        portNumber = n;
        connect();
    }


    public boolean connect() {
        try {
            System.out.println("server");

            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            new serverthread(serverSocket.accept()).start();
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    }
                }
            });
            t1.start();

            return true;

        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

    }


}
