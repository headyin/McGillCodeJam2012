/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.MovingAverage;
import data.Prices;
import java.awt.Color;
import java.awt.Dimension;
import net.sourceforge.chart2d.Chart2D;
import net.sourceforge.chart2d.Chart2DProperties;
import net.sourceforge.chart2d.Dataset;
import net.sourceforge.chart2d.GraphChart2DProperties;
import net.sourceforge.chart2d.GraphProperties;
import net.sourceforge.chart2d.LBChart2D;
import net.sourceforge.chart2d.LegendProperties;
import net.sourceforge.chart2d.MultiColorsProperties;
import net.sourceforge.chart2d.Object2DProperties;

/**
 *
 * @author headyin
 */
public class JFrameLineChart extends javax.swing.JFrame implements Runnable{
    
    String[] legendLabels;
    String titleString;
    
    protected Dataset dataset;
    protected LBChart2D chart2D;
    protected int currentTime;
    protected Prices prices;
    protected MovingAverage ma;

    /**
     * Creates new form JFrameLineChart
     */
    /*public JFrameLineChart(Prices prices) {
        this.prices = prices;
        initComponents();
    }*/
    
    public void JFrameLineChartInit(Prices prices) {
        this.prices = prices;
        initComponents();
    }
    public void init() {
        int maxWidth = 32400;
        int maxHeight = 550;
        Dimension maxSize = new Dimension (maxWidth, maxHeight);
        this.jLabel1.setPreferredSize(maxSize);
        getChart2DDemoI();
        chart2D.setSize(maxSize);
        chart2D.setPreferredSize(maxSize);
        chart2D.pack();
        this.jLabel1.add(chart2D);
        pack();
        this.setVisible(true);
    }
    
    private Chart2D getChart2DDemoI() {

        //Configure object properties
        Object2DProperties object2DProps = new Object2DProperties();
        object2DProps.setObjectTitleText (titleString);
        
        //Configure chart properties
        Chart2DProperties chart2DProps = new Chart2DProperties();
        chart2DProps.setChartDataLabelsPrecision (1);
        
        //Configure legend properties
        LegendProperties legendProps = new LegendProperties();
        
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
        graphProps.setGraphNumbersLinesThicknessModel(1);
        graphProps.setGraphAllowComponentAlignment (true);
        graphProps.setGraphLinesWithinCategoryOverlapRatio (1f);
        
        dataset = new Dataset(3,9,3600);
  
        //Configure graph component colors
        MultiColorsProperties multiColorsProps = new MultiColorsProperties();
        multiColorsProps.setColorsCustomize(true);
        Color[] colors = new Color[] {Color.black,Color.red,Color.blue};
        multiColorsProps.setColorsCustom(colors);
        
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
    
    
    public void update(int type, int time, float prices) {
        int hour = (time - 1) / 3600;
        int second = time - hour * 3600 - 1;
        dataset.set (type, hour, second, prices);
        chart2D.addDataset (dataset);
    }

    @Override
    public void run() {
        init();
        while (currentTime < Prices.TOTAL_TIME - 1) {
            if (currentTime >= prices.getCurrentTime()) {
                continue;
            }
            if (currentTime >= ma.getCurrentTime()) {
                continue;
            }
            currentTime++;
            int price = prices.getPrice(currentTime);
            int price5 = ma.getFastMA(currentTime);            
            int price20 = ma.getSlowMA(currentTime);
            
            update(0, currentTime, price/1000f);
            update(1, currentTime, price5/1000f);
            update(2, currentTime, price20/1000f);
            this.repaint();
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 300));

        jLabel1.setPreferredSize(new java.awt.Dimension(8100, 550));
        jScrollPane1.setViewportView(jLabel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
