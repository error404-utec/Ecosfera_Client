package com.frame;

import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.Departamento;
import com.entities.Localidad;
import com.exceptions.ServiciosException;
import com.services.DepartamentoBeanRemote;
import com.services.LocalidadBeanRemote;


public class JpLoc_Dep extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private Departamento departamento;
	


	private JTable tablaLocalidad;
	private Localidad localidadActualizar = null;
	private JTextField txtfiltro;
	
	
	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public JpLoc_Dep(Departamento departamento) throws NamingException {
		this.departamento = departamento;
		setBounds(new Rectangle(295, 256, 650, 582));
		setBackground(new Color(255, 255, 255));
		
		JPanel pnlNew = new JPanel();
		pnlNew.setBounds(0, 13, 452, 219);
		pnlNew.setBackground(new Color(255, 255, 255));
		pnlNew.setForeground(new Color(255, 255, 255));
		
		JPanel pnltable = new JPanel();
		pnltable.setBounds(0, 244, 452, 259);
		pnltable.setBackground(new Color(255, 255, 255));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);
		
		JPanel pnlOptions = new JPanel();
		pnlOptions.setBounds(0, 511, 452, 55);
		pnlOptions.setBackground(new Color(255, 255, 255));
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 115, 51, 20);
		lblNombre.setForeground(new Color(46, 139, 87));
		lblNombre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtNombre = new JTextField();
		txtNombre.setBounds(22, 148, 418, 24);
		txtNombre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(12, 56, 45, 20);
		lblCodigo.setForeground(new Color(46, 139, 87));
		lblCodigo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(22, 89, 418, 24);
		txtCodigo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtCodigo.setColumns(10);
		
		JButton btnAgregar = new JButton("Aceptar");
		btnAgregar.setBounds(346, 179, 94, 27);
		
		btnAgregar.setBackground(new Color(245, 255, 250));
		btnAgregar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnAgregar.setForeground(new Color(46, 139, 87));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(235, 179, 99, 27);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtCodigo.setText("");
				txtNombre.setText("");
				localidadActualizar = null;
			}
		});
		btnCancelar.setForeground(new Color(46, 139, 87));
		btnCancelar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnCancelar.setBackground(new Color(245, 255, 250));
		
		JScrollPane scroolTablaLocalidad = new JScrollPane();
		
		/*
		scroolTablaZonas.setLayout(new ScrollPaneLayout() {

		private static final long serialVersionUID = 1L;

			@Override
			  public void layoutContainer(Container parent) {
			    JScrollPane scrollPane = (JScrollPane) parent;
			
			    Rectangle availR = scrollPane.getBounds();
			    availR.x = availR.y = 0;
			
			    Insets parentInsets = parent.getInsets();
			    availR.x = parentInsets.left;
			    availR.y = parentInsets.top;
			    availR.width -= parentInsets.left + parentInsets.right + 10;
			    availR.height -= parentInsets.top + parentInsets.bottom;
			
			    Rectangle vsbR = new Rectangle();
			    vsbR.width = 12;
			    vsbR.height = availR.height;
			    vsbR.x = availR.x + availR.width - vsbR.width;
			    vsbR.y = availR.y;
			
			    vsbR.x = vsbR.x + 10;
			    if (viewport != null) {
			      viewport.setBounds(availR);
			    }
			    if (vsb != null) {
			      vsb.setVisible(true);
			      vsb.setBounds(vsbR);
			    }
			  }
			});
		scroolTablaZonas.getVerticalScrollBar().setUI(new EcosferaScrollBar());*/
		scroolTablaLocalidad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
			}
		});
		
		txtfiltro = new JTextField();
		txtfiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrar();
			}
		});
		txtfiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtfiltro.setColumns(10);
		
		JLabel lblfiltro = new JLabel("C\u00F3digo");
		lblfiltro.setForeground(new Color(46, 139, 87));
		lblfiltro.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));

		GroupLayout gl_pnltable = new GroupLayout(pnltable);
		gl_pnltable.setHorizontalGroup(
			gl_pnltable.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnltable.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnltable.createParallelGroup(Alignment.LEADING)
						.addComponent(scroolTablaLocalidad, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(gl_pnltable.createSequentialGroup()
							.addGap(10)
							.addComponent(txtfiltro, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
						.addComponent(lblfiltro, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_pnltable.setVerticalGroup(
			gl_pnltable.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnltable.createSequentialGroup()
					.addGap(18)
					.addComponent(lblfiltro, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtfiltro, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scroolTablaLocalidad, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(107))
		);
		
		
		

		this.tablaLocalidad = this.cargarLocalidad();
		scroolTablaLocalidad.setViewportView(tablaLocalidad);
				
		pnltable.setLayout(gl_pnltable);
		
		JButton btnModificat = new JButton("Modificar");
		btnModificat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				localidadActualizar = null;
				if (tablaLocalidad.getSelectedRow() > -1) {
					Long id = (Long) tablaLocalidad.getValueAt(tablaLocalidad.getSelectedRow(), 0);
					try {
						localidadActualizar = obtenerPorID(id);
						txtNombre.setText(localidadActualizar.getNombre());
						txtCodigo.setText(Long.toString(localidadActualizar.getCodigo()));
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnModificat.setBackground(new Color(245, 255, 250));
		btnModificat.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnModificat.setForeground(new Color(46, 139, 87));
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Localidad localidadEliminar = null;
				if (tablaLocalidad.getSelectedRow() > -1) {
					Long id = (Long) tablaLocalidad.getValueAt(tablaLocalidad.getSelectedRow(), 0);
					try {
						localidadEliminar = obtenerPorID(id);
						eliminarLocalidad(localidadEliminar);;
						tablaLocalidad.setVisible(false);
						tablaLocalidad = cargarLocalidad();
						scroolTablaLocalidad.setViewportView(tablaLocalidad);
						tablaLocalidad.setVisible(true);
						filtrar();
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnEliminar.setForeground(new Color(46, 139, 87));
		btnEliminar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(245, 255, 250));
		GroupLayout gl_pnlOptions = new GroupLayout(pnlOptions);
		gl_pnlOptions.setHorizontalGroup(
			gl_pnlOptions.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlOptions.createSequentialGroup()
					.addContainerGap(247, Short.MAX_VALUE)
					.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnModificat)
					.addGap(12))
		);
		gl_pnlOptions.setVerticalGroup(
			gl_pnlOptions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlOptions.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_pnlOptions.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlOptions.createSequentialGroup()
							.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_pnlOptions.createSequentialGroup()
							.addComponent(btnModificat, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(13))))
		);
		pnlOptions.setLayout(gl_pnlOptions);
		setLayout(null);
		add(pnlNew);
		pnlNew.setLayout(null);
		pnlNew.add(lblCodigo);
		pnlNew.add(txtCodigo);
		pnlNew.add(btnCancelar);
		pnlNew.add(btnAgregar);
		pnlNew.add(txtNombre);
		pnlNew.add(lblNombre);
		
		JLabel lblDepartamento = new JLabel(departamento.getNombre());
		lblDepartamento.setForeground(new Color(46, 139, 87));
		lblDepartamento.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblDepartamento.setBounds(12, 23, 272, 33);
		pnlNew.add(lblDepartamento);
		add(pnltable);
		add(pnlOptions);
		
		
		
		
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (localidadActualizar!=null) {
					try {
						localidadActualizar.setCodigo(Long.parseLong(txtCodigo.getText()));
						localidadActualizar.setNombre(txtNombre.getText());
						
						acutalizarLocalidad(localidadActualizar);
					
						tablaLocalidad.setVisible(false);
						tablaLocalidad = cargarLocalidad();
						scroolTablaLocalidad.setViewportView(tablaLocalidad);
						tablaLocalidad.setVisible(true);
						txtNombre.setText("");
						txtCodigo.setText("");
						localidadActualizar = null;
						filtrar();
					} catch (NamingException e1) {
						e1.printStackTrace();
					}
				}else {
					Localidad localidad = new Localidad();
					localidad.setCodigo(Long.parseLong(txtCodigo.getText()));
					localidad.setNombre(txtNombre.getText());			
					try {
						Departamento departamento1 = new Departamento();
						departamento1 = obtenerDepartamentoPorID(departamento.getId());
						departamento1.asignarLocalidad(localidad);
						crearLocalidad(departamento1);
						tablaLocalidad.setVisible(false);
						tablaLocalidad = cargarLocalidad();
						scroolTablaLocalidad.setViewportView(tablaLocalidad);
						tablaLocalidad.setVisible(true);
						txtNombre.setText("");
						txtCodigo.setText("");
					} catch (NamingException e1) {
						e1.printStackTrace();
					}
				}				
			}
		});

	}
	
	public void crearLocalidad(Departamento departamento) throws NamingException {
		DepartamentoBeanRemote departamentoBeanRemote  = (DepartamentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/DepartamentoBean!com.services.DepartamentoBeanRemote");
		try {
			departamentoBeanRemote.actualizar(departamento);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void eliminarLocalidad(Localidad localidad) throws NamingException {
		LocalidadBeanRemote localidadBeanRemote  = (LocalidadBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/LocalidadBean!com.services.LocalidadBeanRemote");
		try {
			localidadBeanRemote.borrar(localidad.getId());
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void acutalizarLocalidad(Localidad Localidad) throws NamingException {
		LocalidadBeanRemote localidadBeanRemote  = (LocalidadBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/LocalidadBean!com.services.LocalidadBeanRemote");
		try {
			localidadBeanRemote.actualizar(Localidad);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public Departamento obtenerDepartamentoPorID(Long id) throws NamingException {
		DepartamentoBeanRemote departamentoBeanRemote  = (DepartamentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/DepartamentoBean!com.services.DepartamentoBeanRemote");
		return departamentoBeanRemote.obtenerporID(id);
	}
	
	public Localidad obtenerPorID(Long id) throws NamingException {
		LocalidadBeanRemote localidadBeanRemote  = (LocalidadBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/LocalidadBean!com.services.LocalidadBeanRemote");
		return localidadBeanRemote.obtenerPorID(id);
	}
	
	private JTable cargarLocalidad() throws NamingException {
		departamento = obtenerDepartamentoPorID(departamento.getId());
		List<Localidad> lista = departamento.getLocalidades();
		
		
		String[] nombreColumnas = {"ID", "Código", "Nombre" };

		Object[][] datos = new Object[lista.size()][3];
		int fila = 0;

		
		for (Localidad c : lista) {
			datos[fila][0] = c.getId();
			datos[fila][1] = c.getCodigo();
			datos[fila][2] = c.getNombre();
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
		
		
		tablaLocalidad = new JTable(model);
		tablaLocalidad.setRowSelectionAllowed(true);
		tablaLocalidad.setColumnSelectionAllowed(false);
		tablaLocalidad.setBorder(null);
		tablaLocalidad.setBackground(new Color(255, 255, 255));
		tablaLocalidad.setForeground(new Color(153, 153, 153));
		tablaLocalidad.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
		tablaLocalidad.setShowVerticalLines(false);
		tablaLocalidad.setAutoscrolls(true);
		tablaLocalidad.setSize(600, 600);
		Color color = new Color(144,238,144);
		tablaLocalidad.setSelectionBackground(color);
		
		return tablaLocalidad;

	}
	
	private void filtrar() {
		TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaLocalidad.getModel());
		filtro.setRowFilter(RowFilter.regexFilter(this.txtfiltro.getText(), 1));
		this.tablaLocalidad.setRowSorter(filtro);

	}
	

	

	
	
}



