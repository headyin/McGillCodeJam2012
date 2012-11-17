/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author headyin
 */
public class SMA {
    private final int FAST_PERIOD = 5;
    private final int SLOW_PERIOD = 20;
    
    private int[] smaFast;
    private int[] smaSlow;
    private int currentTime;
    
    public SMA() {
        smaFast = new int[Prices.TOTAL_TIME];
        smaSlow = new int[Prices.TOTAL_TIME];
        currentTime = 0;
    }
    
    public void clear() {
        this.currentTime = 0;
    }
    
    
    
}
