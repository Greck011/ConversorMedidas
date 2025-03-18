package Principal;

import Logica.CajaDivisa;
import Logica.CajaMedida;
import Logica.Divisa;
import Logica.Medida;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author greivin
 */
public final class Ventana extends javax.swing.JFrame {

    public Ventana() {
        initComponents();
        setTitle("Convertidor");
        Image icon = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Img/logo.png"));
        setIconImage(icon);
        setLocationRelativeTo(null);

        llenarCaja1Divisa();
        llenarCaja2Divisa();
        iniciarCajas();
    }

    public void limpiarCajas() {

        jComboBoxDato1.setSelectedIndex(0);
        jComboBoxDato2.setSelectedIndex(1);
        jTextFieldDato1.setText("");
        jTextFieldDato2.setText("");
    }

    public void iniciarCajas() {
        jComboBoxCambiar.addItem("Divisas");
        jComboBoxCambiar.addItem("Medidas");

        // Detectar cambio en jComboBoxCambiar y actualizar datos
        jComboBoxCambiar.addActionListener(e -> actualizarCategorias());

        // Cargar los valores iniciales
        actualizarCategorias();
    }

    public void actualizarCategorias() {
        String seleccion = (String) jComboBoxCambiar.getSelectedItem();

        // Limpiar los ComboBox antes de llenarlos nuevamente
        jComboBoxDato1.removeAllItems();
        jComboBoxDato2.removeAllItems();

        if ("Divisas".equals(seleccion)) {
            llenarCaja1Divisa();
            llenarCaja2Divisa();
        } else if ("Medidas".equals(seleccion)) {
            llenarCaja1Medida();
            llenarCaja2Medida();
        }
    }

    public void llenarCaja1Divisa() {
        CajaDivisa cajaDivisa1 = new CajaDivisa();
        cajaDivisa1.ArregloDivisa();

        for (String divisa : cajaDivisa1.getCajaDivisa()) {
            jComboBoxDato1.addItem(divisa);
        }
    }

    public void llenarCaja2Divisa() {
        CajaDivisa cajaDivisa2 = new CajaDivisa();
        cajaDivisa2.ArregloOtraDivisa();

        for (String divisa : cajaDivisa2.getCajaOtrasDivisa()) {
            jComboBoxDato2.addItem(divisa);
        }
    }

    public void llenarCaja1Medida() {
        CajaMedida cajaMedida1 = new CajaMedida();
        cajaMedida1.ArregloMedida();

        for (String medida : cajaMedida1.getCajaMedidas()) {
            jComboBoxDato1.addItem(medida);
        }
    }

    public void llenarCaja2Medida() {
        CajaMedida cajaMedida2 = new CajaMedida();
        cajaMedida2.ArregloOtraMedida();

        for (String medida : cajaMedida2.getCajaOtrasMedidas()) {
            jComboBoxDato2.addItem(medida);
        }
    }

    public void realizarConversion() {
        if ("Divisas".equals(jComboBoxCambiar.getSelectedItem())) {
            ConvertirDivisas();
        } else {
            ConvertirUnidades();
        }
    }

    public void indiceCB() {
        jComboBoxDato1.setSelectedIndex(0);
        jComboBoxDato2.setSelectedIndex(1);
    }

    public void ConvertirDivisas() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#0.00", symbols);

        Divisa divisas = new Divisa();
        String cajaUno;
        String cajaDos;
        double moneda;
        double divisa;

        cajaUno = (String) jComboBoxDato1.getSelectedItem();
        cajaDos = (String) jComboBoxDato2.getSelectedItem();

        moneda = Double.parseDouble(jTextFieldDato1.getText());

        /*---------------------------Divisas------------------------------*/
        if (cajaUno.equals("USD") && cajaDos.equals("CRC")) {
            divisa = divisas.DolarAColon(moneda);
            jTextFieldDato2.setText(String.valueOf(df.format(divisa)));
        }

