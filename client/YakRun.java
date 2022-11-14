import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class YakRun implements Runnable {

    private Socket sock;
    private BufferedReader yakReader;
    // private PrintWriter output;

    public YakRun(Socket s) throws IOException {
        this.sock = s;
        this.yakReader = new BufferedReader( new InputStreamReader(sock.getInputStream()));
        // this.output = new PrintWriter(socket.getOutputStream(),true);
    }
    @Override
    public void run() {
            try {
                while(true) {
                    String yak = yakReader.readLine();
                    System.out.println(yak);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    yakReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    } 
}
