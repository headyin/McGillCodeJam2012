/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Prices;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import net.sourceforge.chart2d.*;

/**
 *
 * @author headyin
 */
public class LineChart implements Runnable{
     private JFrame frame = null;
     private static boolean isApplet = true;
     private Dataset dataset;
     LBChart2D chart2D;
     Prices prices;
     int currentTime;
     
     public LineChart(Prices prices) {
         this.prices = prices;
         this.currentTime = 0;
     }
     
    public void init() {
        
        int maxWidth = 600;
        int maxHeight = 400;
        
        
        Dimension maxSize = new Dimension (maxWidth, maxHeight);
        System.out.println (maxSize);
        
        getChart2DDemoI();
        chart2D.setSize(maxSize);
        chart2D.setPreferredSize(maxSize);
        
        frame = new JFrame();
        frame.getContentPane().add(chart2D);
        frame.setTitle ("LBChart2DFrameDemo");
        frame.addWindowListener (
                new WindowAdapter() {
                    @Override
                    public void windowClosing (WindowEvent e) {
                        destroy();
                    } } );
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation (
                (screenSize.width - frame.getSize().width) / 2,
                (screenSize.height - frame.getSize().height) / 2);
    }
    
    
    public void start() {
        frame.setVisible(true);
        //frame.show();
    }
    
    
    public void destroy() {
        
        if (frame != null) {
            frame.dispose();
        }
        if (!isApplet) {
            System.exit (0);
        }
    }
    
    private Chart2D getChart2DDemoI() {
        
        
        //Configure object properties
        Object2DProperties object2DProps = new Object2DProperties();
        object2DProps.setObjectTitleText ("Weekly LOC Programmed");
        
        //Configure chart properties
        Chart2DProperties chart2DProps = new Chart2DProperties();
        chart2DProps.setChartDataLabelsPrecision (1);
        
        //Configure legend properties
        LegendProperties legendProps = new LegendProperties();
        String[] legendLabels = {"Price", "SMA[5]", "SMA[20]"};
        legendProps.setLegendLabelsTexts (legendLabels);
        
        //Configure graph chart properties
        GraphChart2DProperties graphChart2DProps = new GraphChart2DProperties();
        String[] labelsAxisLabels =
        {"9:30 am", "10:30 am", "11:30 am", "12:30 pm", "1:30 pm", 
            "2:30 pm", "3:30 pm", "4:30 pm", "5:30 pm"};
        graphChart2DProps.setLabelsAxisLabelsTexts (labelsAxisLabels);
        graphChart2DProps.setLabelsAxisTitleText ("Time of the Day");
        graphChart2DProps.setNumbersAxisTitleText ("Price");
        graphChart2DProps.setLabelsAxisTicksAlignment (GraphChart2DProperties.CENTERED);
        
        //Configure graph properties
        GraphProperties graphProps = new GraphProperties();
        graphProps.setGraphBarsExistence (false);
        graphProps.setGraphLinesExistence (true);
        graphProps.setGraphLinesThicknessModel(1);

        graphProps.setGraphAllowComponentAlignment (true);
        graphProps.setGraphLinesWithinCategoryOverlapRatio (1f);
        
        dataset = new Dataset(3,9,3600);
  
        //Configure graph component colors
        MultiColorsProperties multiColorsProps = new MultiColorsProperties();
        
        //Configure chart
        chart2D = new LBChart2D();
        chart2D.setObject2DProperties (object2DProps);
        chart2D.setChart2DProperties (chart2DProps);
        chart2D.setLegendProperties (legendProps);
        chart2D.setGraphChart2DProperties (graphChart2DProps);
        chart2D.addGraphProperties (graphProps);
        chart2D.addMultiColorsProperties (multiColorsProps);
        chart2D.addDataset (dataset);
       
        
        //Optional validation:  Prints debug messages if invalid only.
        if (!chart2D.validate (false)) {
            chart2D.validate (true);
        }        
        return chart2D;
    }
    
    private void setChart2D() {
        
    }
    
    public void update(int type, int time, int prices) {
        int hour = (time - 1) / 3600;
        int second = time - hour * 3600 - 1;
        dataset.set (type, hour, second, prices);
        chart2D.addDataset (dataset);
        

    }

    @Override
    public void run() {
        init();
        start();
        while (currentTime < Prices.TOTAL_TIME - 1) {
            if (currentTime >= prices.getCurrentTime()) {
                continue;
            }
            currentTime++;
            update(0, currentTime, prices.getPrice(currentTime));
            frame.repaint();
        }
        
    }
}