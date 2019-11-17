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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.entities.Estado;
import com.entities.Localidad;
import com.entities.Observacion;
import com.entities.Usuario;
import com.entities.Zona;
import com.exceptions.ServiciosException;
import com.framework.EcosferaScrollBar;
import com.framework.MDTFormatoFecha;
import com.services.EstadoBeanRemote;
import com.services.ObservacionBeanRemote;
import com.services.UsuarioBeanRemote;
import com.services.ZonaBeanRemote;
import com.session.Sesion;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;



public class JpConsulta extends JPanel {

	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;

	private JTable tablaReporte;
	JFormattedTextField formattedTextField;
	JFormattedTextField formattedTextField_1;

	
	public JpConsulta() throws NamingException {
		JFRPrincipal.setlblTitulopanel("Mantenimiento Zonas");

		setBounds(new Rectangle(295, 256, 662, 609));
		setBackground(new Color(255, 255, 255));
		
		JPanel pnltable = new JPanel();
		pnltable.setBounds(-13, 13, 689, 456);
		pnltable.setBackground(new Color(255, 255, 255));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);
		
		JScrollPane scroolTablareporte = new JScrollPane();
		scroolTablareporte.setBounds(12, 51, 640, 392);
		
		JLabel lblfiltro = new JLabel("Filtros");
		lblfiltro.setBounds(12, 0, 75, 20);
		lblfiltro.setForeground(new Color(46, 139, 87));
		lblfiltro.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		
		

		this.tablaReporte = this.cargarPermisos();
		scroolTablareporte.setViewportView(tablaReporte);
		setLayout(null);
		add(pnltable);
		pnltable.setLayout(null);
		pnltable.add(scroolTablareporte);
		pnltable.add(lblfiltro);
		try {
			JFormattedTextField formattedTextField;
			formattedTextField = new JFormattedTextField(new MDTFormatoFecha());
			formattedTextField.setBounds(113, 21, 75, 22);
			pnltable.add(formattedTextField);
			
			JFormattedTextField formattedTextField_1 = new JFormattedTextField(new MDTFormatoFecha());
			formattedTextField_1.setBounds(247, 21, 75, 22);
			pnltable.add(formattedTextField_1);
			
			JLabel lblDesde = new JLabel("desde");
			lblDesde.setForeground(new Color(46, 139, 87));
			lblDesde.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
			lblDesde.setBounds(72, 21, 75, 20);
			pnltable.add(lblDesde);
			
			JLabel lblHasta = new JLabel("hasta");
			lblHasta.setForeground(new Color(46, 139, 87));
			lblHasta.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
			lblHasta.setBounds(200, 21, 75, 20);
			pnltable.add(lblHasta);
			
			JButton btnFiltrar = new JButton("filtrar");
			btnFiltrar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try {
						tablaReporte = cargarPermisos();
						System.out.println(ParseFecha(formattedTextField.getText()));
						System.out.println(ParseFecha(formattedTextField_1.getText()));
					} catch (NamingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnFiltrar.setForeground(new Color(46, 139, 87));
			btnFiltrar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
			btnFiltrar.setBackground(new Color(245, 255, 250));
			btnFiltrar.setBounds(342, 11, 94, 27);
			pnltable.add(btnFiltrar);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tablaReporte = cargarPermisos();

	}
	
	
	

	

	
	
	
	private JTable cargarPermisos() throws NamingException {
		//System.out.println(ParseFecha(formattedTextField.getText()));
		//System.out.println(ParseFecha(formattedTextField_1.getText()));
		List<Observacion> lista = listarObservaciones();

		String[] nombreColumnas = {"ID", "Descripción", "Geolocalizaciòn", "Fenómeno","Fecha","Usuario","Localidad" };

		Object[][] datos = new Object[lista.size()][7];
		int fila = 0;

		
		for (Observacion c : lista) {
			datos[fila][0] = c.getId();
			datos[fila][1] = c.getDescripcion();
			datos[fila][2] = c.getGeolocalizacion();
			datos[fila][3] = c.getTipo().getNombre();
			datos[fila][4] = c.getFecha();
			if(c.getFecha().after(ParseFecha(formattedTextField_1.getText()))){
				System.out.println("funciona1");
			}
			if(c.getFecha().before(ParseFecha(formattedTextField_1.getText()))){
				System.out.println("funciona2");
			}
			datos[fila][5] = c.getUsuario().getUsuario();
			datos[fila][6] = c.getLocalidad().getNombre();
			fila++;

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
		
		
		tablaReporte = new JTable(model);
		tablaReporte.setRowSelectionAllowed(true);
		tablaReporte.setColumnSelectionAllowed(false);
		tablaReporte.setBorder(null);
		tablaReporte.setBackground(new Color(255, 255, 255));
		tablaReporte.setForeground(new Color(153, 153, 153));
		tablaReporte.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
		tablaReporte.setShowVerticalLines(false);
		tablaReporte.setAutoscrolls(true);
		tablaReporte.setSize(600, 600);
		Color color = new Color(144,238,144);
		tablaReporte.setSelectionBackground(color);
		
		return tablaReporte;

	}
	
	private void filtrar() {
		//TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaReporte.getModel());
		//filtro.setRowFilter(RowFilter.regexFilter(this.txtfiltro.getText(), 1));
		//this.tablaReporte.setRowSorter(filtro);

	}
	
	public List<Observacion> listarObservaciones() throws NamingException {
		ObservacionBeanRemote observacionBeanRemote  = (ObservacionBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/ObservacionBean!com.services.ObservacionBeanRemote");
		return observacionBeanRemote.obtenerTodas();
		//return observacionBeanRemote.obtenerTodas();
	}
	
	
	
	public void reportarError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}
	
	
	public static Date ParseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
    }
}



