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
            float floatingPrice = dataFromServer.readFloat();
            price = Math.round(floatingPrice * 1000);
            //dataToServer.flush();
        } catch (IOException sendingFail) {
            System.out.println("Failed to send data to server");
        }
        return price;
    }
    
}
