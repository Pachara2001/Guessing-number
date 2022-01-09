import java.io.*;
import java.net.Socket;
import java.util.Scanner;


// Pachara Srisomboonchote 6210401295
// Peerawich Tantavachkij 6210400167
public class Client {

    public static void main(String [] args) {
        String serverName = "localhost";
        int port = 4587;
        try {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);

            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            System.out.println("\n" +
                    "░██████╗░██╗░░░██╗███████╗░██████╗░██████╗██╗███╗░░██╗░██████╗░  ███╗░░██╗██╗░░░██╗███╗░░░███╗\n" +
                    "██╔════╝░██║░░░██║██╔════╝██╔════╝██╔════╝██║████╗░██║██╔════╝░  ████╗░██║██║░░░██║████╗░████║\n" +
                    "██║░░██╗░██║░░░██║█████╗░░╚█████╗░╚█████╗░██║██╔██╗██║██║░░██╗░  ██╔██╗██║██║░░░██║██╔████╔██║\n" +
                    "██║░░╚██╗██║░░░██║██╔══╝░░░╚═══██╗░╚═══██╗██║██║╚████║██║░░╚██╗  ██║╚████║██║░░░██║██║╚██╔╝██║\n" +
                    "╚██████╔╝╚██████╔╝███████╗██████╔╝██████╔╝██║██║░╚███║╚██████╔╝  ██║░╚███║╚██████╔╝██║░╚═╝░██║\n" +
                    "░╚═════╝░░╚═════╝░╚══════╝╚═════╝░╚═════╝░╚═╝╚═╝░░╚══╝░╚═════╝░  ╚═╝░░╚══╝░╚═════╝░╚═╝░░░░░╚═╝");
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Guessing integer between 0-100: ");
            int input;
            if(scanner.hasNextInt()){
             input = scanner.nextInt();}
            else continue;
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
                System.out.println("----------------------------------------------------------------------------");
                if(i.equalsIgnoreCase("y")) {
                    client.close();
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
