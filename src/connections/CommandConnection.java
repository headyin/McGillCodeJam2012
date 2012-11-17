/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connections;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author headyin
 */
public class CommandConnection {
    private InetAddress serverAddress;
    private int serverPricePort;
    private Socket serverSocket;
    
    private DataOutputStream dataToServer;
    private DataInputStream dataFromServer;
    
    public CommandConnection(String serverAddress, int serverPort) {
        try {
            this.serverAddress = InetAddress.getByName(serverAddress);
            this.serverPricePort = serverPort;
        } catch (UnknownHostException unknownServer) {
            System.out.println("Unknown server address");
        }
        
    }
    
    public boolean connect() {
        try {
            serverSocket = new Socket(serverAddress, serverPricePort);
            dataToServer = new DataOutputStream(serverSocket.getOutputStream());
            dataFromServer = new DataInputStream(serverSocket.getInputStream());
            return true;
        } catch (IOException connectionFail) {
            System.out.println("Failed to connect the server");
            return false;
        }       
    }
    
    public int buyOrSell(String command) {
        int price = 0;
        try {
            dataToServer.writeBytes(command + "\n");
            int c = dataFromServer.read();
            if (c == 'E') {
                return -1;
            };
            while (c != '.') {
                price = price * 10 + c - '0';
                c = dataFromServer.read();
            }
            c = dataFromServer.read();
            price = price * 10 + c - '0';
            c = dataFromServer.read();
            price = price * 10 + c - '0';
            c = dataFromServer.read();
            price = price * 10 + c - '0';
        } catch (IOException sendingFail) {
            System.out.println("Failed to send data to server");
        }
        return price;
    }
    
}
