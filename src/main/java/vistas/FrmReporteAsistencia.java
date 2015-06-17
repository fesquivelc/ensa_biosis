/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import algoritmo.AnalisisAsistenciaCaliente;
import com.personal.utiles.ReporteUtil;
import entidades.escalafon.AreaEmpleado;
import entidades.escalafon.Departamento;
import entidades.escalafon.Empleado;
import entidades.reportes.RptAsistenciaDetallado;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdesktop.observablecollections.ObservableCollections;
import utiles.DetalleReporteAsistenciaComparator;
import vistas.dialogos.DlgEmpleado;
import vistas.dialogos.DlgOficina;
import vistas.modelos.MCFiltro;
import vistas.modelos.MTDetalleRegistroAsistencia;
import vistas.renders.RenderIndicadorAsistencia;

/**
 *
 * @author RyuujiMD
 */
public class FrmReporteAsistencia extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmReporteAsistencia
     */
    public FrmReporteAsistencia() {
        initComponents();
        inicializar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        pnlBusqueda = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlFechas = new javax.swing.JPanel();
        dcFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        dcFechaFin = new com.toedter.calendar.JDateChooser();
        pnlFiltro = new javax.swing.JPanel();
        cboFiltro = new javax.swing.JComboBox();
        txtBusqueda = new javax.swing.JTextField();
        btnFiltro = new javax.swing.JButton();
        cboTipoAsistencia = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        tabReportes = new javax.swing.JTabbedPane();
        pnlDetallado = new javax.swing.JPanel();
        tabDetalles = new javax.swing.JTabbedPane();
        pnlDetallePermiso = new javax.swing.JPanel();
        pnlDetalleVacacion = new javax.swing.JPanel();
        pnlDetalleHorario = new javax.swing.JPanel();
        pnlDetalleMarcacion = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalle = new org.jdesktop.swingx.JXTable();
        pnlImprimirSAP = new javax.swing.JPanel();
        btnReportePermisos = new javax.swing.JButton();
        btnImprimirDetalle = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        pnlResumen = new javax.swing.JPanel();

        setClosable(true);
        setTitle("REPORTE DE ASISTENCIA");

        jPanel1.setLayout(new java.awt.GridBagLayout());

        pnlBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Búsqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 14))); // NOI18N
        java.awt.GridBagLayout pnlBusquedaLayout = new java.awt.GridBagLayout();
        pnlBusquedaLayout.columnWidths = new int[] {0, 8, 0, 8, 0, 8, 0};
        pnlBusquedaLayout.rowHeights = new int[] {0, 8, 0, 8, 0, 8, 0};
        pnlBusqueda.setLayout(pnlBusquedaLayout);

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setText("Fechas:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlBusqueda.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setText("Filtrar por:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlBusqueda.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setText("Tipo de asistencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlBusqueda.add(jLabel3, gridBagConstraints);

        pnlFechas.setLayout(new javax.swing.BoxLayout(pnlFechas, javax.swing.BoxLayout.LINE_AXIS));

        dcFechaInicio.setDateFormatString("dd/MM/yyyy");
        dcFechaInicio.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        pnlFechas.add(dcFechaInicio);

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setText("-");
        pnlFechas.add(jLabel4);

        dcFechaFin.setDateFormatString("dd/MM/yyyy");
        dcFechaFin.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        pnlFechas.add(dcFechaFin);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        pnlBusqueda.add(pnlFechas, gridBagConstraints);

        pnlFiltro.setLayout(new java.awt.GridBagLayout());

        cboFiltro.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cboFiltro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pnlFiltro.add(cboFiltro, new java.awt.GridBagConstraints());

        txtBusqueda.setEditable(false);
        txtBusqueda.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        pnlFiltro.add(txtBusqueda, gridBagConstraints);

        btnFiltro.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnFiltro.setText("...");
        btnFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroActionPerformed(evt);
            }
        });
        pnlFiltro.add(btnFiltro, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        pnlBusqueda.add(pnlFiltro, gridBagConstraints);

        cboTipoAsistencia.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cboTipoAsistencia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODO", "ASISTENCIA REGULAR", "MINUTOS EXTRA AUTORIZADOS", "MINUTOS EXTRA NO AUTORIZADOS", "FALTA INJUSTIFICADA", "PERMISOS CON GOCE", "PERMISOS SIN GOCE", "VACACIONES" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlBusqueda.add(cboTipoAsistencia, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton1.setText("Procesar reporte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlBusqueda.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(pnlBusqueda, gridBagConstraints);

        tabReportes.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        pnlDetallado.setLayout(new java.awt.GridBagLayout());

        tabDetalles.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabDetalles.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlDetallePermisoLayout = new javax.swing.GroupLayout(pnlDetallePermiso);
        pnlDetallePermiso.setLayout(pnlDetallePermisoLayout);
        pnlDetallePermisoLayout.setHorizontalGroup(
            pnlDetallePermisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 731, Short.MAX_VALUE)
        );
        pnlDetallePermisoLayout.setVerticalGroup(
            pnlDetallePermisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 127, Short.MAX_VALUE)
        );

        tabDetalles.addTab("Detalle del permiso", pnlDetallePermiso);

        javax.swing.GroupLayout pnlDetalleVacacionLayout = new javax.swing.GroupLayout(pnlDetalleVacacion);
        pnlDetalleVacacion.setLayout(pnlDetalleVacacionLayout);
        pnlDetalleVacacionLayout.setHorizontalGroup(
            pnlDetalleVacacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 731, Short.MAX_VALUE)
        );
        pnlDetalleVacacionLayout.setVerticalGroup(
            pnlDetalleVacacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 127, Short.MAX_VALUE)
        );

        tabDetalles.addTab("Detalle de vacación", pnlDetalleVacacion);

        javax.swing.GroupLayout pnlDetalleHorarioLayout = new javax.swing.GroupLayout(pnlDetalleHorario);
        pnlDetalleHorario.setLayout(pnlDetalleHorarioLayout);
        pnlDetalleHorarioLayout.setHorizontalGroup(
            pnlDetalleHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 731, Short.MAX_VALUE)
        );
        pnlDetalleHorarioLayout.setVerticalGroup(
            pnlDetalleHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 127, Short.MAX_VALUE)
        );

        tabDetalles.addTab("Detalle del horario", pnlDetalleHorario);

        javax.swing.GroupLayout pnlDetalleMarcacionLayout = new javax.swing.GroupLayout(pnlDetalleMarcacion);
        pnlDetalleMarcacion.setLayout(pnlDetalleMarcacionLayout);
        pnlDetalleMarcacionLayout.setHorizontalGroup(
            pnlDetalleMarcacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 731, Short.MAX_VALUE)
        );
        pnlDetalleMarcacionLayout.setVerticalGroup(
            pnlDetalleMarcacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 127, Short.MAX_VALUE)
        );

        tabDetalles.addTab("Marcaciones en el día", pnlDetalleMarcacion);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.2;
        pnlDetallado.add(tabDetalles, gridBagConstraints);

        tblDetalle.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tblDetalle.setGridColor(new java.awt.Color(102, 102, 102));
        tblDetalle.setRowHeight(27);
        jScrollPane1.setViewportView(tblDetalle);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.4;
        pnlDetallado.add(jScrollPane1, gridBagConstraints);

        java.awt.GridBagLayout pnlImprimirSAPLayout = new java.awt.GridBagLayout();
        pnlImprimirSAPLayout.columnWidths = new int[] {0, 8, 0, 8, 0, 8, 0};
        pnlImprimirSAPLayout.rowHeights = new int[] {0};
        pnlImprimirSAP.setLayout(pnlImprimirSAPLayout);

        btnReportePermisos.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        btnReportePermisos.setText("Reporte de permisos");
        btnReportePermisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportePermisosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        pnlImprimirSAP.add(btnReportePermisos, gridBagConstraints);

        btnImprimirDetalle.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        btnImprimirDetalle.setText("Reporte general");
        btnImprimirDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirDetalleActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        pnlImprimirSAP.add(btnImprimirDetalle, gridBagConstraints);

        jButton2.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jButton2.setText("Reporte de vacaciones");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        pnlImprimirSAP.add(jButton2, gridBagConstraints);

        jButton3.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jButton3.setText("Reporte de horas extra");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        pnlImprimirSAP.add(jButton3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        pnlDetallado.add(pnlImprimirSAP, gridBagConstraints);

        tabReportes.addTab("Reporte detallado", pnlDetallado);

        pnlResumen.setLayout(new java.awt.GridBagLayout());
        tabReportes.addTab("Reporte resúmen", pnlResumen);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.3;
        jPanel1.add(tabReportes, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImprimirDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirDetalleActionPerformed
        // TODO add your handling code here:
        File reporte = new File("reportes/reporte_registro_asistencia_caliente.jasper");
        Map<String, Object> parametros = new HashMap();
        reporteador.verReporte(this.asistenciaDetalladoList, reporte, parametros, null);
    }//GEN-LAST:event_btnImprimirDetalleActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        List<Empleado> empleadosAnalisis = new ArrayList<>();
//        MCFiltro.TipoFiltro filtro = (MCFiltro.TipoFiltro) mcFiltro.getSelectedItem();
        if (empleadoSeleccionado != null) {
            empleadosAnalisis.add(empleadoSeleccionado);
        } else if (departamentoSeleccionado != null) {
            List<AreaEmpleado> areaEmpleadoList = departamentoSeleccionado.getAreaEmpleadoList();
            for (AreaEmpleado areaEmpleado : areaEmpleadoList) {
                empleadosAnalisis.add(areaEmpleado.getEmpleado());
            }
        }
        this.asistenciaDetalladoList.clear();
        if (!empleadosAnalisis.isEmpty()) {
            List<RptAsistenciaDetallado> reporte = analisis.analisisDetallado(dcFechaInicio.getDate(), dcFechaFin.getDate(), empleadosAnalisis);
            if (!reporte.isEmpty()) {
                if (cboTipoAsistencia.getSelectedIndex() > 0) {
                    reporte = this.filtrar(reporte);
                }

                Collections.sort(reporte, comparador);
                this.asistenciaDetalladoList.addAll(reporte);
                this.tblDetalle.packAll();
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroActionPerformed
        // TODO add your handling code here:
        MCFiltro.TipoFiltro filtro = (MCFiltro.TipoFiltro) mcFiltro.getSelectedItem();
        switch (filtro) {
            case POR_EMPLEADO:
                DlgEmpleado empleados = new DlgEmpleado(this);
                empleadoSeleccionado = empleados.getSeleccionado();
                txtBusqueda.setText(empleadoSeleccionado == null ? "" : empleadoSeleccionado.getNombreCompleto());
                break;
            case POR_OFICINA:
                DlgOficina oficinas = new DlgOficina(this);
                this.departamentoSeleccionado = oficinas.getSeleccionado();
                txtBusqueda.setText(departamentoSeleccionado == null ? "" : departamentoSeleccionado.getNombre());
                break;
            case POR_GRUPO_HORARIO:
                break;
        }
    }//GEN-LAST:event_btnFiltroActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        File reporte = new File("reportes/ensa_sap_reporte_horas_extra.jasper");
        Map<String, Object> parametros = new HashMap();
        reporteador.verReporte(filtrar(asistenciaDetalladoList), reporte, parametros, null);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnReportePermisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportePermisosActionPerformed
        // TODO add your handling code here:
        File reporte = new File("reportes/ensa_sap_reporte_permiso.jasper");
        Map<String, Object> parametros = new HashMap();
        reporteador.verReporte(filtrar(asistenciaDetalladoList), reporte, parametros, null);
    }//GEN-LAST:event_btnReportePermisosActionPerformed

    private final ReporteUtil reporteador = new ReporteUtil();
    private Empleado empleadoSeleccionado;
    private Departamento departamentoSeleccionado;
    private MCFiltro mcFiltro = new MCFiltro();
    private final AnalisisAsistenciaCaliente analisis = new AnalisisAsistenciaCaliente();
    private List<RptAsistenciaDetallado> asistenciaDetalladoList;
    private MTDetalleRegistroAsistencia mtdra;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltro;
    private javax.swing.JButton btnImprimirDetalle;
    private javax.swing.JButton btnReportePermisos;
    private javax.swing.JComboBox cboFiltro;
    private javax.swing.JComboBox cboTipoAsistencia;
    private com.toedter.calendar.JDateChooser dcFechaFin;
    private com.toedter.calendar.JDateChooser dcFechaInicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlBusqueda;
    private javax.swing.JPanel pnlDetallado;
    private javax.swing.JPanel pnlDetalleHorario;
    private javax.swing.JPanel pnlDetalleMarcacion;
    private javax.swing.JPanel pnlDetallePermiso;
    private javax.swing.JPanel pnlDetalleVacacion;
    private javax.swing.JPanel pnlFechas;
    private javax.swing.JPanel pnlFiltro;
    private javax.swing.JPanel pnlImprimirSAP;
    private javax.swing.JPanel pnlResumen;
    private javax.swing.JTabbedPane tabDetalles;
    private javax.swing.JTabbedPane tabReportes;
    private org.jdesktop.swingx.JXTable tblDetalle;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables

    private final DetalleReporteAsistenciaComparator comparador = new DetalleReporteAsistenciaComparator();

    private void inicializar() {
        this.asistenciaDetalladoList = ObservableCollections.observableList(new ArrayList<RptAsistenciaDetallado>());
        this.mtdra = new MTDetalleRegistroAsistencia(asistenciaDetalladoList);

        this.cboFiltro.setModel(mcFiltro);
        this.tblDetalle.setModel(mtdra);
        RenderIndicadorAsistencia render = new RenderIndicadorAsistencia();
        this.tblDetalle.getColumn(0).setCellRenderer(render);
    }

    private List<RptAsistenciaDetallado> filtrar(List<RptAsistenciaDetallado> listado) {
        List<RptAsistenciaDetallado> filtroList = new ArrayList<>();
//        char tipoAsistencia;
//        tipoAsistencia = tipo.charAt(0);
        for (RptAsistenciaDetallado registro : listado) {
            switch (cboTipoAsistencia.getSelectedIndex()) {
                case 1:
                    if (registro.getTipoDetalle().equals("J") && registro.getTipoAsistencia().equals("R")) {
                        filtroList.add(registro);
                    }
                    break;
                case 2:
                    if (registro.getMinutosExtra() != null) {
                        if (registro.getMinutosExtra() > 0 && registro.isMinutosExtraAutorizado()) {
                            filtroList.add(registro);
                        }
                    }
                    break;
                case 3:
                    if (registro.getMinutosExtra() != null) {
                        if (registro.getMinutosExtra() > 0 && !registro.isMinutosExtraAutorizado()) {
                            filtroList.add(registro);
                        }
                    }

                    break;
                case 4:
                    if (registro.getTipoAsistencia().equals("F")) {
                        filtroList.add(registro);
                    }
                    break;
                case 5:
                    if (registro.getTipoAsistencia().equals("P")) {
                        if (registro.getPermiso().getTipoPermiso().getTipoDescuento() == 'C') {
                            filtroList.add(registro);
                        }
                    }
                    break;
                case 6:
                    if (registro.getTipoAsistencia().equals("P")) {
                        if (registro.getPermiso().getTipoPermiso().getTipoDescuento() == 'S') {
                            filtroList.add(registro);
                        }
                    }
                    break;
                case 7:
                    if (registro.getTipoAsistencia().equals("V")) {
                        filtroList.add(registro);
                    }
                    break;
            }
        }
        return filtroList;
    }
}
