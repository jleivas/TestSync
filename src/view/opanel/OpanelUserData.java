/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.opanel;

import entities.User;
import fn.GV;
import fn.Icons;
import fn.OptionPane;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.ContentAdmin;

/**
 *
 * @author sdx
 */
public class OpanelUserData extends javax.swing.JPanel {
    private static User stUser = GV.user();
    /**
     * Creates new form OpanelSelectDate
     */
    public OpanelUserData() {
        initComponents();
        cargarCboTipo();
        loadData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txtUsername = new javax.swing.JTextField();
        txtMail = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        cboTipo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        txtPass2 = new javax.swing.JPasswordField();
        txtPass1 = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel1.setText("Username");

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Cancel_50px.png"))); // NOI18N
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCancelarMousePressed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/btn_Ok_50px.png"))); // NOI18N
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGuardarMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel3.setText("Nombre");

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel4.setText("Email");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel5.setText("Tipo");

        txtNombre.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        txtNombre.setBorder(null);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtUsername.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        txtUsername.setBorder(null);
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsernameKeyTyped(evt);
            }
        });

        txtMail.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        txtMail.setBorder(null);
        txtMail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMailKeyTyped(evt);
            }
        });

        cboTipo.setEditable(true);
        cboTipo.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTipo.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel7.setText("Clave");

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel8.setText("Repita clave");

        txtPass2.setBorder(null);
        txtPass2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPass2KeyTyped(evt);
            }
        });

        txtPass1.setBorder(null);
        txtPass1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPass1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jSeparator5)
                                .addGap(144, 144, 144)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPass1)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPass2)))
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardar))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator2)
                                .addComponent(txtNombre)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMail)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(20, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(txtPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator6)
                    .addComponent(jSeparator5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addGap(65, 65, 65))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        OptionPane.closeOptionPanel();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseEntered
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnCancelar.getIcon().toString()))));
    }//GEN-LAST:event_btnCancelarMouseEntered

    private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseExited
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnCancelar.getIcon().toString()))));
    }//GEN-LAST:event_btnCancelarMouseExited

    private void btnCancelarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarMousePressed

    private void btnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseClicked
        GV.cursorWAIT(this);
        String pass = null;
        String userName = txtUsername.getText();
        
        String temp = txtPass1.getText().trim().replaceAll(" ","");
        if(OptionPane.getConfirmation("Modificar mis datos", "¿Estas seguro que deseas modificar tus datos?", JOptionPane.INFORMATION_MESSAGE)){
            GV.cursorWAIT(this);
            if(temp.length() > 4){
                if(temp.equals(txtPass2.getText().trim()))
                    pass = GV.enC(txtPass1.getText().trim());
                else{
                    OptionPane.showMsg("Clave incorrecta", "Ambas claves ingresadas deben ser iguales\n"
                            + "\nLa clave debe ser:\n"
                            + "Sin espacios\nSin caracteres especiales\nDebe contener mas de cuatro caracteres", JOptionPane.WARNING_MESSAGE);
                    GV.cursorDF(this);
                    return;
                }
            }
            if(stUser !=  null){
                GV.cursorWAIT(this);
                dao.Dao load = new dao.Dao();
                if(pass != null){
                    stUser.setPass(pass);
                }
                if(!stUser.getUsername().equals(userName)){
                    try {
                        GV.cursorWAIT(this);
                        if(load.get(userName, 0, new User())!= null){
                            OptionPane.showMsg("No se puede modificar", "El nombre de usuario "+userName+" ya se encuentra en uso\n"
                                    + "Intente con otro valor...",JOptionPane.WARNING_MESSAGE);
                            GV.cursorDF(this);
                            return;
                        }
                    } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                        OptionPane.showMsg("Error inesperado", "Ocurrió un error al intentar consultar el nombre de usuario\n"
                                + "en la base de datos.\nDetalle:"+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                        GV.cursorDF(this);
                        return;
                    }
                }
                GV.cursorWAIT(this);
                stUser.setEmail(txtMail.getText());
                stUser.setNombre(txtNombre.getText());
                stUser.setUsername(txtUsername.getText());
                stUser.setTipo(cboTipo.getSelectedIndex());
                GV.setUser(stUser);
                
                load.update(stUser);
                ContentAdmin.lblUserName.setText(GV.user().getNombre());
                GV.cursorDF(this);
                OptionPane.closeOptionPanel();
            }else{
                OptionPane.showMsg("Error", "No se puede cargar el usuario", JOptionPane.ERROR_MESSAGE);
                GV.cursorDF(this);
                return;
            }
            
        }else{
            GV.cursorDF(this);
            return;
        }
    }//GEN-LAST:event_btnGuardarMouseClicked

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnGuardar.getIcon().toString()))));
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnGuardar.getIcon().toString()))));
    }//GEN-LAST:event_btnGuardarMouseExited

    private void btnGuardarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarMousePressed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        int largo = 45;
        if(txtNombre.getText().length() >= largo){
            evt.consume();
            OptionPane.showMsg("Error de ingreso de datos", "El nombre solo debe contener hasta 45 caracteres", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyTyped
        int largo = 45;
        if(txtUsername.getText().length() >= largo){
            evt.consume();
            OptionPane.showMsg("Error de ingreso de datos", "El username solo debe contener hasta 45 caracteres", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtUsernameKeyTyped

    private void txtMailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMailKeyTyped
        int largo = 45;
        if(txtMail.getText().length() >= largo){
            evt.consume();
            OptionPane.showMsg("Error de ingreso de datos", "El email solo debe contener hasta 45 caracteres", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtMailKeyTyped

    private void txtPass1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPass1KeyTyped
        int largo = 25;
        if(txtPass1.getText().length() >= largo){
            evt.consume();
            OptionPane.showMsg("Error de ingreso de datos", "El password solo debe contener hasta 25 caracteres", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtPass1KeyTyped

    private void txtPass2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPass2KeyTyped
        int largo = 25;
        if(txtPass2.getText().length() >= largo){
            evt.consume();
            OptionPane.showMsg("Error de ingreso de datos", "El password solo debe contener hasta 25 caracteres", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtPass2KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCancelar;
    private javax.swing.JLabel btnGuardar;
    private javax.swing.JComboBox<String> cboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPass1;
    private javax.swing.JPasswordField txtPass2;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    private void cargarCboTipo() {
        if(GV.tipoUserAdmin())
            cboTipo.setEditable(true);
        else
            cboTipo.setEditable(false);
        cboTipo.removeAllItems();
        cboTipo.addItem("Sin asignar");
        cboTipo.addItem("Jefatura");
        cboTipo.addItem("Administrativo");
        cboTipo.addItem("Ventas");
        cboTipo.addItem("Sin asignar");
        cboTipo.addItem("Sin asignar");
        cboTipo.addItem("Sin asignar");
        cboTipo.addItem("Sistema");
    }

    private void loadData() {
        if(stUser != null){
            txtMail.setText(stUser.getEmail());
            txtNombre.setText(stUser.getNombre());
            txtUsername.setText(stUser.getUsername());
            cboTipo.setSelectedIndex(stUser.getTipo());
        }else{
            OptionPane.showMsg("Error al cargar usuario", "No se pueden cargar los datos del usuario para ser modificados.", JOptionPane.ERROR_MESSAGE);
        }
    }
}
