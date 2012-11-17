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
public class PriceConnection {
    
    private InetAddress serverAddress;
    private int serverPricePort;
    private Socket serverSocket;
    
    private DataOutputStream dataToServer;
    private DataInputStream dataFromServer;
    
    public PriceConnection(String serverAddress, int serverPort) {
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
    
    public void startStockExchange() {
        try {
            dataToServer.writeBytes("H\n");
            //dataToServer.flush();
        } catch (IOException sendingFail) {
            System.out.println("Failed to send data to server");
        }
    }
    
    public int receivePrice() {
        int price = 0;
        try {
            int c = dataFromServer.read();       
            if (c == 'C') {
                price = -1;
            } else {
                while (c != '.') {
                    price = price * 10 + c - '0';
                    c = dataFromServer.read();
                }
                c = dataFromServer.read();
                while (c != '|') {
                    price = price * 10 + c - '0';
                    c = dataFromServer.read();
                }
            }
            return price;
        } catch (IOException receivingFail) {
            price = -2;
            System.out.println("Failed to receive data from server");
        }
        return price;
    }
    
    
}
