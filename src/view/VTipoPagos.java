/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.Dao;
import entities.TipoPago;
import fn.Boton;
import fn.GlobalValues;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import fn.Icons;
import fn.OptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author home
 */
public class VTipoPagos extends javax.swing.JPanel {
    Boton boton = new Boton();
    Dao load= new Dao();
    private static TipoPago stTipoPago = null;
    TableRowSorter trs;
    DefaultTableModel modelo = new DefaultTableModel() {
           @Override
           public boolean isCellEditable(int fila, int columna) {
               return false; //Con esto conseguimos que la tabla no se pueda editar
           }
        };
    /**
     * Creates new form VClientes
     */
    public VTipoPagos() {
        GlobalValues.IS_ONLINE = true;
        ContentAdmin.lblTitle.setText("Tipos de pago");
        load.sincronize(new TipoPago());
        initComponents();
        modelo.addColumn("Tipo");
        tblListar.setModel(modelo);
        load();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cboMostrar = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListar = new javax.swing.JTable();
        btnAbrir = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JLabel();
        btnRestaurar = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreN = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtNombreU = new javax.swing.JTextField();
        btnModificar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Lista de registros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 0, 11))); // NOI18N

        cboMostrar.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        cboMostrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activos", "Eliminados" }));
        cboMostrar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMostrarItemStateChanged(evt);
            }
        });

        tblListar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Tipo"
            }
        ));
        jScrollPane1.setViewportView(tblListar);

        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Eye_50px.png"))); // NOI18N
        btnAbrir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAbrirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAbrirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAbrirMouseExited(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Trash_50px.png"))); // NOI18N
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarMouseExited(evt);
            }
        });

        btnRestaurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Data_Backup_50px.png"))); // NOI18N
        btnRestaurar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRestaurarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRestaurarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRestaurarMouseExited(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Search_Property_25px_1.png"))); // NOI18N

        txtBuscar.setBorder(null);
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAbrir)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnRestaurar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAbrir, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRestaurar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboMostrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Crear nuevo registro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 0, 11))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel2.setText("Nombre");

        txtNombreN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreNKeyTyped(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Save_50px.png"))); // NOI18N
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
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombreN, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombreN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnGuardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(108, 217, 186));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Editar registro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 0, 11))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel11.setText("Nombre");

        txtNombreU.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreUKeyTyped(evt);
            }
        });

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Save_50px.png"))); // NOI18N
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnModificarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnModificarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombreU, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnModificar)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificar)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtNombreU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreNKeyTyped
        int largo = 45;
        if(txtNombreN.getText().length() >= largo){
            evt.consume();
            OptionPane.showMsg("Error de ingreso de datos", "El nombre solo debe contener hasta 45 caracteres", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtNombreNKeyTyped

    private void txtNombreUKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreUKeyTyped
        int largo = 45;
        if(txtNombreU.getText().length() >= largo){
            evt.consume();
            OptionPane.showMsg("Error de ingreso de datos", "El nombre solo debe contener hasta 45 caracteres", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtNombreUKeyTyped

    private void btnAbrirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAbrirMouseClicked
        try{
            int fila = tblListar.getSelectedRow();
            String nombre = tblListar.getValueAt(fila, 0).toString();
            
            abrirTipoPago(nombre);
            
        }catch(Exception e){
            OptionPane.showMsg("Seleccione un elemento en la tabla","Debe hacer clic sobre un elemento de la tabla,\n"
                    + "Luego presione el botón \"Ver\".",  JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAbrirMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        try{
            int fila = tblListar.getSelectedRow();
            String nombre = tblListar.getValueAt(fila, 0).toString();
            TipoPago temp = (TipoPago)load.get(nombre,0,new TipoPago());
            if(OptionPane.getConfirmation("Eliminar tipo de pago", "¿Esta seguro que desea eliminar el registro "+temp.getNombre()+"?", JOptionPane.WARNING_MESSAGE))
                if(load.delete(nombre,0, temp))
                    OptionPane.showMsg("Eliminar tipo de pago", "El registro ha sido eliminado", JOptionPane.INFORMATION_MESSAGE);
                else
                    OptionPane.showMsg("Eliminar tipo de pago", "No se pudo eliminar el registro", JOptionPane.WARNING_MESSAGE);
            cargarDatos("0");
            
        }catch(Exception e){
            OptionPane.showMsg("Seleccione tipo de pago","Error al cargar valores,\n"
                    + "es posible que no se haya seleccionado un registro:\n"
                    + "Debe hacer clic sobre un elemento de la tabla,\n"
                    + "Luego presione el botón \"Ver\".\n"
                    + "Otro posible error: el valor seleccionado no tiene un identificador válido.",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnRestaurarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRestaurarMouseClicked
        try{
            int fila = tblListar.getSelectedRow();
            String nombre = tblListar.getValueAt(fila, 0).toString();
            if(OptionPane.getConfirmation("Confirmación de tipo de pago", "¿Esta seguro que desea restaurar este registro?", JOptionPane.INFORMATION_MESSAGE)){
                if(load.restore(nombre, 0, new TipoPago())){
                    OptionPane.showMsg("Restaurar tipo de pago", "El registro ha sido restaurado", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    OptionPane.showMsg("Restaurar tipo de pago", "No se pudo restaurar el registro", JOptionPane.WARNING_MESSAGE);
                }
                cargarDatos("-1");
            }
        }catch(Exception e){
            OptionPane.showMsg("Seleccione tipo de pago","Error al cargar valores,\n"
                    + "es posible que no hay seleccionado un registro\n"
                    + "o el valor seleccionado no tiene un identificador válido.",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnRestaurarMouseClicked

    private void btnAbrirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAbrirMouseEntered
        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnAbrir.getIcon().toString()))));
    }//GEN-LAST:event_btnAbrirMouseEntered

    private void btnAbrirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAbrirMouseExited
        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnAbrir.getIcon().toString()))));
    }//GEN-LAST:event_btnAbrirMouseExited

    private void btnEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseEntered
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnEliminar.getIcon().toString()))));
    }//GEN-LAST:event_btnEliminarMouseEntered

    private void btnEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseExited
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnEliminar.getIcon().toString()))));
    }//GEN-LAST:event_btnEliminarMouseExited

    private void btnRestaurarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRestaurarMouseEntered
        btnRestaurar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnRestaurar.getIcon().toString()))));
    }//GEN-LAST:event_btnRestaurarMouseEntered

    private void btnRestaurarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRestaurarMouseExited
        btnRestaurar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnRestaurar.getIcon().toString()))));
    }//GEN-LAST:event_btnRestaurarMouseExited

    private void cboMostrarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMostrarItemStateChanged
        load();
    }//GEN-LAST:event_cboMostrarItemStateChanged

    private void btnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseClicked
        
        String nombre=txtNombreN.getText();
        if(!nombre.isEmpty() || nombre.length()>3)
            nombre = GlobalValues.strToName(nombre);
        else{
            OptionPane.showMsg("Guardar tipo de pago", "El nuevo registro debe tener un nombre válido.", JOptionPane.WARNING_MESSAGE);
            return;
        }
        

        TipoPago tipoPago= new TipoPago(GlobalValues.LOCAL_SYNC.getMaxId(new TipoPago()), nombre, 1, null, 0);
        try {
            load.add(tipoPago);
        } catch (InstantiationException | IllegalAccessException ex) {
            OptionPane.showMsg("Error inesperado","Ocurrió un error al intentar insertar un nuevo registro:\n"
                    + "No se pudo insertar tipo de pago\n\n"
                    + ex, JOptionPane.ERROR_MESSAGE);
        }
        cargarDatos("0");
    }//GEN-LAST:event_btnGuardarMouseClicked

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnGuardar.getIcon().toString()))));
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnGuardar.getIcon().toString()))));
    }//GEN-LAST:event_btnGuardarMouseExited

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        String nombre= txtNombreU.getText();
        if(!nombre.isEmpty() || nombre.length()>3)
            nombre = GlobalValues.strToName(nombre);
        else{
            OptionPane.showMsg("Modificar tipo de pago", "El registro debe tener un nombre válido.", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        stTipoPago.setNombre(nombre);
        if(GlobalValues.LOCAL_SYNC.exist(stTipoPago)){
            OptionPane.showMsg("Modificar tipo de pago", "Ya existe un registro con el mismo nombre.", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(load.update(stTipoPago))
            OptionPane.showMsg("Modificar Inventario", "Operación realizada con exito",  JOptionPane.INFORMATION_MESSAGE);
        else
            OptionPane.showMsg("Modificar Inventario", "No se pudo efectuar la operación", JOptionPane.WARNING_MESSAGE);
        cargarDatos("0");
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnModificarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseEntered
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnModificar.getIcon().toString()))));// TODO add your handling code here:
    }//GEN-LAST:event_btnModificarMouseEntered

    private void btnModificarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseExited
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnModificar.getIcon().toString()))));// TODO add your handling code here:
    }//GEN-LAST:event_btnModificarMouseExited

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        txtBuscar.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(final KeyEvent e) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)"+txtBuscar.getText(), 0,1,2,3,4,5,7,8,9,10));
            }
            
        });
        
        
        
        trs = new TableRowSorter(modelo);
        
        tblListar.setRowSorter(trs);
    }//GEN-LAST:event_txtBuscarKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAbrir;
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JLabel btnGuardar;
    private javax.swing.JLabel btnModificar;
    private javax.swing.JLabel btnRestaurar;
    private javax.swing.JComboBox<String> cboMostrar;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblListar;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtNombreN;
    private javax.swing.JTextField txtNombreU;
    // End of variables declaration//GEN-END:variables

    
    private void load(){
        if(cboMostrar.getSelectedIndex()==0){//en nueva version cargar ventana completa con lista de clientes estatica global
            cargarDatos("0");
        }else{
            cargarDatos("-1");
        }
    }
    private void cargarDatos(String listar) {
        limpiarTextField();
        if(listar.equals("-1")){
            btnRestaurar.setVisible(true);
            btnEliminar.setVisible(false);
            btnAbrir.setVisible(false);
            btnModificar.setVisible(false);
        }else{
            btnRestaurar.setVisible(false);
            btnEliminar.setVisible(true);
            btnAbrir.setVisible(true);
            btnModificar.setVisible(true);
        }
        try{
            modelo.setNumRows(0);
            for (Object object : load.listar(listar, new TipoPago())) {
                TipoPago temp = (TipoPago)object;
                Object[] fila = new Object[1];
                fila[0] = temp.getNombre();
                modelo.addRow(fila);
            }
            tblListar.updateUI();
            if(tblListar.getRowCount() == 0){
                OptionPane.showMsg("No existen registros", "No existen tipos de pago registrados.",JOptionPane.INFORMATION_MESSAGE);
            }
            
        }catch(Exception e){
            OptionPane.showMsg("Ocurrió un error inesperado", "Error al cargar valores en la tabla, ["+e.getMessage()+"]",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirTipoPago(String nombre) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
            stTipoPago = (TipoPago)load.get(nombre,0,new TipoPago());
            if(stTipoPago!=null){
                if(stTipoPago.getNombre().isEmpty() || stTipoPago.getNombre().equals("null"))
                    txtNombreU.setText("");
                else
                    txtNombreU.setText(stTipoPago.getNombre());
                
            }else{
                OptionPane.showMsg("Seleccione Tipo de pago","Error al cargar valores,\n"
                    + "es posible que no se haya seleccionado un registro\n"
                    + "o el valor seleccionado no tiene un identificador válido.",JOptionPane.WARNING_MESSAGE);
            }
    }

    private void limpiarTextField() {
        txtNombreU.setText("");
        txtNombreN.setText("");
    }
}
