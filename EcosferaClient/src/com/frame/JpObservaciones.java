package com.frame;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.entities.Observacion;
import com.services.ObservacionBeanRemote;
import com.toedter.calendar.JCalendar;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class JpObservaciones extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tablaObservaciones;
	JCalendar calendar = new JCalendar();
	JCalendar calendar_1 = new JCalendar();


	public JpObservaciones() throws NamingException {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 593, 500);
		add(panel);
		
		JLabel lblFechaInicial = new JLabel("Fecha Inicial");
		lblFechaInicial.setForeground(new Color(46, 139, 87));
		lblFechaInicial.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblFechaInicial.setBounds(0, 18, 116, 20);
		panel.add(lblFechaInicial);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 215, 581, 272);
		panel.add(scrollPane);
		
		this.tablaObservaciones = this.cargarObservaciones();
		scrollPane.setViewportView(tablaObservaciones);
		
		JLabel lblFechaFinal = new JLabel("Fecha Final");
		lblFechaFinal.setForeground(new Color(46, 139, 87));
		lblFechaFinal.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblFechaFinal.setBounds(383, 18, 116, 20);
		panel.add(lblFechaFinal);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Date mifecha = calendar.getDate();
				long dato = mifecha.getTime();
				java.sql.Date coso = new java.sql.Date(dato);
				
				Date mifecha2 = calendar_1.getDate();
				long dato2 = mifecha2.getTime();
				java.sql.Date coso2 = new java.sql.Date(dato2);

				if(!(coso.after(coso2))) {
					tablaObservaciones.setVisible(false);
					tablaObservaciones = filtrar();
					scrollPane.setViewportView(tablaObservaciones);
					tablaObservaciones.setVisible(true);
					scrollPane.revalidate();
					scrollPane.repaint();
				}else {
					reportarError("La fecha final debe ser mayor a la fecha inicial.");
				}
				
			}
		});
		btnFiltrar.setForeground(new Color(46, 139, 87));
		btnFiltrar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnFiltrar.setBackground(Color.WHITE);
		btnFiltrar.setBounds(252, 43, 86, 27);
		panel.add(btnFiltrar);
		calendar.setBounds(0, 43, 198, 130);
		panel.add(calendar);
		calendar_1.setBounds(383, 43, 198, 130);
		panel.add(calendar_1);

	}
	
	public void reportarError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}
	
	
	
private JTable cargarObservaciones() throws NamingException {
		
		List<Observacion> lista = listarObservaciones();
		String[] nombreColumnas = {"ID", "Fecha", "Descripción", "Geolocalizacion", "Usuario", "Localidad", "Fenomeno" };

		Object[][] datos = new Object[lista.size()][7];
		int fila = 0;

		
		for (Observacion o : lista) {
			System.out.println(o.getId());
			datos[fila][0] = o.getId();
			datos[fila][1] = o.getFecha();
			datos[fila][2] = o.getDescripcion();
			datos[fila][3] = o.getGeolocalizacion();
			datos[fila][4] = o.getUsuario().getNombre();
			datos[fila][5] = o.getLocalidad().getNombre();
			datos[fila][6] = o.getTipo().getNombre();
			fila++;

		}
		
		DefaultTableModel model = new DefaultTableModel(datos, nombreColumnas) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				 return String.class;
			}
		};
		
		tablaObservaciones = new JTable(model);
		tablaObservaciones.setRowSelectionAllowed(true);
		tablaObservaciones.setColumnSelectionAllowed(false);
		tablaObservaciones.setBorder(null);
		tablaObservaciones.setBackground(new Color(255, 255, 255));
		tablaObservaciones.setForeground(new Color(153, 153, 153));
		tablaObservaciones.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
		tablaObservaciones.setShowVerticalLines(false);
		tablaObservaciones.setAutoscrolls(true);
		tablaObservaciones.setSize(600, 600);
		Color color = new Color(144,238,144);
		tablaObservaciones.setSelectionBackground(color);
		
		
		return tablaObservaciones;


	
	}


	private JTable filtrar() {
		Date mifecha = calendar.getDate();
		long dato = mifecha.getTime();
		java.sql.Date coso = new java.sql.Date(dato);
		
		Date mifecha2 = calendar_1.getDate();
		long dato2 = mifecha2.getTime();
		java.sql.Date coso2 = new java.sql.Date(dato2);
		
		
		List<Observacion> lista;
		try {
			lista = listarObservaciones();
		
		String[] nombreColumnas = {"ID", "Fecha", "Descripción", "Geolocalizacion", "Usuario", "Localidad", "Fenomeno" };
		int fila = 0;
		
		for (Observacion o : lista) {
			if ((o.getFecha().after(coso) && o.getFecha().before(coso2))){
				fila ++;
			}
		}
		Object[][] datos = new Object[fila][7];
		fila = 0;

		
		for (Observacion o : lista) {

			if ((o.getFecha().after(coso) && o.getFecha().before(coso2))) {
				datos[fila][0] = o.getId();
				datos[fila][1] = o.getFecha();
				datos[fila][2] = o.getDescripcion();
				datos[fila][3] = o.getGeolocalizacion();
				datos[fila][4] = o.getUsuario().getNombre();
				datos[fila][5] = o.getLocalidad().getNombre();
				datos[fila][6] = o.getTipo().getNombre();
				fila++;
			}
		}
		
		DefaultTableModel model = new DefaultTableModel(datos, nombreColumnas) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				 return String.class;
			}
		};
		
		tablaObservaciones = new JTable(model);
		tablaObservaciones.setRowSelectionAllowed(true);
		tablaObservaciones.setColumnSelectionAllowed(false);
		tablaObservaciones.setBorder(null);
		tablaObservaciones.setBackground(new Color(255, 255, 255));
		tablaObservaciones.setForeground(new Color(153, 153, 153));
		tablaObservaciones.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
		tablaObservaciones.setShowVerticalLines(false);
		tablaObservaciones.setAutoscrolls(true);
		tablaObservaciones.setSize(600, 600);
		Color color = new Color(144,238,144);
		tablaObservaciones.setSelectionBackground(color);
		
		
		return tablaObservaciones;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return tablaObservaciones;

	}
	
	private List<Observacion> listarObservaciones() throws NamingException  {
		ObservacionBeanRemote observacionesBean = (ObservacionBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/ObservacionBean!com.services.ObservacionBeanRemote");
		return observacionesBean.obtenerTodas();
	
	}
	
	
}
