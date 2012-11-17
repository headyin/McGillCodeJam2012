/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mcgillcodejam2012;
import connections.PriceConnection;
import data.Prices;
import handler.EMAHandler;
import handler.LWMAHandler;
import handler.PriceHandler;
import handler.SMAandTMAHandler;


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
        Prices prices = new Prices();
        PriceHandler priceHandler = new PriceHandler(priceConnection, prices);
        SMAandTMAHandler STHandler = new SMAandTMAHandler("localhost", 4001, prices);
        EMAHandler emaHandler = new EMAHandler("localhost", 4001, prices);
        LWMAHandler lwmaHandler = new LWMAHandler("localhost", 4001, prices);
        Thread tPriceHandler = new Thread(priceHandler);
        Thread tSTHandler = new Thread(STHandler);
        Thread tEmaHandler = new Thread(emaHandler);
        Thread tLwmaHandler = new Thread(lwmaHandler);
        
        
        tSTHandler.start();
        tEmaHandler.start();
        tLwmaHandler.start();
        tPriceHandler.start();
     
    }
}
