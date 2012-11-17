/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mcgillcodejam2012;
import connections.CommandConnection;
import connections.PriceConnection;
import handler.PriceHandler;

/**
 *
 * @author headyin
 */
public class McGillCodeJam2012 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PriceConnection priceConnection = new PriceConnection("localhost", 4000);
        CommandConnection commandConnection = new CommandConnection("localhost", 4001);
        PriceHandler priceHandler = new PriceHandler(priceConnection, commandConnection);
        new Thread(priceHandler).start();
        
            
     
    }
}
