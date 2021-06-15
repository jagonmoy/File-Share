
package swing;

import java.awt.event.ActionListener;

public class PanelStatus_Item extends javax.swing.JPanel {

    public PanelStatus_Item() {
        initComponents();
    }
    public void showStatus(int values) {
        pro.setValue(values);
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pro = new javax.swing.JProgressBar();
        cmd = new javax.swing.JButton();

        pro.setStringPainted(true);

        cmd.setText("DOWNLOAD");
        cmd.setName("DOWNLOAD"); // NOI18N
        cmd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pro, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmd))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(pro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void cmdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdActionPerformed
        if ( cmd.getName().equals("PAUSE")) {
            cmd.setText("PLAY");
            cmd.setName("PLAY");
            eventPause.actionPerformed(evt);
        }
        else if ( cmd.getName().equals("PLAY")) {
            cmd.setText("PAUSE");
            cmd.setName("PAUSE");
            eventPause.actionPerformed(evt);
        }
        else if (cmd.getName().equals("DOWNLOAD"))eventSave.actionPerformed(evt);
    }//GEN-LAST:event_cmdActionPerformed
    private ActionListener eventSave;
    private ActionListener eventPause;
    
    public void addEventSave(ActionListener eventSave) {
         this.eventSave = eventSave;
    }
    public void addEvent (ActionListener event) {
        this.eventPause = event;
    }
    public void done(){
        cmd.setText("DONE");
        cmd.setName("DONE");
    }
    
    public boolean isPause() {
        return cmd.getName().equals("PLAY");
    }
    
    public void startfile() {
        //cmd.setName("DOWNLOAD");
        cmd.setText("PAUSE");
        cmd.setName("PAUSE");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmd;
    private javax.swing.JProgressBar pro;
    // End of variables declaration//GEN-END:variables
}
