package com.frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.Observacion;
import com.entities.Zona;
import com.exceptions.ServiciosException;
import com.framework.EcosferaScrollBar;
import com.services.ObservacionBeanRemote;
import com.services.ZonaBeanRemote;
import com.session.Sesion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


public class JpInicio extends JPanel {

	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;




	

	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public JpInicio() throws NamingException {
		JFRPrincipal.setlblTitulopanel("Bienvenido " + Sesion.getUsuario().getNombre());

		setBounds(new Rectangle(295, 256, 662, 609));
		setBackground(new Color(255, 255, 255));
		
		JPanel pnltable = new JPanel();
		pnltable.setBounds(110, 13, 437, 548);
		pnltable.setBackground(new Color(255, 255, 255));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);
		
		JLabel lblfiltro = new JLabel("Tus aportes");
		lblfiltro.setBounds(134, 167, 72, 32);
		lblfiltro.setForeground(new Color(46, 139, 87));
		lblfiltro.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		setLayout(null);
		add(pnltable);
		pnltable.setLayout(null);
		pnltable.add(lblfiltro);
		
		JLabel lblAprobados = new JLabel("Aprobados");
		lblAprobados.setForeground(new Color(46, 139, 87));
		lblAprobados.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblAprobados.setBounds(238, 167, 72, 32);
		pnltable.add(lblAprobados);
		
		JLabel labelAportes = new JLabel("123");
		labelAportes.setHorizontalAlignment(SwingConstants.CENTER);
		labelAportes.setForeground(new Color(46, 139, 87));
		labelAportes.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		labelAportes.setBounds(132, 225, 83, 32);
		pnltable.add(labelAportes);
		
		labelAportes.setText(String.valueOf(cargarValores()));
		JLabel labelAprobados = new JLabel("0");
		labelAprobados.setHorizontalAlignment(SwingConstants.CENTER);
		labelAprobados.setForeground(new Color(46, 139, 87));
		labelAprobados.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		labelAprobados.setBounds(227, 225, 83, 32);
		pnltable.add(labelAprobados);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(JpInicio.class.getResource("/recursos/icons/line.png")));
		label.setBounds(-57, 123, 440, 280);
		pnltable.add(label);

	}
	
	
	

	
	public int cargarValores() throws NamingException {
		ObservacionBeanRemote observacionBeanRemote  = (ObservacionBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/ObservacionBean!com.services.ObservacionBeanRemote");
		List<Observacion> col = observacionBeanRemote.obtenerPorUsuario(Sesion.getUsuario());
		int contador = 0;
		for(Observacion elemento : col) {
			contador++;
		}
		return contador;
	}
	
	
}



