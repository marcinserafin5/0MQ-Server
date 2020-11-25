import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class Server
{

    public static synchronized String changeColor(String color) {
            if(color == "Bialy")
            {
                color="Czarny";
            }else{
                color="Bialy";
            }
            return color;
}


    public static void main(String[] args) throws Exception
    {
        String[] test = {"d","c"};
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.REP);
            socket.bind("tcp://127.0.0.1:5555");
            String color ="Czarny";
            System.out.println("FFAFAFA");
            while (!Thread.currentThread().isInterrupted()) {

                // Block until a message is received
                byte[] reply = socket.recv(0);

                // Print the message
                if (reply != null) {

                    System.out.println("received: "+ new String(reply));

                }else{
                    System.out.println("NIE DOSTALEM");
                }

                // Send a response
                String response = "Hello, world!";
                if(socket.send(color.toString(), 0)){
                    socket.notifyAll();
                   color=changeColor(color);

                }


            }
        }
    }
}