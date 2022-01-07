import java.net.*;
import java.io.*;

// Pachara Srisomboonchote 6210401295
// Peerawich Tantavachkij 6210400167
public class Server extends Thread {
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(20000);
    }

    public void run() {
        while(true) {
            try {
                System.out.println("Waiting for client on port " +
                        serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                GuessNum game = new GuessNum();
                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                System.out.println("Target : "+game.getTarget());
                while(true){
                    String strOut="";

                    DataInputStream in = new DataInputStream(server.getInputStream());
                    int input = Integer.parseInt(in.readUTF());

                   int result= game.guess(input);
                    System.out.println("From player: "+input);
                    if( result==0) {
                        System.out.println("correct !! closing game");
                        DataOutputStream out = new DataOutputStream(server.getOutputStream());
                        strOut="Congrats !! you win this game in "+game.getCount()+" Times";
                        out.writeUTF(strOut);
                        out.writeUTF("Play again?? (Y/N) : ");
                        server.close();
                        break;
                    }
                    if(result==101){
                        strOut="Number must within range 0-100";
                    }
                    if(result ==-1){
                        strOut="This number : "+input+" is too low!!";
                    }
                    if(result == 1){
                        strOut ="This number : "+input+" is too high!!";
                    }

                    DataOutputStream out = new DataOutputStream(server.getOutputStream());
                    out.writeUTF(strOut);

                }

            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String [] args) {
        int port = 4587;
        try {
            Thread t = new Server(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
