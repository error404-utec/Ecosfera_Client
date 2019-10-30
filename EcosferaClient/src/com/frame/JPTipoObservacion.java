package com.frame;

import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JPTipoObservacion extends JPanel {
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelEmergencia;
	private JTextField txtFiltro;
	private final JTable tablaTipoObservacion = new JTable();

	/**
	 * Create the panel.
	 */
	public JPTipoObservacion() {
		
		JPanel pnlNew = new JPanel();
		pnlNew.setBackground(Color.WHITE);
		
		JPanel pnlTable = new JPanel();
		pnlTable.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(pnlNew, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(pnlTable, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(pnlNew, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlTable, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblFiltro = new JLabel("Filtro");
		lblFiltro.setForeground(new Color(46, 139, 87));
		lblFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtFiltro = new JTextField();
		txtFiltro.setColumns(10);
		
		JScrollPane scrollTablaTipoObservacion = new JScrollPane();
		GroupLayout gl_pnlTable = new GroupLayout(pnlTable);
		gl_pnlTable.setHorizontalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTable.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlTable.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFiltro)
						.addGroup(gl_pnlTable.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_pnlTable.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollTablaTipoObservacion)
								.addComponent(txtFiltro, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_pnlTable.setVerticalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTable.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFiltro)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollTablaTipoObservacion, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
		);
		scrollTablaTipoObservacion.setColumnHeaderView(tablaTipoObservacion);
		pnlTable.setLayout(gl_pnlTable);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblNombre.setForeground(new Color(46, 139, 87));
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setForeground(new Color(46, 139, 87));
		lblDireccin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtTelEmergencia = new JTextField();
		txtTelEmergencia.setColumns(10);
		
		JLabel lblTelfonoDeEmergencia = new JLabel("Tel\u00E9fono de Emergencia");
		lblTelfonoDeEmergencia.setForeground(new Color(46, 139, 87));
		lblTelfonoDeEmergencia.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setForeground(new Color(46, 139, 87));
		btnAceptar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		
		JPanel Close_Panel = new JPanel();
		Close_Panel.setForeground(Color.WHITE);
		Close_Panel.setBackground(Color.WHITE);
		GroupLayout gl_pnlNew = new GroupLayout(pnlNew);
		gl_pnlNew.setHorizontalGroup(
			gl_pnlNew.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlNew.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlNew.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAceptar, Alignment.TRAILING)
						.addComponent(lblDireccin, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelfonoDeEmergencia)
						.addGroup(Alignment.TRAILING, gl_pnlNew.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_pnlNew.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
								.addComponent(txtDireccion, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
								.addComponent(txtTelEmergencia, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
							.addGap(11))
						.addGroup(gl_pnlNew.createSequentialGroup()
							.addComponent(lblNombre)
							.addPreferredGap(ComponentPlacement.RELATED, 358, Short.MAX_VALUE)
							.addComponent(Close_Panel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_pnlNew.setVerticalGroup(
			gl_pnlNew.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlNew.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlNew.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre)
						.addComponent(Close_Panel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDireccin)
					.addGap(5)
					.addComponent(txtDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblTelfonoDeEmergencia)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtTelEmergencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAceptar)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		pnlNew.setLayout(gl_pnlNew);
		setLayout(groupLayout);

	}
}
