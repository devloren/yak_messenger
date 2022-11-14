
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class Thredz extends Thread {
    private Socket sock;
    private ArrayList<Thredz> thredzarray;
    private PrintWriter ghostWriteR;

    public Thredz(Socket socket, ArrayList<Thredz> threads) {
        this.sock = socket;
        this.thredzarray = threads;
    }

    @Override
    public void run() {
        try {
            //Reading the input from Client
            BufferedReader buffyz = new BufferedReader( new InputStreamReader(sock.getInputStream()));
            
            //returning the output to the client : true statement is to flush the buffer otherwise
            //we have to do it manuallyy
             ghostWriteR = new PrintWriter(sock.getOutputStream(),true);


            //inifite loop for server
            while(true) {
                String outStr = buffyz.readLine();
                //if user types exit command
                if(outStr.equals("exit")) {
                    break;
                }
                sendComment(outStr);
                //output.println("Server says " + outputString);
                System.out.println("msg :  " + outStr);

            }


        } catch (Exception e) {
            System.out.println("Error occured " +e.getStackTrace());
        }
    }

    private void sendComment(String o) {
        for( Thredz sT: thredzarray) {
            sT.ghostWriteR.println(o);
        }

    }
}
