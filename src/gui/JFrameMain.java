/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
public class JFrameMain extends javax.swing.JFrame {
    
    PriceConnection priceConnection;
    Prices prices;
    TransactionCollector transactionCollector;
    PriceHandler priceHandler;
    SMAandTMAHandler STHandler;
    EMAHandler emaHandler;
    LWMAHandler lwmaHandler;
    Thread tPriceHandler;
    Thread tSTHandler;
    Thread tEmaHandler;
    Thread tLwmaHandler;
    String serverName;
    int pricePort;
    int tradePort;
    LineChartSMA lineChartSMA;
    LineChartTMA lineChartTMA;
    LineChartEMA lineChartEMA;
    LineChartLWMA lineChartLWMA;
    Thread tLineChartSMA;
    Thread tLineChartTMA;
    Thread tLineChartEMA;
    Thread tLineChartLWMA;
    
    /**
     * Creates new form JFrameMain
     */
    public JFrameMain() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldServerName = new javax.swing.JTextField();
        jTextFieldPricePort = new javax.swing.JTextField();
        jTextFieldTradePort = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Start = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Please setup server information");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(640, 480));
        setResizable(false);

        jTextFieldServerName.setText("192.168.2.20");

        jTextFieldPricePort.setText("4000");

        jTextFieldTradePort.setText("4001");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("mxExchange Server Name or IP:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Price Feed Port:");
        jLabel2.setToolTipText("");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Trade/Booking Port:");

        Start.setText("Start");
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldServerName, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                    .addComponent(jTextFieldPricePort, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                    .addComponent(jTextFieldTradePort))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(Start, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldServerName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Start, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldPricePort, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jTextFieldTradePort))
                .addGap(166, 166, 166))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartActionPerformed
        serverName = this.jTextFieldServerName.getText();
        pricePort = Integer.parseInt(this.jTextFieldPricePort.getText());
        tradePort = Integer.parseInt(this.jTextFieldTradePort.getText());
        
        priceConnection = new PriceConnection(serverName, pricePort);
        prices = new Prices();
        transactionCollector = new TransactionCollector();
        priceHandler = new PriceHandler(priceConnection, prices);
        STHandler = new SMAandTMAHandler(serverName, tradePort, 
                prices,transactionCollector);
        emaHandler = new EMAHandler(serverName, tradePort, 
                prices,transactionCollector);
        lwmaHandler = new LWMAHandler(serverName, tradePort, 
                prices,transactionCollector);        
        tPriceHandler = new Thread(priceHandler);
        tSTHandler = new Thread(STHandler);
        tEmaHandler = new Thread(emaHandler);
        tLwmaHandler = new Thread(lwmaHandler);
        lineChartSMA = new LineChartSMA(prices, STHandler.getSMA());
        tLineChartSMA = new Thread(lineChartSMA);
        lineChartEMA = new LineChartEMA(prices, emaHandler.getEMA());
        tLineChartEMA = new Thread(lineChartEMA);
        lineChartTMA = new LineChartTMA(prices, STHandler.getTMA());
        tLineChartTMA = new Thread(lineChartTMA);
        lineChartLWMA = new LineChartLWMA(prices, lwmaHandler.getLWMA());
        tLineChartLWMA = new Thread(lineChartLWMA);
        
        this.tEmaHandler.start();
        this.tLwmaHandler.start();
        this.tSTHandler.start();
        this.tPriceHandler.start();
        
         //this.tLineChartSMA.start();
        //this.tLineChartEMA.start();
       // this.tLineChartLWMA.start();
       // this.tLineChartTMA.start();
        
    }//GEN-LAST:event_StartActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameMain().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Start;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextFieldPricePort;
    private javax.swing.JTextField jTextFieldServerName;
    private javax.swing.JTextField jTextFieldTradePort;
    // End of variables declaration//GEN-END:variables
}
