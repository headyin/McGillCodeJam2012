/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mcgillcodejam2012;
import connections.PriceConnection;
import data.PriceHandler;
import data.Prices;
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
        priceConnection.connect();
        PriceHandler priceHandler = new PriceHandler(priceConnection);
        new Thread(priceHandler).start();
        
            
     
    }
}
