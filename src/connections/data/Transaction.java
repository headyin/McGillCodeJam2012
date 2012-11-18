/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connections.data;

/**
 *
 * @author headyin
 */
public class Transaction {
    public static final String[] TYPES = {"","buy", "sell"};
    public static final String[] STRATEGIES = {"SMA", "LWMA", "EMA", "TMA"};
    private int strategy;
    private int time;
    private int price;
    private int type;
    private int manager;
    
    public Transaction(int time, int type, int price, int manager, int strategy) {
        this.time = time;
        this.type = type;
        this.price = price;
        this.manager = manager;
        this.strategy = strategy;
    }
    
    public int getTime() {
        return this.time;
    }
    
    public String getType() {
        return TYPES[this.type];
    }
    
    public int getPrice() {
        return this.price;
    }
    
    public String getManager() {
        return "Mamager" + this.manager;
    }
    
    public String getStrategy() {
        return STRATEGIES[this.strategy];
    }
    
}