        if (cajaUno.equals("CRC") && cajaDos.equals("USD")) {
            divisa = divisas.ColonADolar(moneda);
            jTextFieldDato2.setText(String.valueOf(df.format(divisa)));
        }
    }

    public void ConvertirUnidades() {
        DecimalFormat df = new DecimalFormat("#.###");

        String cajaUno;
        String cajaDos;
        double mil;
        double cen;
        double pul;
        double met;
        double unidad;

        Medida medidas = new Medida();

        cajaUno = (String) jComboBoxDato1.getSelectedItem();
        cajaDos = (String) jComboBoxDato2.getSelectedItem();

        mil = Double.parseDouble(jTextFieldDato1.getText());
        cen = Double.parseDouble(jTextFieldDato1.getText());
        pul = Double.parseDouble(jTextFieldDato1.getText());
        met = Double.parseDouble(jTextFieldDato1.getText());

        /*---------------------------MM------------------------------*/
        if (cajaUno.equals("MM") && cajaDos.equals("CM")) {
            unidad = medidas.MilimetrosACM(mil);
            jTextFieldDato2.setText(String.valueOf(df.format(unidad)));
        }

        if (cajaUno.equals("MM") && cajaDos.equals("PG")) {
            unidad = medidas.MilimetrosAPG(pul);
            jTextFieldDato2.setText(String.valueOf(df.format(unidad)));
        }

        if (cajaUno.equals("MM") && cajaDos.equals("MT")) {
            unidad = medidas.MilimetrosAMTR(met);
            jTextFieldDato2.setText(String.valueOf(df.format(unidad)));
        }

        /*---------------------------CM------------------------------*/
        if (cajaUno.equals("CM") && cajaDos.equals("MM")) {
            unidad = medidas.CentimetrosAMM(cen);
            jTextFieldDato2.setText(String.valueOf(df.format(unidad)));
        }

        if (cajaUno.equals("CM") && cajaDos.equals("PG")) {
            unidad = medidas.CentimetrosAPG(cen);
            jTextFieldDato2.setText(String.valueOf(df.format(unidad)));
        }

        if (cajaUno.equals("CM") && cajaDos.equals("MT")) {
            unidad = medidas.CentimetrosAMTR(cen);
            jTextFieldDato2.setText(String.valueOf(df.format(unidad)));
        }

        /*---------------------------PG------------------------------*/
        if (cajaUno.equals("PG") && cajaDos.equals("MM")) {
            unidad = medidas.PulgadaAMM(pul);
            jTextFieldDato2.setText(String.valueOf(df.format(unidad)));
        }

        if (cajaUno.equals("PG") && cajaDos.equals("CM")) {
            unidad = medidas.PulgadaACM(pul);
            jTextFieldDato2.setText(String.valueOf(df.format(unidad)));
        }

        if (cajaUno.equals("PG") && cajaDos.equals("MT")) {
            unidad = medidas.PulgadaAMTR(pul);
            jTextFieldDato2.setText(String.valueOf(df.format(unidad)));
        }

        /*---------------------------MT------------------------------*/
        if (cajaUno.equals("MT") && cajaDos.equals("MM")) {
            unidad = medidas.MetrosAMM(met);
            jTextFieldDato2.setText(String.valueOf(df.format(unidad)));
        }

        if (cajaUno.equals("MT") && cajaDos.equals("CM")) {
            unidad = medidas.MetrosACM(met);
            jTextFieldDato2.setText(String.valueOf(df.format(unidad)));
        }

        if (cajaUno.equals("MT") && cajaDos.equals("PG")) {
            unidad = medidas.MetrosAPG(met);
            jTextFieldDato2.setText(String.valueOf(df.format(unidad)));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonConvertir = new javax.swing.JButton();
        jButtonLimpiar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jComboBoxCambiar = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldDato1 = new javax.swing.JTextField();
        jTextFieldDato2 = new javax.swing.JTextField();
        jComboBoxDato2 = new javax.swing.JComboBox<>();
        jComboBoxDato1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(18, 18, 18));
        setMaximumSize(new java.awt.Dimension(700, 350));
        setMinimumSize(new java.awt.Dimension(700, 350));
        setPreferredSize(new java.awt.Dimension(700, 300));
        setResizable(false);
        setSize(new java.awt.Dimension(700, 350));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Fondo.setBackground(new java.awt.Color(25, 24, 24));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/p.png"))); // NOI18N

        jButtonConvertir.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButtonConvertir.setText("CONVERTIR");
        jButtonConvertir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConvertirActionPerformed(evt);
            }
        });

        jButtonLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButtonLimpiar.setText("LIMPIAR");
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(9, 82, 82));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Unidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jComboBoxCambiar.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jComboBoxCambiar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jComboBoxCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCambiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBoxCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(9, 82, 82));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Cajas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jTextFieldDato1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jTextFieldDato2.setEditable(false);
        jTextFieldDato2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jComboBoxDato2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboBoxDato2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jComboBoxDato2.setSelectedIndex(-1);

        jComboBoxDato1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboBoxDato1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jComboBoxDato1.setSelectedIndex(-1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxDato1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldDato1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxDato2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldDato2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxDato2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDato1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDato1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDato2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(752, 752, 752))
        );

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/_0ff610ae-0d30-411b-a2fe-2f593e490d21.jpeg"))); // NOI18N

        javax.swing.GroupLayout FondoLayout = new javax.swing.GroupLayout(Fondo);
        Fondo.setLayout(FondoLayout);
        FondoLayout.setHorizontalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FondoLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(FondoLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(FondoLayout.createSequentialGroup()
                            .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonConvertir)
                                .addComponent(jButtonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(28, 28, 28))
        );
        FondoLayout.setVerticalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FondoLayout.createSequentialGroup()
                        .addComponent(jButtonConvertir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLimpiar)
                        .addGap(0, 34, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConvertirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConvertirActionPerformed
        try {
            ConvertirDivisas();
            ConvertirUnidades();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Datos Erroneos\n" + e.getMessage());
        }
    }//GEN-LAST:event_jButtonConvertirActionPerformed

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
        limpiarCajas();
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    private void jComboBoxCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCambiarActionPerformed
        indiceCB();
    }//GEN-LAST:event_jComboBoxCambiarActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Fondo;
    private javax.swing.JButton jButtonConvertir;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JComboBox<String> jComboBoxCambiar;
    private javax.swing.JComboBox<String> jComboBoxDato1;
    private javax.swing.JComboBox<String> jComboBoxDato2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextFieldDato1;
    private javax.swing.JTextField jTextFieldDato2;
    // End of variables declaration//GEN-END:variables

}
