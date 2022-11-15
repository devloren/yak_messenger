import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class YAK_CLIENT {

    public static void main(String[] args) {
        try (Socket sock = new Socket(/*"192.168.86.21"*/"localhost", 45999)){
            //reading the input from server
            BufferedReader input = new BufferedReader( new InputStreamReader(sock.getInputStream()));
            
            
            PrintWriter ghostWriteR = new PrintWriter(sock.getOutputStream(),true);

            Scanner scanner = new Scanner(System.in);
            String userYak;
            String yak;
            String handle = "empty";

            YakRun clientRun = new YakRun(sock);


            new Thread(clientRun).start();
           //loop closes when user enters exit command
           
           do {
               
               if (handle.equals("empty")) {
                    System.out.println("Enter your name ");
                    userYak = scanner.nextLine();
                    handle = userYak;
                    ghostWriteR.println(userYak + " has entered the yak");
                    if (userYak.equals("exit")) {
                        System.out.println("User Exists");
                        break;
                    }
               } 
               else {
                    String message = ( "~: " );
                    System.out.print(message);
                    userYak = scanner.nextLine();

                    ghostWriteR.println(handle + ": " + userYak);
                    if (userYak.equals("exit")) {
                        //reading the input from server
                        break;
                    }
                }

           } while (!userYak.equals("exit")); 
        } catch (Exception e) {
            System.out.println("error: " + e.getStackTrace());
    }
    }
}
