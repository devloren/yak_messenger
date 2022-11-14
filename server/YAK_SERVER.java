import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class YAK_SERVER {

    public static void main(String[] args) {
        
        ArrayList<Thredz> thredz = new ArrayList<>();
        try (ServerSocket ss = new ServerSocket(45999)){
            while(true) {
                Socket socket = ss.accept();
                Thredz theThredz = new Thredz(socket, thredz);
                //starting the thread
                thredz.add(theThredz); 
                theThredz.start();

                //get all the list of currently running thread

            }
        } catch (Exception e) {
            System.out.println("Error " + e.getStackTrace());
        }
    }
}