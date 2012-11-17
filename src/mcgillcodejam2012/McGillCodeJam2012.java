/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mcgillcodejam2012;
import connections.PriceConnection;
import connections.data.TransactionCollector;
import data.Prices;
import data.handler.EMAHandler;
import data.handler.LWMAHandler;
import data.handler.PriceHandler;
import data.handler.SMAandTMAHandler;


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
        TransactionCollector transactionCollector = new TransactionCollector();
        PriceHandler priceHandler = new PriceHandler(priceConnection, prices);
        SMAandTMAHandler STHandler = new SMAandTMAHandler("localhost", 4001, 
                prices,transactionCollector);
        EMAHandler emaHandler = new EMAHandler("localhost", 4001, 
                prices,transactionCollector);
        LWMAHandler lwmaHandler = new LWMAHandler("localhost", 4001, 
                prices,transactionCollector);
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
