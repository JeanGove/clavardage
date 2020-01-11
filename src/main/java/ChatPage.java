import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author corentin
 */
public class ChatPage extends javax.swing.JFrame {
    private Controller c;
    private OptionPage op;
    /**
     * Creates new form ChatPage
     */
    public ChatPage(Controller c) {
        this.c = c;
        
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
            java.util.logging.Logger.getLogger(ChatPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        jSplitPane1 = new javax.swing.JSplitPane();
        MessagesPanel = new javax.swing.JPanel();
        MessageScroller = new javax.swing.JScrollPane();
        Messages = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        EnvoiField = new javax.swing.JTextField();
        EnvoiBttn = new javax.swing.JButton();
        TopPanel = new javax.swing.JPanel();
        DialUserLabel = new javax.swing.JLabel();
        OptionsBttn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Userlist = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ClavardApp");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jSplitPane1.setMinimumSize(new java.awt.Dimension(100, 83));

        MessagesPanel.setMinimumSize(new java.awt.Dimension(250, 83));
        MessagesPanel.setLayout(new java.awt.BorderLayout());

        MessageScroller.setViewportBorder(null);
        MessageScroller.setAutoscrolls(true);

        Messages.setText("<html><p>Messages</p></html>");
        Messages.setToolTipText("");
        Messages.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        MessageScroller.setViewportView(Messages);

        MessagesPanel.add(MessageScroller, java.awt.BorderLayout.CENTER);

        jPanel1.setToolTipText("");
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        EnvoiField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnvoiFieldActionPerformed(evt);
            }
        });
        jPanel1.add(EnvoiField);

        EnvoiBttn.setText("Envoyer");
        EnvoiBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnvoiBttnActionPerformed(evt);
            }
        });
        jPanel1.add(EnvoiBttn);

        MessagesPanel.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        TopPanel.setPreferredSize(new java.awt.Dimension(495, 45));
        TopPanel.setLayout(new java.awt.BorderLayout());

        DialUserLabel.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        DialUserLabel.setText("Messages");
        TopPanel.add(DialUserLabel, java.awt.BorderLayout.CENTER);

        OptionsBttn.setText("Options");
        OptionsBttn.setToolTipText("");
        OptionsBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OptionsBttnActionPerformed(evt);
            }
        });
        TopPanel.add(OptionsBttn, java.awt.BorderLayout.LINE_END);
        OptionsBttn.getAccessibleContext().setAccessibleName("Options");

        MessagesPanel.add(TopPanel, java.awt.BorderLayout.NORTH);

        jSplitPane1.setRightComponent(MessagesPanel);

        Userlist.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        Userlist.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        Userlist.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                UserlistValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(Userlist);

        jSplitPane1.setLeftComponent(jScrollPane2);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EnvoiFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnvoiFieldActionPerformed
        // TODO add your handling code here:
        this.send();
    }//GEN-LAST:event_EnvoiFieldActionPerformed

    private void UserlistValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_UserlistValueChanged
        // TODO add your handling code here:
        this.setOnDialUser();
    }//GEN-LAST:event_UserlistValueChanged

    private void EnvoiBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnvoiBttnActionPerformed
        // TODO add your handling code here:
        this.send();
    }//GEN-LAST:event_EnvoiBttnActionPerformed

    private void OptionsBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OptionsBttnActionPerformed
        // TODO add your handling code here:
        this.op.open();
    }//GEN-LAST:event_OptionsBttnActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        //Disconnect and close if successfully done
        if(this.c.disconnect()){
            //Close the window
            System.exit(0);
        }
        
    }//GEN-LAST:event_formWindowClosing

    public void send(){
        String content = this.EnvoiField.getText();
        int idSrc = this.c.getId();
        int idDst = this.c.getUserList().get(this.Userlist.getSelectedIndex()).getId();
        Date date = new Date();
        
        Message message = new Message(date, idSrc, idDst, content);
        /*System.out.println(this.c.getUserList().size());
        System.out.println(this.c.getUserList().get(idDst).getPseudo());
        System.out.println(this.c.getUserList().get(idDst).connector != null);
        this.c.getUserList().get(idDst).connector.out.writeObject(message);*/
        this.c.sendMessage(message, this.c.getUserList().get(this.Userlist.getSelectedIndex()));
        System.out.println("Message sent: length = "+content.length()+" characters");
        this.EnvoiField.setText("");
    }
    
    public void refreshUserlist(){
       // this.Userlist.setVisible(false);
        DefaultListModel<String> lm = new DefaultListModel<String>();
        for(User u: this.c.getUserList()){
            lm.addElement(u.getPseudo());
        }
        this.Userlist.setModel(lm);
        //this.Userlist.setVisible(true);
    }
    
    public void setOnDialUser(){
        String pseudo = this.Userlist.getSelectedValue();
        int id = this.c.getUserList().get(this.Userlist.getSelectedIndex()).getId();
        
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        
        this.DialUserLabel.setText(pseudo);
        ArrayList<Message> messages = this.c.getHistory().load(id);
        
        String content = "<html>";
        for(Message message: messages){
            User u = this.c.getUserByID(message.getSourceId());
            String nom = (u != null)? u.getPseudo(): "Vous";
            content += "<p><b>"+
                    nom +
                    ":</b> "+ message.getContent() +
                    "<b><small style='opacity:0.2'>         "+ df.format(message.getDate())+"</small></b>"; 
        }
        content += "</html>";
        
        this.Messages.setText(content);
    }
    

    
    /**
     * @param args the command line arguments
     */
    public void open() {
        this.refreshUserlist();
        
        //Select the first user if available
        if(this.Userlist.getModel().getSize() > 0){
            this.Userlist.setSelectedIndex(0);
            this.setOnDialUser();
        }

      //  this.jScrollPane2.getViewport().setOpaque(false);
        this.MessageScroller.getViewport().setOpaque(false);
        //Make optionPage available
        this.op = new OptionPage(this.c);
      
        this.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DialUserLabel;
    private javax.swing.JButton EnvoiBttn;
    private javax.swing.JTextField EnvoiField;
    private javax.swing.JScrollPane MessageScroller;
    private javax.swing.JLabel Messages;
    private javax.swing.JPanel MessagesPanel;
    private javax.swing.JButton OptionsBttn;
    private javax.swing.JPanel TopPanel;
    private javax.swing.JList<String> Userlist;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables
}
