
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Iterator;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author corentin
 */
public class LoginPage extends javax.swing.JFrame {
    
    
    public Controller controller = null;
    private InetAddress broadcast;

    /**
     * Creates new form LoginPage
     * @param c Controller used to manage the application
     * @param interf Interface used to communicate
     */
    public LoginPage(Controller c,String interf) {
        this.controller = c;
        this.getBroadcastAddress(interf);
                /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ConnexionLabel = new javax.swing.JLabel();
        PseudoTextArea = new javax.swing.JTextField();
        IDTextArea = new javax.swing.JTextField();
        PseudoLabel = new javax.swing.JLabel();
        IDLabel = new javax.swing.JLabel();
        Se_connecter = new javax.swing.JButton();
        ErrorPanel = new javax.swing.JPanel();
        ErrorText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ClavardApp");

        ConnexionLabel.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        ConnexionLabel.setText("Connexion");

        IDTextArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDTextAreaActionPerformed(evt);
            }
        });

        PseudoLabel.setText("Pseudo");

        IDLabel.setText("ID personnel");

        Se_connecter.setText("Se connecter");
        Se_connecter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Se_connecterActionPerformed(evt);
            }
        });

        ErrorPanel.setBackground(new java.awt.Color(225, 34, 32));
        ErrorPanel.setForeground(new java.awt.Color(254, 254, 254));

        ErrorText.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        ErrorText.setForeground(new java.awt.Color(254, 254, 254));
        ErrorText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ErrorText.setText("Le pseudo \"pseudo\" n'est plus disponible");

        javax.swing.GroupLayout ErrorPanelLayout = new javax.swing.GroupLayout(ErrorPanel);
        ErrorPanel.setLayout(ErrorPanelLayout);
        ErrorPanelLayout.setHorizontalGroup(
            ErrorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ErrorPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ErrorText, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        ErrorPanelLayout.setVerticalGroup(
            ErrorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ErrorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ErrorText)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ConnexionLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(IDLabel)
                            .addComponent(PseudoLabel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ErrorPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PseudoTextArea)
                            .addComponent(IDTextArea, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Se_connecter, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ConnexionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(PseudoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PseudoTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(IDLabel)
                .addGap(2, 2, 2)
                .addComponent(IDTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Se_connecter, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(ErrorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ErrorPanel.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IDTextAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDTextAreaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDTextAreaActionPerformed

    private void Se_connecterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Se_connecterActionPerformed
        // TODO add your handling code here:
        String pseudo = this.PseudoTextArea.getText();
        try{
            int ID = Integer.parseInt( this.IDTextArea.getText());

            System.out.println(this.controller != null);

            if(this.controller.login(ID, pseudo)){
                try{
                    DatagramSocket ds = new DatagramSocket();
                    //Ask for system's users ID
                    String message = "connected|"+pseudo+"|"+ID;
                    DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(),this.broadcast,1025);
                    //Send the message
                    ds.send(outPacket);

                    //Open the new window and close this one
                    ChatPage cp = new ChatPage(this.controller);
                    this.controller.setChatPage(cp);
                    cp.open();

                    this.setVisible(false);
                }catch(Exception e){
                    e.printStackTrace();
                }

            }else{
                //Pseudo is not available
                this.ErrorText.setText("Le pseudo \""+pseudo+"\" n'est plus disponible");
                this.ErrorPanel.setVisible(true);
            }
        }catch(NumberFormatException e){
            //Pseudo is not available
            this.ErrorText.setText("Veuillez un numéro pour l'ID personnel");
            this.ErrorPanel.setVisible(true);
        }
    }//GEN-LAST:event_Se_connecterActionPerformed

    public void setController(Controller c){
        this.controller = c;
    }
    
    /**
     * @param c the controller used
     */
    public void open(Controller c) {
        
        //this.controller = c;
        this.ErrorPanel.setVisible(false);

        /* Create and display the form */
        /*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage(c).setVisible(true);
            }
        });*/
        this.setVisible(true);
    }
    
    	/**
	 * Get a broadcast address associated to the currently used interface
	 * @param interf The name of the used interface on the system
	 * @return The InetAddress linked to the broadcast address
	 */
	private InetAddress getBroadcastAddress(String interf){
		try{
			NetworkInterface en1 = NetworkInterface.getByName(interf);
			try{
				List<InterfaceAddress> list = en1.getInterfaceAddresses();
				Iterator<InterfaceAddress> it = list.iterator();

				while (it.hasNext()) {
					InterfaceAddress ia = it.next();
					if(ia.getBroadcast() !=  null){
						this.broadcast = ia.getBroadcast();
						return this.broadcast;
					}
				}
			}catch(NullPointerException ne){
				//In case of interface without any address
				System.out.println("ERR: No address defined on "+interf);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ConnexionLabel;
    private javax.swing.JPanel ErrorPanel;
    private javax.swing.JLabel ErrorText;
    private javax.swing.JLabel IDLabel;
    private javax.swing.JTextField IDTextArea;
    private javax.swing.JLabel PseudoLabel;
    private javax.swing.JTextField PseudoTextArea;
    private javax.swing.JButton Se_connecter;
    // End of variables declaration//GEN-END:variables
}
