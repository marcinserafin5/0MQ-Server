import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import java.util.Scanner;

public class Client {

//    ZContext context = new ZContext(1);
//    ZMQ.Socket socket = context.createSocket(ZMQ.PAIR);

    public void sendMessage() {
        Scanner scan = new Scanner(System.in);
        try (ZContext context = new ZContext()) {
            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            System.out.println("FFAFAFA");
//            System.out.println( );

            socket.connect("tcp://127.0.0.1:5555");

            while(true) {
                boolean b = socket.send("hddi");
                System.out.println("Sent with return: " + b);

                byte[] reply = socket.recv(0);

                // Print the message
                if (reply != null) {
                    System.out.println("received: " + new String(reply));
                } else {
                    System.out.println("NIE DOSTALEM");
                }
                String firstName = scan.nextLine();
            }
    }
//    catch(Exception e){
//        System.out.println("POJEBALO "  + e.getStackTrace().toString());
//    }
    }

    public static void main(String args[]) {
        Client client = new Client();
        client.sendMessage();
    }

}