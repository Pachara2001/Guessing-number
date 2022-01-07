import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String [] args) {
        String serverName = "localhost";
        int port = 4587;
        try {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);

            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Guessing integer between 0-100: ");
            int input = scanner.nextInt();
            System.out.println();
            out.writeUTF(""+input);
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            String fromServ =in.readUTF();
            System.out.println("GameMaster says: " + fromServ);
            if(fromServ.length()>35) {
                fromServ =in.readUTF();
                System.out.print(fromServ);
                String i = scanner.next();
                if(i.equalsIgnoreCase("y")) {
                    System.out.println();
                    System.out.println("Connecting to " + serverName + " on port " + port);
                    client = new Socket(serverName, port);
                    outToServer = client.getOutputStream();
                    out = new DataOutputStream(outToServer);
                    System.out.println("Just connected to " + client.getRemoteSocketAddress());
                    continue;
                }

                else{
                client.close();
                break;}
            }

            }
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
