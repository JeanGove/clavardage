package Interface;


import Controller.Application;
import Controller.Configuration.Parameter;
import Interface.ChatPage;
import Controller.Controller;
import Database.ActiveUserList;
import Tests.RemoteConnection;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
        //Get the controller associated to this interface
        this.controller = c;
        
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
        //Get the broadcast address
        this.getBroadcastAddress(interf);
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
        RemoteModePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        RemoteServerIPTextArea = new javax.swing.JTextField();
        Se_connecter1 = new javax.swing.JButton();
        RemoteModeCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ClavardApp");
        setMinimumSize(new java.awt.Dimension(415, 0));

        ConnexionLabel.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        ConnexionLabel.setText("Connexion");

        IDTextArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDTextAreaActionPerformed(evt);
            }
        });

        PseudoLabel.setText("Pseudo");

        IDLabel.setText("ID personnel");

        Se_connecter.setBackground(new java.awt.Color(47, 234, 54));
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
                .addContainerGap(21, Short.MAX_VALUE)
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

        RemoteModePanel.setOpaque(false);

        jLabel1.setText("Adresse du serveur distant");

        RemoteServerIPTextArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoteServerIPTextAreaActionPerformed(evt);
            }
        });

        Se_connecter1.setText("Se connecter");
        Se_connecter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Se_connecter1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RemoteModePanelLayout = new javax.swing.GroupLayout(RemoteModePanel);
        RemoteModePanel.setLayout(RemoteModePanelLayout);
        RemoteModePanelLayout.setHorizontalGroup(
            RemoteModePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RemoteModePanelLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(RemoteModePanelLayout.createSequentialGroup()
                .addComponent(RemoteServerIPTextArea)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Se_connecter1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        RemoteModePanelLayout.setVerticalGroup(
            RemoteModePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RemoteModePanelLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RemoteModePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RemoteServerIPTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Se_connecter1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        RemoteModeCheckBox.setText("Mode distant");
        RemoteModeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoteModeCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(RemoteModePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ErrorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RemoteModeCheckBox)
                            .addComponent(IDLabel)
                            .addComponent(PseudoLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(IDTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Se_connecter, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ConnexionLabel)
                            .addComponent(PseudoTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ConnexionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PseudoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PseudoTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(IDLabel)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Se_connecter, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RemoteModePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RemoteModeCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(ErrorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        ErrorPanel.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IDTextAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDTextAreaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDTextAreaActionPerformed

    private void Se_connecterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Se_connecterActionPerformed
        this.connect();
    }//GEN-LAST:event_Se_connecterActionPerformed

    private void Se_connecter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Se_connecter1ActionPerformed
        // TODO add your handling code here:
        this.connect();
    }//GEN-LAST:event_Se_connecter1ActionPerformed

    private void RemoteModeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoteModeCheckBoxActionPerformed
        // TODO add your handling code here:
        this.refreshRemoteModeDisplay();
    }//GEN-LAST:event_RemoteModeCheckBoxActionPerformed

    private void RemoteServerIPTextAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoteServerIPTextAreaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RemoteServerIPTextAreaActionPerformed

    public void connect(){
        // Make sure that if the method connect is used that RemoteModeCheckbox is not checked and vice-versa
        if(this.RemoteModeCheckBox.isSelected()) {
            this.connectAsRemoteUser();
        }else{
            // TODO add your handling code here:
            String pseudo = this.PseudoTextArea.getText();
            try{
                int ID = Integer.parseInt( this.IDTextArea.getText());

                System.out.println(this.controller != null);
                int retVal = this.controller.login(ID, pseudo);
                if(retVal == 0){
                    try{
                        DatagramSocket ds = new DatagramSocket();
                        //Ask for system's users ID
                        String message = "connected|"+pseudo+"|"+ID;
                        DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(),this.broadcast,1025);

                        //Send the message
                        ds.send(outPacket);

                        this.goToChatPage(false);
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }else if(retVal == 1){
                    //Pseudo is not available
                    this.ErrorText.setText("Le pseudo \""+pseudo+"\" n'est plus disponible");
                    this.ErrorPanel.setVisible(true);
                }else{
                    //Pseudo is not available
                    this.ErrorText.setText("Une session avec cet ID est déjà ouverte");
                    this.ErrorPanel.setVisible(true);
                }
            }catch(NumberFormatException e){
                //Pseudo is not available
                this.ErrorText.setText("Veuillez un numéro pour l'ID personnel valide");
                this.ErrorPanel.setVisible(true);
            }
        }
    }
    
    public void connectAsRemoteUser(){
        InetAddress addr;
        String host = this.RemoteServerIPTextArea.getText();
        try {
            addr = InetAddress.getByName(host);
            int port = 1025;

            //Get the ID and pseudo of the user requesting me for name and ID
            String pseudo = this.PseudoTextArea.getText();
            try{
                int id = Integer.parseInt(this.IDTextArea.getText());
                try {
                    //Prepare the response
                    DatagramSocket datas = new DatagramSocket();
                    String message = "remoteRequest|"+pseudo+"|"+id;

                    //Send the request to the server
                    DatagramPacket dp = new DatagramPacket(message.getBytes(), message.length(), addr, port);
                    datas.send(dp);

                    //Receive the reply from the server
                    byte[] buffer = new byte[256];

                    DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
                    datas.setSoTimeout(5000);
                    try {

                        datas.receive(inPacket);
                        System.out.print(".");
                        String data = new String(inPacket.getData(),0,inPacket.getLength());

                        this.broadcast = InetAddress.getByName(this.RemoteServerIPTextArea.getText());
                        
                        //System.out.println(str.split("\\|")[1]);
                        int reply = Integer.parseInt(data.split("\\|")[1]);

                        switch (reply) {
                            case 0:
                                //Reply to the server
                                message = "remoteEstablished|"+pseudo+"|"+id;
                                //Send the reply to the server
                                dp = new DatagramPacket(message.getBytes(), message.length(), addr, port);
                                datas.send(dp);
                                //Wait for the last reply
                                datas.receive(inPacket);
                                System.out.println("REPLY: Connexion réussie");
                                
                                this.controller.login(id, pseudo);
                                
                                //Create a TCP connection to receive the list of active users
                                int newPort = inPacket.getPort();
                                System.out.println(newPort);
                                TimeUnit.MILLISECONDS.sleep(500);
                                Socket sock = new Socket(addr,newPort);
                                //Receive the list of active users
                                ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
                                ActiveUserList aul = (ActiveUserList) ois.readObject();
                                aul.initialize(this.broadcast);
                                
                                //Set the remote server address as the address where informations are sent
                                this.controller.broadcast = this.broadcast;
                                
                                // Drop the own user present in the server list
                                aul.removeUser(this.controller.getId());
              
                                //Load the server's active user list
                                this.controller.loadActiveUserList(aul);
                                //Go to the chat page
                                this.goToChatPage(true);
                                break;
                            case 1:
                                //Pseudo is not available
                                this.ErrorText.setText("Le pseudo \""+pseudo+"\" n'est plus disponible");
                                this.ErrorPanel.setVisible(true);
                                break;
                            case 2:
                                //ID is not available
                                this.ErrorText.setText("Une session avec cet ID est déjà ouverte");
                                this.ErrorPanel.setVisible(true);
                                break;
                            default:
                                System.out.println("ERR: Réponse incomprise"); 
                                // An error occurred
                                this.ErrorText.setText("Erreur réponse");
                                this.ErrorPanel.setVisible(true);
                                break;
                        }
                    }catch(SocketTimeoutException ste){
                        System.out.println("ERR: Réponse non reçue");   
                        
                        // An error occurred
                        this.ErrorText.setText("Aucune réponse du serveur");
                        this.ErrorPanel.setVisible(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("ERR: Problème lors de la réception du paquet"); 
                                                
                        // An error occurred
                        this.ErrorText.setText("Une erreur est survenue");
                        this.ErrorPanel.setVisible(true);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(RemoteConnection.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("ERR: L'objet reçu ne correspond pas à une classe connue"); 
                                                
                        // An error occurred
                        this.ErrorText.setText("Une erreur est survenue");
                        this.ErrorPanel.setVisible(true);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                    } 


                } catch (SocketException ex) {
                    Logger.getLogger(RemoteConnection.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("ERR: Problème d'ouverture de socket, port: "+port);
                                            
                    // An error occurred
                    this.ErrorText.setText("Connexion à "+host+":"+port+" impossible");
                    this.ErrorPanel.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(RemoteConnection.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("ERR: paquet non envoyé");
                                                                    
                    // An error occurred
                    this.ErrorText.setText("Une erreur est survenue lors du transfert d'un paquet");
                    this.ErrorPanel.setVisible(true);
                }
            }catch(NumberFormatException nfex){
                //Pseudo is not available
                this.ErrorText.setText("Veuillez un numéro pour l'ID personnel valide");
                this.ErrorPanel.setVisible(true);
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(RemoteConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERR: nom d'hôte invalide");           
                                                                               
            // An error occurred
            this.ErrorText.setText("Serveur "+host+" inconnu ou inaccessible");
            this.ErrorPanel.setVisible(true);
        }
        
    }
    
    private void goToChatPage(boolean remoted) {
        //Open the new window and close this one
        ChatPage cp = new ChatPage(this.controller,remoted);
        this.controller.setChatPage(cp);
        cp.open();
        this.saveValues();
        this.setVisible(false);
    }
    
    public void setController(Controller c){
        this.controller = c;
    }
    
    public void refreshRemoteModeDisplay(){
        
        this.Se_connecter1.setVisible(this.RemoteModeCheckBox.isSelected());
        this.Se_connecter.setVisible(!this.RemoteModeCheckBox.isSelected());
        this.RemoteModePanel.setVisible(this.RemoteModeCheckBox.isSelected());
        
        this.pack();
    }
    
    /**
     * @param c the controller used
     */
    public void open(Controller c) {
       // System.out.println(new File("src/main/java/resources/appicon.png").getAbsolutePath());
        //ImageIcon icon = new ImageIcon(getClass().getResource("appicon.png").getPath());
        /*ImageIcon icon = new ImageIcon(getClass().getResource("appicon.png").getPath());
        this.setIconImage(icon.getImage());*/
        //getClass().getResource(name).getPath()
        //String log = ""+ new File(getClass().getResource("appicon.png").getFile()).exists();
        //System.out.println(log);
        //this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("appicon.png")));
        
        //this.controller = c;
        if(this.broadcast != null)  this.ErrorPanel.setVisible(false);
        this.refreshRemoteModeDisplay();
        this.prefillForm();

        /* Create and display the form */
        /*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage(c).setVisible(true);
            }
        });*/
        this.setVisible(true);
    }
    
    private void prefillForm(){
        Parameter p;
        if((p = Application.option.get("pseudo")) != null)
            this.PseudoTextArea.setText(p.value);
        if((p = Application.option.get("id")) != null)
            this.IDTextArea.setText(p.value);
        if((p = Application.option.get("remoteAddr")) != null)
            this.RemoteServerIPTextArea.setText(p.value);
        if((p = Application.option.get("remoteMode")) != null)
            this.RemoteModeCheckBox.setSelected(p.value.equals("true"));
    }
    
    private void saveValues(){
        Application.option.add(new Parameter("pseudo",this.PseudoTextArea.getText()));
        Application.option.add(new Parameter("id",this.IDTextArea.getText()));
        Application.option.add(new Parameter("remoteAddr",this.RemoteServerIPTextArea.getText()));
        Application.option.add(new Parameter("remoteMode",this.RemoteModeCheckBox.isSelected()+""));
        Application.option.save();
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
                        
                        this.ErrorText.setText("Aucune adresse n'est défini sur "+interf);
                        this.ErrorPanel.setVisible(true);
                        
                        this.Se_connecter.setEnabled(false);
                        this.Se_connecter1.setEnabled(false);
                        this.RemoteModeCheckBox.setEnabled(false);
                        this.PseudoTextArea.setEnabled(false);
                        this.IDTextArea.setEnabled(false);
                        this.RemoteServerIPTextArea.setEnabled(false);

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
    private javax.swing.JCheckBox RemoteModeCheckBox;
    private javax.swing.JPanel RemoteModePanel;
    private javax.swing.JTextField RemoteServerIPTextArea;
    private javax.swing.JButton Se_connecter;
    private javax.swing.JButton Se_connecter1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
