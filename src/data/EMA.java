/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author headyin
 */
public class EMA extends MovingAverage{
    
    public EMA(Prices prices) {
        super.init(prices);
    }
    
    @Override
    public void calcMovingAverage() {
        
    }
    
}
