/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;


/**
 *
 * @author headyin
 */
public class Prices {
    public final static int TOTAL_TIME = 32401;
    
    private int[] prices;
    private int currentTime;

    
    public Prices() {
        prices = new int[TOTAL_TIME];
        currentTime = 0;
    }
    
    public void clear() {
        currentTime = 0;
    }
    
    public void addPrice(int time, int price) {
        this.currentTime = time;
        this.prices[time] = price;
    }
    
    public int getPrice(int time) {
        return this.prices[time];
    }
    
    public int getCurrentPrice() {
        return this.prices[this.currentTime];
    }
    
    public String getPriceString(int time) {
        int price = this.prices[time];
        String priceString = String.valueOf(price);
        int length = priceString.length();
        return priceString.substring(0, length - 3) + "." 
                + priceString.substring(length - 3);
    }
    
    public String getCurrentPriceString() {
        return getPriceString(this.currentTime);
    }
}
