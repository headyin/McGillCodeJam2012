/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.LWMA;
import data.Prices;

/**
 *
 * @author headyin
 */
public class LineChartLWMA extends JFrameLineChart{
    public LineChartLWMA(Prices prices, LWMA lwma) {
        this.JFrameLineChartInit(prices);
        this.ma = lwma;
        this.legendLabels = new String[] {"price","LWMA[5]", "LWMA[20]"};
        this.titleString = "Linear Weighted Moving Average";
        this.setTitle(titleString);
        this.setLocation(10, 300);
    }
}
